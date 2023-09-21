package com.example.revise.controller;

import com.example.revise.dto.ApiResponse;
import com.example.revise.dto.ItemData;
import com.example.revise.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ItemData.ItemObjRes item(@PathVariable long id) {

        ItemData item = itemService.getItem(id);

        return new ItemData.ItemObjRes(ApiResponse.OK, null, item);
    }

    @PostMapping("/items/add")
    public ItemData.ItemCustomRes<String> addItem(@RequestBody ItemData.AddItemParam param) {
        itemService.addItem(param);

        return new ItemData.ItemCustomRes<>(ApiResponse.OK, null, "OK");
    }
}
