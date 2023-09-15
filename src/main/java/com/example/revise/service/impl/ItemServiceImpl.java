package com.example.revise.service.impl;

import com.example.revise.dto.ItemData;
import com.example.revise.entity.Item;
import com.example.revise.repository.ItemRepository;
import com.example.revise.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Item item = Item.of(param);

        itemRepository.save(item);
    }

    private boolean duplicated(String itemCode) {
        Item item = null;

        try {
            item = getItem(itemCode);
        } catch (IllegalArgumentException ignored) {
        }

        return item != null;
    }

    private Item getItem(String itemCode) {
        return itemRepository.findByItemCode(itemCode).orElseThrow(() -> new IllegalArgumentException("item does not exist"));
    }

    private <T, E> T convert(E e, Class<T> tClass) {
        return modelMapper.map(e, tClass);
    }
}
