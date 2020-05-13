package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "file_info")
@NoArgsConstructor
public class FileInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private RequestResult requestResult;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    public FileInfo(Request request, String fileSize, String fileType) {
        this.request = request;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public String getImage(){
        return "/image/"+fileName;
    }}


