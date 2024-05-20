package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 정보 수정
    @Transactional(readOnly = false)
    public void updateMember(MemberDTO dto) {//3
        //1. 회원 존재 여부 체크
        Member member = memberRepository.findById(dto.getId()).orElseThrow(IllegalArgumentException::new);

        //2. 정보 수정 (이름. 비밀번호, 이메일, 폰번호, mbti)
        if(dto.getUsername() != null){
            member.setUsername(dto.getUsername());
        }
        if(dto.getPw() != null){
            member.setPw(dto.getUsername());
        }
        if (dto.getEmail() != null){
            member.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null){
            member.setPhone(dto.getPhone());
        }
        if (dto.getGender() != null){
            member.setGender(dto.getGender());
        }
        if (dto.getMbti() != null){
            member.setMbti(dto.getMbti());
        }
    }

    //회원 1명 조회
    @Transactional
    public MemberDTO getMemberOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new MemberDTO(member);
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