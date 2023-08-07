package jdbc;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.junit5.DBUnitExtension;
import com.github.database.rider.spring.api.DBRider;
import jdbc.config.TestConfig;
import jdbc.dao.RoleDao;
import jdbc.models.Role;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(DBUnitExtension.class)
@SpringJUnitConfig(TestConfig.class)
@DBUnit(url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", user = "user", password = "123", driver = "org.h2.Driver",
        caseSensitiveTableNames = true)
@DBRider
class HibernateRoleDaoTest {

    @Autowired
    RoleDao roleDao;

    @BeforeAll
    static void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(TestConfig.class);
        context.refresh();
    }

    @Test
    @DataSet(value = "roles.yml")
    void testFindByName() {
        Role role = roleDao.findByName("Admin");
        assertNotNull(role);
        assertEquals(1L, role.getId());
    }

    @Test
    @DataSet(value = "roles.yml")
    void testFindById() {
        Role role = roleDao.findById(1L);
        assertNotNull(role);
        assertEquals("Admin", role.getName());
    }

    @Test
    @DataSet(value = "insert.yml", cleanBefore = true)
    void testCreate() {
        Role role = new Role(3L, "superAdmin");
        roleDao.create(role);
        List<Role> all = roleDao.findAll();
        Role roleFromDb = roleDao.findByName("superAdmin");
        assertNotNull(roleFromDb);
        assertEquals(3,all.size());
    }

    @Test
    @DataSet(value = "insert.yml", cleanBefore = true)
    void testUpdate() {
        Role role = roleDao.findByName("User");
        role.setName("UberMegaAdmin");
        roleDao.create(role);
        Role roleFromDb = roleDao.findByName("UberMegaAdmin");
        assertNotNull(roleFromDb);
    }

    @Test
    @DataSet(value = "roles.yml", cleanBefore = true)
    void testRemove() {
        List<Role> listBefore = roleDao.findAll();
        Role role = roleDao.findById(2L);
        roleDao.remove(role);
        Role roleFromDB = roleDao.findById(role.getId());
        List<Role> listAfter = roleDao.findAll();
        assertNull(roleFromDB);
        assertEquals(listBefore.size(), listAfter.size() + 1);
    }


    @Test
    @DataSet(value = "roles.yml", cleanBefore = true)
    void testFindAll() {
        List<Role> roleList = roleDao.findAll();
        assertNotNull(roleList);
        assertEquals(2, roleList.size());
    }

}
