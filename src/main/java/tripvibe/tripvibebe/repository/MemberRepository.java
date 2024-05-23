package tripvibe.tripvibebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripvibe.tripvibebe.domain.Member;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberId(String memberId);

}
