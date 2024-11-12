package dev4lphas.estramypyme.estramypyme_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_company")
public class UserCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String identificationNumber;

    @Column(nullable = false)
    private String nameOrBusinessName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String typeUser;

    @Column(nullable = false)
    private String companySize;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private String registrationDate;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private boolean isBookDownloaded;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getNameOrBusinessName() {
        return nameOrBusinessName;
    }

    public void setNameOrBusinessName(String nameOrBusinessName) {
        this.nameOrBusinessName = nameOrBusinessName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getCompanySize() {
        return companySize;
    }

    public void setCompanySize(String companySize) {
        this.companySize = companySize;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isBookDownloaded() {
        return isBookDownloaded;
    }

    public void setBookDownloaded(boolean isBookDownloaded) {
        this.isBookDownloaded = isBookDownloaded;
    }
}