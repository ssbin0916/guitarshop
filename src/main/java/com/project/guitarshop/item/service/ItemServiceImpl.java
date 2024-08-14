package com.project.guitarshop.item.service;

import com.project.guitarshop.autocomplete.dto.AutocompleteResponse;
import com.project.guitarshop.autocomplete.service.AutocompleteService;
import com.project.guitarshop.item.dto.ItemRequest.AddItemRequest;
import com.project.guitarshop.item.dto.ItemRequest.FindItemRequest;
import com.project.guitarshop.item.dto.ItemResponse.AddItemResponse;
import com.project.guitarshop.item.dto.ItemResponse.FindItemResponse;
import com.project.guitarshop.item.dto.QItemResponse_FindItemResponse;
import com.project.guitarshop.item.entity.Item;
import com.project.guitarshop.item.repository.ItemRepository;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.project.guitarshop.item.entity.QItem.item;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final AutocompleteService autocompleteService;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    @Override
    public List<AddItemResponse> save(List<AddItemRequest> requests) {

        validateRequests(requests);

        List<Item> items = requests.stream()
                .map(this::createItemFromRequest)
                .collect(Collectors.toList());

        itemRepository.saveAll(items);

        return items.stream()
                .map(AddItemResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Page<FindItemResponse> search(FindItemRequest request, Pageable pageable) {
        List<String> autoCompleteNames = request.autoCompleteNames();
        if (request.name() != null && (autoCompleteNames == null || autoCompleteNames.isEmpty())) {
            AutocompleteResponse autocompleteResponse = autocompleteService.getAutocomplete(request.name());
            autocompleteResponse.getList().stream()
                    .map(AutocompleteResponse.Data::getValue)
                    .collect(Collectors.toList());
        }

        JPAQuery<FindItemResponse> query = jpaQueryFactory
                .select(new QItemResponse_FindItemResponse(
                        item.name,
                        item.price,
                        item.category,
                        item.brand))
                .from(item);

        if (request.name() != null) {
            query.where(item.name.containsIgnoreCase(request.name()));
        }

        if (request.category() != null) {
            query.where(item.category.eq(request.category()));
        }

        if (request.brand() != null) {
            query.where(item.brand.eq(request.brand()));
        }

        if (pageable.getSort().isSorted()) {
            for (Sort.Order o : pageable.getSort()) {
                PathBuilder pathBuilder = new PathBuilder(item.getType(), item.getMetadata());

                query.orderBy(new OrderSpecifier<>(o.isAscending() ? Order.ASC :
                        Order.DESC, pathBuilder.get(o.getProperty())));
            }
        }

        QueryResults<FindItemResponse> results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<FindItemResponse> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content, pageable, total);
    }

    private void validateRequests(List<AddItemRequest> requests) {
        for (AddItemRequest request : requests) {
            if (request.name() == null || request.name().trim().isEmpty()) {
                throw new IllegalArgumentException("아이템 이름은 필수입니다.");
            }
            if (request.category() == null) {
                throw new IllegalArgumentException("아이템 카테고리는 필수입니다.");
            }
            if (request.brand() == null) {
                throw new IllegalArgumentException("아이템 브랜드는 필수입니다.");
            }
        }
    }

    private Item createItemFromRequest(AddItemRequest request) {
        autocompleteService.addAutocomplete(request.name());
        return Item.builder()
                .name(request.name())
                .price(request.price())
                .quantity(request.quantity())
                .category(request.category())
                .brand(request.brand())
                .build();
    }
}