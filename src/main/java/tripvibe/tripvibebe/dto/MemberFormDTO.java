package tripvibe.tripvibebe.dto;
// 회원가입 / 로그인 DTO

import lombok.Getter;
import lombok.Setter;
import tripvibe.tripvibebe.role.Role;

import java.time.LocalDate;

@Getter
@Setter
public class MemberFormDTO {

    // MemberFormDTO : 회원가입 화면에서 넘어온 가입 정보를 담는 객체
    private String username;

    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;


}
