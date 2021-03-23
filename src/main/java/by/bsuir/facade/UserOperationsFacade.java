package by.bsuir.facade;

import by.bsuir.converter.UserConverter;
import by.bsuir.dto.UserDTO;
import by.bsuir.model.User;
import by.bsuir.ApplicationProperties;
import by.bsuir.model.Worker;
import by.bsuir.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class UserOperationsFacade {

    private UserService userService;

    private UserConverter userConverter;

    private ApplicationProperties applicationProperties;

    public ModelAndView delete(final String userID) {
        userService.delete(userID);
        return  createUserListPage(1);
    }

    public UserDTO findById(final String id) {
        User user = userService.findById(id);
        return userConverter.convert(user);
    }

    public void setUserService(final UserService userService) {
        this.userService = userService;
    }

    public void setUserConverter(final UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Transactional
    public ModelAndView createUserListPage(final int page) {
        ModelAndView mav = new ModelAndView("userList");
        int recordsPerPage = Integer.valueOf(applicationProperties.getAmountOfRecordsPerPage());
        List<User> list = userService.getAllForPagination((page - 1) * recordsPerPage,
                recordsPerPage);
        int noOfRecords = userService.getAll().size();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        mav.addObject("noOfPages", noOfPages);
        mav.addObject("currentPage", page);
        mav.addObject("usersList", list);
        return mav;
    }

    public ModelAndView createCurrentUserPage() {
        ModelAndView mav = new ModelAndView("userProfile");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        UserDTO userDTO = userConverter.convert(user);
        mav.addObject("user", user);
        mav.addObject("userDTO", userDTO);
        return mav;
    }

    public ModelAndView changePassword(UserDTO userDTO) {
        User user = userService.findById(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        userService.update(user);
        return new ModelAndView("redirect:/exit");
    }

    public ModelAndView createUserUpdatePage(String id) {
        ModelAndView mav = new ModelAndView("userUpdate");
        User user = userService.findById(id);
        mav.addObject("user", user);
        return mav;
    }

    public int isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(currentUser);
        if (currentUser==null) return -2;
        if(user.getRole().equals("ROLE_ADMIN")) return 1;
        else if(user.getRole().equals("ROLE_MANAGER")) return 0;
        else return -1;
    }

    public ModelAndView updateUser(String id, UserDTO userDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = authentication.getName();
        User user = userService.findById(id);
        boolean noExit=false;
        if (user.getRole().equals(userDTO.getRole())) noExit = true;
        user.setRole(userDTO.getRole());
        if (userDTO.getPassword()!=null && !userDTO.getPassword().isEmpty()) user.setPassword(userDTO.getPassword());
        userService.update(user);
        if (noExit && id.equals(currentUser)) return new ModelAndView("redirect:/admin/users/list");
        if (id.equals(currentUser)) return new ModelAndView("redirect:/exit");
        else return new ModelAndView("redirect:/admin/users/list");
    }

    public void update(final User user) {
        userService.update(user);
    }

    public void setApplicationProperties(final ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
}
