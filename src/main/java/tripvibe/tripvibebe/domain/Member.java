package tripvibe.tripvibebe.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private String gender;

    private String mbti;

    private String imgName;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();


}