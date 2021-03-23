package by.bsuir.service.interfaces;

import by.bsuir.model.Privilege;

import java.util.List;

public interface PrivilegeService {
    List<Privilege> getAll();
    void update(Privilege privilege);
    void save(Privilege privilege);
    void delete(int id);
    Privilege findById(int id);
}
