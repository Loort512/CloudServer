package cloudServer.domain.user.service;

import cloudServer.domain.user.MyUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<MyUser> getUserByID(long id);

    List<MyUser> getAllUsers();

    Optional<MyUser> loginUser(String username, String password);
}
