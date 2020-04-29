package kz.iitu.hackday.coursehero.entity;

import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    @NotNull private Long userId;
    private String text;
    @Column(name = "course_id")
    @NotNull private Long courseId;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;
    @Enumerated(EnumType.STRING)
    private FeedbackType type;
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

}
