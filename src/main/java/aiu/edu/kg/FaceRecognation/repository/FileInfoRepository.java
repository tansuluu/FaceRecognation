package aiu.edu.kg.FaceRecognation.repository;

import aiu.edu.kg.FaceRecognation.entity.RequestProcess;
import aiu.edu.kg.FaceRecognation.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoRepository extends JpaRepository<RequestProcess, Long> {

    List<RequestProcess> findAllByRequest(Request request);
}
