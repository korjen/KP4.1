package by.bsuir.service.interfaces;

import by.bsuir.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    void update(User user);
    void save(User user);
    void delete(String id);
    User findById(String id);
    List<User> getAllForPagination(int currentPage, int recordsAmount);
    List<User> findByWorker(int id);
    boolean isPasswordMatch(User user, User authorisation);
}
