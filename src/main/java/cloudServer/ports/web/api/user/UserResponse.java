package cloudServer.ports.web.api.user;

import lombok.Data;

@Data
public class UserResponse {

    private String token;
    private boolean isAdmin;
}
