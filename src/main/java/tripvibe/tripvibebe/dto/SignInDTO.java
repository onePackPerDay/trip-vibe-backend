package tripvibe.tripvibebe.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInDTO {

    @NotBlank
    private String memberId;

    @NotBlank
    private String pw;
}
