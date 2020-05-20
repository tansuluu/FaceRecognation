package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.dto.PersonDTO;
import aiu.edu.kg.FaceRecognation.dto.RequestDTO;
import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.RequestProcess;
import aiu.edu.kg.FaceRecognation.enums.ResultCode;
import aiu.edu.kg.FaceRecognation.enums.ResultDetail;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.model.PersonModel;
import aiu.edu.kg.FaceRecognation.model.ProcessModel;
import aiu.edu.kg.FaceRecognation.model.RequestResponse;
import aiu.edu.kg.FaceRecognation.model.ResponseMessage;
import aiu.edu.kg.FaceRecognation.service.PersonService;
import aiu.edu.kg.FaceRecognation.service.RequestProcessService;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RequestRestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestProcessService requestProcessService;

    @Autowired
    private PersonService personService;


    @ResponseBody
    @RequestMapping(value = "/createRequest", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseEntity<?> addRequest(@ModelAttribute RequestDTO requestDTO){
        return requestService.validateAndSave(requestDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getRequest/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getRequest(@PathVariable("id") Long id){
        ResponseMessage responseMessage = new ResponseMessage(ResultCode.SUCCESS, ResultDetail.OK);
        Optional<Request> request = requestService.findById(id);
        if (request.isPresent()) {
            RequestResponse requestResponse = new RequestResponse(request.get().getTitle(), request.get().getStatus());
            if (request.get().getStatus() == StageStatus.FINISHED) {
                List<RequestProcess> requestProcesses = requestProcessService.findAllByRequest(request.get());
                List<ProcessModel> result = new ArrayList<>();
                for (RequestProcess requestProcess : requestProcesses) {
                    result.addAll(requestProcess.getRequestResults().stream().map(t -> new ProcessModel(t)).collect(Collectors.toList()));
                }
                requestResponse.setResult(result);
            }
            responseMessage.setResult(requestResponse);
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);        }
        else {
            responseMessage.setResultCode(ResultCode.FAIL);
            responseMessage.setDetailCode(ResultDetail.WRONG_REQUEST_ID);
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/createPerson", method = RequestMethod.PUT, consumes = {"multipart/form-data"})
    public ResponseEntity<?> save(@ModelAttribute PersonDTO personDTO){
        return personService.validateAndSave(personDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getPerson/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") Long id){
        Optional<Person> person = personService.findById(id);
        ResponseMessage responseMessage = new ResponseMessage(ResultCode.SUCCESS, ResultDetail.OK);
        if(person.isPresent()){
            responseMessage.setResult(new PersonModel(person.get()));
            return new ResponseEntity<>(responseMessage, HttpStatus.OK);
        }else{
            responseMessage.setResultCode(ResultCode.FAIL);
            responseMessage.setDetailCode(ResultDetail.WRONG_REQUEST_ID);
            return new ResponseEntity<>(responseMessage, HttpStatus.BAD_REQUEST);
        }

    }

}
