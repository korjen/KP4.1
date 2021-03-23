package by.bsuir.service.interfaces;

import by.bsuir.model.Salary;

import java.util.List;

public interface SalaryService {
    List<Salary> getAll();
    void update(Salary salary);
    void save(Salary salary);
    void delete(int id);
    Salary findById(int id);
    List<Salary> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage);
    List<Salary> getAllForPagination(int currentPage, int numberOfRecordsPerPage);
}
