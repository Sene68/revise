package com.example.revise.controller;

import com.example.revise.dto.ItemData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping("/items")
    public ItemData items() {
        return new ItemData("item1", "A001");
    }
}
