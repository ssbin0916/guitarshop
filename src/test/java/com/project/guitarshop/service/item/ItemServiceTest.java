//package com.project.guitarshop.service.item;
//
//import com.project.guitarshop.entity.item.Brand;
//import com.project.guitarshop.dto.item.ItemRequest.AddItemRequest;
//import com.project.guitarshop.dto.item.ItemRequest.FindItemRequest;
//import com.project.guitarshop.dto.item.ItemResponse.AddItemResponse;
//import com.project.guitarshop.dto.item.ItemResponse.FindItemResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.project.guitarshop.entity.item.Brand.*;
//import static com.project.guitarshop.entity.item.Category.ELECTRIC_GUITAR;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//@Transactional
////@Rollback(value = false)
//class ItemServiceTest {
//
//    @Autowired
//    private ItemService itemService;
//
//    @Test
//    void save() {
//        // given
//        List<AddItemRequest> requests = new ArrayList<>();
//        AddItemRequest request = AddItemRequest.builder()
//                .name("제임스 타일러")
//                .price(7500000)
//                .quantity(1)
//                .category(ELECTRIC_GUITAR)
//                .brand(JAMESTYLER)
//                .build();
//        requests.add(request);
//
//        // when
//        List<AddItemResponse> responses = itemService.save(requests);
//        AddItemResponse response = responses.get(0);
//
//        // then
//        assertEquals("제임스 타일러", response.getName());
//        assertEquals(7500000, response.getPrice());
//        assertEquals(1, response.getQuantity());
//        assertEquals(ELECTRIC_GUITAR, response.getCategory());
//        assertEquals(JAMESTYLER, response.getBrand());
//    }
//
//    @Test
//    void search() {
//        // given
//        int pageSize = 10;
//        init();
//
//        FindItemRequest request = FindItemRequest.builder()
//                .sort(true)
//                .build();
//
//        // when
//        Page<FindItemResponse> page = itemService.search(request, PageRequest.of(0, pageSize));
//        List<FindItemResponse> items = page.getContent();
//
//        // then
//        assertEquals(5, items.size());
//        assertEquals("PRS", items.get(0).getName());
//        assertEquals("제임스 타일러", items.get(1).getName());
//        assertEquals(PRS, items.get(0).getBrand());
//    }
//
//
//
//    @Test
//    void searchByName() {
//        //given
//        int pageSize = 10;
//        init();
//
//        FindItemRequest request = FindItemRequest.builder()
//                .name("타일러")
//                .sort(true)
//                .build();
//
//        Page<FindItemResponse> page = itemService.search(request, PageRequest.of(0, pageSize));
//        List<FindItemResponse> items = page.getContent();
//
//        //then
//        assertEquals(2, items.size());
//    }
//
////    @Test
////    void brandInCategory() {
////        //given
////        init();
////
////        //when
////        List<FindItemResponse> items = itemService.search(new FindItemRequest(null, null, ELECTRIC_GUITAR, JAMESTYLER, true));
////
////        //then
////        assertEquals(2, items.size());
////    }
////
////
////    @Test
////    void findAllBySortPriceAscending() {
////        //given
////        init();
////
////        //when
////        List<FindItemResponse> items = itemService.search(new FindItemRequest(null, null, null, null, true));
////
////
////        //then
////        assertEquals(5, items.size());
////        assertEquals(items.get(0).getPrice(), 1500000);
////    }
////
////    @Test
////    void findAllBySortPriceDescending() {
////        //given
////        init();
////
////        //when
////        List<FindItemResponse> items = itemService.search(new FindItemRequest(null, null, null, null, false));
////
////
////        //then
////        assertEquals(5, items.size());
////        assertEquals(items.get(0).getPrice(), 8500000);
////    }
////
////    @Test
////    void findAllBySortBrandAndPriceAscending() {
////        //given
////        init();
////
////        //when
////        List<FindItemResponse> items = itemService.search(new FindItemRequest(null, null, ELECTRIC_GUITAR, JAMESTYLER, true));
////
////        //then
////        assertEquals(2, items.size());
////        assertEquals(items.get(0).getPrice(), 3500000);
////        assertEquals(items.get(1).getPrice(), 7500000);
////    }
////
////
////    @Test
////    void findAllBySortBrandAndPriceDescending() {
////        //given
////        init();
////
////        //when
////        List<FindItemResponse> items = itemService.search(new FindItemRequest(null, null, ELECTRIC_GUITAR, JAMESTYLER, false));
////
////        //then
////        assertEquals(2, items.size());
////        assertEquals(items.get(0).getPrice(), 7500000);
////        assertEquals(items.get(1).getPrice(), 3500000);
////    }
//
//
//    private void init() {
//        addItem("제임스 타일러", 7500000, JAMESTYLER);
//        addItem("존 써", 8500000, SUHR);
//        addItem("펜더", 6500000, FENDER);
//        addItem("PRS", 1500000, PRS);
//        addItem("제임스 타일러", 3500000,JAMESTYLER);
//    }
//
//    private void addItem(String name, int price, Brand brand) {
//        AddItemRequest request = AddItemRequest.builder()
//                .name(name)
//                .price(price)
//                .quantity(1)
//                .category(ELECTRIC_GUITAR)
//                .brand(brand)
//                .build();
//        List<AddItemRequest> requests = new ArrayList<>();
//        requests.add(request);
//        itemService.save(requests);
//    }
//}