package dev4lphas.estramypyme.estramypyme_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany;
import java.util.Optional;

@Repository
public interface UserCompanyRepository extends JpaRepository<UserCompany, Long> {
    Optional<UserCompany> findByIdentificationNumber(String identificationNumber);
    Optional<UserCompany> findByEmail(String email);
    void deleteByEmail(String email);
    void deleteByIdentificationNumber(String identificationNumber);
}

