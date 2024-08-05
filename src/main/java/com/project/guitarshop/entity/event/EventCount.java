package com.project.guitarshop.entity.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class EventCount {

    private static final int END = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Event event;
    private int limitCount;

    public EventCount(Event event, int limitCount) {
        this.event = event;
        this.limitCount = limitCount;
    }

    public synchronized void decrease() {
        this.limitCount--;
    }

    public boolean end() {
        return this.limitCount == END;
    }
}
