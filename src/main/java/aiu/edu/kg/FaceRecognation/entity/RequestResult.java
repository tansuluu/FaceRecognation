package aiu.edu.kg.FaceRecognation.entity;


import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "request_result")
public class RequestResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double percentage;

    private String fileName;

    @ManyToOne
    @JoinColumn(name = "request_process_id")
    private RequestProcess requestProcess;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

}
