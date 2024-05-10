package com.project.guitarShop.controller.item;

import com.project.guitarShop.dto.item.ItemRequest.*;
import com.project.guitarShop.dto.item.ItemResponse.*;
import com.project.guitarShop.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiItemController {

    private final ItemService itemService;

    @PostMapping("/addItem")
    public ResponseEntity<AddItemResponse> save(@Valid @RequestBody AddItemRequest request) {

        AddItemResponse response = itemService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
