package com.project.guitarShop.service.coupon;

import com.project.guitarShop.entity.event.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CouponServiceTest {

    @Autowired
    private CouponService couponService;

    @Test
    void 선착순이벤트_100명에게_기프티콘_30개_제공() throws InterruptedException {

        final Event chickenEvent = Event.CHICKEN;
        final int people = 100;
        final int limitCount = 30;
        final CountDownLatch countDownLatch = new CountDownLatch(people);
        couponService.setEventCount(chickenEvent, limitCount);

        List<Thread> workers = Stream
                .generate(() -> new Thread(new AddQueueWorker(countDownLatch, chickenEvent)))
                .limit(people)
                .collect(Collectors.toList());

        workers.forEach(Thread::start);
        countDownLatch.await();
        Thread.sleep(5000);

        final long failEventPeople = couponService.getSize(chickenEvent);
        assertEquals(people - limitCount, failEventPeople);
    }

    private class AddQueueWorker implements Runnable {

        private final CountDownLatch countDownLatch;
        private final Event event;

        public AddQueueWorker(CountDownLatch countDownLatch, Event event) {
            this.countDownLatch = countDownLatch;
            this.event = event;
        }

        @Override
        public void run() {
            couponService.addQueue(event);
            countDownLatch.countDown();
        }
    }
}