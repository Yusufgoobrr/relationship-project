package com.yusuf.govermentsystem.citizen;

import com.yusuf.govermentsystem.passport.Passport;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;

@Entity
public class Citizen {
    @Id
    @SequenceGenerator(name = "citizen_id_seq", sequenceName = "citizen_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "citizen_id_seq")
    private Long citizenId;

    @Column(columnDefinition = "VARCHAR", length = 50, nullable = false)
    @Size(max = 50)
    @NotBlank
    private String firstName;

    @Column(columnDefinition = "VARCHAR", length = 50, nullable = false)
    @Size(max = 50)
    @NotBlank
    private String lastName;

    @Column(nullable = false)
    @NotNull
    private LocalDate dateOfBirth;

    @NaturalId
    @Column(unique = true, nullable = false)
    @NotBlank
    private String email;

    @OneToOne(mappedBy = "citizen", orphanRemoval = true, cascade = CascadeType.ALL)
    private Passport passport;

    public Citizen() {
    }

    public Citizen(Long citizenId, String firstName, String lastName, LocalDate dateOfBirth, String email, Passport passport) {
        this.citizenId = citizenId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.passport = passport;
    }


    public Long getCitizenId() {
        return citizenId;
    }

    public void setCitizenId(Long citizenId) {
        this.citizenId = citizenId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
        if (passport != null && passport.getCitizen() != this) {
            passport.setCitizen(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Citizen)) return false;
        Citizen that = (Citizen) o;
        return email != null && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "id=" + citizenId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}