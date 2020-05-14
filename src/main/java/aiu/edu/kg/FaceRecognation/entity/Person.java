package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
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
    private String face_encodings;
    @Column(name = "person_position", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonPosition personPosition;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "request_result_id")
    private RequestResult requestResult;

    @Column(name = "removed_date")
    public Date removedDate;

    public String getImage(){
        return "/image/"+fileName;
    }

}
