package com.project.guitarShop.repository.item;

import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemRepositoryCustom {

    Page<FindItemResponse> search(FindItemRequest request, Pageable pageable);

}
