package com.yusuf;

import com.yusuf.citizen.Citizen;
import com.yusuf.citizen.CitizenRepository;
import com.yusuf.passport.Passport;
import com.yusuf.passport.PassportRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(PassportRepository passportRepository, CitizenRepository citizenRepository) {
        return args -> {
            Citizen citizen1 = new Citizen(
                    null,
                    "John",
                    "Doe",
                    LocalDate.of(1990, 3, 12),
                    "john.doe@example.com",
                    null
            );

            Passport passport1 = new Passport(
                    null,
                    "M77777777",
                    LocalDate.of(2018, 1, 10),
                    LocalDate.of(2028, 1, 10),
                    "USA",
                    citizen1
            );

            citizen1.setPassport(passport1);
            citizenRepository.save(citizen1);
            Long citizenId = citizen1.getCitizenId();
            Citizen thatCitizen = citizenRepository.findById(citizenId).orElseThrow(IllegalArgumentException::new);
            System.out.println(thatCitizen.getPassport());

            citizenRepository.deleteById(citizenId);
            if (citizenRepository.existsById(citizenId)) {
                System.out.println("The citizen isn't deleted ❌.");
            } else {
                System.out.println("The citizen is deleted. ✅.");
            }
            if (passportRepository.existsPassportByCitizen_CitizenId(citizenId)) {
                System.out.println("The passport isn't deleted ❌.");
            } else {
                System.out.println("The citizen isn't deleted ❌.");
            }
        };
    }
}


