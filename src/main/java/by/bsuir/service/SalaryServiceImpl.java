package by.bsuir.service;

import by.bsuir.dao.interfaces.SalaryDAO;
import by.bsuir.model.Salary;
import by.bsuir.service.interfaces.SalaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {
    private SalaryDAO salaryDAO;

    @Override
    public List<Salary> getAll() {
        return salaryDAO.getAll();
    }

    @Override
    public void update(Salary salary) {
        salaryDAO.update(salary);
    }

    @Override
    public void save(Salary salary) {
        salaryDAO.save(salary);
    }

    @Override
    public void delete(int id) {
        Salary salary = salaryDAO.findById(id);
        if (salary!=null) salaryDAO.delete(salary);
    }

    @Override
    public Salary findById(int id) {
        return salaryDAO.findById(id);
    }

    public void setSalaryDAO(SalaryDAO salaryDAO) {
        this.salaryDAO = salaryDAO;
    }

    @Override
    public List<Salary> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage) {
        return salaryDAO.getAllForPaginationByWorker(id,currentPage,numberOfRecordsPerPage);
    }

    @Override
    public List<Salary> getAllForPagination(int currentPage, int numberOfRecordsPerPage) {
        return salaryDAO.getAllForPagination(currentPage,numberOfRecordsPerPage);
    }
}
