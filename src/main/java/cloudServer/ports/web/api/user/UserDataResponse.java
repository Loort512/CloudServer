package cloudServer.ports.web.api.user;

import cloudServer.domain.user.MyUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDataResponse {

    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private boolean isAdmin;

    public static UserDataResponse of(MyUser myUser) {
        return new UserDataResponse(myUser.getId(),
                myUser.getUsername(),
                myUser.getFirstName(),
                myUser.getLastName(),
                myUser.isAdmin());
    }
}
