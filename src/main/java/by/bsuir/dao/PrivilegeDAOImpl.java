package by.bsuir.dao;

import by.bsuir.dao.interfaces.PrivilegeDAO;
import by.bsuir.model.Privilege;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class PrivilegeDAOImpl extends AbstractBaseDAO<Privilege> implements PrivilegeDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(WorkingTimeDAOImpl.class);
    @Override
    public Privilege findById(int id) {
        return super.findByIntId(id, Privilege.class);
    }

    @Override
    public void save(Privilege privilege) {
        super.save(privilege);
    }

    @Override
    public void delete(Privilege privilege) {
        super.delete(privilege);
    }

    @Override
    public void update(Privilege privilege) {
        super.update(privilege);
    }

    @Override
    public List<Privilege> getAll() {return super.getAll("from Privilege", Privilege.class);
    }
}
