package aiu.edu.kg.FaceRecognation.model;

import aiu.edu.kg.FaceRecognation.enums.StageStatus;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestResponse {
    private String title;
    private StageStatus status;
    List<ProcessModel> result;

    public RequestResponse(String title, StageStatus status) {
        this.title = title;
        this.status = status;
    }
}
