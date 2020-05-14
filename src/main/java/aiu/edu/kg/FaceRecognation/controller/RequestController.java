package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/request")
public class RequestController {

    private final String ADMIN = "ADMIN";

    @Autowired
    private RequestService requestService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestProcessService requestProcessService;

    @RequestMapping("/index")
    public String allRequest(Model model, Principal principal){
        model.addAttribute("item", new Request());
        if (userService.findByUsername(principal.getName()).getRoles().stream().filter(r-> r.getName().equals(ADMIN)).count() > 0){
            model.addAttribute("items", requestService.all());
        }else
            model.addAttribute("items", requestService.getAllByUser(userService.findByUsername(principal.getName())));
        return "request";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addRequest(@Valid Request request, @RequestParam("file") List<MultipartFile> files, Principal principal){
        request.setUser(userService.findByUsername(principal.getName()));
        request = requestService.save(request);
        for(MultipartFile file : files) {
            requestProcessService.save(request, file);
        }
        return  "redirect:/request/index";
    }

    @RequestMapping(value = "/view")
    public String view(@RequestParam("id") Long id, Model model){
        Request request = requestService.getById(id);
        model.addAttribute("item", request);
        model.addAttribute("processes", requestProcessService.findAllByRequest(request));
        return "requestResult";
    }

    @RequestMapping(value = "delete")
    public String delete(@RequestParam("id") Long id){
        requestService.delete(id);
        return "redirect:/request/index";
    }
}
