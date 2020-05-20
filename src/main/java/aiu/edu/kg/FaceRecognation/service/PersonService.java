package aiu.edu.kg.FaceRecognation.service;

import aiu.edu.kg.FaceRecognation.dto.PersonDTO;
import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.enums.ResultDetail;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
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

    @Autowired
    private UserService userService;

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
            personExist.setGender(person.getGender());
            if (!file.isEmpty()){
                String fileName = FILE+person.getId()+ "." + FilenameUtils.getExtension(file.getOriginalFilename());
                storageService.store(file, fileName);
                personExist.setFileName(fileName);
                personExist.setFace_encodings(null);
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

    public ResponseMessage<Long> validateAndSave(PersonDTO personDTO){
        ResponseMessage<Long> responseMessage = new ResponseMessage<>(ResultCode.FAIL);
        User user = userService.findByUsername(personDTO.getUsername());
        if (user==null){
            responseMessage.setDetailCode(ResultDetail.USER_NOT_FOUND);
            return responseMessage;
        }
        else if (personDTO.getFile()==null || personDTO.getFile().isEmpty()){
            responseMessage.setDetailCode(ResultDetail.FILES_ARE_EMPTY);
            return responseMessage;
        }
        else if(personDTO.getName() == null){
            responseMessage.setDetailCode(ResultDetail.NAME_IS_EMPTY);
            return responseMessage;
        }
        else if(personDTO.getSurname() == null){
            responseMessage.setDetailCode(ResultDetail.SURNAME_IS_EMPTY);
            return responseMessage;
        }
        else if(personDTO.getPersonPosition() == null){
            responseMessage.setDetailCode(ResultDetail.POSITION_IS_EMPTY);
            return responseMessage;
        }
        else if(personDTO.getGender() == null){
            responseMessage.setDetailCode(ResultDetail.GENDER_IS_EMPTY);
            return responseMessage;
        }
        else{
            Person person = new Person(personDTO.getSurname(),personDTO.getName(),personDTO.getPatronymic(), personDTO.getGroupName(),
                    personDTO.getFaculty(), personDTO.getPersonPosition(), personDTO.getGender(),user);
            person = personRepository.save(person);
            String fileName = FILE+person.getId()+ "." + FilenameUtils.getExtension(personDTO.getFile().getOriginalFilename());
            storageService.store(personDTO.getFile(), fileName);
            person.setFileName(fileName);
            this.personRepository.save(person);
            responseMessage.setResult(person.getId());
            responseMessage.setDetailCode(ResultDetail.OK);
            responseMessage.setResultCode(ResultCode.SUCCESS);
            return responseMessage;
        }
    }

}
