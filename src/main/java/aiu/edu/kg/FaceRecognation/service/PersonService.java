package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person get(Long id){
        return personRepository.getOne(id);
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }

    public Person save(Person person){
        return personRepository.save(person);
    }

}
