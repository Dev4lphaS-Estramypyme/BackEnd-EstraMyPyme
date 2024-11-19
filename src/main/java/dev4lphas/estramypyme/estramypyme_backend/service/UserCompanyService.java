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
    private UserCompanyRepository usercompanyRepository;

    public List<UserCompany> getUsersCompanies() {
        return usercompanyRepository.findAll();
    }

    public Optional<UserCompany> getUserCompanyByIdentificationNumber(String identificationNumber) {
        return usercompanyRepository.findByIdentificationNumber(identificationNumber);
    }

    public UserCompany createUserCompany(UserCompany usercompany) {
        Optional<UserCompany> existingUserCompany = usercompanyRepository.findByIdentificationNumber(usercompany.getIdentificationNumber());
        if (existingUserCompany.isPresent()) {
            throw new EntityExistsException("Ya existe una empresa con el número de identificación: " + usercompany.getIdentificationNumber());
        }
        return usercompanyRepository.save(usercompany);
    }

    public UserCompany updateUserCompany(String identificationNumber, UserCompany usercompany) {
        Optional<UserCompany> existingUserCompany = usercompanyRepository.findByIdentificationNumber(identificationNumber);

        if (!existingUserCompany.isPresent()) {
            throw new EntityNotFoundException("Empresa no encontrada con el número de identificación: " + identificationNumber);
        }

        UserCompany updatedUserCompany = existingUserCompany.get();

        updatedUserCompany.setIdentificationNumber(usercompany.getIdentificationNumber());
        updatedUserCompany.setNameOrBusinessName(usercompany.getNameOrBusinessName());
        updatedUserCompany.setEmail(usercompany.getEmail());
        updatedUserCompany.setPassword(usercompany.getPassword());
        updatedUserCompany.setTypeUser(usercompany.getTypeUser());
        updatedUserCompany.setCompanySize(usercompany.getCompanySize());
        updatedUserCompany.setSector(usercompany.getSector());
        updatedUserCompany.setRegistrationDate(usercompany.getRegistrationDate());

        return usercompanyRepository.save(updatedUserCompany);
    }

    public UserCompany updateStatus(String identificationNumber, boolean isActive) {
        Optional<UserCompany> existingUserCompany = usercompanyRepository.findByIdentificationNumber(identificationNumber);

        if (!existingUserCompany.isPresent()) {
            throw new EntityNotFoundException("Empresa no encontrada con el número de identificación: " + identificationNumber);
        }

        UserCompany updatedUserCompany = existingUserCompany.get();
        updatedUserCompany.setActive(isActive);

        return usercompanyRepository.save(updatedUserCompany);
    }

    @Transactional
    public void deleteUserCompanyByIdentificationNumber(String identificationNumber) {
        Optional<UserCompany> existingUserCompany = usercompanyRepository.findByIdentificationNumber(identificationNumber);
        if (!existingUserCompany.isPresent()) {
            throw new RuntimeException("No se encontró ninguna empresa con la identificación " + identificationNumber);
        }
        usercompanyRepository.deleteByIdentificationNumber(identificationNumber);
    }

    public Optional<UserCompany> getUserCompanyByEmail(String email) {
        return usercompanyRepository.findByEmail(email);
    }

    @Transactional
    public void deleteUserCompanyByEmail(String email) {
        Optional<UserCompany> existingUserCompany = usercompanyRepository.findByEmail(email);
        if (!existingUserCompany.isPresent()) {
            throw new RuntimeException("No se encontró ninguna empresa con el correo " + email);
        }
        usercompanyRepository.deleteByEmail(email);
    }

    public UserCompany updateUserCompanyByEmail(String email, UserCompany usercompany) {
        UserCompany existingUserCompany = usercompanyRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con el correo: " + email));

                existingUserCompany.setIdentificationNumber(usercompany.getIdentificationNumber());
                existingUserCompany.setNameOrBusinessName(usercompany.getNameOrBusinessName());
                existingUserCompany.setEmail(usercompany.getEmail());
                existingUserCompany.setPassword(usercompany.getPassword());
                existingUserCompany.setTypeUser(usercompany.getTypeUser());
                existingUserCompany.setCompanySize(usercompany.getCompanySize());
                existingUserCompany.setSector(usercompany.getSector());
                existingUserCompany.setRegistrationDate(usercompany.getRegistrationDate());

        return usercompanyRepository.save(existingUserCompany);
    }

    public UserCompany updateStatusByEmail(String email, boolean isActive) {
        UserCompany existingUserCompany = usercompanyRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con el correo: " + email));

        existingUserCompany.setActive(isActive);

        return usercompanyRepository.save(existingUserCompany);
    }

    public UserCompany updateBookDownloadedStatus(String email) {
        UserCompany existingUserCompany = usercompanyRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con el correo: " + email));

        existingUserCompany.setIsBookDownloaded(true);

        return usercompanyRepository.save(existingUserCompany);
    }
}