package com.example.revise.entity;

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
