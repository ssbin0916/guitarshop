package com.project.guitarshop.service.item;

import com.project.guitarshop.dto.item.ItemRequest.AddItemRequest;
import com.project.guitarshop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarshop.dto.item.ItemResponse.AddItemResponse;
import com.project.guitarshop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarshop.entity.item.Item;
import com.project.guitarshop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public List<AddItemResponse> save(List<AddItemRequest> requests) {

        validateRequests(requests);

        List<Item> items = requests.stream()
                .map(this::createItemFromRequest)
                .collect(Collectors.toList());

        itemRepository.saveAll(items);

        return items.stream()
                .map(AddItemResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FindItemResponse> search(FindItemRequest request, Pageable pageable) {
        return itemRepository.search(request, pageable);
    }

    private void validateRequests(List<AddItemRequest> requests) {
        for (AddItemRequest request : requests) {
            if (request.name() == null || request.name().trim().isEmpty()) {
                throw new IllegalArgumentException("아이템 이름은 필수입니다.");
            }
            if (request.category() == null) {
                throw new IllegalArgumentException("아이템 카테고리는 필수입니다.");
            }
            if (request.brand() == null) {
                throw new IllegalArgumentException("아이템 브랜드는 필수입니다.");
            }
        }
    }

    private Item createItemFromRequest(AddItemRequest request) {
        return Item.builder()
                .name(request.name())
                .price(request.price())
                .quantity(request.quantity())
                .category(request.category())
                .brand(request.brand())
                .build();
    }

}



