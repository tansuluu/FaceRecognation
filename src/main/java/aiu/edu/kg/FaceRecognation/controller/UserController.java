package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private String message;

    @RequestMapping("/index")
    public String allUsers(Model model){
        model.addAttribute("users", userService.all());
        model.addAttribute("message", message);
        message=null;
        return "users";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addUser(@Valid User user){
        User userExists = userService.findByUsername(user.getUsername());
        if(user.getId()==null) {
            if (userExists != null) {
                message = "*There is already a user registered with the login provided";
            }else userService.save(user);
        }else userService.update(user, userExists);
        return "redirect:/user/index";
    }

    @RequestMapping(value = "/get/{id}")
    public String get(@PathVariable("id") Long id, Model model){
        model.addAttribute("item",userService.findById(id));
        return "modals/userModal";
    }

    @RequestMapping(value = "/get")
    public String get(Model model){
        model.addAttribute("item", new User());
        return "modals/userModal";
    }

    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.delete(id);
        return "redirect:/user/index";
    }

}
