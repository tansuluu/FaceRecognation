package aiu.edu.kg.FaceRecognation.entity;

import aiu.edu.kg.FaceRecognation.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "file_info")
public class FileInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @Column(name = "file_size")
    private String fileSize;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "path", nullable = false)
    private String path;
}
