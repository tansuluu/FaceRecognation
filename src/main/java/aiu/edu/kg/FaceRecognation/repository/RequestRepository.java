package aiu.edu.kg.FaceRecognation.repository;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository  extends JpaRepository<Request, Long> {

    List<Request> getAllByUser(User user);
    List<Request> findAllByStatusAndSent(StageStatus status, int sent);
    Optional<Request> findById(Long id);

}
