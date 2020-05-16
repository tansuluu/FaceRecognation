package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestRestController {

    //TODO ADD BASIC AUTHENTICATION

    @Autowired
    private RequestService requestService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseMessage<String> addRequest(@ModelAttribute RequestDTO requestDTO){
        return requestService.validateAndSave(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMessage<String> getRequest(){
        ResponseMessage<String> responseMessage = new ResponseMessage(ResultCode.SUCCESS);
        responseMessage.setResult("OK");
        return responseMessage;
    }

}
