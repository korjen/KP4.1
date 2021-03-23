package by.bsuir.dao.interfaces;

import by.bsuir.model.WorkContract;

import java.util.List;

public interface WorkContractDAO {
    WorkContract findById(int id);

    void save(WorkContract contract);

    void delete(WorkContract contract);

    void update(WorkContract contract);

    List<WorkContract> getAll();
}
