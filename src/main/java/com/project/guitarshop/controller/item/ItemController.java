package com.project.guitarshop.controller.item;

import com.project.guitarshop.dto.item.ItemRequest.AddItemRequest;
import com.project.guitarshop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarshop.dto.item.ItemResponse.AddItemResponse;
import com.project.guitarshop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarshop.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody List<AddItemRequest> requests) {
        List<AddItemResponse> responses = itemService.save(requests);
        return ResponseEntity.status(HttpStatus.CREATED).body(responses);
    }

    @GetMapping
    public ResponseEntity<Page<FindItemResponse>> search(@Valid @RequestBody FindItemRequest request, Pageable pageable) {
        Page<FindItemResponse> response = itemService.search(request, pageable);
        return ResponseEntity.ok().body(response);
    }

}
