package tripvibe.tripvibebe.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Review {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 값을 자동으로 생성
    @Column(name = "review_id")
    private  Long id;

    @Column(nullable = false)
    private  String title;

    @Lob
    private byte[] img;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private int rating;

    private LocalDate createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
