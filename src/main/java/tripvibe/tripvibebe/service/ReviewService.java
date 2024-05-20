package tripvibe.tripvibebe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tripvibe.tripvibebe.domain.Review;
import tripvibe.tripvibebe.dto.ReviewDTO;
import tripvibe.tripvibebe.repository.ReviewRepository;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ObjectMapper jacksonObjectMapper;

    //리뷰 목록 (main)
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviews() {
        //모든 리뷰 데이터를 가져와서 dto로 변환 후, 리스트로 만들어서 반환
        return reviewRepository.findAll()
                .stream()
                .map(review -> new ReviewDTO(review))
                .collect(Collectors.toList());
    }

    //리뷰 1개 조회
    @Transactional(readOnly = true)
    public ReviewDTO getReviewOne(Long id){
        Review review = reviewRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new ReviewDTO(review);
    }

    //리뷰 등록
    @Transactional
    public void saveReview(MultipartFile img, String stringReview) throws Exception {
        Review review = new ObjectMapper().readValue(stringReview, Review.class); //Json 문자열을 Review에 매핑


        //sfsdfsf.txt
        String path = "C:/fullstack/image/"; //이미지를 저장할 서버 주소
        String originalImgName = img.getOriginalFilename(); //파일 원본 이름
        String extension = originalImgName.substring(originalImgName.indexOf(".")); //확장자
        String newImgName = UUID.randomUUID().toString() + extension; //서버에 저장할 새 파일 이름
        img.transferTo(new File(path+newImgName)); //지정된 경로를 가진 File 객체 생성

        review.setImgName(newImgName);
        reviewRepository.save(review);
    }

    //리뷰 수정
    @Transactional
    public void updateReview(Long id, ReviewDTO dto){
        Review review = reviewRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if(dto.getTitle() != null){
            review.setTitle(dto.getTitle());
        }
        if(dto.getContent() != null){
            review.setContent(dto.getContent());
        }
//        if (dto.getImg() != null){
//            review.setImg(dto.getImg());
//        }
        review.setCreatedDate(dto.getCreatedDate());
    }

    //리뷰 삭제
    @Transactional
    public void deleteReview(Long id){
        //1. id에 맞는 리뷰 데이터 찾기 (없으면 에러)
        Review review = reviewRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        //2. 리뷰 삭제
        reviewRepository.delete(review);
    }


}
