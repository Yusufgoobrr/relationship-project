package com.yusuf.gallerymanagement.artist;

import com.yusuf.gallerymanagement.painting.Painting;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Artist {

    @Id
    @SequenceGenerator(name = "artist_id_seq", sequenceName = "artist_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    private Long artistId;

    @NaturalId
    @Column(nullable = false, length = 100)
    private String stageName;

    @Column(length = 150)
    private String realName;

    @Column(columnDefinition = "TEXT")
    private String biography;

    @Column(nullable = false)
    @Min(value = 1800)
    @Max(value = 2026)
    private Integer birthYear;

    @Column(nullable = false)
    private String specialtyStyle;

    @Column(nullable = false)
    private Boolean activeStatus = true;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "artist", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Painting> paintingList = new ArrayList<>();

    public Artist() {
    }

    public Artist(Long artistId, String stageName, String realName, String biography, Integer birthYear, String specialtyStyle, Boolean activeStatus, List<Painting> paintingList) {
        this.artistId = artistId;
        this.stageName = stageName;
        this.realName = realName;
        this.biography = biography;
        this.birthYear = birthYear;
        this.specialtyStyle = specialtyStyle;
        this.activeStatus = activeStatus;
        this.paintingList = paintingList;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public String getSpecialtyStyle() {
        return specialtyStyle;
    }

    public void setSpecialtyStyle(String specialtyStyle) {
        this.specialtyStyle = specialtyStyle;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        this.activeStatus = activeStatus;
    }

    public List<Painting> getPaintingList() {
        return paintingList;
    }

    public void setPaintingList(List<Painting> paintingList) {
        this.paintingList = paintingList;
    }

    public void addPainting(Painting painting) {
        paintingList.add(painting);
    }

    public void removePainting(Painting painting) {
        paintingList.remove(painting);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // same object
        if (o == null || getClass() != o.getClass()) return false; // null or different class
        Artist artist = (Artist) o;
        return stageName != null && stageName.equals(artist.stageName); // natural ID equality
    }

    @Override
    public int hashCode() {
        return stageName != null ? stageName.hashCode() : 0; // match equals
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artistId=" + artistId +
                ", stageName='" + stageName + '\'' +
                ", realName='" + realName + '\'' +
                ", birthYear=" + birthYear +
                ", specialtyStyle='" + specialtyStyle + '\'' +
                ", activeStatus=" + activeStatus +
                '}';
    }

}
