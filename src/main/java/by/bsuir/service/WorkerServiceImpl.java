package by.bsuir.service;

import by.bsuir.dao.interfaces.WorkerDAO;
import by.bsuir.model.Worker;
import by.bsuir.service.interfaces.WorkerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class WorkerServiceImpl implements WorkerService {
    private WorkerDAO workerDAO;

    @Override
    public List<Worker> getAll() {
        return workerDAO.getAll();
    }

    @Override
    public void update(Worker worker) {
        workerDAO.update(worker);
    }

    @Override
    public void save(Worker worker) {
        workerDAO.save(worker);
    }

    @Override
    public void delete(int id) {
        Worker worker = workerDAO.findById(id);
        if(worker!=null) workerDAO.delete(worker);
    }

    @Override
    public Worker findById(int id) {
        return workerDAO.findById(id);
    }

    @Override
    public List<Worker> getAllForPagination(int currentPage, int recordsAmount) {
        return workerDAO.getAllForPagination(currentPage, recordsAmount);
    }

    public void setWorkerDAO(WorkerDAO workerDAO) {
        this.workerDAO = workerDAO;
    }
}
