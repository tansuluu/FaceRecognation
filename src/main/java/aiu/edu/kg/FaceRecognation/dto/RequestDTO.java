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
public class RequestDTO {

    private String title;
    private FileType fileType;
    private Gender gender;
    private PersonPosition personPosition;
    private List<MultipartFile> files;
    private String username;
}
