package cloudServer.domain.user.service;

import cloudServer.domain.user.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<MyUser> getUserByID(long id);

    Optional<MyUser> getUserByUsername(String username);

    List<MyUser> getAllUsers();

    Optional<MyUser> loginUser(String username, String password);

    String createUser(String username, String password, String firstName, String lastName);

    Optional<MyUser> getUserByToken(String token);

    void deleteUser(MyUser myUser);
}
