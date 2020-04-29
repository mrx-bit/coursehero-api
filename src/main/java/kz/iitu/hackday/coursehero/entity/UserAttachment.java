package kz.iitu.hackday.coursehero.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kz.iitu.hackday.coursehero.entity.enums.AttachmentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user_document_attachment")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class UserAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long userId;
    @Column(name = "user_assignment_answer_id")
    private Long userAssignmentAnswerId;
    @Enumerated(EnumType.STRING)
    private AttachmentType type;
    private String filename;
    @Column(name = "mimetype")
    private String mimeType;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date uploaded;
    @JsonIgnore
    @Column(name="content", columnDefinition = "mediumblob")
    private byte[] content;
    private Boolean deleted;
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_assignment_answer_id", insertable = false, updatable = false)
    @JsonIgnore
    private UserAssignmentAnswer userAssignmentAnswer;
}
