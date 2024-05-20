package tripvibe.tripvibebe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import tripvibe.tripvibebe.dto.MemberFormDTO;
import tripvibe.tripvibebe.role.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 값을 자동으로 생성
    @Column(name = "member_id")
    private  Long id;

    @Column(nullable = false) // 변경 @NotNull
    private String username;

    @Column(nullable = false) // 변경 @NotNull
    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;

    // 추가
    @Enumerated(EnumType.STRING)
    private Role role; // USER, ADMIN

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setUsername(memberFormDTO.getUsername());
        member.setPw(passwordEncoder.encode(memberFormDTO.getPw()));
        member.setEmail(memberFormDTO.getEmail());
        member.setPhone(memberFormDTO.getPhone());
        member.setBirth(memberFormDTO.getBirth());
        member.setGender(memberFormDTO.getGender());
        member.setMbti(memberFormDTO.getMbti());
        member.setRole(Role.USER);

        return member;
    }

}
