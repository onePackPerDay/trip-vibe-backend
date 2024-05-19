package tripvibe.tripvibebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.service.MemberService;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PutMapping("/mypage/{id}")
    public void updateMember(@PathVariable Long id, @RequestBody MemberDTO dto) {
        dto.setId(id);
        memberService.updateMember(dto);
    }
}
