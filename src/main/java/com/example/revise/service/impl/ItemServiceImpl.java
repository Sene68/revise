package com.example.revise.service.impl;

import com.example.revise.dto.ItemData;
import com.example.revise.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public ItemData getItem() {
        return new ItemData("item1", "001", 1);
    }
}
