package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.service.RequestProcessService;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import aiu.edu.kg.FaceRecognation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/request")
public class RequestRestController {

    //TODO ADD BASIC AUTHENTICATION

    @Autowired
    private UserService userService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestProcessService requestProcessService;
    @RequestMapping(value = "/add", method = RequestMethod.POST)

    public ResponseMessage<String> addRequest(@Valid Request request, @RequestParam("file") List<MultipartFile> files, @RequestParam("username") String username){
        request.setUser(userService.findByUsername(username));
        request = requestService.save(request);
        for(MultipartFile file : files) {
            requestProcessService.save(request, file);
        }
        return  "redirect:/request/index";
    }
}
