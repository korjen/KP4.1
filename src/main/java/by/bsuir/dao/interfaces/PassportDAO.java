package by.bsuir.dao.interfaces;

import by.bsuir.model.Passport;

import java.util.List;

public interface PassportDAO {
    Passport findById(String id);

    void save(Passport passport);

    void delete(Passport passport);

    void update(Passport passport);

    List<Passport> getAll();

}
