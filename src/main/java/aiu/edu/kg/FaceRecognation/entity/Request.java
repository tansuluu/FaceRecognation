package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "request")
public class Request extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private StageStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



}