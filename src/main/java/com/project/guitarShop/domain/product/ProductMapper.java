package com.project.guitarShop.domain.product;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {

    @Select("SELECT * FROM product")
    List<Product> findAll();

    @Select("SELECT * FROM product WHERE id = #{id}")
    Product findById(Long id);

    @Insert("INSERT INTO product(name, price, image, count, category) " +
            "VALUES(#{name}, #{price}, #{image}, #{count}, #{category})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(Product product);

    @Update("UPDATE product SET name = #{name}, price = #{price}, image = #{image}, " +
            "count = #{count}, category = #{category} WHERE id = #{id}")
    void update(Product product);

    @Delete("DELETE FROM product WHERE id = #{id}")
    void delete(Long id);
}
