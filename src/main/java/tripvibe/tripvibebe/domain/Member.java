package tripvibe.tripvibebe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

}
