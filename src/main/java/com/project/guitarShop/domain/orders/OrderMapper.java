package com.project.guitarShop.domain.orders;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    @Select("SELECT * FROM orders WHERE id = #{id}")
    Order findById(Long id);

    @Select("SELECT * FROM orders WHERE memberId = #{memberId}")
    List<Order> findByMemberId(Long memberId);

    @Insert("INSERT INTO orders(id, memberId, price, name, phone, email, email, address, orderDate) " +
            "VALUES(orders_id_SEQ.NEXTVAL, #{memberId}, #{price}, #{name}, #{phone}, #{email}, #{address}, #{orderDate})")
    void insert(Order order);

    @Update("UPDATE orders SET price = #{price}, address = #{address}, orderDate = #{orderDate} " +
            "WHERE id = #{id}")
    void update(Order order);

    @Delete("DELETE FROM orders WHERE id = #{id}")
    void delete(Long id);
}
