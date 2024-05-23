package tripvibe.tripvibebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMemberId(String memberId);

    // DB에서 아이디 + 비번 같이 찾아옴. 로그인 시 id pw 일치여부
    boolean existsByMemberIdAndPw(String memberId, String pw);

    boolean existsByMemberId(String memberId);

}
