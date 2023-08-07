package jdbc.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RoleBasedLandingPage extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String role = authentication.getAuthorities().toString();
        String landingUrl = "";
        if (role.contains("Admin")) {
            landingUrl = "/table";
        } else {
            landingUrl = "/hello";
        }
        getRedirectStrategy().sendRedirect(request, response, landingUrl);
    }

}
