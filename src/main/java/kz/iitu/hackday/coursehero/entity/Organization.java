package kz.iitu.hackday.coursehero.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatarUrl;
    @NotNull private String name;
    private String address;
    @ColumnDefault("true")
    private Boolean isActive;
}
