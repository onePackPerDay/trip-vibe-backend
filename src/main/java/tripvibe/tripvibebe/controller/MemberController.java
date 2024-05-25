package tripvibe.tripvibebe.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tripvibe.tripvibebe.dto.LoginDTO;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/tripvibe/signup")
    public void joinMember(@RequestBody MemberDTO dto) {
        memberService.joinMember(dto);
    }

    //로그인
    @PostMapping("/tripvibe/signin")
    public Map<String, Object> signIn(@RequestBody LoginDTO dto, HttpSession session) {
        MemberDTO memberDTO = memberService.signIn(dto);
        Map<String, Object> response = new HashMap<>();

        if (memberDTO != null) {
            session.setAttribute("member", memberDTO);
            response.put("status", "success");
            response.put("memberId", memberDTO.getId());
            System.out.println(session.getAttribute("member"));
            return response;// 로그인 성공 시, 응답 코드 200과 함께 전송
        } else {
            response.put("status", "fail");
            response.put("message", "Invalid credentials");
            System.out.println(session.getAttribute("member"));
            return response; // 로그인 실패 시, 응답 코드 401과 함께 전송
        }

    }

    //로그아웃
    @PostMapping("/tripvibe/signout")
    public void logout(HttpSession session) {
        System.out.println(session.getAttribute("member"));
        session.invalidate();
    }

    //로그인 상태 확인
    @GetMapping("/tripvibe/checksession")
    public Map<String, Object> checkSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        MemberDTO member = (MemberDTO) session.getAttribute("member");
        System.out.println("checkSession==>"+member);
        if (member != null) { //로그인 상태
            response.put("memberInfo", member.getId());
            response.put("status", "success");
            return response;
        } else {
            response.put("status", "fail");
            return response;
        }
    }

    ////////////마이페이지/////////////

    //회원 조회
    @GetMapping("/tripvibe/mypage")
    public MemberDTO getMemberOne(HttpSession session) {
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");
        if (loginMember != null) {
            return memberService.getMemberOne(loginMember.getId());
        } else {
            throw new IllegalStateException("접근불가");
        }
    }

    //회원 정보 수정
    @PutMapping("/tripvibe/mypage")
    public void updateMember(@RequestParam Long id, @RequestParam MultipartFile img, @RequestParam("member") String stringMember) throws Exception {
        memberService.updateMember(id, img, stringMember);
    }

    // 회원 탈퇴
    @DeleteMapping("/tripvibe/mypage")
    public void deleteMember(@RequestParam Long id, HttpSession session) {
        // 현재 로그인한 회원의 정보를 가져옴
        MemberDTO loginMember = (MemberDTO) session.getAttribute("member");

        // 만약 현재 로그인한 회원의 ID와 삭제하려는 회원의 ID가 일치한다면 탈퇴 처리
        if (loginMember != null && loginMember.getId().equals(id)) {
            memberService.deleteMember(id);
            session.invalidate();
        } else {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
    }

}