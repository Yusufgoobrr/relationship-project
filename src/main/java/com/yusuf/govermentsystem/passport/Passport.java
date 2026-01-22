package com.yusuf.govermentsystem.passport;

import com.yusuf.govermentsystem.citizen.Citizen;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Passport {
    @Id
    @SequenceGenerator(name = "passport_id_seq", sequenceName = "passport_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_id_seq")
    private Long passportId;

    @Column(length = 9, nullable = false, unique = true)
    @Size(min = 9, max = 9, message = "Value must be exactly 9 characters long")
    @NotBlank
    private String passportNumber;


    @Column(nullable = false)
    @NotNull
    private LocalDate issueDate;


    @Column(nullable = false)
    @NotNull
    private LocalDate expiryDate;

    @Column(nullable = false, length = 100)
    @NotBlank
    @Size(max = 100, message = "Country name should be maximum 100 characters long.")
    private String issuingCountry;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizenId", referencedColumnName = "citizenId", nullable = false, unique = true)
    private Citizen citizen;

    public Passport() {
    }

    public Passport(Long passportId, String passportNumber, LocalDate issueDate, LocalDate expiryDate, String issuingCountry, Citizen citizen) {
        this.passportId = passportId;
        this.passportNumber = passportNumber;
        this.issueDate = issueDate;
        this.expiryDate = expiryDate;
        this.issuingCountry = issuingCountry;
        this.citizen = citizen;
    }


    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getIssuingCountry() {
        return issuingCountry;
    }

    public void setIssuingCountry(String issuingCountry) {
        this.issuingCountry = issuingCountry;
    }

    public Citizen getCitizen() {
        return citizen;
    }

    public void setCitizen(Citizen citizen) {
        this.citizen = citizen;
        if (citizen != null && citizen.getPassport() != this) {
            citizen.setPassport(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Passport)) return false;
        Passport that = (Passport) o;
        return passportNumber != null && passportNumber.equals(that.passportNumber);
    }

    @Override
    public int hashCode() {
        return passportNumber != null ? passportNumber.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + passportId +
                ", number='" + passportNumber + '\'' +
                '}';
    }
}