package aiu.edu.kg.FaceRecognation.dto;

import aiu.edu.kg.FaceRecognation.enums.FileType;
import aiu.edu.kg.FaceRecognation.enums.Gender;
import aiu.edu.kg.FaceRecognation.enums.PersonPosition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String surname;
    private String name;
    private String patronymic;
    private String groupName;
    private String faculty;
    private Gender gender;
    private PersonPosition personPosition;
    private MultipartFile file;
    private String username;
}
