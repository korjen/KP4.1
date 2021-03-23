package by.bsuir.dao.interfaces;

import by.bsuir.model.WorkingTime;

import java.util.List;

public interface WorkingTimeDAO {
    WorkingTime findById(int id);

    void save(WorkingTime timeTable);

    void delete(WorkingTime timeTable);

    void update(WorkingTime timeTable);

    List<WorkingTime> getAll();

    List<WorkingTime> findByWorker(int idWorker);

    List<WorkingTime> getAllForPaginationByWorker(int id, int currentPage, int numberOfRecordsPerPage);
}
