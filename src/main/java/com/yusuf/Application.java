package com.yusuf;

import com.yusuf.enums.*;
import com.yusuf.fitnessmanagement.member.Member;
import com.yusuf.fitnessmanagement.member.MemberRepository;
import com.yusuf.fitnessmanagement.fitnessclass.FitnessClass;
import com.yusuf.fitnessmanagement.fitnessclass.FitnessClassRepository;
import com.yusuf.fitnessmanagement.enrollment.Enrollment;
import com.yusuf.fitnessmanagement.enrollment.EnrollmentId;
import com.yusuf.fitnessmanagement.enrollment.EnrollmentRepository;

import com.yusuf.gallerymanagement.artist.Artist;
import com.yusuf.gallerymanagement.artist.ArtistRepository;
import com.yusuf.gallerymanagement.painting.Painting;
import com.yusuf.govermentsystem.citizen.Citizen;
import com.yusuf.govermentsystem.citizen.CitizenRepository;
import com.yusuf.govermentsystem.passport.Passport;
import com.yusuf.govermentsystem.passport.PassportRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    private final CitizenRepository citizenRepository;
    private final PassportRepository passportRepository;
    private final ArtistRepository artistRepository;
    private final MemberRepository memberRepository;
    private final FitnessClassRepository fitnessClassRepository;
    private final EnrollmentRepository enrollmentRepository;

    public Application(CitizenRepository citizenRepository,
                       PassportRepository passportRepository,
                       ArtistRepository artistRepository,
                       MemberRepository memberRepository,
                       FitnessClassRepository fitnessClassRepository,
                       EnrollmentRepository enrollmentRepository) {
        this.citizenRepository = citizenRepository;
        this.passportRepository = passportRepository;
        this.artistRepository = artistRepository;
        this.memberRepository = memberRepository;
        this.fitnessClassRepository = fitnessClassRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {

            System.out.println("=== Citizen & Passport Demo ===");

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
            Citizen loadedCitizen = citizenRepository.findById(citizenId).orElseThrow();
            System.out.println("Citizen's passport: " + loadedCitizen.getPassport());

            citizenRepository.deleteById(citizenId);
            System.out.println(citizenRepository.existsById(citizenId)
                    ? "Citizen not deleted ❌" : "Citizen deleted ✅");
            System.out.println(passportRepository.existsPassportByCitizen_CitizenId(citizenId)
                    ? "Passport not deleted ❌" : "Passport deleted ✅");

            System.out.println("\n=== Artist & Painting Demo ===");
            enrollmentRepository.deleteAll();
            memberRepository.deleteAll();
            fitnessClassRepository.deleteAll();
            Artist artist = new Artist();
            artist.setStageName("Vincent");
            artist.setRealName("Vincent van Gogh");
            artist.setBirthYear(1853);
            artist.setSpecialtyStyle("Post-Impressionism");
            artist.setActiveStatus(false);
            artist.setPaintingList(new ArrayList<>());

            Painting p1 = new Painting();
            p1.setTitle("Starry Night");
            p1.setYearCreated(1889);
            p1.setPaintingType(PaintingType.OIL);
            p1.setDimensionWidth(BigDecimal.valueOf(73.7));
            p1.setDimensionHeight(BigDecimal.valueOf(92.1));
            p1.setPriceInCents(1000000L);
            p1.setSold(false);
            p1.setArtist(artist);
            artist.getPaintingList().add(p1);

            Painting p2 = new Painting();
            p2.setTitle("Sunflowers");
            p2.setYearCreated(1888);
            p2.setPaintingType(PaintingType.OIL);
            p2.setDimensionWidth(BigDecimal.valueOf(95.0));
            p2.setDimensionHeight(BigDecimal.valueOf(73.0));
            p2.setPriceInCents(800000L);
            p2.setSold(true);
            p2.setArtist(artist);
            artist.getPaintingList().add(p2);

            Painting p3 = new Painting();
            p3.setTitle("Café Terrace at Night");
            p3.setYearCreated(1888);
            p3.setPaintingType(PaintingType.OIL);
            p3.setDimensionWidth(BigDecimal.valueOf(80.7));
            p3.setDimensionHeight(BigDecimal.valueOf(65.3));
            p3.setPriceInCents(600000L);
            p3.setSold(false);
            p3.setArtist(artist);
            artist.getPaintingList().add(p3);

            artistRepository.save(artist);

            Painting newPainting = new Painting();
            newPainting.setTitle("Irises");
            newPainting.setYearCreated(1889);
            newPainting.setPaintingType(PaintingType.OIL);
            newPainting.setDimensionWidth(BigDecimal.valueOf(71.0));
            newPainting.setDimensionHeight(BigDecimal.valueOf(93.0));
            newPainting.setPriceInCents(500000L);
            newPainting.setSold(false);
            newPainting.setArtist(artist);
            artist.getPaintingList().add(newPainting);
            artistRepository.save(artist);

            artist.getPaintingList().removeIf(p -> p.getTitle().equals("Café Terrace at Night"));
            artistRepository.save(artist);

            System.out.println("\n=== Fitness Management Demo ===");

            Member member = new Member();
            member.setFullName("Yusuf Ali");
            member.setEmail("yusuf.f75474itness@gmail.com");
            member.setPhoneNumber("123456789");
            member.setMembershipTier(MembershipTier.PREMIUM);
            member.setEmergencyContactName("Dad");
            member.setEmergencyContactPhone("999999999");
            memberRepository.save(member);

            FitnessClass class1 = new FitnessClass();
            class1.setClassName("HIIT Training");
            class1.setDescription("High intensity cardio");
            class1.setInstructorName("John Trainer");
            class1.setDayOfTheWeek(Days.MONDAY);
            class1.setStartTime(Time.valueOf("10:00:00"));
            class1.setDurationInMinutes(60);
            class1.setMaxCapacity(2);
            class1.setDifficultyLevel(DifficultyLevel.INTERMEDIATE);
            class1.setRequiredMembershipTier(MembershipTier.BASIC);

            FitnessClass class2 = new FitnessClass();
            class2.setClassName("Advanced Strength");
            class2.setDescription("Heavy strength program");
            class2.setInstructorName("Sarah Coach");
            class2.setDayOfTheWeek(Days.WEDNESDAY);
            class2.setStartTime(Time.valueOf("18:00:00"));
            class2.setDurationInMinutes(90);
            class2.setMaxCapacity(1);
            class2.setDifficultyLevel(DifficultyLevel.ADVANCED);
            class2.setRequiredMembershipTier(MembershipTier.PREMIUM);

            fitnessClassRepository.save(class1);
            fitnessClassRepository.save(class2);

            for (FitnessClass fc : List.of(class1, class2)) {
                long enrolledCount = enrollmentRepository.countByFitnessClass_FitnessClassId(fc.getFitnessClassId());
                if (enrolledCount < fc.getMaxCapacity()) {
                    Enrollment enrollment = new Enrollment();
                    enrollment.setMember(member);
                    enrollment.setFitnessClass(fc);
                    enrollment.setId(new EnrollmentId(member.getMemberId(), fc.getFitnessClassId()));
                    enrollment.setAttendanceStatus(AttendanceStatus.ENROLLED);
                    enrollmentRepository.save(enrollment);
                    System.out.println("Enrolled in " + fc.getClassName());
                } else {
                    System.out.println(fc.getClassName() + " is full ❌");
                }
            }

            EnrollmentId eid = new EnrollmentId(member.getMemberId(), class1.getFitnessClassId());
            Enrollment e = enrollmentRepository.findById(eid).orElseThrow();
            e.setAttendanceStatus(AttendanceStatus.ATTENDED);
            enrollmentRepository.save(e);
            System.out.println("\nUpdated " + class1.getClassName() + " status to ATTENDED");

            System.out.println("\nEnrollments for " + member.getFullName() + ":");
            enrollmentRepository.findAllWithMemberAndClass().stream()
                    .filter(en -> en.getMember().getMemberId().equals(member.getMemberId()))
                    .forEach(en -> System.out.println(
                            "Class=" + en.getFitnessClass().getClassName() +
                                    ", Status=" + en.getAttendanceStatus()
                    ));

            System.out.println("\nMembers in " + class1.getClassName() + ":");
            enrollmentRepository.findAllWithMemberAndClass().stream()
                    .filter(en -> en.getFitnessClass().getFitnessClassId().equals(class1.getFitnessClassId()))
                    .forEach(en -> System.out.println(
                            "Member=" + en.getMember().getFullName() +
                                    ", Email=" + en.getMember().getEmail()
                    ));

            Enrollment compositeCheck = enrollmentRepository.findByIdWithMemberAndClass(eid).orElseThrow();
            System.out.println("\nComposite Key Check: Enrollment fetched -> " +
                    "Member=" + compositeCheck.getMember().getFullName() +
                    ", Class=" + compositeCheck.getFitnessClass().getClassName());

            Member member2 = new Member();
            member2.setFullName("Ali Veli");
            member2.setEmail("ali.veli@gmail.com");
            member2.setMembershipTier(MembershipTier.PREMIUM);
            member2.setEmergencyContactName("John Doe");
            member2.setEmergencyContactPhone("555-0199");
            memberRepository.save(member2);

            long enrolledCountClass2 = enrollmentRepository.countByFitnessClass_FitnessClassId(class2.getFitnessClassId());
            if (enrolledCountClass2 < class2.getMaxCapacity()) {
                Enrollment newEnrollment = new Enrollment();
                newEnrollment.setMember(member2);
                newEnrollment.setFitnessClass(class2);
                newEnrollment.setId(new EnrollmentId(member2.getMemberId(), class2.getFitnessClassId()));
                newEnrollment.setAttendanceStatus(AttendanceStatus.ENROLLED);
                enrollmentRepository.save(newEnrollment);
                System.out.println("Enrolled " + member2.getFullName() + " in " + class2.getClassName());
            } else {
                System.out.println(class2.getClassName() + " is full ❌");
            }

            System.out.println("\n=== Fitness Management Demo Complete ===");
        };
    }
}