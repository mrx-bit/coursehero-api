package kz.iitu.hackday.coursehero.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String avatarUrl;
    @NotNull private String title;
    @Column(length = 500)
    private String shortRawDescription;
    @Column(length = 5000)
    private String fullRawDescription;
    @Column(name = "organization_id")
    private Long organizationId;
    @Column(name = "author_id")
    @NotNull
    private Long authorId;
    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private User author;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createDate;
    @ColumnDefault("true")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "organization_id", insertable = false, updatable = false)
    private Organization organization;

    @ManyToMany
    @JoinTable(name = "course_category",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "course_tag",
            joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private List<Tag> tags;

}
