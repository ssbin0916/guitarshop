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

    @Select("SELECT * FROM member WHERE member_id = #{memberId}")
    Optional<Member> findByMemberId(String memberId);

    @Insert("INSERT INTO member(member_id, password, name, age, gender, email, phone, address, role) " +
            "VALUES(#{memberId}, #{password}, #{name}, #{age}, #{gender}, #{email}, #{phone}, #{address}, #{role})")
    void save(Member member);

    @Update("UPDATE member SET member_id = #{memberId}, password = #{password}, name = #{name}, " +
            "age = #{age}, gender = #{gender}, email = #{email}, phone = #{phone}, " +
            "address = #{address}, role = #{role} WHERE id = #{id}")
    void update(Member member);

    @Delete("DELETE FROM member WHERE id = #{id}")
    default void deleteById(Long id) {

    }
}
