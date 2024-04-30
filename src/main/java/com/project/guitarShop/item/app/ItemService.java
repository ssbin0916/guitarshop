package com.project.guitarShop.item.app;

import java.util.List;

public interface ItemService {

    ItemResponse save(ItemRequest itemRequest);

    List<ItemResponse> findALlBySortPrice(boolean ascending);
}
