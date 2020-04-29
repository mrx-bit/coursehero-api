package kz.iitu.hackday.coursehero.entity;

import kz.iitu.hackday.coursehero.entity.enums.AssignmentType;
import kz.iitu.hackday.coursehero.entity.enums.FeedbackType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class LessonAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lesson_id")
    @NotNull
    private Long lessonId;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;
    private int orderVal;
    @Enumerated(EnumType.STRING)
    private AssignmentType type;
    private String rawText;
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "lesson_id", insertable = false, updatable = false)
    private Lesson lesson;

}
