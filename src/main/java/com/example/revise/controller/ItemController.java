package com.example.revise.controller;

import com.example.revise.dto.ApiResponse;
import com.example.revise.dto.ItemData;
import com.example.revise.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/items")
    public ItemData.ItemListRes items() {
        List<ItemData> items = itemService.getItems();

        return new ItemData.ItemListRes(ApiResponse.OK, null, items);
    }

    @GetMapping("/items/{id}")
    public ItemData.ItemObjRes item(@PathVariable int id) {

        ItemData item1 = itemService.getItem();

        return new ItemData.ItemObjRes(ApiResponse.OK, null, item1);
    }
}
