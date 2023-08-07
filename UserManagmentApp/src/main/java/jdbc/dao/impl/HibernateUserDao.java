package jdbc.dao.impl;

import jdbc.dao.UserDao;
import jdbc.models.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HibernateUserDao extends GenericHibernateDao<User> implements UserDao {


    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateUserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }


    @Override
    protected Class<User> getEntityClass() {
        return User.class;
    }

    @Override
    @Transactional
    public User findByLogin(String login) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery(
                "FROM User WHERE login = :login", User.class);
        query.setParameter("login", login);
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public User findByEmail(String email) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery(
                "FROM User WHERE email = :email", User.class);
        query.setParameter("email", email);
        return query.uniqueResult();
    }
}


