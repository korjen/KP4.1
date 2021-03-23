package by.bsuir.dao.interfaces;

import by.bsuir.model.Worker;

import java.util.List;

public interface WorkerDAO {
    Worker findById(int id);

    void save(Worker worker);

    void delete(Worker worker);

    void update(Worker worker);

    List<Worker> getAll();

    List<Worker> getAllForPagination(int currentPage, int numberOfRecordsPerPage);
}
