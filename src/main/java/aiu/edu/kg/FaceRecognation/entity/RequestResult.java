package aiu.edu.kg.FaceRecognation.entity;


import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "request_result")
public class RequestResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double percentage;

    @OneToMany
    @JoinColumn(name = "people", nullable = false)
    private List<Person> people;

}
