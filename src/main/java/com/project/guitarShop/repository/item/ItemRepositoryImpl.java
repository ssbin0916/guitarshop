package com.project.guitarShop.repository.item;

import com.project.guitarShop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarShop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarShop.dto.item.QItemResponse_FindItemResponse;
import com.project.guitarShop.repository.orderItem.OrderItemRepository;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.project.guitarShop.entity.item.QItem.item;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    private final OrderItemRepository orderItemRepository;

    public ItemRepositoryImpl(EntityManager em, OrderItemRepository itemRepository) {
        this.queryFactory = new JPAQueryFactory(em);
        this.orderItemRepository = itemRepository;
    }

    @Override
    public List<FindItemResponse> search(FindItemRequest request) {

        JPAQuery<FindItemResponse> query = queryFactory
                .select(new QItemResponse_FindItemResponse(
                        item.name,
                        item.price,
                        item.category,
                        item.brand))
                .from(item);

        if (request.getName() != null) {
            query.where(item.name.like("%" + request.getName() + "%"));
        }

        if (request.getPrice() != null) {
            query.where(item.price.eq(request.getPrice()));
        }

        if (request.getCategory() != null) {
            query.where(item.category.eq(request.getCategory()));
        }

        if (request.getBrand() != null) {
            query.where(item.brand.eq(request.getBrand()));
        }

        if (request.getCategory() != null) {
            query.where(item.category.eq(request.getCategory()));
            if (request.getBrand() != null) {
                query.where(item.brand.eq(request.getBrand()));
            }
        }

        if (request.isSortAscending()) {
            query.orderBy(item.price.asc());
        } else {
            query.orderBy(item.price.desc());
        }

        return query.fetch();
    }
}
