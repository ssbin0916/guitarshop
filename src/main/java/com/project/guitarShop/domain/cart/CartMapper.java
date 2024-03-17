package com.project.guitarShop.domain.cart;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Select("SELECT * FROM cart WHERE memberId = #{memberId}")
    List<Cart> findByMemberId(Long MemberId);

    @Insert("INSERT INTO cart(memberId, productId, quantity) VALUES(#{memberId}, #{productId}, #{quantity})")
    void save(Cart cart);

    @Update("UPDATE cart SET quantity = #{quantity} WHERE id = #{id}")
    void updateQuantity(Cart cart);

    @Delete("DELETE FROM cart WHERE id = #{id}")
    void delete(Long id);
}
