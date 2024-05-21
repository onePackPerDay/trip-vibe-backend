package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    //회원 정보 수정
    @Transactional(readOnly = false)
    public void updateMember(Long id, MemberDTO dto) {//member_id로 조회
        //1. 회원 존재 여부 체크 (고유 번호로 검사)
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        //2. 정보 수정 (비밀번호, 이메일, 폰번호, mbti)
        if(dto.getPw() != null){
            member.setPw(dto.getPw());
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
    public MemberDTO getMemberOne(Long id) { //고유 번호로 조회
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new MemberDTO(member);
    }

    //회원 가입
    @Transactional
    public void joinMember(MemberDTO dto) {
        Member member = Member.builder() // builder를 통해 entity화 함
                .memberId(dto.getMemberId())
                .pw(dto.getPw())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birth(dto.getBirth())
                .gender(dto.getGender())
                .mbti(dto.getMbti())
                .build();
        if (dto.getPw() == null) {
            throw new IllegalStateException("비밀번호 필수");
        }
        memberRepository.save(member);
    }


}