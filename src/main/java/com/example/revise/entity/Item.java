package com.example.revise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Item {

    @Id
    @GeneratedValue
    private Long id;

    private String itemName;

    private String itemCode;

    private int version;
}
