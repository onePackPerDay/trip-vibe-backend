package tripvibe.tripvibebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.dto.ResponseDTO;
import tripvibe.tripvibebe.dto.SignInDTO;
import tripvibe.tripvibebe.dto.SignInResponseDTO;
import tripvibe.tripvibebe.dto.SignUpDTO;
import tripvibe.tripvibebe.service.AuthService;

//==login test==//
@RestController
@RequestMapping("/tripvibe")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseDTO<?> signUp(@RequestBody SignUpDTO memberDTO) {
        System.out.println("memberDTO.toString() =========== " + memberDTO.toString());

        authService.joinMember(memberDTO);
        return authService.signUp(memberDTO);
    }

    @PostMapping("/signin")
    public ResponseDTO<?> signIn(@RequestBody SignInDTO signInDTO) {
        ResponseDTO<?> result = authService.signIn(signInDTO);

        return result;
    }

    // Response 결과에 따라 Header에 Token 설정
//    private ResponseEntity<?> setToken(ResponseDTO<?> result) {
//        // 요청이 성공인 경우
//        if(result.getResult()) {
//            // reulst -> data -> token 추출
//            SignInResponseDTO signInResponse = (SignInResponseDTO) result.getData();
//
//            // Header에 Auth에 Token 지정, Body에는 result 그대로 작성. result 내의 token은 제거해도 될듯
//            return ResponseEntity.ok()
//                    .header("Authorization", "Bearer " + signInResponse.getToken())
//                    .body(result);
//        } else {
//            return ResponseEntity.ok().body(result);
//        }
//    }
}
