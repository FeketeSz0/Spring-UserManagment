package jdbc.controllers;

import jdbc.models.User;
import jdbc.services.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/hello")
public class IndexController {

    UserService userService;

    @Autowired
    public IndexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getIndex(Model model, Principal principal) {
        Object login = principal.getName();
        User user = userService.findByLogin((String) login);
        model.addAttribute("firstname", user.getFirstName());
        return "hello";
    }
}
