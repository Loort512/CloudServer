package cloudServer.ports.web.api.user;

import cloudServer.application.services.ApiService;
import cloudServer.domain.user.MyUser;
import cloudServer.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:8080")//°°mr°° TODO: http://localhost:8080
public class UserController {

    private final UserService userService;

    private final HttpSession session;

    private final ApiService apiService;


    @GetMapping
    public ResponseEntity<List<MyUser>> getUsers(@RequestParam(required = false) Long id) {
        long sessionUID = apiService.getUserIdFromSession(session);
        List<MyUser> users = new ArrayList<>();
        if(id != null) {
            users.addAll(userService.getUserByID(id));
        }else {
            users.addAll(userService.getAllUsers());
        }

        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody CreateUserRequestBody createUserRequestBody) {
        if(createUserRequestBody.getUsername().equals("")) {
            return ResponseEntity.badRequest().body("username cannot be empty");
        }
        Optional<MyUser> existingUser = userService.getUserByUsername(createUserRequestBody.getUsername());

        if(existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("username already exists");
        }

        String token = userService.createUser(createUserRequestBody.getUsername(), createUserRequestBody.getPassword());

        return ResponseEntity.ok(token);
    }

    @GetMapping("/login")
    public ResponseEntity<String> processLogin(@RequestParam(required = true) String username,
                                                @RequestParam(required = true) String password) {

        Optional<MyUser> user = userService.loginUser(username, password);

        if(!user.isPresent()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "invalid Credentials");
        }

        return ResponseEntity.ok(user.get().getCustomToken());
    }
}
