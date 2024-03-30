package com.project.guitarShop.domain.product;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(Long id);

    @Select("SELECT * FROM product WHERE name = #{name}")
    Product findByName(String name);

    @Insert("INSERT INTO product(id, name, price, image, count, category) " +
            "VALUES(product_id_SEQ.NEXTVAL, #{name}, #{price}, #{image}, #{count}, #{category})")
    void save(Product product);

    @Update("UPDATE product SET name = #{name}, price = #{price}, image = #{image}, " +
            "count = #{count}, category = #{category} WHERE id = #{id}")
    void update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void delete(Long id);
}
