package by.bsuir.service.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(final HttpServletRequest httpServletRequest,
                                        final HttpServletResponse httpServletResponse,
                                        final Authentication authentication) throws IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String contextPath = httpServletRequest.getContextPath();
        if (roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect(contextPath + "/admin/");
        }
        else if (roles.contains("ROLE_WORKER")) {
            httpServletResponse.sendRedirect(contextPath + "/account/");
        }
        else if (roles.contains("ROLE_MANAGER")) {
            httpServletResponse.sendRedirect(contextPath + "/manager/");
        }
    }
}
