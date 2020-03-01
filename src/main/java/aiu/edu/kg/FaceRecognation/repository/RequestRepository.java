package aiu.edu.kg.FaceRecognation.repository;

import aiu.edu.kg.FaceRecognation.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository  extends JpaRepository<Request, Long> {
}
