package by.bsuir.dao.interfaces;

import by.bsuir.model.Salary;

import java.util.List;

public interface SalaryDAO {
    Salary findById(int id);

    void save(Salary salary);

    void delete(Salary salary);

    void update(Salary salary);

    List<Salary> getAll();

    List<Salary> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage);

    List<Salary> getAllForPagination(int currentPage, int numberOfRecordsPerPage);
}
