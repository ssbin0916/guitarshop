package com.project.guitarShop.facade;

import com.project.guitarShop.repository.redis.RedisLockRepository;
import com.project.guitarShop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LettuceLockFacade {

    private final RedisLockRepository redisLockRepository;
    private final OrderService orderService;

    @Transactional
    public Long order(Long memberId, Long itemId) throws InterruptedException {
        String lockKey = "order:member:" + memberId + ":item:" + itemId;
        while (!redisLockRepository.lock(lockKey)) {
            Thread.sleep(100);
        }
        try {
            return orderService.order(memberId, itemId);
        } catch (Exception e) {
            throw new RuntimeException("주문 처리 중 오류가 발생했습니다.", e);
        } finally {
            redisLockRepository.unlock(lockKey);
        }
    }

    @Transactional
    public void cancelOrder(Long orderId) throws InterruptedException {
        String lockKey = "order:cancel:" + orderId;
        while (!redisLockRepository.lock(lockKey)) {
            Thread.sleep(100);
        }
        try {
            orderService.cancelOrder(orderId);
        } catch (Exception e) {
            throw new RuntimeException("주문 취소 중 오류가 발생했습니다.", e);
        } finally {
            redisLockRepository.unlock(lockKey);
        }
    }

    @Transactional
    public Long orderFromCart(Long cartId) throws InterruptedException {
        String lockKey = "order:cart:" + cartId;
        while (!redisLockRepository.lock(lockKey)) {
            Thread.sleep(100);
        }
        try {
            return orderService.orderFromCart(cartId);
        } catch (Exception e) {
            throw new RuntimeException("장바구니에서 주문 처리 중 오류가 발생했습니다.", e);
        } finally {
            redisLockRepository.unlock(lockKey);
        }
    }
}
