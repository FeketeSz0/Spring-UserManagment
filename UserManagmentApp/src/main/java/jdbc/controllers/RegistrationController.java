package jdbc.controllers;

import jdbc.dto.UserForm;
import jdbc.services.UserService;
import jdbc.utils.RecaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    private Environment environment;

    private String SITE_KEY;

    @Autowired
    public RegistrationController(UserService userService, Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") @Validated UserForm userForm, BindingResult result, HttpSession httpSession,
                          HttpServletRequest request, Model model) {

        String response = request.getParameter("g-recaptcha-response");
        String secretKey = environment.getProperty("google.recaptcha.secret-key");
        boolean isValidKey = RecaptchaUtil.verifyRecaptcha(response, secretKey);
        model.addAttribute("siteKey", SITE_KEY);
        result = userService.validateNewUser(userForm, result);
        if (!isValidKey) {

            return "register";
        }
        if (result.hasErrors()) {
            return "register";
        }

        userForm.setRole("User");
        userService.addUser(userForm);
        httpSession.setAttribute("register", true);
        return "redirect:/login";
    }

    @GetMapping
    public String getUser(Model model) {
        SITE_KEY = environment.getProperty("google.recaptcha.site-key");
        UserForm user = new UserForm();
        user.setIsEditMode(false);
        model.addAttribute("user", user);
        model.addAttribute("siteKey", SITE_KEY);
        return "register";
    }
}

