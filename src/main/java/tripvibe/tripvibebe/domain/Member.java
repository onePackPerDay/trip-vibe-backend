package tripvibe.tripvibebe.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 값을 자동으로 생성
    @Column(name = "account_num")
    private  Long id;

    @Column(nullable = false, unique = true) // 변경 @NotEmpty
    private String memberId;

    @Column(nullable = false) // 변경 @NotEmpty
    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


    // 새거
//    public static Member createMember(MemberFormDTO memberFormDTO, PasswordEncoder passwordEncoder) {
//        Member member = new Member();
//
//        member.setMemberId(memberFormDTO.getMemberId());
//        member.setPw(passwordEncoder.encode(memberFormDTO.getPw()));
//        member.setEmail(memberFormDTO.getEmail());
//        member.setPhone(memberFormDTO.getPhone());
//        member.setBirth(memberFormDTO.getBirth());
//        member.setGender(memberFormDTO.getGender());
//        member.setMbti(memberFormDTO.getMbti());
//
//        return member;
//    }


}