package by.bsuir.service.security;

import by.bsuir.dao.interfaces.UserDAO;
import by.bsuir.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String username) {
        User user = userDAO.findById(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), grantedAuthorities);
    }
    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
