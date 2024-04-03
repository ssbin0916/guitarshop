package com.project.guitarShop.domain.member;

import com.project.guitarShop.domain.Order;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Member {

    private Long id;

    @NotEmpty(message = "아이디를 입력하세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 영문 또는 숫자로만 가능합니다.")
    private String loginId;

    @NotEmpty(message = "비밀번호를 입력하세요")
    @Size(min = 8, max = 20, message = "비밀번호는 8글자에서 20글자 사이로 입력하세요")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$", message = "비밀번호는 대문자와 특수문자를 포함해야 합니다"),
            @Pattern(regexp = "^[^\\s]+$")
    })
    private String password;

    @NotEmpty(message = "비밀번호 확인을 입력하세요")
    @Size(min = 8, max = 20, message = "비밀번호 확인은 8글자에서 20글자 사이로 입력하세요")
    @Pattern.List({
            @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).+$", message = "비밀번호 확인은 대문자와 특수문자를 포함해야 합니다"),
            @Pattern(regexp = "^[^\\s]+$")
    })
    private String confirmPassword;

    @NotEmpty(message = "이름을 입력하세요")
    @Pattern(regexp = "^[가-힣]{2,4}$", message = "잘못된 입력입니다")
    private String name;

    @NotEmpty(message = "나이를 입력하세요")
    @Range(min = 1, max = 150, message = "잘못된 입력입니다")
    private Integer age;

    @NotEmpty(message = "성별을 선택하세요")
    private String gender;

    @NotEmpty(message = "전화번호를 입력하세요")
    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "잘못된 형식입니다")
    private String phone;

    @NotEmpty(message = "이메일을 입력하세요")
    @Email(message = "잘못된 입력입니다")
    private String email;

    @NotEmpty(message = "생년월일을 입력하세요")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "잘못된 형식입니다")
    private String birthDate;

    @NotEmpty(message = "주소를 입력하세요")
    private String address;

    @NotEmpty
    private Role role;

    private List<Order> orders = new ArrayList<>();

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
