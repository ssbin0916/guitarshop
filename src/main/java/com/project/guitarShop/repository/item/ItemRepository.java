package com.project.guitarShop.repository.item;

import com.project.guitarShop.dto.item.ItemResponse;
import com.project.guitarShop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Optional<ItemResponse> findByName(String name);

}
