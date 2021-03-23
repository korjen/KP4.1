package by.bsuir.converter;

import by.bsuir.dto.UserDTO;
import by.bsuir.model.User;

public class UserConverter implements TwoWayConverter<User, UserDTO> {

    @Override
    public UserDTO convert(User user) {
        if (user!=null) return new UserDTO(user.getLogin(),user.getRole(),user.getPassword());
        else return null;
    }

    @Override
    public User convertBack(UserDTO target) {
        return new User(target.getLogin(),target.getRole(),target.getPassword());
    }
}
