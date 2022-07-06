package cloudServer.application.services;

import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<MyUser> getUserByID(long id) {
        return null;
    }

    @Override
    public List<MyUser> getAllUsers() {
        return null;
    }

    @Override
    public Optional<MyUser> loginUser(String username, String password) {
        return Optional.empty();
    }
}
