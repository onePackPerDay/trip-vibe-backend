package tripvibe.tripvibebe.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    //회원 정보 수정(고유 번호로 회원 정보 불러오기) 엔드포인트
    @PutMapping("/tripvibe/mypage/edit/{id}")
    public void updateMember(@PathVariable Long id, @RequestParam("member") String stringMember) throws Exception {
        memberService.updateMember(id, stringMember);
    }

    //회원 1명 조회 (고유 번호로 마이페이지 불러오기)
    @GetMapping("/tripvibe/mypage/{id}")
    public MemberDTO getMemberOne(@PathVariable Long id) {
        return memberService.getMemberOne(id);
    }

    // 회원가입
    @PostMapping("/tripvibe/signup")
    public void joinMember(@RequestBody MemberDTO dto) {
        memberService.joinMember(dto);
    }

//    @GetMapping("/tripvibe/signin")
//    public void loginTest() { // loginTest -> ??? 바꿔야함
//        Optional<Member> member = memberRepository.findByMemberId("moon11"); // 테스트 추후 삭제
//        System.out.println("아이디 정보떠야함 제발 : " + member.get().getMemberId()); // 테스트 추후 삭제
//    }

//    @GetMapping("/tripvibe/getmember")  //클라이언트로부터 JWT 토큰을 받아서 해당 토큰에 담겨 있는 사용자 정보를 추출하여 반환, 회원 정보 같은거 볼때
//    public ResponseEntity<Object> getMember(HttpServletRequest request){
//        try {
//            String token = request.getHeader("jwt-auth-token");
//            Map<String, Object> tokenInfoMap = jwtService.getInfo(token); //JWT 토큰을 파싱하여 사용자 정보를 포함한 맵을 반환
//
//            Member member = new ObjectMapper().convertValue(tokenInfoMap.get("member"), Member.class); //JWT 토큰에서 추출된 사용자 정보를 Member 객체로 변환
//
//            return new ResponseEntity<Object>(member, HttpStatus.OK); //변환된 Member 객체를 ResponseEntity에 담아서 HTTP 응답으로 반환
//
//        } catch(Exception e) {
//            return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
//        }
//    }

//    @PostMapping("/excludepath/signin") //로그인에 성공하면 JWT 토큰을 생성하여 클라이언트에게 전달
//    public ResponseEntity<Object> login(@RequestParam String memberId, @RequestParam String pw, HttpServletResponse response) { //클라이언트에서 받아온 정보를(id,pw) dto에
//        ResponseEntity<Object> loginResponse = memberService.login(memberId,pw, response);
//        if (loginResponse.getStatusCode() == HttpStatus.OK) {
//            return new ResponseEntity<>("Login success", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(loginResponse.getBody().toString(), loginResponse.getStatusCode());
//        }
//    }

    //로그인
    @PostMapping("/tripvibe/login")
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
    @PostMapping("/tripvibe/logout")
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
