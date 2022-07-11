package cloudServer.ports.web.api.user;

import lombok.Data;

@Data
public class CreateUserRequestBody {

    private String username;
    private String password;
}
