package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import aiu.edu.kg.FaceRecognation.enums.Gender;
import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "person")
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String groupName;
    private String faculty;
    private String fileName;

    @Column(columnDefinition="blob")
    private String face_encodings;

    @Column(name = "person_position", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonPosition personPosition;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "removed_date")
    public Date removedDate;

    public String getImage(){
        return "/image/"+fileName;
    }

    public Person(String surname, String name, String patronymic, String groupName, String faculty, PersonPosition personPosition, Gender gender, User user) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.groupName = groupName;
        this.faculty = faculty;
        this.personPosition = personPosition;
        this.gender = gender;
        this.user = user;
    }
}
