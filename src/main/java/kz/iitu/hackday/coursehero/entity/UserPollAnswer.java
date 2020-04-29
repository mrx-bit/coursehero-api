package kz.iitu.hackday.coursehero.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_poll_answer", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "poll_id"})})
@Data
@EqualsAndHashCode(of = "id")
public class UserPollAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "poll_id")
    @NotNull
    private Long pollId;
    @Column(name = "poll_option_id")
    @NotNull
    private Long pollOptionId;
    private String textAnswer;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "poll_id", insertable = false, updatable = false)
    private Poll poll;
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name = "poll_option_id", insertable = false, updatable = false)
    private PollOption pollOption;

}
