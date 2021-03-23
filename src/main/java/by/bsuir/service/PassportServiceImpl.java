package by.bsuir.service;

import by.bsuir.dao.interfaces.PassportDAO;
import by.bsuir.model.Passport;
import by.bsuir.service.interfaces.PassportService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PassportServiceImpl implements PassportService {
    private PassportDAO passportDAO;

    @Override
    public List<Passport> getAll() {
        return passportDAO.getAll();
    }

    @Override
    public void update(Passport passport) {
        passportDAO.update(passport);
    }

    @Override
    public void save(Passport passport) {
        passportDAO.save(passport);
    }

    @Override
    public void delete(String id) {
        Passport passport = passportDAO.findById(id);
        if (passport!=null) passportDAO.delete(passport);
    }

    @Override
    public Passport findById(String id) {
        return passportDAO.findById(id);
    }

    public void setPassportDAO(PassportDAO passportDAO) {
        this.passportDAO = passportDAO;
    }
}
