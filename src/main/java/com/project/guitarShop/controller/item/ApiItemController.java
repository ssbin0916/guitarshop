package com.project.guitarShop.controller.item;

import com.project.guitarShop.dto.item.ItemRequest.*;
import com.project.guitarShop.dto.item.ItemResponse.*;
import com.project.guitarShop.exception.item.NotFoundItemException;
import com.project.guitarShop.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
public class ApiItemController {

    private final ItemService itemService;

    @PostMapping("/addItem")
    public ResponseEntity<?> save(@Valid @RequestBody AddItemRequest request) {

        try {
            AddItemResponse response = itemService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류기 발생했습니다.");
        }
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@Valid @RequestBody FindItemRequest request) {

        try {
            List<FindItemResponse> response = itemService.search(request);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NotFoundItemException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 오류가 발생했습니다.");
        }
    }
}
