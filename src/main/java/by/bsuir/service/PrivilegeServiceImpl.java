package by.bsuir.service;

import by.bsuir.dao.interfaces.PrivilegeDAO;
import by.bsuir.model.Privilege;
import by.bsuir.service.interfaces.PrivilegeService;

import java.util.List;

public class PrivilegeServiceImpl implements PrivilegeService {
    private PrivilegeDAO privilegeDAO;

    @Override
    public List<Privilege> getAll() {
        return privilegeDAO.getAll();
    }

    @Override
    public void update(Privilege privilege) {
        privilegeDAO.update(privilege);
    }

    @Override
    public void save(Privilege privilege) {
        privilegeDAO.save(privilege);
    }

    @Override
    public void delete(int id) {
        Privilege privilege = privilegeDAO.findById(id);
        if(privilege!=null) privilegeDAO.delete(privilege);
    }

    @Override
    public Privilege findById(int id) {
        return privilegeDAO.findById(id);
    }

    public void setPrivilegeDAO(PrivilegeDAO privilegeDAO) {
        this.privilegeDAO = privilegeDAO;
    }
}
