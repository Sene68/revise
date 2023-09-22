package com.example.revise.repository;

import com.example.revise.entity.Item;
import com.example.revise.enums.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findByItemCode(String itemCode);

    Optional<List<Item>> findAllByItemCodeAndItemStatus(String ItemCode, ItemStatus itemStatus);

    Optional<Item> findByItemCodeAndItemStatus(String ItemCode, ItemStatus itemStatus);
}
