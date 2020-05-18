package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.dto.PersonDTO;
import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.RequestProcess;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.model.PersonModel;
import aiu.edu.kg.FaceRecognation.model.ProcessModel;
import aiu.edu.kg.FaceRecognation.model.RequestResponse;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.service.PersonService;
import aiu.edu.kg.FaceRecognation.service.RequestProcessService;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RequestRestController {

    //TODO ADD BASIC AUTHENTICATION

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestProcessService requestProcessService;

    @Autowired
    private PersonService personService;


    @ResponseBody
    @RequestMapping(value = "/createRequest", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseMessage<Long> addRequest(@ModelAttribute RequestDTO requestDTO){
        return requestService.validateAndSave(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getRequest/{id}", method = RequestMethod.GET)
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

    @ResponseBody
    @RequestMapping(value = "/createPerson", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseMessage<Long> save(@ModelAttribute PersonDTO personDTO){
        return personService.validateAndSave(personDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET)
    public PersonModel get(@PathVariable("id") Long id){
        return new PersonModel(personService.get(id));
    }

}
