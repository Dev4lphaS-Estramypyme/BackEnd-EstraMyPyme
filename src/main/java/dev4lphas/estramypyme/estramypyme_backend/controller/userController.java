package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev4lphas.estramypyme.estramypyme_backend.model.User;
import dev4lphas.estramypyme.estramypyme_backend.repository.UserRepository;
import dev4lphas.estramypyme.estramypyme_backend.service.UserService;

@RestController
@RequestMapping("api/users")
public class userController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // Listar todos los usuario.
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Busqueda de cualquier usuario por id.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Busqueda de admin por correo.
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
    /*
     * @PutMapping("/admin/update/{email}")
     * public ResponseEntity<User> updateAdmin(@PathVariable String
     * email, @RequestBody User user) {
     * User updateAdmin = userService.updateAdmin(email, user);
     * 
     * if (updateAdmin == null)
     * return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     * 
     * return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
     * }
     */

/*     @PutMapping("/admin/update/{email}")
    public ResponseEntity<String> updateAdmin(@PathVariable String email, @RequestBody User user) {
       User updateAdmin = userService.updateAdmin(email, user);
   
       if (updateAdmin == null) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Admin with email: " + email + " not found.");
       }
       userService.updateAdmin(email, user);
          
       return ResponseEntity.ok("Admin with email: " + email + " has been updated." + updateAdmin);
   } */

    @PutMapping("/admin/update/{email}")
    public ResponseEntity<?> updateAdmin(@PathVariable String email, @RequestBody User user) {
        System.out.println(email);
        User updateAdmin = userService.updateAdmin(email, user);

        if (updateAdmin == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Admin with email: " + email + " not found.");
        }
        userService.updateAdmin(email, user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("updatedAdmin", updateAdmin);
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

    // Busqueda de Estudiante por correo.
    @GetMapping("/student/{email}")
    public User getStundentByEmail(@PathVariable String email) {
        return userService.getStundentByEmail(email);
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
    public ResponseEntity<User> updateStudentRole(@PathVariable String email, @RequestBody User user) {
        User updateStudentRole = userService.updateStudentRole(email, user);

        if (updateStudentRole == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateStudentRole, HttpStatus.OK);
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

    // Busqueda de Profesor por correo.
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
    public ResponseEntity<User> updateTeacherRole(@PathVariable String email, @RequestBody User user) {
        User updateTeacherRole = userService.updateTeacherRole(email, user);

        if (updateTeacherRole == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateTeacherRole, HttpStatus.OK);
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

}
