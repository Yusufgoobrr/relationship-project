package com.yusuf.passport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport,Long> {
    boolean findByCitizen_CitizenId(Long citizenCitizenId);

    boolean existsPassportByCitizen_CitizenId(Long citizenCitizenId);
}
