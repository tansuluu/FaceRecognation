package aiu.edu.kg.FaceRecognation.model;

import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.entity.RequestResult;
import aiu.edu.kg.FaceRecognation.enums.Gender;
import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Getter
@Setter
public class PersonModel {

    private String imageUrl;
    private String name;
    private String surname;
    private String patronymic;
    private String faculty;
    private String groupName;
    private PersonPosition personPosition;
    private Gender gender;

    public PersonModel(Person request) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath() .path("/image/").path(request.getFileName()).toUriString();
        this.imageUrl = fileDownloadUri;
        this.name = request.getName();
        this.surname = request.getSurname();
        this.patronymic = request.getPatronymic();
        this.faculty = request.getFaculty();
        this.groupName = request.getGroupName();
        this.personPosition = request.getPersonPosition();
        this.gender =  request.getGender();
    }
}
