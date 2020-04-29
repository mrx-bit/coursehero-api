package kz.iitu.hackday.coursehero.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatarUrl;
    private int orderVal;
    @NotNull private String title;
    @Column(length = 5000)
    private String rawText;
    @Column(name = "course_id")
    @NotNull
    private Long courseId;
    private Double avgRating;
    @ColumnDefault("0")
    private int likesCount;
    @ColumnDefault("0")
    private int dislikesCount;
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;
}
