package by.bsuir.dao;

import by.bsuir.dao.interfaces.UserDAO;
import by.bsuir.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractBaseDAO<User> implements UserDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDAOImpl.class);
    @Override
    public User findById(String id) {
        return super.findByStringId(id, User.class);
    }

    @Override
    public void save(User user) {
        super.save(user);
    }

    @Override
    public void delete(User user) {
        super.delete(user);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public List<User> getAll() {
        return super.getAll("from User", User.class);
    }

    @Override
    public List<User> getAllForPagination(int currentPage, int numberOfRecordsPerPage) {
        List<User> users = new ArrayList<>();
        try {
            users = sessionFactory.getCurrentSession().createQuery(
                    "FROM User", User.class)
                    .setFirstResult(currentPage)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
        }
        catch (NoResultException ex) {
            LOGGER.error("No users found for pagination");
        }
        return users;
    }

    @Override
    public List<User> findByWorker(int id) {
        List<User> users= new ArrayList<>();
        String sql = "FROM User WHERE User.worker.idWorker="+id;
        try{
            users = sessionFactory.getCurrentSession().createQuery(sql).getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("No user for this worker");
        }
        return users;
    }
}
