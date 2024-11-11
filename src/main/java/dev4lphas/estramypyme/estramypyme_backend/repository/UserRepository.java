package dev4lphas.estramypyme.estramypyme_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev4lphas.estramypyme.estramypyme_backend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}

    User findAdminByEmail(String email);

    User findStudentByEmail(String email);

    User findTeacherByEmail(String email);
    
}

