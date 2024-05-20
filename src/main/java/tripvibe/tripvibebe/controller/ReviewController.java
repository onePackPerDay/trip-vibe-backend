package tripvibe.tripvibebe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tripvibe.tripvibebe.dto.ReviewDTO;
import tripvibe.tripvibebe.service.ReviewService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 목록 조회 (main)
    @GetMapping("/tripvibe/reviewlist")
    public List<ReviewDTO> getReviews() {
        return reviewService.getReviews();
    }

    //리뷰 1개 조회
    @GetMapping("/tripvibe/review/detail/{id}")
    public ReviewDTO getReviewOne(@PathVariable Long id){
        return reviewService.getReviewOne(id);
    }

    //리뷰 등록
    @PostMapping("/tripvibe/review")
    public void saveReview(@RequestBody ReviewDTO dto){
        reviewService.saveReview(dto);
    }

    //리뷰 수정
    @PutMapping("/trip/vibe/review/detail/{id}")
    public void updateReview(@PathVariable Long id, @RequestBody ReviewDTO dto){
       reviewService.updateReview(id, dto);
    }

    //리뷰 삭제
    @DeleteMapping("/tripvibe/review/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }

}
