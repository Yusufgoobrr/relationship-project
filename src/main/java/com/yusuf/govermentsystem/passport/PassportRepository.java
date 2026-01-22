package com.yusuf.govermentsystem.passport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    boolean existsPassportByCitizen_CitizenId(Long citizenCitizenId);
}
