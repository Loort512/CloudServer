package cloudServer.ports.web.api;

import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final UserService userService;

    //This method is executed before accessing the interface. We only need to write the business logic to verify the login status here to verify the login status before the user calls the specified interface.
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
return true;
 /*       String token = request.getHeader("token");
        if(request.getRequestURL().toString().contains("/login") ||
                request.getRequestURL().toString().contains("/register") ||
                userService.getUserByToken(token).isPresent()) {
            return true;
        }
        response.setStatus(403);
        return false;*/
        /*if (handler instanceof HandlerMethod) {
            NeedLogin needLogin = ((HandlerMethod) handler).getMethodAnnotation(NeedLogin.class);
            if (null == needLogin) {
                needLogin = ((HandlerMethod) handler).getMethod().getDeclaringClass()
                        .getAnnotation(NeedLogin.class);
            }
            // Check login if you have login validation annotations
            if (null != needLogin) {
                WxUserInfoContext curUserContext = (WxUserInfoContext) request.getSession()
                        .getAttribute("curUserContext");
                //If session No, not logged in.
                if (null == curUserContext) {
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("Not logged in!");
                    return false;
                }
            }

        }*/

    }
}
