package jdbc.dao;

import jdbc.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao extends Dao<User> {

    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);


}
