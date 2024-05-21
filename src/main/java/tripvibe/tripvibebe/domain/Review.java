package tripvibe.tripvibebe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tripvibe.tripvibebe.dto.ReviewDTO;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 값을 자동으로 생성
    @Column(name = "review_id")
    private  Long id;

    @Column(nullable = false)
    private  String title;

    private String imgName;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

    private Date createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //ReviewDTO -> Review Entity
    public Review(ReviewDTO dto) { //id는 자동 생성
        this.title = dto.getTitle();
        this.imgName = dto.getImgName();
        this.content = dto.getContent();
        this.rating = dto.getRating();
        this.createdDate = dto.getCreatedDate();
    }
}
