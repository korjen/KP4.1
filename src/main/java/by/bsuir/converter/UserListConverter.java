package by.bsuir.converter;

import by.bsuir.dto.UserDTO;
import by.bsuir.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserListConverter implements ListTwoWayConverter<User, UserDTO> {
    private UserConverter userConverter;

    @Override
    public List<UserDTO> convertList(List<User> source) {
        List<UserDTO> userDTOs =new ArrayList<>();
        for (User user : source) {
            userDTOs.add(userConverter.convert(user));
        }
        return userDTOs;
    }

    @Override
    public List<User> convertBackList(List<UserDTO> target) {
        List<User> users = new ArrayList<>();
        for(UserDTO userDTO : target){
            users.add(userConverter.convertBack(userDTO));
        }
        return users;
    }

    public void setUserConverter(UserConverter userConverter) {
        this.userConverter = userConverter;
    }
}
