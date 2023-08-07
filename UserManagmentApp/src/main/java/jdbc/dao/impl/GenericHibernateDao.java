package jdbc.dao.impl;

import jdbc.dao.Dao;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public abstract class GenericHibernateDao<E> implements Dao<E> {


    private final SessionFactory sessionFactory;

    @Autowired
    public GenericHibernateDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected abstract Class<E> getEntityClass();

    //SELECT * FROM Users INNER JOIN Roles on Users.role_id = Roles.id"
    @Override
    @Transactional
    public List<E> findAll() {
        Query<E> genericList = sessionFactory.getCurrentSession().
                createQuery("FROM " + getEntityClass().getSimpleName(), getEntityClass());
        return genericList.getResultList();
    }

    @Override
    @Transactional
    public E findById(Long id) {
        Query<E> query = sessionFactory.getCurrentSession().createQuery(
                "FROM " + getEntityClass().getSimpleName() + " WHERE id = :id", getEntityClass());
        query.setParameter("id", id);
        return query.uniqueResult();
    }

    @Override
    @Transactional
    public void remove(E entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }

    @Override
    @Transactional
    public void create(E entity) {
        sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    @Transactional
    public void update(E entity) {
        sessionFactory.getCurrentSession().update(entity);
    }
}



