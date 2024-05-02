package com.project.guitarShop.service.cart;

import com.project.guitarShop.dto.cart.CartRequest;
import com.project.guitarShop.dto.cart.CartResponse;
import com.project.guitarShop.domain.cart.Cart;
import com.project.guitarShop.repository.cart.CartRepository;
import com.project.guitarShop.exception.NotFoundCartException;
import com.project.guitarShop.exception.NotFoundItemException;
import com.project.guitarShop.exception.NotFoundOrderException;
import com.project.guitarShop.item.domain.QItem;
import com.project.guitarShop.domain.orderItem.OrderItem;
import com.project.guitarShop.orderItem.domain.QOrderItem;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final JPAQueryFactory queryFactory;
    private final QItem qItem = QItem.item;
    private final QOrderItem qOrderItem = QOrderItem.orderItem;


    @Override
    public CartResponse addCart(Long id, OrderItem orderItem) {
//        Cart cart = cartRepository.findById(id)
//                .orElseThrow(() -> new NotFoundCartException("장바구니를 찾을 수 없습니다."));

        //검증 메서드 작성

        cart.getOrderItems().add(orderItem);
        return cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long orderItemId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundCartException("장바구니를 찾을 수 없습니다."));
    }

    @Override
    public void updateItemQuantity(Long cartId, Long orderItemId, int amount) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new NotFoundCartException("장바구니를 찾을 수 없습니다."));
        OrderItem orderItem = cart.getOrderItems().stream()
                .filter(item -> item.getId().equals(orderItemId))
                .findFirst()
                .orElseThrow(() -> new NotFoundOrderException("주문 항목을 찾을 수 없습니다."));

        if (amount > 0) {
            queryFactory.update(qOrderItem)
                    .where(
                            qOrderItem.id.eq(orderItemId),
                            qOrderItem.cart.id.eq(cartId)
                    )
                    .set(qOrderItem.item.quantity, qOrderItem.item.quantity.add(amount))
                    .execute();
        } else if (amount < 0) {
            queryFactory.update(qOrderItem)
                    .where(
                            qOrderItem.id.eq(orderItemId),
                            qOrderItem.cart.id.eq(cartId),
                            qOrderItem.item.quantity.goe(Math.abs(amount))
                    )
                    .set(qOrderItem.item.quantity, qOrderItem.item.quantity.subtract(Math.abs(amount)))
                    .execute();
        } else {
            throw new NotFoundItemException("아이템을 찾을 수 없습니다.");
        }
    }

    /**
     * 검증 메서드
     */
    private void validateExistCart(CartRequest cartRequest) {
        List<CartResponse> findCarts = cartRepository.findByIdgetId());
    }
}
