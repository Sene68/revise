package com.example.revise.service;

import com.example.revise.dto.ItemData;
import com.example.revise.entity.Item;

import java.util.List;

public interface ItemService {
    ItemData getItem(long id);

    List<ItemData> getItems();

    void addItem(ItemData.AddItemParam param);
}
