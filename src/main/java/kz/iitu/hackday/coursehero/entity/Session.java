package kz.iitu.hackday.coursehero.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    @NotNull
    private Long userId;
    @NotNull
    private String email;
    @Column(name = "session_token", unique = true)
    @NotNull
    private String token;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @Temporal(TemporalType.TIMESTAMP)
    private Date closed;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
    private Long stateId;
    private String context;
    private String locale;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="stateId", referencedColumnName = "id", insertable = false, updatable = false)
    private SessionState state;
}
