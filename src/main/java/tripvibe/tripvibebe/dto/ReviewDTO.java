package tripvibe.tripvibebe.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tripvibe.tripvibebe.domain.Review;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO {

    private  Long id;

    private  String title;

    private String imgName;

    private String content;

    private int rating;

    private Date createdDate;

    //Review Entity -> ReviewDTO
    public ReviewDTO(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.imgName = review.getImgName();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdDate = review.getCreatedDate();
    }


}
