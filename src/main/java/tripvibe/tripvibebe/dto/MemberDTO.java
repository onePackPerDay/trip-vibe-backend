package tripvibe.tripvibebe.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tripvibe.tripvibebe.domain.Member;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {
    private  Long id;

    private  String username;

    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;



    public MemberDTO(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
        this.pw = member.getPw();
        this.email = member.getEmail();
        this.phone = member.getPhone();
        this.birth = member.getBirth();
        this.gender = member.getGender();
        this.mbti = member.getMbti();
    }


}
