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
    public ItemData getItem(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("item does not exist"));

        return convert(item, ItemData.class);
    }

    @Override
    public List<ItemData> getItems() {
        List<ItemData> itemDataList = new ArrayList<>();

        List<Item> items = itemRepository.findAll();

        items.forEach(item -> itemDataList.add(convert(item, ItemData.class)));

        return itemDataList;
    }

    @Override
    public void addItem(ItemData.AddItemParam param) {
        Assert.notNull(param, "item must be not null");
        Item item = duplicated(param.getItemCode());

        int version = 1;

        if (item != null)
            version = item.getVersion() + 1;

        try {
            inactiveItems(param.getItemCode());

            Item newItem = Item.revise(param, version);
            itemRepository.save(newItem);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private Item duplicated(String itemCode) {
        Item item = null;

        try {
            item = getItem(itemCode);
        } catch (IllegalArgumentException ignored) {
        }

        return item;
    }

    private Item getItem(String itemCode) {
        return itemRepository.findByItemCode(itemCode).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
    }

    private void inactiveItems(String itemCode) {
        try {
            List<Item> items = itemRepository.findAllByItemCodeAndItemStatus(itemCode, ItemStatus.ACTIVE).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
            items.forEach(Item::inactive);
        } catch (Exception ignored) {
        }
    }

    private <T, E> T convert(E e, Class<T> tClass) {
        return modelMapper.map(e, tClass);
    }
}
