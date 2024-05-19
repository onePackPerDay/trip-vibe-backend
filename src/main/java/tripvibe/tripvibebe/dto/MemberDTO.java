package tripvibe.tripvibebe.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDTO {
    private  Long id;

    private  String username;

    private String pw;

    private String email;

    private String phone;

    private LocalDate birth;

    private String gender;

    private String mbti;
}
