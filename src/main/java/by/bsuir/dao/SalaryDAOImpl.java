package by.bsuir.dao;

import by.bsuir.dao.interfaces.SalaryDAO;
import by.bsuir.model.Salary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl extends AbstractBaseDAO<Salary> implements SalaryDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(WorkingTimeDAOImpl.class);
    @Override
    public Salary findById(int id) {
        return super.findByIntId(id, Salary.class);
    }

    @Override
    public void save(Salary salary) {
        super.save(salary);
    }

    @Override
    public void delete(Salary salary) {
        super.delete(salary);
    }

    @Override
    public void update(Salary salary) {
        super.update(salary);
    }

    @Override
    public List<Salary> getAll() {
        return super.getAll("from Salary", Salary.class);
    }

    @Override
    public List<Salary> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage) {
        List<Salary> salaries = new ArrayList<>();
        try {
            salaries = sessionFactory.getCurrentSession().createQuery(
                    "SELECT salary FROM Salary salary where salary.worker.idWorker="+id, Salary.class)
                    .setFirstResult(currentPage)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
        }
        catch (NoResultException ex) {
            LOGGER.error("No salary found for pagination");
        }
        return salaries;
    }

    @Override
    public List<Salary> getAllForPagination(int currentPage, int numberOfRecordsPerPage) {
        List<Salary> salaries = new ArrayList<>();
        try {
            salaries = sessionFactory.getCurrentSession().createQuery(
                    "FROM Salary", Salary.class)
                    .setFirstResult(currentPage)
                    .setMaxResults(numberOfRecordsPerPage)
                    .list();
        }
        catch (NoResultException ex) {
            LOGGER.error("No salary found for pagination");
        }
        return salaries;
    }
}
