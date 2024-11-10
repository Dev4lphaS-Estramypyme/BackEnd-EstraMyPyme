package dev4lphas.estramypyme.estramypyme_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.repository.UserRepository;
import dev4lphas.estramypyme.estramypyme_backend.model.User;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public boolean isAdminOrTeacher(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return user != null && (user.getRoleName() == User.RoleName.Admin || user.getRoleName() == User.RoleName.Teacher);
    }
}
