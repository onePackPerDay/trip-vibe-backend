package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.*;
import tripvibe.tripvibebe.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    // 회원가입
    public ResponseDTO<?> signUp(SignUpDTO dto) {
        String memberId = dto.getMemberId();
        String pw = dto.getPw();
        String confirmPw = dto.getConfirmPw();

        // 아이디(memberId) 중복 확인
        try {
            // 존재 : true / 존재 ㄴ : false
            if (memberRepository.existsByMemberId(memberId)) {
                return ResponseDTO.setFailed("중복된 아이디에요");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("DB 연결에 실패했어요");
        }

        // 비밀번호(pw) 중복 확인
        if (!pw.equals(confirmPw)) {
            return ResponseDTO.setFailed("비밀번호가 일치하지 않아요");
        }

//        MemberDTO member = new MemberDTO(dto);
        Member member = new Member(dto);


        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPw = passwordEncoder.encode(pw);

        boolean isPwMatch = passwordEncoder.matches(pw, hashedPw);

        if (!isPwMatch) {
            return ResponseDTO.setFailed("암호화에 실패했어요");
        }

        member.setPw(hashedPw);

        // MemberRepository를 통해 DB에 엔티티 저장
        try {
            memberRepository.save(member);
        } catch (Exception e) {
            return ResponseDTO.setFailed("DB 연결에 실패했어요");
        }

        return ResponseDTO.setSuccess("회원 생성 성공");
    }

    // 로그인
    public ResponseDTO<SignInResponseDTO> signIn(SignInDTO dto) {
        String memberId = dto.getMemberId();
        String pw = dto.getPw();
        Member member;

        try {
            // 아이디로 사용자 정보 갖고오기
            member = memberRepository.findByMemberId(memberId).orElse(null);
            if (member == null) {
                return ResponseDTO.setFailed("입력한 아이디로 등록된 계정이 존재하지 않아요");
            }

            // 사용자가 입력한 비밀번호를 BCryptPasswordEncoder로 암호화
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            String encodedPw = member.getPw();

            // <저장된 암호화된 비번>과 <입력된 암호화된 비번> 비교
            if (!pwEncoder.matches(pw, encodedPw)) {
                return ResponseDTO.setFailed("비밀번호가 일치하지 않아요");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("DB 연결에 실패했어요");
        }

        // 클라이언트에 비번 제공 방지
        member.setPw("");

        try {
            // 아이디 비번이 맞는지
            boolean isExist = memberRepository.existsByMemberIdAndPw(memberId, pw);

            // 아이디 비번 틀리면
            if (!isExist) {
                return ResponseDTO.setFailed("아이디나 비밀번호가 잘못되었어요");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("DB 연결에 실패했어요");
        }

        Member memberEntity = null;

        try {
            member = memberRepository.findByMemberId(memberId).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("DB 연결에 실패했어요");
        }

        member.setPw("");

        String token = "";
        int expirationTime = 3600; // 토큰 만료시간 (1시간)

        if (token == null) { 
            // 왜 실행 안되는지 모르겠음
            return ResponseDTO.setFailed("토큰 생성에 실패했어요");
        }

        SignInResponseDTO signInResponseDTO = new SignInResponseDTO(member, token, expirationTime);

        return ResponseDTO.setSuccessData("로그인에 성공했어요", signInResponseDTO);
    }
    
    
    
    
    
    
    
    

    // MemberService에 같은 내용 반복되니 새로운게 되면 MemberService부분 지워야함
    //회원 가입
//    @Transactional
    public void joinMember(SignUpDTO dto) {
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
