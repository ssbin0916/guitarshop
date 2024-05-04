package com.project.guitarShop.domain.member;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.order.Order;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String loginId;

    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String confirmPassword;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String rrn;
    private String gender;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String email;
    private String role;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @Builder
    public Member(Long id, String loginId, String password, String confirmPassword, String name, String rrn, String gender, String phone, String email, String role, Address address, List<Order> orders) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.rrn = rrn;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.role = role;
        this.address = address;
        this.orders = orders;
    }

    public void updateInfo(String updatePhone, String updateEmail, Address updateAddress) {
        if (updatePhone != null && !this.phone.equals(updatePhone)) {
            this.phone = updatePhone;
        }
        if (updateEmail != null && !this.email.equals(updateEmail)) {
            this.email = updateEmail;
        }
        if (updateAddress != null && !this.address.equals(updateAddress)) {
            this.address = updateAddress;
        }
    }

    public void updatePassword(String updatePassword, String updateConfirmPassword, BCryptPasswordEncoder passwordEncoder) {
        if (updatePassword != null && !passwordEncoder.matches(updatePassword, this.password)) {
            this.password = passwordEncoder.encode(updatePassword);
        }
        if (updateConfirmPassword != null && !passwordEncoder.matches(updateConfirmPassword, this.confirmPassword)) {
            this.confirmPassword = passwordEncoder.encode(updateConfirmPassword);
        }
    }
}
