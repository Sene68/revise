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

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public ItemData getItem(int id) {
        List<ItemData> items = new ArrayList<>();

        ItemData item1 = new ItemData(1, "item1", "001", 1);
        ItemData item2 = new ItemData(2, "item2", "002", 1);

        items.add(item1);
        items.add(item2);

        return items.stream().filter(item -> item.getId() == id).findAny().orElse(null);
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

    private <T, E> T convert(E e, Class<T> tClass) {
        return modelMapper.map(e, tClass);
    }
}
