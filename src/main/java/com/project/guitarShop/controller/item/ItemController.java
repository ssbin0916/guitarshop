package com.project.guitarShop.controller.item;

import com.project.guitarShop.dto.item.ItemRequest.AddItemRequest;
import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.AddItemResponse;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarShop.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody List<AddItemRequest> requests) {
        List<AddItemResponse> responses = itemService.save(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<FindItemResponse>> search(@Valid @RequestBody FindItemRequest request, Pageable pageable) {
        Page<FindItemResponse> response = itemService.search(request, pageable);
        return ResponseEntity.ok().body(response);
    }

}
