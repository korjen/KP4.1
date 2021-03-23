package by.bsuir.service.interfaces;

import by.bsuir.model.Worker;
import java.util.List;

public interface WorkerService {
    List<Worker> getAll();
    void update(Worker worker);
    void save(Worker worker);
    void delete(int id);
    Worker findById(int id);
    List<Worker> getAllForPagination(int currentPage, int recordsAmount);
}
