package by.bsuir.service;

import by.bsuir.dao.interfaces.UserDAO;
import by.bsuir.model.User;
import by.bsuir.service.interfaces.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }

    @Override
    public List<User> getAllForPagination(final int currentPage, final int recordsAmount) {
        return userDAO.getAllForPagination(currentPage, recordsAmount);
    }

    @Override
    public void update(User user) {
        if (!user.getPassword().contains("$2a$11$"))
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.update(user);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(String id) {
        User user = userDAO.findById(id);
        if(user!=null) userDAO.delete(user);
    }

    @Override
    public User findById(String id) {
        return userDAO.findById(id);
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean isPasswordMatch(User user, User authorisation) {
        return bCryptPasswordEncoder.matches(authorisation.getPassword(),user.getPassword());
    }

    @Override
    public List<User> findByWorker(int id) {
        return userDAO.findByWorker(id);
    }
}
