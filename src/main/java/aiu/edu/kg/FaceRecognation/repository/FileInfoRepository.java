package aiu.edu.kg.FaceRecognation.repository;

import aiu.edu.kg.FaceRecognation.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

}
