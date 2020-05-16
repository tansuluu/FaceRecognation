package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.RequestProcess;
import aiu.edu.kg.FaceRecognation.enums.RequestStatus;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.model.ProcessModel;
import aiu.edu.kg.FaceRecognation.model.RequestResponse;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.service.RequestProcessService;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RequestRestController {

    //TODO ADD BASIC AUTHENTICATION

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestProcessService requestProcessService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseMessage<Long> addRequest(@ModelAttribute RequestDTO requestDTO){
        return requestService.validateAndSave(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public RequestResponse getRequest(@PathVariable("id") Long id){
        Request request = requestService.getById(id);
        RequestResponse requestResponse = new RequestResponse(request.getTitle(), request.getStatus());
        if (request.getStatus() == StageStatus.FINISHED) {
            List<RequestProcess> requestProcesses = requestProcessService.findAllByRequest(request);
            List<ProcessModel> result = new ArrayList<>();
            for (RequestProcess requestProcess : requestProcesses) {
                result.addAll(requestProcess.getRequestResults().stream().map(t -> new ProcessModel(t)).collect(Collectors.toList()));
            }
            requestResponse.setResult(result);
        }

        return requestResponse;
    }

}
