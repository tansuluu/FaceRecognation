package aiu.edu.kg.FaceRecognation.controller;

import aiu.edu.kg.FaceRecognation.entity.User;
import aiu.edu.kg.FaceRecognation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/users")
    public String allUsers(Model model){
        model.addAttribute("users", userService.all());
        model.addAttribute("user", new User());
        return "users";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model){
        User userExists = userService.findByUsername(user.getUsername());
        if (userExists != null) {
            bindingResult
                    .rejectValue("username", "error.user",
                            "*There is already a user registered with the login provided");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", 1);
            return "users";
        }
        userService.save(user);
        return "redirect:/users";

    }

}
