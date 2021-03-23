package by.bsuir.dao;

import by.bsuir.dao.interfaces.WorkerDAO;
import by.bsuir.model.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class WorkerDAOImpl  extends AbstractBaseDAO<Worker> implements WorkerDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(WorkerDAOImpl.class);
    @Override
    public Worker findById(int id) {
        return super.findByIntId(id, Worker.class);
    }

    @Override
    public void save(Worker worker) {
        super.save(worker);
    }

    @Override
    public void delete(Worker worker) {
        super.delete(worker);
    }

    @Override
    public void update(Worker worker) {
        super.update(worker);
    }

    @Override
    public List<Worker> getAll() {
        return super.getAll("from Worker", Worker.class);
    }

    @Override
    public List<Worker> getAllForPagination(int currentPage, int numberOfRecordsPerPage) {
        List<Worker> workers = new ArrayList<>();
        try {
            workers = sessionFactory.getCurrentSession().createQuery(
                    "FROM Worker", Worker.class)
                    .setFirstResult(currentPage)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
        }
        catch (NoResultException ex) {
            LOGGER.error("No workers found for pagination");
        }
        return workers;
    }
}
