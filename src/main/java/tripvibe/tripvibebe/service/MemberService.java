package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //회원 정보 수정
    @Transactional
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

}
