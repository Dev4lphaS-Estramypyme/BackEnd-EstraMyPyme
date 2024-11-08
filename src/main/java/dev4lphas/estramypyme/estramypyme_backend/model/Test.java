package dev4lphas.estramypyme.estramypyme_backend.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "tests") 
@NoArgsConstructor
@AllArgsConstructor
public class Test {

    @Id
    private String id;

    @Column(nullable = false)
    private Long companyId; // Cambiado a Long

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date testDate;

    @Column(nullable = false)
    private Boolean isReviewed;

    public Test(String id, Long companyId) { // Cambiado a Long
        this.id = id;
        this.companyId = companyId;
        this.testDate = new Date(); 
        this.isReviewed = false; 
    }

    // Métodos getter y setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Boolean getIsReviewed() {
        return isReviewed;
    }

    public void setIsReviewed(Boolean isReviewed) {
        this.isReviewed = isReviewed;
    }
}