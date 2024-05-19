package tripvibe.tripvibebe.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberDTO;
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


    /**
     * 회원가입
     * @param request DTO
     */
    @PostMapping("/tripvibe/signup")
    public CreateMemberResponce saveMember(@RequestBody @Validated MemberDTO memberDTO) {

        Member member = getMember(memberDTO);

        Long id = memberService.join(member);
        return new CreateMemberResponce(id);
    }

    /**
     * 회원 세팅 메서드. 회원 정보 전부 세팅
     */
    private Member getMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setUsername(memberDTO.getUsername());
        member.setPw(memberDTO.getPw());
        member.setPhone(memberDTO.getPhone());
        member.setEmail(memberDTO.getEmail());
        member.setBirth(memberDTO.getBirth());
        member.setGender(memberDTO.getGender());
        member.setMbti(memberDTO.getMbti());
        return member;
    }

    //    @PostMapping("/tripvibe/signup") // 변경
//    public CreateMemberResponce saveMember2(@RequestBody @Validated Member member) {
//        Long id = memberService.join(member);
//        return new CreateMemberResponce(id);
//    }


    @Data
    static class CreateMemberResponce {
        private Long id;

        public CreateMemberResponce(Long id) {
            this.id = id;
        }
    }


}
