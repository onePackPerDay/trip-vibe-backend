package tripvibe.tripvibebe.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tripvibe.tripvibebe.dto.ReviewDTO;
import tripvibe.tripvibebe.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

   //리뷰 목록 (main)
    @Transactional(readOnly = true)
    public List<ReviewDTO> getReviews() {
        //모든 리뷰 데이터를 가져와서 dto로 변환 후, 리스트로 만들어서 반환
        return reviewRepository.findAll()
                .stream()
                .map(review -> new ReviewDTO(review))
                .collect(Collectors.toList());
    }

}
