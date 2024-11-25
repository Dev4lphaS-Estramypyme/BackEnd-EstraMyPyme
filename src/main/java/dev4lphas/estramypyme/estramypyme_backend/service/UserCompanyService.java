package dev4lphas.estramypyme.estramypyme_backend.service;

import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany;
import dev4lphas.estramypyme.estramypyme_backend.repository.UserCompanyRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserCompanyService {

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    public UserCompany createUserCompany(UserCompany userCompany) {
        Optional<UserCompany> existingUserByEmail = userCompanyRepository.findByEmail(userCompany.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new EntityExistsException("La empresa con el correo " + userCompany.getEmail() + " ya existe.");
        }

        Optional<UserCompany> existingUserById = userCompanyRepository.findByIdentificationNumber(userCompany.getIdentificationNumber());
        if (existingUserById.isPresent()) {
            throw new EntityExistsException("La empresa con el número de identificación " + userCompany.getIdentificationNumber() + " ya existe.");
        }

        return userCompanyRepository.save(userCompany);
    }

    public Optional<UserCompany> authenticate(String email, String password) {
        Optional<UserCompany> userCompany = userCompanyRepository.findByEmail(email);
        if (userCompany.isPresent() && userCompany.get().getPassword().equals(password)) {
            return userCompany;
        } else {
            return Optional.empty();
        }
    }

    public UserCompany updateBookDownloadedStatus(String email) {
        UserCompany existingUserCompany = userCompanyRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con el correo: " + email));

        existingUserCompany.setIsBookDownloaded(true);

        return userCompanyRepository.save(existingUserCompany);
    }
}