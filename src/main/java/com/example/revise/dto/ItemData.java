package com.example.revise.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemData {
    private String itemName;
    private String itemCode;
    private int version;

    @NoArgsConstructor
    public static class ItemObjRes extends ApiResponse<ItemData> implements Serializable {
        public ItemObjRes(int status, String error, ItemData data) {
            super(status, error, data);
        }
    }

    @NoArgsConstructor
    public static class ItemListRes extends ApiResponse<List<ItemData>> implements Serializable {
        public ItemListRes(int status, String error, List<ItemData> list) {
            super(status, error, list);
        }
    }

    @NoArgsConstructor
    public static class ItemCustomRes<T> extends ApiResponse<T> {
        public ItemCustomRes(int status, String error, T data) { super(status, error, data); }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class AddItemParam {
        private String itemName;
        private String itemCode;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ReviseItemParam {
        private String itemCode;
        private int version;
    }
}
