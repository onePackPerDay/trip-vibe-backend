package tripvibe.tripvibebe.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import tripvibe.tripvibebe.domain.Review;

import java.time.LocalDate;

public class ReviewDTO {

    private  Long id;

    private  String title;

    private byte[] img;

    private String content;

    private int rating;

    private LocalDate createdDate;

    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.img = review.getImg();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdDate = review.getCreatedDate();
    }
}
