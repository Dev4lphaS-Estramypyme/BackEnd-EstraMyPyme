package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.DTO.LoginRequest;
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


    // Obtener un admin por email.
    @GetMapping("/admin/{email}")
    public ResponseEntity<User> getAdminByEmail(@PathVariable String email) {
        User admin = userService.getAdminByEmail(email);
        if (admin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(admin, HttpStatus.OK);
    }

    // Crear un nuevo admin.
    @PostMapping("/admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        User newAdmin = userService.createAdmin(user);
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    // Actualizar un admin existente.
    @PutMapping("/admin/{email}")
    public ResponseEntity<User> updateAdmin(@PathVariable String email, @RequestBody User user) {
        User updatedAdmin = userService.updateAdmin(email, user);
        if (updatedAdmin == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedAdmin, HttpStatus.OK);
    }

    // Eliminar un admin por id.
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Object> deleteAdminById(@PathVariable Long id) {
        userService.deleteAdminById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener un estudiante por email.
    @GetMapping("/student/{email}")
    public ResponseEntity<User> getStudentByEmail(@PathVariable String email) {
        User student = userService.getStudentByEmail(email);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    // Crear un nuevo estudiante.
    @PostMapping("/student")
    public ResponseEntity<User> createStudent(@RequestBody User user) {
        User newStudent = userService.createStudent(user);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    // Actualizar un estudiante existente.
    @PutMapping("/student/{email}")
    public ResponseEntity<User> updateStudent(@PathVariable String email, @RequestBody User user) {
        User updatedStudent = userService.updateStudent(email, user);
        if (updatedStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }


    // Actualizar el estado activo de un estudiante.
    @PutMapping("/student/active/{email}")
    public ResponseEntity<User> updateStudentActive(@PathVariable String email, @RequestBody User user) {
        User updatedStudent = userService.updateStudentActive(email, user);
        if (updatedStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
    }

    // Eliminar un estudiante por id.
    @DeleteMapping("/student/{id}")
    public ResponseEntity<Object> deleteStudentById(@PathVariable Long id) {
        userService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Obtener un profesor por email.
    @GetMapping("/teacher/{email}")
    public ResponseEntity<User> getTeacherByEmail(@PathVariable String email) {
        User teacher = userService.getTeacherByEmail(email);
        if (teacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    // Crear un nuevo profesor.
    @PostMapping("/teacher")
    public ResponseEntity<User> createTeacher(@RequestBody User user) {
        User newTeacher = userService.createTeacher(user);
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // Actualizar un profesor existente.
    @PutMapping("/teacher/{email}")
    public ResponseEntity<User> updateTeacher(@PathVariable String email, @RequestBody User user) {
        User updatedTeacher = userService.updateTeacher(email, user);
        if (updatedTeacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }


    // Actualizar el estado activo de un profesor.
    @PutMapping("/teacher/active/{email}")
    public ResponseEntity<User> updateTeacherActive(@PathVariable String email, @RequestBody User user) {
        User updatedTeacher = userService.updateTeacherActive(email, user);
        if (updatedTeacher == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTeacher, HttpStatus.OK);
    }

    // Eliminar un profesor por id.
    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Object> deleteTeacherById(@PathVariable Long id) {
        userService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para manejar el login
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> user = userService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("user", user.get());
            response.put("redirectUrl", getRedirectUrl(user.get()));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Correo y/o contraseña incorrectos.", HttpStatus.UNAUTHORIZED);
        }
    }

    private String getRedirectUrl(User user) {
        return "/dashboard-admin";
    }
}