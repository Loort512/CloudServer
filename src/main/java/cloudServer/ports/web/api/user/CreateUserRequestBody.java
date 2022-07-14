package cloudServer.ports.web.api.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CreateUserRequestBody {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isAdmin = false;

    public static List<String> validate(CreateUserRequestBody user) {
        List<String> errors = new ArrayList<>();

        if(user.getUsername() == null || "".equals(user.getUsername())) {
           errors.add("Username cannot be empty.");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())) {
            errors.add("password cannot be empty.");
        }
        if(user.getFirstName() == null || "".equals(user.getFirstName())) {
            errors.add("first name cannot be empty.");
        }
        if(user.getLastName() == null || "".equals(user.getLastName())) {
            errors.add("last name cannot be empty.");
        }
        if(user.getFirstName() != null && !user.getFirstName().matches("[a-zA-Z]*")) {
            errors.add("first name must contain chars.");
        }
        if(user.getLastName() != null && !user.getLastName().matches("[a-zA-Z]*")) {
            errors.add("last name must contain chars.");
        }

        return errors;
    }
}
