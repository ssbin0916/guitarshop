package com.project.guitarShop.item.repository;

import com.project.guitarShop.item.app.ItemResponse;
import com.project.guitarShop.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<ItemResponse> findByName(String name);

}
