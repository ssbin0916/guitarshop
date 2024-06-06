package com.project.guitarShop.service.item;

import com.project.guitarShop.dto.item.ItemRequest.*;
import com.project.guitarShop.dto.item.ItemResponse.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    List<AddItemResponse> save(List<AddItemRequest> requests);

    Page<FindItemResponse> search(FindItemRequest request, Pageable pageable);
}
