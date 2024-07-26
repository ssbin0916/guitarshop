package com.project.guitarshop.service.coupon;

import com.project.guitarshop.entity.event.Coupon;
import com.project.guitarshop.entity.event.Event;
import com.project.guitarshop.entity.event.EventCount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {

    private static final long FIRST_ELEMENT = 0;
    private static final long LAST_ELEMENT = -1;
    private static final long PUBLISH_SIZE = 10;
    private static final long LAST_INDEX = 1;

    private final RedisTemplate<String, Object> redisTemplate;
    private EventCount eventCount;

    public void setEventCount(Event event, int queue) {
        this.eventCount = new EventCount(event, queue);
    }

    public void addQueue(Event event) {
        final String people = Thread.currentThread().getName();
        final long now = System.currentTimeMillis();

        redisTemplate.opsForZSet().add(event.toString(), people, (int) now);
        log.info("사용자 '{}'이(가) 이벤트 '{}'의 대기열에 추가되었습니다. 시간: {}", people, event.getName(), now);
    }

    public void getOrder(Event event) {
        final long start = FIRST_ELEMENT;
        final long end = LAST_ELEMENT;

        Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), start, end);

        for (Object people : queue) {
            Long rank = redisTemplate.opsForZSet().rank(event.toString(), people);
            log.info("사용자 '{}'의 현재 대기 순번은 {}번 입니다. 이벤트: '{}'", people, rank, event.getName());
        }
    }

    public void publish(Event event) {
        final long start = FIRST_ELEMENT;
        final long end = PUBLISH_SIZE - LAST_INDEX;

        Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), start, end);
        for (Object people : queue) {
            final Coupon coupon = new Coupon(event);
            log.info("사용자 '{}'에게 이벤트 '{}'의 쿠폰이 발급되었습니다. 쿠폰 코드: {}", people, event.getName(), coupon.getCode());
            redisTemplate.opsForZSet().remove(event.toString(), people);
            this.eventCount.decrease();
        }
    }

    public boolean validEnd() {
        return this.eventCount != null && this.eventCount.end();
    }

    public Long getSize(Event event) {
        return redisTemplate.opsForZSet().size(event.toString());
    }
}
