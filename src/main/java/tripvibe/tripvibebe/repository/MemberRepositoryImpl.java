package tripvibe.tripvibebe.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import tripvibe.tripvibebe.domain.Member;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepository {

    private final EntityManager em;

    // 회원등록
    public void save(Member member) {
        em.persist(member);
    }

    // 회원조회
    public Member findOne(Long id) {
        return em.find(Member.class, id); // params :     1 = type, 2 = pk
    }

    // 회원조회(리스트)
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // 이름으로 검색
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.username = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
