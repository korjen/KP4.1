package by.bsuir.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public abstract class AbstractBaseDAO<T> {
    protected SessionFactory sessionFactory;

    protected void save(final T obj) {
        sessionFactory.getCurrentSession().save(obj);
    }

    protected List<T> getAll(final String queryString, final Class<T> type) {
        return sessionFactory.getCurrentSession().createQuery(queryString, type).list();
    }

    protected void update(final T object) {
        this.sessionFactory.getCurrentSession().update(object);
    }

    protected void delete(final T object) {
        this.sessionFactory.getCurrentSession().delete(object);
    }

    protected T findByIntId(final int id, final Class<T> type) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(type, id);
    }

    protected T findByStringId(final String id, final Class<T> type) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(type, id);
    }

    public void setSessionFactory(final org.hibernate.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        sessionFactory.openSession();
    }
}
