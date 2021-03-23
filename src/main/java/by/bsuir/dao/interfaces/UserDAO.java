package by.bsuir.dao.interfaces;

import by.bsuir.model.User;

import java.util.List;

public interface UserDAO {
    User findById(String id);

    void save(User user);

    void delete(User user);

    void update(User user);

    List<User> getAll();

    List<User> getAllForPagination(int currentPage, int numberOfRecordsPerPage);

    List<User> findByWorker(int id);
}
