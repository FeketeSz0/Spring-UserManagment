package jdbc.dao;

import jdbc.models.Role;


public interface RoleDao extends Dao<Role> {
    Role findByName(String name);

}
