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

    @Column(nullable = false)
    private  String username;

    @Column(nullable = false)
    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updatePw(String pw) {
        this.pw = pw;
    }

    public void updatePhone(String phone) {
        this.phone = phone;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateMbti(String mbti) {
        this.mbti = mbti;
    }

    public void updateGender(String gender) {
        this.gender = gender;
    }
}
