package com.example.revise.service;

import com.example.revise.dto.ItemData;

import java.util.List;

public interface ItemService {
    ItemData getItem();

    List<ItemData> getItems();
}
