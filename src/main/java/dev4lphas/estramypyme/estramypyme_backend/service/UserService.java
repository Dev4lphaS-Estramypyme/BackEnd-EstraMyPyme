package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dev4lphas.estramypyme.estramypyme_backend.model.User;
import dev4lphas.estramypyme.estramypyme_backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Listar todos los usuario
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Busqueda de cualquier usuario por id
    public User getUserById(Long idusuario) {
        return userRepository.findById(idusuario).orElse(null);
    }

    // Buscar admin por email
    public User getAdminByEmail(String email) {
        return userRepository.findAdminByEmail(email);
    }

    // Creación de admin, validando por correo, además rolename siempre admin
    public User createAdmin(User user) {
        User existingAdmin = userRepository.findAdminByEmail(user.getEmail());
        if (existingAdmin != null)
            return null;
        user.setRolename("Admin");
        return userRepository.save(user);
    }

    // Actualizar admin buscando por correo unico
    public User updateAdmin(String email, User user) {
        User existingAdmin = userRepository.findAdminByEmail(email);

        if (existingAdmin == null)
            return null;

        existingAdmin.setId(user.getId() == null ? existingAdmin.getId() : user.getId());
        existingAdmin.setEmail(user.getEmail() == null ? existingAdmin.getEmail() : user.getEmail());
        existingAdmin.setPassword(user.getPassword() == null ? existingAdmin.getPassword() : user.getPassword());
        existingAdmin.setName(user.getName() == null ? existingAdmin.getName() : user.getName());
        existingAdmin.setActive(existingAdmin.getActive());
        existingAdmin.setRolename(user.getRolename() == null ? existingAdmin.getRolename() : user.getRolename());

        return userRepository.save(existingAdmin);
    }
    
    // Buscar Teacher por email
    public User getTeacherByEmail(String email) {
        return userRepository.findTeacherByEmail(email);
    }

    // Creación de teacher, validando por correo, además rolename siempre teacher
    public User createTeacher(User user) {
        User existingTeacher = userRepository.findTeacherByEmail(user.getEmail());
        if (existingTeacher != null)
            return null;
        user.setRolename("Teacher");
        return userRepository.save(user);
    }

    // Actualizar teacher buscando por correo unico
    public User updateTeacher(String email, User user) {
        User existingTeacher = userRepository.findTeacherByEmail(email);

        if (existingTeacher == null)
            return null;

        existingTeacher.setId(user.getId() == null ? existingTeacher.getId() : user.getId());
        existingTeacher.setEmail(user.getEmail() == null ? existingTeacher.getEmail() : user.getEmail());
        existingTeacher.setPassword(user.getPassword() == null ? existingTeacher.getPassword() : user.getPassword());
        existingTeacher.setName(user.getName() == null ? existingTeacher.getName() : user.getName());
        existingTeacher.setActive(existingTeacher.getActive());
        existingTeacher.setRolename(existingTeacher.getRolename());

        return userRepository.save(existingTeacher);
    }

    // Actualizar role teacher buscando por correo unico
    public User updateTeacherRole(String email, User user) {
        User existingTeacher = userRepository.findTeacherByEmail(email);

        if (existingTeacher == null)
            return null;

        existingTeacher.setId(existingTeacher.getId());
        existingTeacher.setEmail(existingTeacher.getEmail());
        existingTeacher.setPassword(existingTeacher.getPassword());
        existingTeacher.setName(existingTeacher.getName());
        existingTeacher.setActive(existingTeacher.getActive());
        existingTeacher.setRolename(user.getRolename() == null ? existingTeacher.getRolename() : user.getRolename());

        return userRepository.save(existingTeacher);
    }

    // Actualizar active teacher buscando por correo unico
    public User updateTeacherActive(String email, User user) {
        User existingTeacher = userRepository.findTeacherByEmail(email);

        if (existingTeacher == null)
            return null;

        existingTeacher.setId(existingTeacher.getId());
        existingTeacher.setEmail(existingTeacher.getEmail());
        existingTeacher.setPassword(existingTeacher.getPassword());
        existingTeacher.setName(existingTeacher.getName());
        existingTeacher.setActive(user.getActive());
        existingTeacher.setRolename(existingTeacher.getRolename());

        return userRepository.save(existingTeacher);
    }


    // Buscar Student por email
    public User getStundentByEmail(String email) {
        return userRepository.findStudentByEmail(email);
    }

    // Creación de student, validando por correo, además rolename siempre student
    public User createStudent(User user) {
        User existingStudent = userRepository.findStudentByEmail(user.getEmail());
        if (existingStudent != null)
            return null;
        user.setRolename("Student");
        return userRepository.save(user);
    }

    // Actualizar student buscando por correo unico
    public User updateStudent(String email, User user) {
        User existingStudent = userRepository.findStudentByEmail(email);
        if (existingStudent == null)
            return null;

        existingStudent.setId(user.getId() == null ? existingStudent.getId() : user.getId());
        existingStudent.setEmail(user.getEmail() == null ? existingStudent.getEmail() : user.getEmail());
        existingStudent.setPassword(user.getPassword() == null ? existingStudent.getPassword() : user.getPassword());
        existingStudent.setName(user.getName() == null ? existingStudent.getName() : user.getName());
        existingStudent.setActive(existingStudent.getActive());
        existingStudent.setRolename(existingStudent.getRolename());

        return userRepository.save(existingStudent);
    }

    // Actualizar role student buscando por correo unico
    public User updateStudentRole(String email, User user) {
        User existingStudent = userRepository.findStudentByEmail(email);

        if (existingStudent == null)
            return null;

        existingStudent.setId(existingStudent.getId());
        existingStudent.setEmail(existingStudent.getEmail());
        existingStudent.setPassword(existingStudent.getPassword());
        existingStudent.setName(existingStudent.getName());
        existingStudent.setActive(existingStudent.getActive());
        existingStudent.setRolename(user.getRolename() == null ? existingStudent.getRolename() : user.getRolename());

        return userRepository.save(existingStudent);
    }

    // Actualizar active student buscando por correo unico
    public User updateStudentActive(String email, User user) {
        User existingStudent = userRepository.findStudentByEmail(email);

        if (existingStudent == null)
            return null;

        existingStudent.setId(existingStudent.getId());
        existingStudent.setEmail(existingStudent.getEmail());
        existingStudent.setPassword(existingStudent.getPassword());
        existingStudent.setName(existingStudent.getName());
        existingStudent.setActive(user.getActive());
        System.out.println(user);
        existingStudent.setRolename(existingStudent.getRolename());

        return userRepository.save(existingStudent);
    }



}
