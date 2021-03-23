package by.bsuir.dao;

import by.bsuir.dao.interfaces.WorkingTimeDAO;
import by.bsuir.model.WorkingTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class WorkingTimeDAOImpl extends AbstractBaseDAO<WorkingTime> implements WorkingTimeDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(WorkingTimeDAOImpl.class);
    @Override
    public WorkingTime findById(int id) {
        return super.findByIntId(id, WorkingTime.class);
    }

    @Override
    public void save(WorkingTime timeTable) {  super.save(timeTable);
    }

    @Override
    public void delete(WorkingTime timeTable) {
        super.delete(timeTable);
    }

    @Override
    public void update(WorkingTime timeTable) {
        super.update(timeTable);
    }

    @Override
    public List<WorkingTime> getAll() {
        return super.getAll("from WorkingTime", WorkingTime.class);
    }

    @Override
    public List<WorkingTime> findByWorker(int idWorker) {
        List<WorkingTime> timeTable = null;
        try{
            timeTable = sessionFactory.getCurrentSession().createQuery("SELECT time FROM WorkingTime time WHERE time.worker.idWorker="+idWorker).getResultList();
        } catch (NoResultException e) {
            LOGGER.warn("No time table for this worker");
        }
        return timeTable;
    }

    @Override
    public List<WorkingTime> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage) {
        List<WorkingTime> timeTable = new ArrayList<>();
        String sql = "SELECT time FROM WorkingTime time where time.worker.idWorker="+id;
        try {
            timeTable = sessionFactory.getCurrentSession().createQuery(sql,
                     WorkingTime.class)
                    .setFirstResult(currentPage)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
        }
        catch (NoResultException ex) {
            LOGGER.error("No timetable found for pagination");
        }
        catch (Exception e){
            LOGGER.error(e.toString());
        }
        return timeTable;
    }
}
