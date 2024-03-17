package com.project.guitarShop.domain.orders;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order findById(Long id);

    @Select("SELECT * FROM orders WHERE memberId = #{memberId}")
    List<Order> findByMemberId(Long memberId);

    @Insert("INSERT INTO orders(memberId, totalPrice, address, orderDate) " +
            "VALUES(#{memberId}, #{totalPrice}, #{address}, #{orderDate})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Order order);

    @Update("UPDATE orders SET totalPrice = #{totalPrice}, address = #{address}, orderDate = #{orderDate} " +
            "WHERE id = #{id}")
    void update(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    void delete(Long id);
}
