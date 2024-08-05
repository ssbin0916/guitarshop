package com.project.guitarshop.item.service;

import com.project.guitarshop.item.dto.ItemRequest.*;
import com.project.guitarshop.item.dto.ItemResponse.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemService {

    List<AddItemResponse> save(List<AddItemRequest> requests);

    Page<FindItemResponse> search(FindItemRequest request, Pageable pageable);
}
