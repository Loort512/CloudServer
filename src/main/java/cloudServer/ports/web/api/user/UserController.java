package cloudServer.ports.web.api.user;

import cloudServer.application.services.ApiService;
import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    private final HttpSession session;

    private final ApiService apiService;


    @GetMapping
    public ResponseEntity<List<UserDataResponse>> getUsers(@RequestParam(required = false) Long id) {
        long sessionUID = apiService.getUserIdFromSession(session);
        List<MyUser> users = new ArrayList<>();
        if(id == null) {
            if(apiService.isUserAdmin(session)) {
                users.addAll(userService.getAllUsers());
            }else {
                log.info("User {} tried to access all User Data. But has no admin role!", sessionUID);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        }else {
            Optional<MyUser> myUser = userService.getUserByID(id);
            if(myUser.isPresent()) {
                users.add(myUser.get());
            }
        }
        List<UserDataResponse> response = users
                .stream()
                .map(myUser -> UserDataResponse.of(myUser))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequestBody createUserRequestBody) {
        List<String> validationErrors = CreateUserRequestBody.validate(createUserRequestBody);
        if(!validationErrors.isEmpty()) {
            return ResponseEntity.badRequest().body(validationErrors.stream().collect(Collectors.joining("\n")));
        }

        Optional<MyUser> existingUser = userService.getUserByUsername(createUserRequestBody.getUsername());

        if(existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("username already exists");
        }

        String token = userService.createUser(createUserRequestBody.getUsername(),
                createUserRequestBody.getPassword(),
                createUserRequestBody.getFirstName(),
                createUserRequestBody.getLastName());

        return ResponseEntity.status(HttpStatus.CREATED).body(token);
    }

    @GetMapping("/login")
    public ResponseEntity<UserResponse> processLogin(@RequestParam(required = true) String username,
                                                @RequestParam(required = true) String password) {
        UserResponse userResponse = new UserResponse();
        Optional<MyUser> user = userService.loginUser(username, password);

        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "invalid Credentials");
        }

        userResponse.setToken(user.get().getCustomToken());
        userResponse.setAdmin(user.get().isAdmin());

        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        Optional<MyUser> user = userService.getUserByID(id);

        if(!user.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        userService.deleteUser(user.get());

        return ResponseEntity.status(204).build();
    }
}
