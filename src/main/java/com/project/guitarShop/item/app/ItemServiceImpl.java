package com.project.guitarShop.item.app;

import com.project.guitarShop.item.domain.Item;
import com.project.guitarShop.item.domain.QItem;
import com.project.guitarShop.item.repository.ItemRepository;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final JPAQueryFactory queryFactory;
    private final QItem qItem = QItem.item;
    private final ItemRepository itemRepository;

    @Override
    @Transactional
    public ItemResponse save(ItemRequest itemRequest) {
        Item item = Item.toDomain(itemRequest);

        itemRepository.save(item);

        Long itemId = item.getId();
        Item saveItem = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException());
        return new ItemResponse(saveItem.getName(), saveItem.getPrice(), saveItem.getQuantity(), saveItem.getCategory(), saveItem.getBrand());
    }

    @Override
    public List<ItemResponse> findALlBySortPrice(boolean ascending) {
        OrderSpecifier<Integer> orderSpecifier = ascending ? qItem.price.asc() : qItem.price.desc();
        return queryFactory
                .select(new QItemResponse(qItem.name, qItem.price, qItem.quantity, qItem.category, qItem.brand))
                .from(qItem)
                .orderBy(orderSpecifier)
                .fetch();
    }
}



