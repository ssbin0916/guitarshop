package com.project.guitarShop.item.repository;

import com.project.guitarShop.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
