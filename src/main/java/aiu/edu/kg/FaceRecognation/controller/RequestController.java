package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;

@Controller
@RequestMapping("/request")
public class RequestController {


    @Autowired
    private RequestService requestService;

    @Autowired
    private StorageService storageService;

    @Autowired
    private RequestResultService requestResultService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String allRequest(Model model, Principal principal){
        model.addAttribute("item", new Request());
        if (principal.getName().equals("tmyrzaeva")){
            model.addAttribute("items", requestService.all());
        }else
            model.addAttribute("items", requestService.getAllByUser(userService.findByUsername(principal.getName())));

        return "request";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
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

    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        String mimeType;
        try {
            mimeType = Files.probeContentType(file.getFile().toPath());
        } catch (IOException e) {
            System.out.println("Error while loading file " + e);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @RequestMapping(value = "/get")
    public String view(@RequestParam("id")Long id, Model model){
        model.addAttribute("item", requestResultService.getAllByRequest(requestService.getById(id)).get(0));
        return "requestResult";
    }

    @RequestMapping(value = "delete")
    public String delete(Long id){
        requestService.delete(id);
        return "redirect:/index";
    }
}
