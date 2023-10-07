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

    @GetMapping("/items/{itemCode}")
    public ItemData.ItemObjRes item(@PathVariable String itemCode) {

        ItemData item = itemService.getItem(itemCode);

        return new ItemData.ItemObjRes(ApiResponse.OK, null, item);
    }

    @GetMapping("/items/{itemCode}/history")
    public ItemData.ItemListRes itemHistory(@PathVariable String itemCode) {
        List<ItemData> items = itemService.getItemHistory(itemCode);

        return new ItemData.ItemListRes(ApiResponse.OK, null, items);
    }

    @PostMapping("/items/add")
    public ItemData.ItemCustomRes<String> addItem(@RequestBody ItemData.AddItemParam param) {
        itemService.addItem(param);

        return new ItemData.ItemCustomRes<>(ApiResponse.OK, null, "OK");
    }

    @PostMapping("/items/revise")
    public ItemData.ItemCustomRes<String> reviseItem(@RequestBody ItemData.ReviseItemParam param) {
        itemService.reviseItem(param);

        return new ItemData.ItemCustomRes<>(ApiResponse.OK, null, "OK");
    }
}
