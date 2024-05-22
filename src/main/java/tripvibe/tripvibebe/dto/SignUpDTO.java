package tripvibe.tripvibebe.dto;


import lombok.*;
import tripvibe.tripvibebe.domain.Member;

import java.time.LocalDate;

//==login test==//
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDTO {

    private Long id;

    private String memberId;

    private String pw;

    private String confirmPw; //추가됨

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;

    private String token; //추가됨

    @Override
    public String toString() {
        return "SignUpDTO{" +
                "id=" + id +
                ", memberId='" + memberId + '\'' +
                ", pw='" + pw + '\'' +
                ", confirmPw='" + confirmPw + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birth=" + birth +
                ", gender='" + gender + '\'' +
                ", mbti='" + mbti + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
