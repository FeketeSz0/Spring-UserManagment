package jdbc;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.spring.api.DBRider;
import jdbc.config.TestConfig;
import jdbc.dao.UserDao;
import jdbc.models.Role;
import jdbc.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(DBUnitExtension.class)
@SpringJUnitConfig(TestConfig.class)
@DBUnit(url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", user = "user", password = "123", driver = "org.h2.Driver",
        caseSensitiveTableNames = true)

@DBRider
class HibernateUserDaoTest {
    @Autowired
    UserDao userDao;

    @BeforeAll
    static void setUp() throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestConfig.class);
        context.refresh();
    }

    @Test
    @DataSet(value = {"users.yml, roles.yml"}, cleanAfter = true)
    void testFindByLogin() {
        User user = userDao.findByLogin("MichaelS");
        assertNotNull(user);
        assertEquals("MichaelS", user.getLogin());
    }

    @Test
    @DataSet(value = {"users.yml, roles.yml"}, cleanAfter = true)
    void testFindById() {
        User user = userDao.findById(1L);
        assertNotNull(user);
        assertEquals(user.getLogin(), "MichaelS");
    }

    @Test
    @DataSet(value = {"users.yml, roles.yml"}, cleanAfter = true)
    void testFindByEmail() {
        User user = userDao.findByEmail("Dwight@example.com");
        assertNotNull(user);
        assertEquals("Dwight@example.com", user.getEmail());
    }

    @Test
    @DataSet(value = {"users.yml, roles.yml"}, cleanAfter = true)
    void testFindAll() {
        List<User> userList = userDao.findAll();
        assertNotNull(userList);
        assertEquals(3, userList.size());
    }

    @Test
    @DataSet(value = "insert.yml", cleanAfter = true, strategy = SeedStrategy.IDENTITY_INSERT)
    void testCreate() {
        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPass");
        user.setEmail("test@example.com");
        userDao.create(user);

        User userFromDb = userDao.findByLogin("testLogin");
        assertNotNull(userFromDb);
        assertEquals("testLogin", userFromDb.getLogin());
        System.out.println(user.getLogin());
        assertEquals("testPass", userFromDb.getPassword());
        assertEquals("test@example.com", userFromDb.getEmail());
    }

    @Test
    @DataSet(value = {"users.yml, roles.yml"}, cleanAfter = true)
    void testUpdate() {
        User user = userDao.findByLogin("DwightS");
        user.setFirstName("ChangedFirstName");
        userDao.update(user);
        User userFromDb = userDao.findById(user.getId());
        assertEquals("ChangedFirstName", userFromDb.getFirstName());
    }

    @Test
    @DataSet(value = {"users.yml, roles.yml"}, cleanAfter = true)
    void testRemove() {
        List<User> listBefore = userDao.findAll();
        User user = userDao.findByLogin("MichaelS");
        userDao.remove(user);
        User userFromDb = userDao.findById(user.getId());
        List<User> listAfter = userDao.findAll();
        assertNull(userFromDb);
        assertEquals(listBefore.size(), listAfter.size() + 1);
    }

}
