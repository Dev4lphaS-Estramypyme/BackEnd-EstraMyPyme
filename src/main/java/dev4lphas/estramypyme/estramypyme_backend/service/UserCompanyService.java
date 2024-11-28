package dev4lphas.estramypyme.estramypyme_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany;
import dev4lphas.estramypyme.estramypyme_backend.repository.UserCompanyRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserCompanyService {

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    public List<UserCompany> getAllUserCompanies() {
        return userCompanyRepository.findAll();
    }

    public Optional<UserCompany> getUserCompanyById(Long id) {
        return userCompanyRepository.findById(id);
    }

    public UserCompany createUserCompany(UserCompany userCompany) {
        userCompany.setIsBookDownloaded(false); // Asegurarse de que el valor por defecto sea false
        return userCompanyRepository.save(userCompany);
    }

    public UserCompany updateUserCompany(Long id, UserCompany userCompany) {
        Optional<UserCompany> existingUserCompany = userCompanyRepository.findById(id);
        if (existingUserCompany.isPresent()) {
            UserCompany updatedUserCompany = existingUserCompany.get();
            updatedUserCompany.setNameOrBusinessName(userCompany.getNameOrBusinessName());
            updatedUserCompany.setEmail(userCompany.getEmail());
            updatedUserCompany.setPassword(userCompany.getPassword());
            updatedUserCompany.setIdentificationNumber(userCompany.getIdentificationNumber());
            return userCompanyRepository.save(updatedUserCompany);
        } else {
            throw new EntityNotFoundException("La empresa con el ID " + id + " no fue encontrada.");
        }
    }

    public void deleteUserCompanyById(Long id) {
        Optional<UserCompany> existingUserCompany = userCompanyRepository.findById(id);
        if (existingUserCompany.isPresent()) {
            userCompanyRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("La empresa con el ID " + id + " no fue encontrada.");
        }
    }

    public Optional<UserCompany> authenticate(String email, String password) {
        return userCompanyRepository.findByEmailAndPassword(email, password);
    }

    public UserCompany updateBookDownloadedStatus(String email) {
        Optional<UserCompany> existingUserCompany = userCompanyRepository.findByEmail(email);
        if (existingUserCompany.isPresent()) {
            UserCompany userCompany = existingUserCompany.get();
            userCompany.setIsBookDownloaded(true);
            return userCompanyRepository.save(userCompany);
        } else {
            throw new EntityNotFoundException("La empresa con el correo " + email + " no fue encontrada.");
        }
    }
}