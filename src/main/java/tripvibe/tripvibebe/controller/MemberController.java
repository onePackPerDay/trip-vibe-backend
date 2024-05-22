package tripvibe.tripvibebe.controller;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;
import tripvibe.tripvibebe.service.MemberService;

import java.util.Optional;


//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 정보 수정(고유 번호로 회원 정보 불러오기)
    @PutMapping("/tripvibe/mypage/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        memberService.updateMember(id, dto);
    }

    //회원 1명 조회 (고유 번호로 마이페이지 불러오기)
    @GetMapping("/tripvibe/mypage/{id}")
    public MemberDTO getMemberOne(@PathVariable Long id, Authentication auth) {
//        System.out.println("auth : " + auth);
//        System.out.println("auth.getName() : " + auth.getName());
//        System.out.println("auth.isAuthenticated() : " + auth.isAuthenticated());
//
//        boolean 유저권한 = auth.getAuthorities().contains(new SimpleGrantedAuthority("유저"));
//        System.out.println(유저권한);

        return memberService.getMemberOne(id);
    }

    // 회원가입
    // 테스트때문에 주석
//    @PostMapping("/tripvibe/signup")
//    public void joinMember(@RequestBody MemberDTO dto) {
//        memberService.joinMember(dto);
//    }

    // 로그인
    // 테스트때문에 주석
//    @GetMapping("/tripvibe/signin")
//    public void loginTest() { // loginTest -> ??? 바꿔야함
//        Optional<Member> member = memberRepository.findByMemberId("moon11"); // 테스트 추후 삭제
//        System.out.println("아이디 정보떠야함 제발 : " + member.get().getMemberId()); // 테스트 추후 삭제
//    }

//    @RequestMapping("/")
//    @GetMapping
//    public String test() {
//        return "성공";
//    }


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
