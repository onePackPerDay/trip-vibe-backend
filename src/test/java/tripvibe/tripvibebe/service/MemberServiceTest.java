//package tripvibe.tripvibebe.service;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//import tripvibe.tripvibebe.domain.Member;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//class MemberServiceTest {
//
//    @Autowired
//    MemberService memberService;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    public Member createMember() {
//        MemberFormDTO memberFormDTO = new MemberFormDTO();
//
//        memberFormDTO.setUsername("문태준");
//        memberFormDTO.setPw("1234");
//        memberFormDTO.setEmail("asdf@gmail.com");
//        memberFormDTO.setPhone("010-1234-1234");
//        memberFormDTO.setBirth(LocalDate.now());
//        memberFormDTO.setGender("male");
//        memberFormDTO.setMbti("ASDF");
//
//        return Member.createMember(memberFormDTO, passwordEncoder);
//    }
//
//    @Test
//    @DisplayName("회원가입 테스트")
//    public void 회원가입테스트() {
//        Member member = createMember();
//        Member savedMember = memberService.saveMember(member);
//
//        assertEquals(member.getUsername(), savedMember.getUsername());
//        assertEquals(member.getPw(), savedMember.getPw());
//        assertEquals(member.getEmail(), savedMember.getEmail());
//        assertEquals(member.getPhone(), savedMember.getPhone());
//        assertEquals(member.getBirth(), savedMember.getBirth());
//        assertEquals(member.getGender(), savedMember.getGender());
//        assertEquals(member.getMbti(), savedMember.getMbti());
//
//        System.out.println("@@@@@@@@@@@" + savedMember);
//    }
//}