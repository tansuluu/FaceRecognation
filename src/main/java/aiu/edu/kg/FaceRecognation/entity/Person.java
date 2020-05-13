package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String groupName;
    private String faculty;
    private String fileName;
    @Column(name = "person_position", nullable = false)
    @Enumerated(EnumType.STRING)
    private PersonPosition personPosition;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public String getImage(){
        return "/image/"+fileName;
    }

}
