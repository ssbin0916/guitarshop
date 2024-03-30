package com.project.guitarShop.domain.member;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    @Select("SELECT * FROM member")
    List<Member> findAll();

    @Select("SELECT * FROM member WHERE id = #{id}")
    Member findById(Long id);

    @Select("SELECT * FROM member WHERE login_id = #{loginId}")
    Optional<Member> findByLoginId(String loginId);

    @Insert("INSERT INTO member (id, login_id, password, name, age, phone, email, birth_date, address, gender, role) " +
            "VALUES (member_id_SEQ.NEXTVAL, #{loginId}, #{password}, #{name}, #{age}, #{phone}, #{email}, #{birthDate}, #{address}, #{gender}, #{role})")
    void insert(Member member);

    @Update("UPDATE member SET login_id = #{loginId}, password = #{password}, name = #{name}, " +
            "age = #{age}, phone = #{phone}, email = #{email}, birth_date = #{birthDate}, " +
            "address = #{address}, gender = #{gender}, role = #{role} WHERE id = #{id}")
    void update(Member member);

    @Delete("DELETE FROM member WHERE id = #{id}")
    default void delete(Long id) {

    }
}
