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

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    private String file_name;
    private String fullName;
    private String groupClass;

    private double percentage;

    public String getImage(){
        return "/image/"+file_name;
    }
}
