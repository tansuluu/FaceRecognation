package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.repository.PersonRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PersonService {

    private final String FILE = "person_";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StorageService storageService;

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person get(Long id){
        return personRepository.getOne(id);
    }

    public void delete(Long id){
        personRepository.deleteById(id);
    }

    public Person save(Person person, MultipartFile file){
        person = this.personRepository.saveAndFlush(person);
        String fileName = FILE+person.getId()+ "." + FilenameUtils.getExtension(file.getOriginalFilename());
        storageService.store(file, fileName);
        person.setFileName(fileName);
        return personRepository.save(person);
    }

}
