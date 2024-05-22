package tripvibe.tripvibebe.dto;


import jakarta.persistence.Table;
import lombok.*;
import tripvibe.tripvibebe.domain.Member;

import java.time.LocalDate;

//==login test==//
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member")
public class TestDTO {
    private  Long id;

    private  String memberId;

    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;

    private String token;


    public TestDTO(SignUpDTO dto) {
        this.id = dto.getId();
        this.memberId = dto.getMemberId();
        this.pw = dto.getPw();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
        this.birth = dto.getBirth();
        this.gender = dto.getGender();
        this.mbti = dto.getMbti();
        this.token = "";
    }

}
