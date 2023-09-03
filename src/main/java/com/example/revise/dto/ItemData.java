package com.example.revise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemData {
    private String itemName;
    private String itemCode;


    @NoArgsConstructor
    public static class ItemListRes extends ApiResponse<List<ItemData>> implements Serializable {
        public ItemListRes(int status, String error, List<ItemData> list) {
            super(status, error, list);
        }
    }
}
