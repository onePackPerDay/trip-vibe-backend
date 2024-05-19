package tripvibe.tripvibebe.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.service.MemberService;

import java.time.LocalDate;

//  MemberApiController
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/mypage/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        dto.setId(id);
        memberService.updateMember(dto);
    }

    private final MemberService memberService;


    /**
     * 회원가입
     * @param request DTO
     */
    @PostMapping("/tripvibe/signup")
    public CreateMemberResponce saveMember(@RequestBody @Validated CreateMemberRequest request) {

        // 묶어도됨
        Member member = new Member();
        member.setUsername(request.getUsername());
        member.setPw(request.getPw());
        member.setPhone(request.getPhone());
        member.setEmail(request.getEmail());
        member.setBirth(request.getBirth());
        member.setGender(request.getGender());
        member.setMbti(request.getMbti());

        Long id = memberService.join(member);
        return new CreateMemberResponce(id);
    }

    //    @PostMapping("/tripvibe/signup") // 변경
//    public CreateMemberResponce saveMember2(@RequestBody @Validated Member member) {
//        Long id = memberService.join(member);
//        return new CreateMemberResponce(id);
//    }


    /**
     * dto
      */
    @Data
    static class CreateMemberRequest {
        // @NotEmpty
        private String username;

        private String pw;

        private String email;

        private String phone;

        private LocalDate birth;

        private String gender;

        private String mbti;
    }

    @Data
    static class CreateMemberResponce {
        private Long id;

        public CreateMemberResponce(Long id) {
            this.id = id;
        }
    }


}
