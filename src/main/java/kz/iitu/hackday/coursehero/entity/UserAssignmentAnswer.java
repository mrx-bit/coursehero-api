package kz.iitu.hackday.coursehero.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class UserAssignmentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull private Long userId;
    @Column(name = "assignment_id")
    private Long assignmentId;
    private String textRawAnswer;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "userAssignmentAnswer")
    private Set<UserAttachment> attachments;

    @ManyToOne
    @JoinColumn(name = "assignment_id", insertable = false, updatable = false)
    private LessonAssignment assignment;

}
