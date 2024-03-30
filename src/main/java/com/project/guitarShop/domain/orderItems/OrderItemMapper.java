package com.project.guitarShop.domain.orderItems;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderItemMapper {

    @Select("SELECT * FROM orderItem WHERE orderId = #{orderId}")
    List<OrderItem> findByOrderId(Long orderId);

    @Insert("INSERT INTO orderItem(orderId, productId, quantity) VALUES(#{orderId}, #{productId}, #{quantity})")
    void save(OrderItem orderItem);

    @Update("UPDATE orderItem SET productId = #{productId}, quantity = #{quantity} WHERE id = #{id}")
    void update(OrderItem orderItem);

    @Delete("DELETE FROM orderItem WHERE id = #{id}")
    void delete(Long id);
}
