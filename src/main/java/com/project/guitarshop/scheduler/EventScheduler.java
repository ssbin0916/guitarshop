package com.project.guitarshop.scheduler;

import com.project.guitarshop.event.entity.Event;
import com.project.guitarshop.event.service.CouponService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EventScheduler {

    private final CouponService couponService;

    @Scheduled(fixedDelay = 1000)
    private void chickenEventScheduler() {
        if (couponService.validEnd()) {
            log.info("===== 선착순 이벤트가 종료되었습니다. =====");
            return;
        }

        couponService.publish(Event.CHICKEN);
        couponService.getOrder(Event.CHICKEN);
    }
}
