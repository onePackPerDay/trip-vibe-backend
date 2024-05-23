package tripvibe.tripvibebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberId(String memberId);

}
