package aiu.edu.kg.FaceRecognation.model;

import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.enums.ResultDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseMessage<T> {

    private T result;
    private ResultCode resultCode;
    private ResultDetail detailCode;

    public ResponseMessage(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResponseMessage(T result, ResultCode resultCode, ResultDetail detail) {
        this.result = result;
        this.resultCode = resultCode;
        this.detailCode = detail;
    }

}