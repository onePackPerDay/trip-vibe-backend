package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyMemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // form으로 로그인을 받아옴
        // 다음 변수명들이 강제될 수 있음 아닐수도 있고
        // 아이디 : username
        // 비밀번호 : password
        // form action : /login

        Optional<Member> member = memberRepository.findByMemberId(username);

        //==jwt 추가==//
//        Member member1 = member.orElseThrow(() -> new UsernameNotFoundException("해당 유저가 존재하지 않음. username: " + username));

        // 아이디가 DB에 없을 때
        if (member.isEmpty()) {
            throw new UsernameNotFoundException("아이디나 비밀번호가 잘못되었어요"); // 사실 아이디만 잘못된거임
        }
        Member getMember = member.get();

        // 권한
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("유저"));

        // User : Spring Security 구현체임
        // params : 유저아이디(memberId), 비번(pw), 권한정보
        return new User(getMember.getMemberId(), getMember.getPw(),authorities);
        // 이 return문이 Authentication안에 들어감
    }
}

