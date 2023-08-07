package jdbc.dao.impl;

import jdbc.dao.RoleDao;
import jdbc.models.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateRoleDao extends GenericHibernateDao<Role> implements RoleDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateRoleDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }


    @Override
    protected Class<Role> getEntityClass() {
        return Role.class;
    }

    @Override
    @Transactional
    public Role findByName(String name) {
        Query<Role> role = sessionFactory.getCurrentSession().createQuery(
                "FROM Role WHERE name = :name", Role.class);
        role.setParameter("name", name);
        return role.uniqueResult();
    }
}
