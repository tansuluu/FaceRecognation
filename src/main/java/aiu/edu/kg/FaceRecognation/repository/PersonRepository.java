package aiu.edu.kg.FaceRecognation.repository;

import aiu.edu.kg.FaceRecognation.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findAllByRemovedDateIsNull();
}
