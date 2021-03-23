package by.bsuir.service.interfaces;

import by.bsuir.model.WorkContract;

import java.util.List;

public interface WorkContractService {
    List<WorkContract> getAll();
    void update(WorkContract contract);
    void save(WorkContract contract);
    void delete(int id);
    WorkContract findById(int id);
}
