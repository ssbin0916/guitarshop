package com.project.guitarShop.domain.delivery;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.order.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Embeddable
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public Delivery(Address address, DeliveryStatus status) {
        this.address = address;
        this.status = status;
    }
}
