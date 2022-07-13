package cloudServer.application.services;

import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.UserRepository;
import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<MyUser> getUserByID(long id) {
        return null;
    }

    @Override
    public Optional<MyUser> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<MyUser> getAllUsers() {
        return null;
    }

    @Override
    public Optional<MyUser> loginUser(String username, String password) {
        Optional<MyUser> user = userRepository.findByUsername(username);
        if(user.isPresent() && user.get().getCustomPassword().equals(password)) {

            // generate Token for session:
            StringBuilder token = new StringBuilder();
            long currentTimeInMilisecond = Instant.now().toEpochMilli();
            String generatedToken = token.append(currentTimeInMilisecond).append("-")
                    .append(UUID.randomUUID().toString()).toString();

            log.info(String.format("user with id: %s got token: %s", user.get().getId(), user.get().getCustomToken()));

            user.get().setCustomToken(generatedToken);
            userRepository.save(user.get());

            return user;
        }


        return Optional.empty();
    }

    @Override
    public String createUser(String username, String password) {
        MyUser myUser = new MyUser(username, password, false);
        StringBuilder token = new StringBuilder();
        long currentTimeInMilisecond = Instant.now().toEpochMilli();
        token.append(currentTimeInMilisecond).append("-")
                .append(UUID.randomUUID().toString()).toString();
        myUser.setCustomToken(token.toString().substring(0,5));
        userRepository.save(myUser);
        return token.toString();
    }

    @Override
    public Optional<MyUser> getUserByToken(String token) {
        return userRepository.findByCustomToken(token);
    }
}
