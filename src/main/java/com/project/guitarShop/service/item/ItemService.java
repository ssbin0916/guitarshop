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
            throw new NotFoundItemException("입력된 아이템이 없습니다.");
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
        return itemRepository.search(request);
    }
}



