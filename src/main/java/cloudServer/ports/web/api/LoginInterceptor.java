package cloudServer.ports.web.api;

import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final UserService userService;

    //This method is executed before accessing the interface. We only need to write the business logic to verify the login status here to verify the login status before the user calls the specified interface.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpResponse, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if(request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            httpResponse.setStatus(200);
            return true;
        }

        // URLs without login:/register
        if(request.getRequestURL().toString().endsWith("/login")
                || request.getRequestURL().toString().endsWith("/register") ) {
            return true;
        }

        String token = request.getHeader("AuthorizationToken");


        Optional<MyUser> user = userService.getUserByToken(token);
        if(!user.isPresent()) {
            httpResponse.setStatus(403);
            httpResponse.getWriter().println("invalid token!");
            return false;
        }
        session.setAttribute("UserID", user.get().getId());

        return true;
    }

}
