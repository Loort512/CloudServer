package cloudServer.application.services;

import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@Slf4j
@RequiredArgsConstructor
public class ApiService {

    private final UserService userService;

    public boolean isUserAdmin(HttpSession session) {
        return this.getUserFromSession(session).isAdmin();
    }

    public MyUser getUserFromSession(HttpSession session) {
        long userID = getUserIdFromSession(session);

        return userService.getUserByID(userID).get(0);
    }

    public int getUserIdFromSession(HttpSession session) {
        int userID = -1;
        try {
            userID = Integer.parseInt(session.getAttribute("UserID")+"");
        }catch (Exception ex) {
            log.error("Cannot parse UserID in Session : " + session.getAttribute("UserID")+"", ex);
        }

        return userID;
    }
}
