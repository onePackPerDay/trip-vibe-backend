package tripvibe.tripvibebe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tripvibe.tripvibebe.domain.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInResponseDTO {

    private Member member;

    private String token;

    private int expirationTime; // 로그인 토큰 만료시간(초)
}
