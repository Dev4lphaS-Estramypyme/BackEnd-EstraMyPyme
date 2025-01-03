package dev4lphas.estramypyme.estramypyme_backend.repository;

import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {
    boolean existsByIdentificationNumber(String identificationNumber);
    Optional<UserCompany> findByEmail(String email);
    Optional<UserCompany> findByEmailAndPassword(String email, String password);
    void deleteByEmail(String email);
    void deleteByIdentificationNumber(String identificationNumber);
}