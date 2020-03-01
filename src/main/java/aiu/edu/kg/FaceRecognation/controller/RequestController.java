package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import aiu.edu.kg.FaceRecognation.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RequestController {


    @Autowired
    private RequestService requestService;

    @Autowired
    private StorageService storageService;

    @RequestMapping("/requests")
    public String allRequest(Model model){
        model.addAttribute("item", new Request());
        model.addAttribute("items", requestService.all());
        return "request";
    }

    @RequestMapping(value = "/addRequest", method = RequestMethod.POST)
    public String addRequest(@RequestBody Request request, @RequestParam("file") MultipartFile file){
        System.out.println(storageService.store(file));
        return  "redirect:/requests";
    }
}
