package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

}
