package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.service.RequestProcessService;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import aiu.edu.kg.FaceRecognation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RequestRestController {

    //TODO ADD BASIC AUTHENTICATION

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestProcessService requestProcessService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseMessage<String> addRequest(@ModelAttribute RequestDTO requestDTO, BindingResult result){
        ResponseMessage<String> responseMessage = new ResponseMessage(ResultCode.SUCCESS);
        responseMessage.setResult("OK");
        if (result.hasErrors()){
            responseMessage.setResult();
            responseMessage.setResult(result.getAllErrors().iterator().next().getObjectName());
            return responseMessage;
        }
//        request.setUser(userService.findByUsername(username));
//        request = requestService.save(request);
//        for(MultipartFile file : files) {
//            requestProcessService.save(request, file);
//        }

        return responseMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseMessage<String> getRequest(){
        ResponseMessage<String> responseMessage = new ResponseMessage(ResultCode.SUCCESS);
        responseMessage.setResult("OK");
        return responseMessage;
    }

    private ResponseMessage<String> validate(){

    }
}
