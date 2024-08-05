package com.project.guitarshop.repository.item;

import com.project.guitarshop.entity.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
