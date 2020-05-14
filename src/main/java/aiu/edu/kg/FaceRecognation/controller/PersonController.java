package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.service.PersonService;
import aiu.edu.kg.FaceRecognation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.security.Principal;

@RequestMapping("/person")
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String person(Model model){
        model.addAttribute("items", personService.findAll());
        return "person";
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id")Long id, Model model){
        model.addAttribute("item", personService.get(id));
        return "modals/personModal";
    }

    @RequestMapping("/get")
    public String get(Model model){
        model.addAttribute("item", new Person());
        return "modals/personModal";
    }


    @RequestMapping("/save")
    public String save(@Valid Person person, @RequestParam("file") MultipartFile file, Principal principal){
        person.setUser(userService.findByUsername(principal.getName()));
        personService.save(person, file);
        return "redirect:/person/index";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id")Long id){
        personService.delete(id);
        return "redirect:/person/index";
    }


}
