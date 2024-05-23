package tripvibe.tripvibebe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.LoginDTO;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    //회원 정보 수정
    @Transactional
    public void updateMember(Long id, String stringMember) throws Exception {

        //1. 전달받은 id에 맞는 review가 있는 지 확인
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        MemberDTO dto = new ObjectMapper().readValue(stringMember, MemberDTO.class);

        member.setPw(dto.getPw());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setGender(dto.getGender());
        member.setMbti(dto.getMbti());

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

    @Transactional
    public MemberDTO signIn(LoginDTO dto) {
        // 데이터베이스에서 memberId를 가진 사용자 찾기
        Member member = memberRepository.findByMemberId(dto.getMemberId());

        // 사용자가 존재하고 비밀번호가 일치하는지 확인
        if (member != null && member.getPw().equals(dto.getPw())) {
            return new MemberDTO(member);
        }
        return null;
    }

}