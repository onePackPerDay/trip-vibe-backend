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
    @Rollback(value = false)
    public void saveMemberTest() {
        // given
        Member member = createMember();

        // when
        Long join = memberService.join(member);

        // then
        Member savedMember = memberRepository.findById(member.getId()).orElse(null);
        assertNotNull(savedMember);
        assertEquals(member.getUsername(), savedMember.getUsername());
    }


    public Member createMember() {

        MemberFormDTO memberFormDTO = new MemberFormDTO();

        memberFormDTO.setUsername("문태준");
        memberFormDTO.setPw("1234");
        memberFormDTO.setEmail("asdf@gmail.com");
        memberFormDTO.setPhone("010-1234-5678");
        memberFormDTO.setBirth(LocalDate.now());
        memberFormDTO.setGender("male");
        memberFormDTO.setMbti("infj");

        return Member.createMember(memberFormDTO, passwordEncoder);
    }

}