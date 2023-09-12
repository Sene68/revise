package com.example.revise.entity;

import com.example.revise.dto.ItemData;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@Table(name = "item")
public class Item {

    public static Item of(ItemData.AddItemParam param) {
        String itemName = param.getItemName();
        String itemCode = param.getItemCode();

        return Item.builder().itemName(itemName).itemCode(itemCode).version(1).build();
    }

    @Builder
    public Item(String itemName, String itemCode, int version) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.version = version;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String itemName;

    private String itemCode;

    private int version;
}
