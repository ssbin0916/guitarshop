package com.project.guitarshop.autocomplete.controller;

import com.project.guitarshop.autocomplete.dto.AutocompleteResponse;
import com.project.guitarshop.autocomplete.service.AutocompleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/autocomplete")
public class AutocompleteController {

    private final AutocompleteService autocompleteService;

    @GetMapping("/{searchWord}")
    @ResponseBody
    public ResponseEntity<AutocompleteResponse> getAutocompleteList(@PathVariable String searchWord) {
        return ResponseEntity.ok(autocompleteService.getAutocomplete(searchWord));
    }
}
