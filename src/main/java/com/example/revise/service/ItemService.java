package com.example.revise.service;

import com.example.revise.dto.ItemData;

import java.util.List;

public interface ItemService {
    ItemData getItem(long id);

    List<ItemData> getItems();

    void addItem(ItemData.AddItemParam param);
}
