package tripvibe.tripvibebe.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.repository.MemberRepository;
import tripvibe.tripvibebe.service.MemberService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
//@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    //회원 정보 수정
    @PutMapping("/tripvibe/mypage/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        dto.setId(id);
        memberService.updateMember(dto);
    }

    //회원 1명 조회
    @GetMapping("/tripvibe/mypage/{id}")
    public MemberDTO getMemberOne(@PathVariable Long id) {

        return memberService.getMemberOne(id);
    }

    // 회원가입
    @PostMapping("/tripvibe/signup")
    public void createMember(MemberDTO memberDTO) {
        Long memberId = memberService.join(memberDTO);
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

    @Data
    static class CreateMemberResponce {
        private Long id;

        public CreateMemberResponce(Long id) {
            this.id = id;
        }
    }

}
