package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
//    private final MemberRepositoryImpl memberRepositoryImpl;


    //회원 정보 수정
    @Transactional(readOnly = false)
    public void updateMember(MemberDTO dto) {//3
        //1. 회원 존재 여부 체크
        Member member = memberRepository.findById(dto.getId()).orElseThrow(IllegalArgumentException::new);

        //2. 정보 수정 (비밀번호, 이메일, 폰번호, mbti)
        if(dto.getUsername() != null){
            member.updateUsername(dto.getUsername());
        }
        if(dto.getPw() != null){
            member.updatePw(dto.getPw());
        }
        if (dto.getEmail() != null){
            member.updateEmail(dto.getEmail());
        }
        if (dto.getPhone() != null){
            member.updatePhone(dto.getPhone());
        }
        if (dto.getGender() != null){
            member.updateGender(dto.getGender());
        }
        if (dto.getMbti() != null){
            member.updateMbti(dto.getMbti());
        }
    }

    /**
     * 회원가입검증 후 저장
     */
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);

        return member.getId();
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단건 조회
     */
//    public Member findOne(Long memberId) {
//        return memberRepository.findOne(memberId);
//    }




    // 중복 회원 검증 메서드
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByUsername(member.getUsername());// 같은 이름이 있는지 찾아봄
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 있는 아이디임");
        }
    }


}








