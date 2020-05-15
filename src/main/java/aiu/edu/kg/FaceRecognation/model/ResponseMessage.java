package aiu.edu.kg.FaceRecognation.model;


import kg.nurtelecom.api.enums.ResultCode;
import kg.nurtelecom.api.enums.ResultDetail;

public class ResponseMessage<T> {

    private T result;
    private ResultCode resultCode;
    private ResultDetail detailCode;

    public ResponseMessage() {
    }

    public ResponseMessage(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResponseMessage(T result, ResultCode resultCode, ResultDetail detail) {
        this.result = result;
        this.resultCode = resultCode;
        this.detailCode = detail;
    }


    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setResultCode(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public ResultDetail getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(ResultDetail detailCode) {
        this.detailCode = detailCode;
    }
}