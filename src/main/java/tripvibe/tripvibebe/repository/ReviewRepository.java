package tripvibe.tripvibebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripvibe.tripvibebe.domain.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMemberId(Long id);
}
