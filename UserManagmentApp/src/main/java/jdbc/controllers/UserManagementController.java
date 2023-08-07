package jdbc.controllers;

import jdbc.dto.UserForm;
import jdbc.models.User;
import jdbc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class UserManagementController {
    private final UserService userService;

    @Autowired
    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute("user") @Validated UserForm userForm, BindingResult result) {
        result = userService.validateNewUser(userForm, result);
        if (result.hasErrors()) {
            return "adduser";
        }
        userService.addUser(userForm);
        return "redirect:/table";
    }

    @GetMapping("/adduser")
    public String getUser(Model model, Principal principal) {
        UserForm user = new UserForm();
        user.setIsEditMode(false);
        User currentUser = userService.findByLogin(principal.getName());
        String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
        model.addAttribute("fullName", fullName);
        model.addAttribute("user", user);
        return "adduser";
    }

    @GetMapping("edituser/{userId}")
    public String getUser(@PathVariable("userId") String userId, Model model, Principal principal) {

        User currentUser = userService.findByLogin(principal.getName());
        String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
        model.addAttribute("fullName", fullName);

        UserForm userForm = userService.getUserById(userId);
        if (userForm == null) {
            return "redirect:/table";
        } else {
            userForm.setIsEditMode(true);
            model.addAttribute("user", userForm);
            model.addAttribute("userId", userId);
            return "edituser";
        }
    }

    @PostMapping("edituser/{userId}")
    public String doPost(@PathVariable("userId") String userId, @ModelAttribute("user")
    @Validated UserForm userForm, BindingResult result) {
        result = userService.validateExistingUser(userForm, result);
        if (result.hasErrors()) {
            return "edituser";
        }
        userService.editUser(userForm);
        return "redirect:/table";
    }

    @GetMapping("/table")
    public String getTable(Model model, Principal principal) {
        User currentUser = userService.findByLogin(principal.getName());
        String fullName = currentUser.getFirstName() + " " + currentUser.getLastName();
        model.addAttribute("fullName", fullName);
        model.addAttribute("users", userService.listUsers());
        return "table";
    }

    @DeleteMapping("table/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userID) {
        userService.deleteUser(userID);
        return ResponseEntity.ok().build();
    }
}
