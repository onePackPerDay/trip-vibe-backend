//package tripvibe.tripvibebe.security;
//
//import com.nimbusds.jose.*;
//import com.nimbusds.jose.crypto.MACSigner;
//import com.nimbusds.jose.crypto.MACVerifier;
//import com.nimbusds.jwt.JWTClaimsSet;
//import com.nimbusds.jwt.SignedJWT;
//import lombok.Setter;
//import org.springframework.stereotype.Service;
//
//import java.security.SecureRandom;
//import java.time.Instant;
//import java.util.Base64;
//import java.util.Date;
//
//@Service
//@Setter
//public class TokenProvider {
//
//    private static final String SECURITY_KEY; // 암호화된 JWT
//
//    static{
//        SecureRandom random = new SecureRandom();
//        byte[] bytes = new byte[64];
//        random.nextBytes(bytes);
//        SECURITY_KEY = Base64.getEncoder().encodeToString(bytes);
//    }
//
//    // jwt 생성 메서드 : MemberId와 만료시간을 받아 JWT를 생성하고 서명한 후 직렬화된 JWT문자열을 반환함
//    public String createJwt(String memberId, int duration) { // duration : JWT만료시간(초)
//
//        try {
//            // 현재 시간 기준으로 1시간 뒤를 만료시간으로 설정함
//            Instant now = Instant.now(); // 현재시간
//            Instant expirationTime = now.plusSeconds(duration); // 현재시간 + duration(만료시간)
//
//            // JWT 클레임 : 내용설정
//            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
//                    .subject(memberId) // 'sub' 클레임에 사용자 아이디 설정
//                    .issueTime(Date.from(now)) // 'iat' 클레임에 발행시간 설정
//                    .expirationTime(Date.from(expirationTime)) // 'exp' 클레임에 만료시간 설정
//                    .build();
//
//            // JWT 서명 : HS256 알고리즘을 사용하여 JWT를 서명할 준비를 함, 헤더 설정
//            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
//
//
//            // HMAC 서명을 사용하여 JWT를 서명함
//            JWSSigner signer = new MACSigner(SECURITY_KEY.getBytes()); // HMAC 서명 생성
//            signedJWT.sign(signer);
//
//            return signedJWT.serialize();
//        } catch (JOSEException e) {
//            return null;
//        }
//    }
//
//    // JWT 검증 : JWT문자열을 받아 서명을 검증하고 유효한 경우 subject 클레임(memberId)을 반환
//    public String validateJwt(String token) {
//        try {
//            // 서명 확인을 통해 JWT를 검증함
//            SignedJWT signedJWT = SignedJWT.parse(token); // 문자열로된 JWT를 파싱하여 객체로 변환
//            JWSVerifier verifier = new MACVerifier(SECURITY_KEY.getBytes()); // HMAC 검증기 생성
//
//            // JWT 서명 검증
//            if (signedJWT.verify(verifier)) { // 서명이 유효하면
//                return signedJWT.getJWTClaimsSet().getSubject(); // JWT의 'subject' 클레임값(memberId) 반환
//            } else { // 서명이 유효하지 않은 경우
//                return null;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//
//}
