package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "request_process")
@NoArgsConstructor
public class RequestProcess extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @Column(name = "file_name")
    private String fileName;

    @OneToMany(mappedBy="requestProcess", cascade=CascadeType.ALL)
    private List<RequestResult> requestResults;

    public RequestProcess(Request request) {
        this.request = request;
    }

    public String getImage(){
        return "/image/"+fileName;
    }}


