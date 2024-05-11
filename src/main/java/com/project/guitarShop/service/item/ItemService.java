package com.project.guitarShop.service.item;

import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.dto.item.ItemRequest.AddItemRequest;
import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.AddItemResponse;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {

    private final ItemRepository itemRepository;

    public AddItemResponse save(AddItemRequest request) {

        if (request == null) {
            throw new IllegalArgumentException("입력된 아이템이 없습니다.");
        }

        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("아이템 이름은 필수입니다.");
        }

        if (request.getPrice() == null || request.getPrice() < 0) {
            throw new IllegalArgumentException("유효하지 않은 가격입니다.");
        }

        if (request.getQuantity() == null || request.getQuantity() < 0) {
            throw new IllegalArgumentException("유효하지 않은 수량입니다.");
        }

        if (request.getCategory() == null) {
            throw new IllegalArgumentException("유효하지 않은 카테고리 입니다.");
        }

        if (request.getBrand() == null) {
            throw new IllegalArgumentException("유효하지 않은 브랜드입니다.");
        }

        Item item = Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .category(request.getCategory())
                .brand(request.getBrand())
                .build();

        itemRepository.save(item);

        return new AddItemResponse(item);
    }

    @Transactional(readOnly = true)
    public List<FindItemResponse> search(FindItemRequest request) {

        if (request == null) {
            throw new NotFoundItemException("유효하지 않은 요청입니다.");
        }

        if (request.getName() != null && request.getName().length() < 2) {
            throw new IllegalArgumentException("검색어는 2글자 이상이어야 합니다.");
        }

        if (request.getPrice() != null && request.getPrice() < 0) {
            throw new IllegalArgumentException("가격은 0원 이상이어야 합니다.");
        }

        return itemRepository.search(request);
    }
}



