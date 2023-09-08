package com.example.revise.service.impl;

import com.example.revise.dto.ItemData;
import com.example.revise.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

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
        List<ItemData> items = new ArrayList<>();

        ItemData item1 = new ItemData(1, "item1", "001", 1);
        ItemData item2 = new ItemData(2, "item2", "002", 1);

        items.add(item1);
        items.add(item2);

        return items;
    }
}
