package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.Person;
import aiu.edu.kg.FaceRecognation.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@RequestMapping("/person")
@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/index")
    public String person(Model model){
        model.addAttribute("items", personService.findAll());
        return "person";
    }

    @RequestMapping("/get")
    public String get(@RequestParam("id")Long id, Model model){
        model.addAttribute(personService.get(id));
        return "";
    }

    @RequestMapping("/save")
    public String save(@Valid Person person, Model model){
        personService.save(person);
        return "redirect:/index";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id")Long id, Model model){
        personService.delete(id);
        return "redirect:/index";
    }


}
