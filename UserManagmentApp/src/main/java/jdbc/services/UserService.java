package jdbc.services;


import jdbc.dao.RoleDao;
import jdbc.dao.UserDao;
import jdbc.models.Role;
import jdbc.models.User;
import jdbc.dto.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;

    private final RoleDao roleDao;

    @Autowired
    public UserService(UserDao userDao, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public List<User> listUsers() {
        List<User> users = userDao.findAll();
        return users.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());

    }

    public void deleteUser(String userId) {
        Long id = Long.parseLong(userId);
        User user = userDao.findById(id);
        userDao.remove(user);
    }

    public UserForm getUserById(String userId) {
        User user = userDao.findById(Long.parseLong(userId));
        UserForm userForm = new UserForm();
        userForm.setLogin(user.getLogin());
        userForm.setFirstName(user.getFirstName());
        userForm.setLastName(user.getLastName());
        userForm.setRole(user.getRole().getName());
        userForm.setBirthday(user.getBirthday().toString());
        userForm.setEmail(user.getEmail());
        return userForm;
    }

    public void editUser(UserForm form) {
        User foundUser = userDao.findByLogin(form.getLogin());
        User user = setGenericUserInformation(foundUser, form);
        userDao.update(user);
    }


    public Long addUser(UserForm userForm) {
        User user = new User();
        setGenericUserInformation(user, userForm);
        userDao.create(user);
        User byLogin = findByLogin(user.getLogin());
        return byLogin.getId();
    }

    private User setGenericUserInformation(User user, UserForm form) {
        user.setLogin(form.getLogin());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setLogin(form.getLogin());
        user.setEmail(form.getEmail());
        user.setBirthday(convertDate(form.getBirthday()));
        Role role = roleDao.findByName(form.getRole());
        user.setRole(role);
        if (!form.getPassword().isEmpty() && form.getConfirmPassword() != null) {
            user.setPassword(form.getPassword());
        }
        return user;
    }

    public BindingResult validateNewUser(UserForm user, BindingResult bindingResult) {
        User userByLogin = userDao.findByLogin(user.getLogin());
        User userByEmail = userDao.findByEmail(user.getEmail());
        if (userByLogin != null) {
            bindingResult.rejectValue("login", "error.userForm", "username is already taken");
        } else if (userByEmail != null) {
            bindingResult.rejectValue("email", "error.userForm", "email is already taken");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            bindingResult.rejectValue("password", "error.userForm", "password is required");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.userForm", "passwords are not matching");
        }
        return bindingResult;
    }

    public BindingResult validateExistingUser(UserForm user, BindingResult bindingResult) {
        User userByLogin = userDao.findByLogin(user.getLogin());
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.rejectValue("password", "error.userForm", "passwords are not matching");
        }
        if (user.getEmail().equals(userByLogin.getEmail()) && !user.getLogin().equals(userByLogin.getLogin())) {
            bindingResult.rejectValue("email", "error.userForm", "email is already taken");
        }
        return bindingResult;
    }

    private Date convertDate(String stringDate) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date birthday = dateFormat.parse(stringDate);
            return new Date(birthday.getTime());
        } catch (ParseException e) {
            return null;
        }
    }

    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }
}
