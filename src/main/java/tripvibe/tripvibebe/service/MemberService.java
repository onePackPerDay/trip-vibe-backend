package tripvibe.tripvibebe.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tripvibe.tripvibebe.domain.Member;
import tripvibe.tripvibebe.domain.Review;
import tripvibe.tripvibebe.dto.LoginDTO;
import tripvibe.tripvibebe.dto.MemberDTO;
import tripvibe.tripvibebe.dto.ReviewDTO;
import tripvibe.tripvibebe.repository.MemberRepository;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService{

    private final MemberRepository memberRepository;

    //회원 정보 수정
    @Transactional
    public void updateMember(Long id, MultipartFile img, String stringMember) throws Exception {
        //1. 전달받은 id에 맞는 member가 있는 지 확인
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        //2. 전달받은 문자열 Json을 MemberDTO 객체로 매핑
        MemberDTO dto = new ObjectMapper().readValue(stringMember, MemberDTO.class);

        //3. 새 이미지 서버에 저장
        String path = "C:/fullstack/image/";
        String originalImgName = img.getOriginalFilename(); //파일 원본 이름
        String extension = originalImgName.substring(originalImgName.indexOf(".")); //확장자
        String newImgName = UUID.randomUUID().toString() + extension; //서버에 저장할 새 파일 이름
        img.transferTo(new File(path+newImgName)); //지정된 경로를 가진 File 객체 생성

        //4. member수정
        member.setImgName(newImgName);
        member.setPw(dto.getPw());
        member.setEmail(dto.getEmail());
        member.setPhone(dto.getPhone());
        member.setMbti(dto.getMbti());
        member.setGender(dto.getGender());
    }

    //회원 1명 조회
    @Transactional
    public MemberDTO getMemberOne(Long id) { //고유 번호로 조회
        Member member = memberRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new MemberDTO(member);
    }

    //회원 가입
    @Transactional
    public void joinMember(MemberDTO dto) {
        Member member = Member.builder() // builder를 통해 entity화 함
                .memberId(dto.getMemberId())
                .pw(dto.getPw())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .birth(dto.getBirth())
                .gender(dto.getGender())
                .mbti(dto.getMbti())
                .build();
        if (dto.getPw() == null) {
            throw new IllegalStateException("비밀번호 필수");
        }
        memberRepository.save(member);
    }

    @Transactional
    public MemberDTO signIn(LoginDTO dto) {
        // 데이터베이스에서 memberId를 가진 사용자 찾기
        Member member = memberRepository.findByMemberId(dto.getMemberId());

        // 사용자가 존재하고 비밀번호가 일치하는지 확인
        if (member != null && member.getPw().equals(dto.getPw())) {
            return new MemberDTO(member);
        }
        return null;
    }

}