package com.yusuf;

import com.yusuf.gallerymanagement.artist.Artist;
import com.yusuf.gallerymanagement.artist.ArtistRepository;
import com.yusuf.gallerymanagement.painting.Painting;
import com.yusuf.gallerymanagement.painting.PaintingType;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    private final CitizenRepository citizenRepository;
    private final PassportRepository passportRepository;
    private final ArtistRepository artistRepository;

    public Application(CitizenRepository citizenRepository,
                       PassportRepository passportRepository,
                       ArtistRepository artistRepository) {
        this.citizenRepository = citizenRepository;
        this.passportRepository = passportRepository;
        this.artistRepository = artistRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @Transactional
    CommandLineRunner commandLineRunner() {
        return args -> {

            // ==============================
            // 0️⃣ Citizen & Passport Demo
            // ==============================
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

            // Delete citizen and check cascading
            citizenRepository.deleteById(citizenId);
            System.out.println(citizenRepository.existsById(citizenId)
                    ? "Citizen not deleted ❌" : "Citizen deleted ✅");
            System.out.println(passportRepository.existsPassportByCitizen_CitizenId(citizenId)
                    ? "Passport not deleted ❌" : "Passport deleted ✅");

            // ==============================
            // 1️⃣ Artist & Painting Demo
            // ==============================
            System.out.println("\n=== Artist & Painting Demo ===");

            // --- Create artist with 3 paintings ---
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
            System.out.println("Artist and 3 paintings saved.");

            // --- Add a new painting ---
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
            System.out.println("New painting added to artist.");

            // --- Remove a painting ---
            artist.getPaintingList().removeIf(p -> p.getTitle().equals("Café Terrace at Night"));
            artistRepository.save(artist);
            System.out.println("Removed 'Café Terrace at Night'.");

            // --- Lazy loading demo ---
            Artist loadedArtist = artistRepository.findById(artist.getArtistId()).orElseThrow();
            System.out.println("Artist: " + loadedArtist.getStageName());
            loadedArtist.getPaintingList().forEach(p ->
                    System.out.println(" - " + p.getTitle() + " | Sold: " + p.getSold())
            );

            // --- N+1 problem demo ---
            System.out.println("\n--- N+1 problem demo ---");
            List<Artist> allArtists = artistRepository.findAll();
            for (Artist a : allArtists) {
                System.out.println("Artist: " + a.getStageName());
                a.getPaintingList().forEach(p -> System.out.println("   Painting: " + p.getTitle()));
            }

            // --- Solved with JOIN FETCH ---
            System.out.println("\n--- Solved with JOIN FETCH ---");
            List<Artist> artistsWithPaintings = artistRepository.findAllWithPaintings();
            for (Artist a : artistsWithPaintings) {
                System.out.println("Artist: " + a.getStageName());
                a.getPaintingList().forEach(p -> System.out.println("   Painting: " + p.getTitle()));
            }
        };
    }
}