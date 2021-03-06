package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import aiu.edu.kg.FaceRecognation.enums.FileType;
import aiu.edu.kg.FaceRecognation.enums.Gender;
import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "request")
@NoArgsConstructor
public class Request extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StageStatus status;

    @Column(name = "file_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FileType fileType;

    @Column(name = "person_position")
    @Enumerated(EnumType.STRING)
    private PersonPosition personPosition;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private int sent=0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Request(String title, FileType fileType, PersonPosition personPosition, Gender gender, User user) {
        this.title = title;
        this.fileType = fileType;
        this.personPosition = personPosition;
        this.gender = gender;
        this.user = user;
    }
}
