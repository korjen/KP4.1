package by.bsuir.dao;

import by.bsuir.dao.interfaces.WorkContractDAO;
import by.bsuir.model.WorkContract;

import java.util.List;

public class WorkContractDAOImpl extends AbstractBaseDAO<WorkContract> implements WorkContractDAO {
    @Override
    public WorkContract findById(int id) {
        return super.findByIntId(id, WorkContract.class);
    }

    @Override
    public void save(WorkContract contract) {
        super.save(contract);
    }

    @Override
    public void delete(WorkContract contract) {
        super.delete(contract);
    }

    @Override
    public void update(WorkContract contract) {
        super.update(contract);
    }

    @Override
    public List<WorkContract> getAll() {
        return super.getAll("from WorkContract", WorkContract.class);
    }
}
