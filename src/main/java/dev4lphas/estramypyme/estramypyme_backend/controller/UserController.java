package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.User;
import dev4lphas.estramypyme.estramypyme_backend.repository.UserRepository;
import dev4lphas.estramypyme.estramypyme_backend.service.UserService;

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Listar todos los usuarios.
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Búsqueda de cualquier usuario por id.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Endpoint para manejar el login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        Optional<User> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                Map<String, Object> response = new HashMap<>();
                response.put("user", user);
                response.put("redirectUrl", getRedirectUrl(user));
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    private String getRedirectUrl(User user) {
        switch (user.getRoleName()) {
            case Admin:
                return "/dashboard-admin";
            case Teacher:
                return "/dashboard-teacher";
            case Student:
                return "/dashboard-student";
            default:
                return "/dashboard";
        }
    }
}