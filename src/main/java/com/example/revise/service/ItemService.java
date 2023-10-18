package com.example.revise.service;

import com.example.revise.dto.ItemData;
import com.example.revise.entity.Item;

import java.util.List;

public interface ItemService {
    ItemData getItem(String itemCode);

    List<ItemData> getItems();

    List<ItemData> getItemHistory(String itemCode);

    void addItem(ItemData.AddItemParam param);

    void reviseItem(ItemData.ReviseItemParam param);

    void requestReviseItem();
}
