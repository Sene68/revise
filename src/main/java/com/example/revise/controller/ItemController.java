package com.example.revise.controller;

import com.example.revise.dto.ApiResponse;
import com.example.revise.dto.ItemData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @GetMapping("/items")
    public ItemData.ItemListRes items() {
        List<ItemData> items = new ArrayList<>();

        ItemData item1 = new ItemData("item1", "001");
        ItemData item2 = new ItemData("item2", "002");

        items.add(item1);
        items.add(item2);

        return new ItemData.ItemListRes(ApiResponse.OK, null, items);
    }
}
