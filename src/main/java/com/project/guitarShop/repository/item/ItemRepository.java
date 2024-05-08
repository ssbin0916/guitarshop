package com.project.guitarShop.repository.item;

import com.project.guitarShop.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
