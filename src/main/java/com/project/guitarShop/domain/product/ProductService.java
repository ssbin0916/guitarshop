package com.project.guitarShop.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductMapper productMapper;

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return productMapper.findAll();
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productMapper.findById(id);
    }

    @Transactional
    public void save(Product product) {
        productMapper.save(product);
    }

    @Transactional
    public void update(Product product) {
        productMapper.update(product);
    }

    @Transactional
    public void delete(Long id) {
        productMapper.delete(id);
    }
}
