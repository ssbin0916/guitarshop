package com.project.guitarShop.item.app;

import com.project.guitarShop.item.domain.Brand;

import java.util.List;

public interface ItemService {

    ItemResponse save(ItemRequest itemRequest);

    List<ItemResponse> findALlBySortPrice(boolean ascending);

    List<ItemResponse> findAllBySortBrandAndPrice(boolean ascending, Brand brand);
}
