package com.project.guitarShop.service.item;

import com.project.guitarShop.domain.item.Brand;
import com.project.guitarShop.domain.item.Category;
import com.project.guitarShop.dto.item.ItemRequest;
import com.project.guitarShop.dto.item.ItemResponse;
import com.project.guitarShop.service.item.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void save() {
        //given
        ItemRequest itemRequest = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);

        //when
        ItemResponse saveItem = itemService.save(itemRequest);

        //then
        assertEquals("name", saveItem.getName());
        assertEquals(10000, saveItem.getPrice());
        assertEquals(10, saveItem.getQuantity());
        assertEquals(Category.ELECTRIC_GUITAR, saveItem.getCategory());
        assertEquals(Brand.JAMESTYLER, saveItem.getBrand());
    }

    @Test
    void findAllBySortPriceAscending() {
        //given
        ItemRequest itemRequest1 = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest2 = new ItemRequest("name", 20000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest3 = new ItemRequest("name", 30000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest4 = new ItemRequest("name", 40000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest5 = new ItemRequest("name", 50000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest1);
        itemService.save(itemRequest2);
        itemService.save(itemRequest3);
        itemService.save(itemRequest4);
        itemService.save(itemRequest5);

        //when
        List<ItemResponse> items = itemService.findALlBySortPrice(true);

        //then
        assertThat(items).isNotEmpty();

        int prevPrice = Integer.MIN_VALUE;
        for (ItemResponse item : items) {
            assertThat(item.getPrice()).isGreaterThanOrEqualTo(prevPrice);
            prevPrice = item.getPrice();
        }
    }

    @Test
    void findAllBySortPriceDescending() {
        //given
        ItemRequest itemRequest1 = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest2 = new ItemRequest("name", 20000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest3 = new ItemRequest("name", 30000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest4 = new ItemRequest("name", 40000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest5 = new ItemRequest("name", 50000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        itemService.save(itemRequest1);
        itemService.save(itemRequest2);
        itemService.save(itemRequest3);
        itemService.save(itemRequest4);
        itemService.save(itemRequest5);

        //when
        List<ItemResponse> items = itemService.findALlBySortPrice(false);

        //then
        assertThat(items).isNotEmpty();

        int prevPrice = Integer.MAX_VALUE;
        for (ItemResponse item : items) {
            assertThat(item.getPrice()).isLessThanOrEqualTo(prevPrice);
            prevPrice = item.getPrice();
        }
    }

    @Test
    void findAllBySortBrandAndPriceAscending() {
        //given
        ItemRequest itemRequest1 = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest2 = new ItemRequest("name", 20000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest3 = new ItemRequest("name", 30000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest4 = new ItemRequest("name", 40000, 10, Category.ELECTRIC_GUITAR, Brand.SUHR);
        ItemRequest itemRequest5 = new ItemRequest("name", 50000, 10, Category.ELECTRIC_GUITAR, Brand.PRS);
        itemService.save(itemRequest1);
        itemService.save(itemRequest2);
        itemService.save(itemRequest3);
        itemService.save(itemRequest4);
        itemService.save(itemRequest5);

        //when
        List<ItemResponse> items = itemService.findAllBySortBrandAndPrice(true, Brand.JAMESTYLER);

        //then
        assertEquals(3, items.size());
        for (int i = 0; i < items.size() - 1; i++) {
            assertTrue(items.get(i).getPrice() <= items.get(i + 1).getPrice());
        }
    }

    @Test
    void findAllBySortBrandAndPriceDescending() {
        //given
        ItemRequest itemRequest1 = new ItemRequest("name", 10000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest2 = new ItemRequest("name", 20000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest3 = new ItemRequest("name", 30000, 10, Category.ELECTRIC_GUITAR, Brand.JAMESTYLER);
        ItemRequest itemRequest4 = new ItemRequest("name", 40000, 10, Category.ELECTRIC_GUITAR, Brand.SUHR);
        ItemRequest itemRequest5 = new ItemRequest("name", 50000, 10, Category.ELECTRIC_GUITAR, Brand.PRS);
        itemService.save(itemRequest1);
        itemService.save(itemRequest2);
        itemService.save(itemRequest3);
        itemService.save(itemRequest4);
        itemService.save(itemRequest5);

        //when
        List<ItemResponse> items = itemService.findAllBySortBrandAndPrice(false, Brand.JAMESTYLER);

        //then
        assertEquals(3, items.size());
        for (int i = 0; i < items.size() - 1; i++) {
            assertTrue(items.get(i).getPrice() >= items.get(i + 1).getPrice());
        }
    }
}