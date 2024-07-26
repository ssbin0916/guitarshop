package com.project.guitarshop.repository.item;

import com.project.guitarshop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarshop.dto.item.ItemResponse.FindItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<FindItemResponse> search(FindItemRequest request, Pageable pageable);

}
