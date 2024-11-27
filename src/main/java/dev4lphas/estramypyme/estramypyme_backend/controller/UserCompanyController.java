package dev4lphas.estramypyme.estramypyme_backend.controller;

import dev4lphas.estramypyme.estramypyme_backend.DTO.LoginRequest;
import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany;
import dev4lphas.estramypyme.estramypyme_backend.service.UserCompanyService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usersCompanies")
public class UserCompanyController {

    @Autowired
    private UserCompanyService userCompanyService;

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Object> register(@RequestBody UserCompany userCompany) {
        try {
            UserCompany newUserCompany = userCompanyService.createUserCompany(userCompany);
            return new ResponseEntity<>(newUserCompany, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("La empresa con el número de identificación " + userCompany.getIdentificationNumber() + " ya existe.", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        Optional<UserCompany> userCompany = userCompanyService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (userCompany.isPresent()) {
            return new ResponseEntity<>(userCompany.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Correo y/o contraseña incorrectos.", HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/bookDownloaded/{email}")
    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<Object> updateBookDownloadedStatus(@PathVariable String email) {
        try {
            UserCompany updatedUserCompany = userCompanyService.updateBookDownloadedStatus(email);
            return new ResponseEntity<>(updatedUserCompany, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La empresa con el correo " + email + " no fue encontrada.", HttpStatus.NOT_FOUND);
        }
    }
}