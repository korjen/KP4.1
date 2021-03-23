package by.bsuir.service.interfaces;

import by.bsuir.model.WorkingTime;

import java.util.List;

public interface WorkingTimeService {
    List<WorkingTime> getAll();
    void update(WorkingTime workingTime);
    void save(WorkingTime workingTime);
    void delete(int id);
    WorkingTime findById(int id);
    List<WorkingTime> findByWorker(int idWorker);
    List<WorkingTime> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage);
}
