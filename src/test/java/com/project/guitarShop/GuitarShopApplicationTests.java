package com.project.guitarShop;

import com.project.guitarShop.domain.address.Address;
import com.project.guitarShop.domain.member.Member;
import com.project.guitarShop.member.domain.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class GuitarShopApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {

		Member member = new Member(
				"loginId",
				"password",
				"password",
				"name",
				29,
				"phone",
				"email",
				"ROLE_USER",
				new Address("addr1", "addr2", "addr3"),
				null);

		em.persist(member);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QMember qMember = QMember.member;

		Member result = query
				.selectFrom(qMember)
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(member);
	}

}
