package by.bsuir.dao;

import by.bsuir.dao.interfaces.PassportDAO;
import by.bsuir.model.Passport;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PassportDAOImpl extends AbstractBaseDAO<Passport> implements PassportDAO {
    private final static Logger LOGGER = LoggerFactory.getLogger(PassportDAOImpl.class);

    @Override
    protected List<Passport> getAll(String queryString, Class<Passport> type) {
        return super.getAll(queryString, type);
    }

    @Override
    public Passport findById(String id) {
        return super.findByStringId(id, Passport.class);
    }

    @Override
    public void save(Passport passport) {
        super.save(passport);
    }

    @Override
    public void delete(Passport passport) {
        super.delete(passport);
    }

    @Override
    public void update(Passport passport) {
        super.update(passport);
    }

    @Override
    public List<Passport> getAll() {
        return super.getAll("from Passport", Passport.class);
    }
}
