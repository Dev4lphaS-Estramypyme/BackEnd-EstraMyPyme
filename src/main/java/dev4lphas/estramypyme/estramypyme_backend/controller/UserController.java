package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.User;
import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany; // Asegúrate de importar esta clase
import dev4lphas.estramypyme.estramypyme_backend.repository.UserRepository;
import dev4lphas.estramypyme.estramypyme_backend.service.UserService;
import dev4lphas.estramypyme.estramypyme_backend.service.UserCompanyService; // Asegúrate de importar esta clase también

@RestController
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCompanyService userCompanyService; // Asegúrate de tener esta inyección de dependencia

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

    // Búsqueda de admin por correo.
    @GetMapping("/admin/{email}")
    public User getAdminByEmail(@PathVariable String email) {
        return userService.getAdminByEmail(email);
    }

    // Creación de admin.
    @PostMapping("/admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        User newAdmin = userService.createAdmin(user);
        if (newAdmin == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    // Actualizar admin buscando por email.
    @PutMapping("/admin/update/{email}")
    public ResponseEntity<?> updateAdmin(@PathVariable String email, @RequestBody User user) {
        User updatedAdmin = userService.updateAdmin(email, user);
    
        if (updatedAdmin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with email: " + email + " not found.");
        }
    
        Map<String, Object> response = new HashMap<>();
        response.put("updatedAdmin", updatedAdmin);
        response.put("message", "Admin with email: " + email + " has been updated.");
        return ResponseEntity.ok(response);
    }

    // Eliminación de Admin por id.
    @DeleteMapping("/admin/deleteById/{id}")
    public ResponseEntity<String> deleteAdminById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with ID " + id + " not found.");
        }
        try {
            userService.deleteAdminById(id);
            return ResponseEntity.ok("Admin with ID " + id + " has been deleted.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete admin with ID " + id
                    + " because they have related test assignments. But if necessary you can deactivate it ");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin with ID " + id + " not found.");
        }
    }

    // Búsqueda de Estudiante por correo.
    @GetMapping("/student/{email}")
    public User getStudentByEmail(@PathVariable String email) {
        return userService.getStudentByEmail(email);
    }

    // Creación de student.
    @PostMapping("/student")
    public ResponseEntity<User> createStudent(@RequestBody User user) {
        User newStudent = userService.createStudent(user);
        if (newStudent == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    // Actualizar student buscando por email.
    @PutMapping("/student/update/{email}")
    public ResponseEntity<User> updateStudent(@PathVariable String email, @RequestBody User user) {
        User updateStudent = userService.updateStudent(email, user);

        if (updateStudent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    // Actualizar role student buscando por email.
    @PutMapping("/student/role/{email}")
    public ResponseEntity<?> updateStudentRole(@PathVariable String email, @RequestBody Map<String, Integer> request) {
        Integer roleNumber = request.get("rolename");
        if (roleNumber == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Role number is required.");
        }
    
        try {
            User updatedStudentRole = userService.updateStudentRole(email, roleNumber);
            if (updatedStudentRole == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedStudentRole, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // Actualizar active student buscando por email.
    @PutMapping("/student/active/{email}")
    public ResponseEntity<User> updateStudentActive(@PathVariable String email, @RequestBody User user) {
        User updateStudentActive = userService.updateStudentActive(email, user);

        if (updateStudentActive == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateStudentActive, HttpStatus.OK);
    }

    // Eliminación de Student por id.
    @DeleteMapping("/student/deleteById/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student with ID " + id + " not found.");
        }
        try {
            userService.deleteStudentById(id);
            return ResponseEntity.ok("Student with ID " + id + " has been deleted.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete Student with ID " + id
                    + " because they have related test assignments. But if necessary you can deactivate it ");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student with ID " + id + " not found.");
        }
    }

    // Búsqueda de Profesor por correo.
    @GetMapping("/teacher/{email}")
    public User getTeacherByEmail(@PathVariable String email) {
        return userService.getTeacherByEmail(email);
    }

    // Creación de teacher.
    @PostMapping("/teacher")
    public ResponseEntity<User> createTeacher(@RequestBody User user) {
        User newTeacher = userService.createTeacher(user);
        if (newTeacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // Actualizar teacher buscando por email.
    @PutMapping("/teacher/update/{email}")
    public ResponseEntity<User> updateTeacher(@PathVariable String email, @RequestBody User user) {
        User updateTeacher = userService.updateTeacher(email, user);

        if (updateTeacher == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateTeacher, HttpStatus.OK);
    }

    // Actualizar role teacher buscando por email.
    @PutMapping("/teacher/role/{email}")
    public ResponseEntity<?> updateTeacherRole(@PathVariable String email, @RequestBody Map<String, Integer> request) {
        Integer roleNumber = request.get("rolename");
        if (roleNumber == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Role number is required.");
        }
    
        try {
            User updatedTeacherRole = userService.updateTeacherRole(email, roleNumber);
            if (updatedTeacherRole == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updatedTeacherRole, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    // Actualizar active teacher buscando por email.
    @PutMapping("/teacher/active/{email}")
    public ResponseEntity<User> updateTeacherActive(@PathVariable String email, @RequestBody User user) {
        User updateTeacherActive = userService.updateTeacherActive(email, user);

        if (updateTeacherActive == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateTeacherActive, HttpStatus.OK);
    }

    // Eliminación de Teacher por id
    @DeleteMapping("/teacher/deleteById/{id}")
    public ResponseEntity<String> deleteTeacherById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Teacher with ID " + id + " not found.");
        }
        try {
            userService.deleteTeacherById(id);
            return ResponseEntity.ok("Teacher with ID " + id + " has been deleted.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cannot delete Teacher with ID " + id
                    + " because they have related test assignments. But if necessary you can deactivate it ");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher with ID " + id + " not found.");
        }
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
                response.put("role", user.getRoleName().toString());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
            }
        } else {
            Optional<UserCompany> userCompanyOptional = userCompanyService.getUserCompanyByEmail(email);
            if (userCompanyOptional.isPresent()) {
                UserCompany userCompany = userCompanyOptional.get();
                if (userCompany.getPassword().equals(password)) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("user", userCompany);
                    response.put("role", "Company");
                    return new ResponseEntity<>(response, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Invalid password", HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
            }
        }
    }
}