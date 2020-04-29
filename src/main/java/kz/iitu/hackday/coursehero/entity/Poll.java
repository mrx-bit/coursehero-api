package kz.iitu.hackday.coursehero.entity;

import kz.iitu.hackday.coursehero.entity.enums.PollType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int orderVal;
    @NotNull private String text;
    @Enumerated(EnumType.STRING)
    private PollType type;
    @ColumnDefault("true")
    private Boolean isActive;
}
