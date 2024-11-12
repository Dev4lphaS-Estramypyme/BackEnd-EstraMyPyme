package dev4lphas.estramypyme.estramypyme_backend.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dev4lphas.estramypyme.estramypyme_backend.model.UserCompany;
import dev4lphas.estramypyme.estramypyme_backend.service.UserCompanyService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/usersCompanies")
public class UserCompanyController {

    @Autowired
    private UserCompanyService usercompanyService;

    // Obtener todas las empresas
    @GetMapping("")
    public List<UserCompany> getUsersCompanies() {
        return usercompanyService.getUsersCompanies();
    }

    // Obtener una empresa por número de identificación
    @GetMapping("/{identificationNumber}")
    public ResponseEntity<Object> getUserCompany(@PathVariable String identificationNumber) {
        Optional<UserCompany> usercompany = usercompanyService.getUserCompanyByIdentificationNumber(identificationNumber);
    
        if (usercompany.isPresent()) {
            return new ResponseEntity<>(usercompany.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empresa no encontrada con el número de identificación: " + identificationNumber, HttpStatus.NOT_FOUND);
        }
    }

    // Crear una nueva empresa
    @PostMapping("")
    public ResponseEntity<Object> createUserCompany(@RequestBody UserCompany usercompany) {
        try {
            UserCompany newUserCompany = usercompanyService.createUserCompany(usercompany);
            return new ResponseEntity<>(newUserCompany, HttpStatus.CREATED);
        } catch (EntityExistsException e) {
            return new ResponseEntity<>("La empresa con el número de identificación " + usercompany.getIdentificationNumber() + " ya existe.", HttpStatus.BAD_REQUEST);
        }
    }
    
    // Actualizar una empresa existente
    @PutMapping("/edit/{identificationNumber}")
    public ResponseEntity<Object> updateUserCompany(@PathVariable String identificationNumber, @RequestBody UserCompany usercompany) {
        try {
            UserCompany updatedUserCompany = usercompanyService.updateUserCompany(identificationNumber, usercompany);
            return new ResponseEntity<>(updatedUserCompany, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La empresa con el número de identificación " + identificationNumber + " no fue encontrada.", HttpStatus.NOT_FOUND);
        }
    }
    
    // Cambiar el estado (activo/inactivo) de una empresa
    @PutMapping("/status/{identificationNumber}")
    public ResponseEntity<Object> updateStatus(@PathVariable String identificationNumber, @RequestBody String isActiveStr) {
        boolean isActive;
        try {
            isActive = Boolean.parseBoolean(isActiveStr);
            if (!isActiveStr.equalsIgnoreCase("true") && !isActiveStr.equalsIgnoreCase("false")) {
                return new ResponseEntity<>("El valor de 'isActive' debe ser 'true' o 'false'.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("El valor de 'isActive' debe ser un booleano ('true' o 'false').", HttpStatus.BAD_REQUEST);
        }
        
        try {
            UserCompany updatedUserCompany = usercompanyService.updateStatus(identificationNumber, isActive);
            return new ResponseEntity<>(updatedUserCompany, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La empresa con el número de identificación " + identificationNumber + " no fue encontrada.", HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar por identificacion
    @DeleteMapping("/{identificationNumber}")
    public ResponseEntity<String> deleteUserCompany(@PathVariable String identificationNumber) {
        try {
            usercompanyService.deleteUserCompanyByIdentificationNumber(identificationNumber);
            return ResponseEntity.ok("La empresa con el número de identificación " + identificationNumber + " se eliminó correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Buscar por correo
    @GetMapping("/correo/{email}")
    public ResponseEntity<Object> getUserCompanyByEmail(@PathVariable String email) {
        Optional<UserCompany> usercompany = usercompanyService.getUserCompanyByEmail(email);

        if (usercompany.isPresent()) {
            return new ResponseEntity<>(usercompany.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Empresa no encontrada con el correo: " + email, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar por correo
    @DeleteMapping("/correo/{email}")
    public ResponseEntity<String> deleteUserCompanyByEmail(@PathVariable String email) {
        try {
            usercompanyService.deleteUserCompanyByEmail(email);
            return ResponseEntity.ok("La empresa con el correo " + email + " se eliminó correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Actualizar por correo
    @PutMapping("/edit/correo/{email}")
    public ResponseEntity<Object> updateUserCompanyByEmail(@PathVariable String email, @RequestBody UserCompany usercompany) {
        try {
            UserCompany updatedUserCompany = usercompanyService.updateUserCompanyByEmail(email, usercompany);
            return new ResponseEntity<>(updatedUserCompany, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La empresa con el correo " + email + " no fue encontrada.", HttpStatus.NOT_FOUND);
        }
    }

    // Cambiar el estado (activo/inactivo) de una empresa
    @PutMapping("/status/correo/{email}")
    public ResponseEntity<Object> updateStatusByEmail(@PathVariable String email, @RequestBody String isActiveStr) {
        if (!isActiveStr.equalsIgnoreCase("true") && !isActiveStr.equalsIgnoreCase("false")) {
            return new ResponseEntity<>("El valor de 'isActive' debe ser 'true' o 'false'.", HttpStatus.BAD_REQUEST);
        }
        
        boolean isActive = Boolean.parseBoolean(isActiveStr);
        
        try {
            UserCompany updatedUserCompany = usercompanyService.updateStatusByEmail(email, isActive);
            return new ResponseEntity<>(updatedUserCompany, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("La empresa con el correo " + email + " no fue encontrada.", HttpStatus.NOT_FOUND);
        }
    }
}