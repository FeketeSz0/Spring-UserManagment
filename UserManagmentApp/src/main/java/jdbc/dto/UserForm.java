package jdbc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Component
@JsonSerialize
@JsonDeserialize
public class UserForm {
    @NotBlank(message = "username is required")
    private String login;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "password is required")
    private String confirmPassword;
    @Email
    @NotBlank
    private String email;
    @NotBlank(message = "first name is required")
    private String firstName;
    @NotBlank(message = "last name is required")
    private String lastName;
    @NotBlank(message = "birthday is required")
    private String birthday;
    @NotBlank
    private String role;
    @JsonIgnore
    private Boolean isEditMode;
    @JsonIgnore
    private String recaptchaResponse;
}
