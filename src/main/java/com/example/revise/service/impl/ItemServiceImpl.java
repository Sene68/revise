package com.example.revise.service.impl;

import com.example.revise.dto.ItemData;
import com.example.revise.entity.Item;
import com.example.revise.enums.ItemStatus;
import com.example.revise.repository.ItemRepository;
import com.example.revise.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ItemData getItem(String itemCode) {
        Item item = getLatestItem(itemCode);

        return convert(item, ItemData.class);
    }

    @Override
    public List<ItemData> getItems() {
        List<ItemData> itemDataList = new ArrayList<>();

        List<Item> items = itemRepository.findAllByItemStatus(ItemStatus.RELEASE).orElseThrow(() -> new IllegalArgumentException("items does not exist"));

        items.forEach(item -> itemDataList.add(convert(item, ItemData.class)));

        return itemDataList;
    }

    @Override
    public void addItem(ItemData.AddItemParam param) {
        Assert.notNull(param, "item must be not null");

        try {
            Item newItem = Item.of(param);
            itemRepository.save(newItem);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public void reviseItem(ItemData.ReviseItemParam param) {
        Assert.notNull(param, "item must be not null");

        try {
            Item latestItem = getLatestItem(param.getItemCode());
            inactiveReleaseItem(latestItem);

            Item releaseItem = getWorkingItem(param.getItemCode());
            releaseItem(releaseItem);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<ItemData> getItemHistory(String itemCode) {
        List<ItemData> itemDataList = new ArrayList<>();

        List<Item> items = itemRepository.findAllByItemCode(itemCode).orElseThrow(() -> new IllegalArgumentException("item does not exist"));

        items.forEach(item -> itemDataList.add(convert(item, ItemData.class)));

        return itemDataList;
    }

    @Override
    public void requestReviseItem(ItemData.ReviseItemParam param) {
        Assert.notNull(param, "item must be not null");

        try {
            Item item = itemRepository.findByItemCodeAndVersion(param.getItemCode(), param.getVersion()).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
            workingItem(item);

        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Item getLatestItem(String itemCode) {
        return itemRepository.findByItemCodeAndItemStatus(itemCode, ItemStatus.RELEASE).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
    }

    private void inactiveReleaseItem(Item item) {
        item.inactive();
    }

    private void workingItem(Item item) {
        item.working();
    }

    private Item duplicated(String itemCode) {
        Item item = null;

        try {
            item = itemRepository.findByItemCodeOrderByVersionDesc(itemCode).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
        } catch (IllegalArgumentException ignored) {
        }

        return item;
    }

    private void releaseItem(Item item) {
        item.release();
    }

    private Item getWorkingItem(String itemCode) {
        return itemRepository.findByItemCodeAndItemStatus(itemCode, ItemStatus.WORKING).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
    }

    private <T, E> T convert(E e, Class<T> tClass) {
        return modelMapper.map(e, tClass);
    }
}
