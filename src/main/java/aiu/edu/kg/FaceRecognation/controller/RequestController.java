package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import aiu.edu.kg.FaceRecognation.service.StorageService;
import aiu.edu.kg.FaceRecognation.service.UserDetailsServiceImpl;
import aiu.edu.kg.FaceRecognation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class RequestController {


    @Autowired
    private RequestService requestService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private UserService userService;

    @RequestMapping("/requests")
    public String allRequest(Model model){
        model.addAttribute("item", new Request());
        model.addAttribute("items", requestService.all());
        return "request";
    }

    @RequestMapping(value = "/addRequest", method = RequestMethod.POST)
    public String addRequest(@Valid Request request, @RequestParam("file") MultipartFile file, Model model, Principal principal){
        try {
            storageService.store(file);
            request.setFileName(file.getOriginalFilename());
            request.setStatus(StageStatus.NEW);
            request.setUser(userService.findByUsername(principal.getName()));
            requestService.save(request);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
            model.addAttribute("items", requestService.all());
            return "requests";
        }
        return  "redirect:/requests";
    }
}
