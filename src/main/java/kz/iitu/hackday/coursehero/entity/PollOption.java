package kz.iitu.hackday.coursehero.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class PollOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;

    @Column(name = "poll_id")
    @NotNull
    private Long pollId;
    @ColumnDefault("true")
    private Boolean isActive;

//    @ManyToOne
//    @JoinColumn(name = "poll_id", insertable = false, updatable = false)
//    private Poll poll;
}
