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

    //사용자가 쓴 리뷰 목록 조회
    @GetMapping("/tripvibe/myreviewlist/{id}")
    public List<ReviewDTO> getMyReviewList(@PathVariable Long id) {
        return reviewService.getMyReviewList(id);
    }

    //리뷰 1개 조회
    @GetMapping("/tripvibe/review/detail/{id}")
    public ReviewDTO getReviewOne(@PathVariable Long id){
        return reviewService.getReviewOne(id);
    }

    //리뷰 등록
    @PostMapping("/tripvibe/review")
    public void saveReview(@RequestParam Long id, @RequestParam MultipartFile img, @RequestParam("review") String stringReview) throws Exception {
        reviewService.saveReview(id, img, stringReview);
    }

    //리뷰 수정
    @PutMapping("/tripvibe/review/detail/{id}")
    public void updateReview(@PathVariable Long id, @RequestParam MultipartFile img, @RequestParam("review") String stringReview) throws Exception {
       reviewService.updateReview(id, img, stringReview);
    }

    //리뷰 삭제
    @DeleteMapping("/tripvibe/review/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

}
