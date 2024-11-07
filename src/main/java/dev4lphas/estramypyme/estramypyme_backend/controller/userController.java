package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev4lphas.estramypyme.estramypyme_backend.model.User;
import dev4lphas.estramypyme.estramypyme_backend.service.UserService;

@RestController
@RequestMapping("api/users")
public class userController {

    @Autowired
    private UserService userService;

    // Listar todos los usuario
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    // Busqueda de cualquier usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Busqueda de Admin por correo
    @GetMapping("/admin/{email}")
    public User getAdminByEmail(@PathVariable String email) {
        return userService.getAdminByEmail(email);
    }

    // Busqueda de Estudiante por correo
    @GetMapping("/student/{email}")
    public User getStundentByEmail(@PathVariable String email) {
        return userService.getStundentByEmail(email);
    }

    // Busqueda de Profesor por correo
    @GetMapping("/teacher/{email}")
    public User getTeacherByEmail(@PathVariable String email) {
        return userService.getTeacherByEmail(email);
    }

    // Creación de admin, validando por correo.
    @PostMapping("/admin")
    public ResponseEntity<User> createAdmin(@RequestBody User user) {
        User newAdmin = userService.createAdmin(user);
        if (newAdmin == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newAdmin, HttpStatus.CREATED);
    }

    // Actualizar admin buscando por correo único.
    @PutMapping("/admin/{email}")
    public ResponseEntity<User> updateAdmin(@PathVariable String email, @RequestBody User user) {
        User updateAdmin = userService.updateAdmin(email, user);

        if (updateAdmin == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateAdmin, HttpStatus.OK);
    }


    // Creación de teacher, validando por correo.
    @PostMapping("/teacher")
    public ResponseEntity<User> createTeacher(@RequestBody User user) {
        User newTeacher = userService.createTeacher(user);
        if (newTeacher == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newTeacher, HttpStatus.CREATED);
    }

    // Actualizar teacher buscando por correo único.
    @PutMapping("/teacher/{email}")
    public ResponseEntity<User> updateTeacher(@PathVariable String email, @RequestBody User user) {
        User updateTeacher = userService.updateTeacher(email, user);

        if (updateTeacher == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateTeacher, HttpStatus.OK);
    }

    // Actualizar role teacher buscando por correo único.
    @PutMapping("/teacher/role/{email}")
    public ResponseEntity<User> updateTeacherRole(@PathVariable String email, @RequestBody User user) {
        User updateTeacherRole = userService.updateTeacherRole(email, user);

        if (updateTeacherRole == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateTeacherRole, HttpStatus.OK);
    }

    // Actualizar active teacher buscando por correo único.
    @PutMapping("/teacher/active/{email}")
    public ResponseEntity<User> updateTeacherActive(@PathVariable String email, @RequestBody User user) {
        User updateTeacherActive = userService.updateTeacherActive(email, user);

        if (updateTeacherActive == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateTeacherActive, HttpStatus.OK);
    }


    // Creación de student, validando por correo.
    @PostMapping("/student")
    public ResponseEntity<User> createStudent(@RequestBody User user) {
        User newStudent = userService.createStudent(user);
        if (newStudent == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    // Actualizar teacher buscando por correo único.
    @PutMapping("/student/{email}")
    public ResponseEntity<User> updateStudent(@PathVariable String email, @RequestBody User user) {
        User updateStudent = userService.updateStudent(email, user);

        if (updateStudent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    // Actualizar role teacher buscando por correo único.
    @PutMapping("/student/role/{email}")
    public ResponseEntity<User> updateStudentRole(@PathVariable String email, @RequestBody User user) {
        User updateStudentRole = userService.updateStudentRole(email, user);

        if (updateStudentRole == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateStudentRole, HttpStatus.OK);
    }

    // Actualizar active teacher buscando por correo único.
    @PutMapping("/student/active/{email}")
    public ResponseEntity<User> updateStudentActive(@PathVariable String email, @RequestBody User user) {
        User updateStudentActive = userService.updateStudentActive(email, user);

        if (updateStudentActive == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(updateStudentActive, HttpStatus.OK);
    }

}
