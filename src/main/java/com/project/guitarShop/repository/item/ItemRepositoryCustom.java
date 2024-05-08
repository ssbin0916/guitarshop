package com.project.guitarShop.repository.item;

import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;

import java.util.List;

public interface ItemRepositoryCustom {

    List<FindItemResponse> search(FindItemRequest request);

}
