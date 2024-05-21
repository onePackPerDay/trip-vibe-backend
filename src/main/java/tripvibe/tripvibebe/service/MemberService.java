package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

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
            member.setMemberId(dto.getUsername());
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

    @Transactional(readOnly = false)
    public void joinMember(MemberDTO memberDTO) {
        Member member = Member.builder() // builder를 통해 entity화 함
                .memberId(memberDTO.getUsername())
                .pw(memberDTO.getPw())
                .email(memberDTO.getEmail())
                .phone(memberDTO.getPhone())
                .birth(memberDTO.getBirth())
                .gender(memberDTO.getGender())
                .mbti(memberDTO.getMbti())
                .build();
        if (memberDTO.getPw() == null) {
            throw new IllegalStateException("비밀번호 필수");
        }

//        return memberRepository.save(member).getId();
    }










    /**
     * 회원가입
     */
//    @Transactional(readOnly = false)
//    public Long join(Member member) {
//        validateDuplicateMember(member);
//        memberRepository.save(member);
//
//        return member.getId();
//    }

    /**
     * 회원 전체 조회
     */
//    public List<Member> findMembers() {
//        return memberRepository.findAll();
//    }


    // 중복 회원 검증 메서드
//    private void validateDuplicateMember(Member member) {
//        Optional<Member> findMember = memberRepository.findByUsername(member.getUsername());// 같은 이름이 있는지 찾아봄
//        if (findMember != null) {
//            throw new IllegalStateException("이미 있는 아이디임");
//        }
//    }

    // 로그인
//    public MemberDTO findBy(final MemberRequestDTO params) {
//        MemberDTO entity = memberRepository.findByUsernameAndPw(params.getUsername(), params.getPw());
//
//        return entity;
//    }

}