package api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserForm {
    private String login;
    private String password;
    private String confirmPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String birthday;
    private String role;

}
