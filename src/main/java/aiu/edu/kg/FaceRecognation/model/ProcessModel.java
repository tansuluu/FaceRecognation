package aiu.edu.kg.FaceRecognation.model;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.RequestResult;
import aiu.edu.kg.FaceRecognation.enums.Gender;
import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Getter
@Setter
public class ProcessModel {

    private String imageUrl;
    private double percentage;
    private String name;
    private String surname;
    private String patronymic;
    private String faculty;
    private String groupName;
    private PersonPosition personPosition;
    private Gender gender;

    public ProcessModel(RequestResult request) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath() .path("/image/").path(request.getFileName()).toUriString();
        this.imageUrl = fileDownloadUri;
        this.percentage = request.getPercentage();
        this.name = request.getPerson().getName();
        this.surname = request.getPerson().getSurname();
        this.patronymic = request.getPerson().getPatronymic();
        this.faculty = request.getPerson().getFaculty();
        this.groupName = request.getPerson().getGroupName();
        this.personPosition = request.getPerson().getPersonPosition();
        this.gender =  request.getPerson().getGender();
    }
}
