package tripvibe.tripvibebe.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.dto.MemberFormDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트 후 db 자동 롤백
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private MemberRepository memberRepository;

    // 정상
    @Test
    @DisplayName("회원가입 테스트")
    @Rollback
    public void 회원가입() {
        // given
        Member member = createMember("문");

        // when
        Long join = memberService.join(member);

        // then
        Member savedMember = memberRepository.findById(member.getId()).orElse(null);
        assertNotNull(savedMember);
        assertEquals(member.getUsername(), savedMember.getUsername());
    }

    @Test
    @DisplayName("중복 가입 테스트")
    @Rollback
    public void 중복_회원가입_예외() {
        // given
        Member member1 = createMember("김"); // 회원1
        Member member2 = createMember("김"); // 회원2. 같은 정보 들어있음

        // when
        memberService.join(member1);

        Throwable e = assertThrows(IllegalStateException.class,()->{
            memberService.join(member2);
        });

        // then
        assertEquals("이미 있는 아이디임", e.getMessage());
    }


    public Member createMember(String username) {

        MemberFormDTO memberFormDTO = new MemberFormDTO();

        memberFormDTO.setUsername(username);
        memberFormDTO.setPw("1234");
        memberFormDTO.setEmail("asdf@gmail.com");
        memberFormDTO.setPhone("010-1234-5678");
        memberFormDTO.setBirth(LocalDate.now());
        memberFormDTO.setGender("male");
        memberFormDTO.setMbti("infj");

        return Member.createMember(memberFormDTO, passwordEncoder);
    }

}