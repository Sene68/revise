package com.example.revise.entity;

import com.example.revise.dto.ItemData;
import com.example.revise.enums.ItemStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "item")
public class Item {

    public static Item revise(ItemData.AddItemParam param, int version) {
        String itemName = param.getItemName();
        String itemCode = param.getItemCode();

        return Item.builder().itemName(itemName).itemCode(itemCode).version(version).itemStatus(ItemStatus.ACTIVE).build();
    }

    @Builder
    public Item(String itemName, String itemCode, int version, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.version = version;
        this.itemStatus = itemStatus;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String itemName;

    private String itemCode;

    private int version;

    private ItemStatus itemStatus;


    public void active() { this.itemStatus = ItemStatus.ACTIVE; }
    public void inactive() { this.itemStatus = ItemStatus.INACTIVE; }

}
