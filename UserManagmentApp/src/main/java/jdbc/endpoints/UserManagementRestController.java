package jdbc.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import jdbc.dto.UserForm;
import jdbc.models.User;
import jdbc.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserManagementRestController {

    private final UserService userService;

    @Autowired
    public UserManagementRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserForm> getUser(@PathVariable("userId") String userId) throws JsonProcessingException {
        UserForm userForm = userService.getUserById(userId);
        if (userForm == null) {
            return ResponseEntity.notFound().build();
        } else {

            userForm.setIsEditMode(true);
            return ResponseEntity.ok().body(userForm);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String userID) {
        userService.deleteUser(userID);
        return ResponseEntity.ok().body("User deleted");
    }

    @PostMapping()
    public ResponseEntity addUser(@RequestBody UserForm userForm, BindingResult result) {
        result = userService.validateNewUser(userForm, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(userService.addUser(userForm));
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @PutMapping("{id}")
    public ResponseEntity updateUser(@RequestBody UserForm userForm, BindingResult result) {
        result = userService.validateExistingUser(userForm, result);
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        userService.editUser(userForm);
        return ResponseEntity.ok("user updated");
    }

}
