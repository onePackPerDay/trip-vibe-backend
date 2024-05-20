package tripvibe.tripvibebe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tripvibe.tripvibebe.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
