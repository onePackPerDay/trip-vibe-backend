package tripvibe.tripvibebe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;
import tripvibe.tripvibebe.service.MemberService;

import java.util.Optional;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 정보 수정(고유 번호로 회원 정보 불러오기) 엔드포인트
    @PutMapping("/tripvibe/mypage/edit/{id}")
    public void updateMember(@PathVariable Long id, @RequestParam("member") String stringMember) throws Exception {
        memberService.updateMember(id, stringMember);
    }


    //회원 1명 조회 (고유 번호로 마이페이지 불러오기)
    @GetMapping("/tripvibe/mypage/{id}")
    public MemberDTO getMemberOne(@PathVariable Long
                                              id) {
        return memberService.getMemberOne(id);
    }

    // 회원가입
    @PostMapping("/tripvibe/signup")
    public void joinMember(@RequestBody MemberDTO dto) {
        memberService.joinMember(dto);
    }

    /*@GetMapping("/tripvibe/signin")
    public void loginTest() { // loginTest -> ??? 바꿔야함
        Optional<Member> member = memberRepository.findByMemberId("moon11"); // 테스트 추후 삭제
        System.out.println("아이디 정보떠야함 제발 : " + member.get().getMemberId()); // 테스트 추후 삭제
    }*/

    @PostMapping("/tripvibe/signin")
    public String signIn(@RequestParam String memberId, @RequestParam String pw, HttpSession session) {
        MemberDTO dto = memberService.signIn(memberId, pw);
        if (dto != null) {
            session.setAttribute("member", dto);
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }
    }

    /**
     * 회원가입
     */
//    @PostMapping("/tripvibe/signup")
//    public CreateMemberResponce saveMember(@RequestBody @Validated MemberDTO memberDTO) {
//
//        Member member = getMember(memberDTO);
//
//        Long id = memberService.join(member);
//        System.out.println(member);
//        return new CreateMemberResponce(id);
//    }

    /**
     * 회원 세팅 메서드. 회원 정보 전부 세팅
     */
//    private Member getMember(MemberDTO memberDTO) {
//        Member member = new Member();
//        member.setUsername(memberDTO.getUsername());
//        member.setPw(memberDTO.getPw());
//        member.setPhone(memberDTO.getPhone());
//        member.setEmail(memberDTO.getEmail());
//        member.setBirth(memberDTO.getBirth());
//        member.setGender(memberDTO.getGender());
//        member.setMbti(memberDTO.getMbti());
//        return member;
//    }

}
