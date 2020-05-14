package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @ManyToOne
    @JoinColumn(name = "request_result_id")
    private RequestResult requestResult;

    @Column(name = "file_name")
    private String fileName;

    public RequestProcess(Request request) {
        this.request = request;
    }

    public String getImage(){
        return "/image/"+fileName;
    }}


