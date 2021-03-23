package by.bsuir.dao.interfaces;

import by.bsuir.model.Privilege;

import java.util.List;

public interface PrivilegeDAO {
    Privilege findById(int id);

    void save(Privilege privilege);

    void delete(Privilege privilege);

    void update(Privilege privilege);

    List<Privilege> getAll();
}
