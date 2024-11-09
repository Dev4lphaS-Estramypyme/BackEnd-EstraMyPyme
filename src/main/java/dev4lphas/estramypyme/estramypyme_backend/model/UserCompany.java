package dev4lphas.estramypyme.estramypyme_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

@Entity
@Data
@Table(name = "users_companies")
public class UserCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "identification_number", length = 255, nullable = false, unique = true)
    private String identificationNumber;

    @Column(name = "name_or_business_name", length = 255, nullable = false)
    private String nameOrBusinessName;

    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_user", nullable = false)
    private TypeUser typeUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "company_size", nullable = false)
    private CompanySize companySize;

    @Enumerated(EnumType.STRING)
    @Column(name = "sector", nullable = false)
    private Sector sector;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd") // Configura el formato de fecha
    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private Date registrationDate = new Date();

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @Column(name = "is_book_downloaded", nullable = false)
    private Boolean isBookDownloaded = false;

    // Enumeraciones para los campos de tipo ENUM en la base de datos
    public enum TypeUser {
        Natural, Juridico
    }

    public enum CompanySize {
        Pequeña, Mediana, Grande
    }

    public enum Sector {
       Sector_Agrícola, Sector_Industrial, Sector_Servicios, Sector_Construccion;

    }
}
