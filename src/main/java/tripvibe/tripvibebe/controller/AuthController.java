package tripvibe.tripvibebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.dto.ResponseDTO;
import tripvibe.tripvibebe.dto.SignInDTO;
import tripvibe.tripvibebe.dto.SignUpDTO;
import tripvibe.tripvibebe.service.AuthService;

//==login test==//
@RestController
@RequestMapping("/tripvibe")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseDTO<?> signUp(@RequestBody SignUpDTO signUpDTO) {
        System.out.println("memberDTO.toString() =========== " + signUpDTO.toString());

        authService.joinMember(signUpDTO);
        return authService.signUp(signUpDTO);
    }

    @PostMapping("/signin")
    public ResponseDTO<?> signIn(@RequestBody SignInDTO signInDTO) {
        ResponseDTO<?> result = authService.signIn(signInDTO);
        System.out.println("result ============ " + result.toString());

        return result;
    }


}
