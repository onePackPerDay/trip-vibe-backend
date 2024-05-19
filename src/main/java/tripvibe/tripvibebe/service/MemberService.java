package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.repository.MemberRepository;
import tripvibe.tripvibebe.repository.MemberRepositoryImpl;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepositoryImpl memberRepositoryImpl;

    /**
     * 회원가입
     */
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepositoryImpl.save(member);

        return member.getId();
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepositoryImpl.findAll();
    }

    /**
     * 회원 단건 조회
     */
    public Member findOne(Long memberId) {
        return memberRepositoryImpl.findOne(memberId);
    }




    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepositoryImpl.findByName(member.getUsername());// 같은 이름이 있는지 찾아봄
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 있는 아이디임");
        }
    }


}


