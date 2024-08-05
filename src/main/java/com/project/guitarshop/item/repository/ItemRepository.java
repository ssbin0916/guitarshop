package com.project.guitarshop.item.repository;

import com.project.guitarshop.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
