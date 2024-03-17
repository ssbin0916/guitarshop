package com.project.guitarShop.web.product;

import com.project.guitarShop.domain.product.Product;
import com.project.guitarShop.domain.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")

public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    }
}
