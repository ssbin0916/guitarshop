package com.project.guitarshop.repository.item;

import com.project.guitarshop.dto.item.ItemRequest.FindItemRequest;
import com.project.guitarshop.dto.item.ItemResponse.FindItemResponse;
import com.project.guitarshop.dto.item.QItemResponse_FindItemResponse;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static com.project.guitarshop.entity.item.QItem.item;

public class ItemRepositoryImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<FindItemResponse> search(FindItemRequest request, Pageable pageable) {

        JPAQuery<FindItemResponse> query = queryFactory
                .select(new QItemResponse_FindItemResponse(
                        item.name,
                        item.price,
                        item.category,
                        item.brand))
                .from(item);

        if (request.getName() != null) {
            query.where(item.name.containsIgnoreCase(request.getName()));
        }

        if (request.getCategory() != null) {
            query.where(item.category.eq(request.getCategory()));
        }

        if (request.getBrand() != null) {
            query.where(item.brand.eq(request.getBrand()));
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
}
