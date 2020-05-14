package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.repository.PersonRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class PersonService {

    private final String FILE = "person_";

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StorageService storageService;

    public List<Person> findAll(){
        return personRepository.findAllByRemovedDateIsNull();
    }

    public Person get(Long id){
        return personRepository.getOne(id);
    }

    public void delete(Long id){
       Person person = this.personRepository.getOne(id);
       person.setRemovedDate(new Date());
       this.personRepository.save(person);
    }

    public void save(Person person, MultipartFile file){
        if (person.getId()!=null){
            Person personExist = this.personRepository.getOne(person.getId());
            personExist.setName(person.getName());
            personExist.setSurname(person.getSurname());
            personExist.setPatronymic(person.getPatronymic());
            personExist.setPersonPosition(person.getPersonPosition());
            personExist.setFaculty(person.getFaculty());
            if (!file.isEmpty()){
                String fileName = FILE+person.getId()+ "." + FilenameUtils.getExtension(file.getOriginalFilename());
                storageService.store(file, fileName);
                personExist.setFileName(fileName);
            }
            this.personRepository.save(personExist);
        }else {
            person.setCreatedDate(new Date());
            person = this.personRepository.saveAndFlush(person);
            String fileName = FILE + person.getId() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            storageService.store(file, fileName);
            person.setFileName(fileName);
            personRepository.save(person);
        }
    }

}
