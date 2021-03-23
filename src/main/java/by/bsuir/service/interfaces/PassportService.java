package by.bsuir.service.interfaces;

import by.bsuir.model.Passport;

import java.util.List;

public interface PassportService {
    List<Passport> getAll();
    void update(Passport passport);
    void save(Passport passport);
    void delete(String id);
    Passport findById(String id);
}
