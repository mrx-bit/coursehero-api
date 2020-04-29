package kz.iitu.hackday.coursehero.entity;

import kz.iitu.hackday.coursehero.entity.enums.CategoryType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull private String name;
    @Enumerated(EnumType.STRING)
    @NotNull
    private CategoryType type;
    @Column(name = "parent_id")
    private Long parentId;
    @ColumnDefault("true")
    private Boolean isActive;

    @OneToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private Category parent;
}
