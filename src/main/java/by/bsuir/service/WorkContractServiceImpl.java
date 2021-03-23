package by.bsuir.service;

import by.bsuir.dao.interfaces.WorkContractDAO;
import by.bsuir.model.WorkContract;
import by.bsuir.service.interfaces.WorkContractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkContractServiceImpl implements WorkContractService {
    private WorkContractDAO workContractDAO;

    @Override
    public List<WorkContract> getAll() {
        return workContractDAO.getAll();
    }

    @Override
    public void update(WorkContract contract) {
        workContractDAO.update(contract);
    }

    @Override
    public void save(WorkContract contract) {
        workContractDAO.save(contract);
    }

    @Override
    public void delete(int id) {
        WorkContract workContract = workContractDAO.findById(id);
        if(workContract!=null) workContractDAO.delete(workContract);
    }

    @Override
    public WorkContract findById(int id) {
        return workContractDAO.findById(id);
    }

    public void setWorkContractDAO(WorkContractDAO workContractDAO) {
        this.workContractDAO = workContractDAO;
    }
}
