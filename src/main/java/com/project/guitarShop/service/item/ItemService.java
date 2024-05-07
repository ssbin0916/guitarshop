package com.project.guitarShop.service.item;

import com.project.guitarShop.domain.item.Item;
import com.project.guitarShop.dto.item.ItemRequest.AddItemRequest;
import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.AddItemResponse;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarShop.exception.item.ItemSaveException;
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

        try {
            Item save = itemRepository.save(request.toDomain());
            return new AddItemResponse(save);
        } catch (Exception e) {
            throw new ItemSaveException("아이템 등록에 실패했습니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<FindItemResponse> search(FindItemRequest request) {
        return itemRepository.search(request);
    }
}



