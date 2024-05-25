package tripvibe.tripvibebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tripvibe.tripvibebe.dto.ReviewDTO;
import tripvibe.tripvibebe.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 목록 조회 (main)
    @GetMapping("/tripvibe/reviewlist")
    public List<ReviewDTO> getReviewList() {
        return reviewService.getReviewList();
    }

    //사용자가 쓴 리뷰 목록 조회(mypage)
    @GetMapping("/tripvibe/myreviewlist")
    public List<ReviewDTO> getMyReviewList(@RequestParam Long id) {
        return reviewService.getMyReviewList(id);
    }

    //리뷰 상세 조회
    @GetMapping("/tripvibe/review/detail")
    public ReviewDTO getReviewOne(@RequestParam Long id){
        return reviewService.getReviewOne(id);
    }

    //리뷰 등록
    @PostMapping("/tripvibe/review")
    public void saveReview(@RequestParam Long id, @RequestParam MultipartFile img, @RequestParam("review") String stringReview) throws Exception {
        reviewService.saveReview(id, img, stringReview);
    }

    //리뷰 수정
    @PutMapping("/tripvibe/review/detail")
    public void updateReview(@RequestParam Long id, @RequestParam MultipartFile img, @RequestParam("review") String stringReview) throws Exception {
       reviewService.updateReview(id, img, stringReview);
    }

    //리뷰 삭제
    @DeleteMapping("/tripvibe/review")
    public void deleteReview(@RequestParam Long id) {
        reviewService.deleteReview(id);
    }

}
