package study.querydsl.entity;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MemberTest {
    @Autowired
    EntityManager em;

    @Test
    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamA);
        Member member4 = new Member("member4", 40, teamA);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        // ! 초기화 

        em.flush(); // ! 영속성 컨텍스트를 DB에 반영
        em.clear(); // ! 영속성 컨텍스트를 초기화

        em.createQuery("select m from Member m", Member.class)
            .getResultList()
            .forEach(System.out::println); // ! 영속성 컨텍스트에는 없지만 DB에는 있으므로 DB에서 가져온다.
    }
}
