package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.User;
import dev4lphas.estramypyme.estramypyme_backend.model.User.RoleName;
import dev4lphas.estramypyme.estramypyme_backend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean isAdminOrTeacher(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null && (user.getRoleName() == User.RoleName.Admin || user.getRoleName() == User.RoleName.Teacher);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getAdminByEmail(String email) {
        return userRepository.findAdminByEmail(email);
    }

    public User createAdmin(User user) {
        user.setRoleName(User.RoleName.Admin);
        return userRepository.save(user);
    }

    public User updateAdmin(String email, User user) {
        User existingAdmin = userRepository.findAdminByEmail(email);
        if (existingAdmin != null) {
            existingAdmin.setName(user.getName());
            existingAdmin.setPassword(user.getPassword());
            existingAdmin.setActive(user.isActive());
            existingAdmin.setEmail(user.getEmail());
            return userRepository.save(existingAdmin);
        }
        return null;
    }

    public void deleteAdminById(Long id) {
        userRepository.deleteById(id);
    }

    public User getStudentByEmail(String email) {
        return userRepository.findStudentByEmail(email);
    }

    public User createStudent(User user) {
        user.setRoleName(User.RoleName.Student);
        return userRepository.save(user);
    }

    public User updateStudent(String email, User user) {
        User existingStudent = userRepository.findStudentByEmail(email);
        if (existingStudent != null) {
            existingStudent.setName(user.getName());
            existingStudent.setPassword(user.getPassword());
            existingStudent.setActive(user.isActive());
            return userRepository.save(existingStudent);
        }
        return null;
    }

    public User updateStudentRole(String email, int roleNumber) {
        User existingStudent = userRepository.findStudentByEmail(email);
        if (existingStudent != null) {
            existingStudent.setRoleName(convertToRoleName(roleNumber));
            return userRepository.save(existingStudent);
        }
        return null;
    }

    public User updateStudentActive(String email, User user) {
        User existingStudent = userRepository.findStudentByEmail(email);
        if (existingStudent != null) {
            existingStudent.setActive(user.isActive());
            return userRepository.save(existingStudent);
        }
        return null;
    }

    public void deleteStudentById(Long id) {
        userRepository.deleteById(id);
    }

    public User getTeacherByEmail(String email) {
        return userRepository.findTeacherByEmail(email);
    }

    public User createTeacher(User user) {
        user.setRoleName(User.RoleName.Teacher);
        return userRepository.save(user);
    }

    public User updateTeacher(String email, User user) {
        User existingTeacher = userRepository.findTeacherByEmail(email);
        if (existingTeacher != null) {
            existingTeacher.setName(user.getName());
            existingTeacher.setPassword(user.getPassword());
            existingTeacher.setActive(user.isActive());
            return userRepository.save(existingTeacher);
        }
        return null;
    }

    public User updateTeacherRole(String email, int roleNumber) {
        User existingTeacher = userRepository.findTeacherByEmail(email);
        if (existingTeacher != null) {
            existingTeacher.setRoleName(convertToRoleName(roleNumber));
            return userRepository.save(existingTeacher);
        }
        return null;
    }

    public User updateTeacherActive(String email, User user) {
        User existingTeacher = userRepository.findTeacherByEmail(email);
        if (existingTeacher != null) {
            existingTeacher.setActive(user.isActive());
            return userRepository.save(existingTeacher);
        }
        return null;
    }

    public void deleteTeacherById(Long id) {
        userRepository.deleteById(id);
    }

    public RoleName convertToRoleName(int roleNumber) {
    switch (roleNumber) {
        case 0:
            return RoleName.Admin;
        case 1:
            return RoleName.Student;
        case 2:
            return RoleName.Teacher;
        default:
            throw new IllegalArgumentException("Invalid role number: " + roleNumber);
    }
}
}