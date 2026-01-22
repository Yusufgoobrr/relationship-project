package com.yusuf.gallerymanagement.painting;

import com.yusuf.enums.PaintingType;
import com.yusuf.gallerymanagement.artist.Artist;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Painting {

    @Id
    @SequenceGenerator(name = "painting_id_seq", sequenceName = "painting_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "painting_id_seq")
    private Long paintingId;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false)
    private Integer yearCreated;

    //db constraint needed.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaintingType paintingType;
    @Column(nullable = false, precision = 6, scale = 2)
    @DecimalMin(value = "0.0")
    private BigDecimal dimensionWidth;

    @Column(nullable = false, precision = 6, scale = 2)
    @DecimalMin(value = "0.0")
    private BigDecimal dimensionHeight;

    @Column(nullable = false)
    @Min(value = 0)
    private Long priceInCents;

    @Column(nullable = false)
    private Boolean isSold = false;
    @Column(nullable = false, updatable = false)
    private LocalDate dateAdded;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artistId", referencedColumnName = "artistId", nullable = false,
            foreignKey = @ForeignKey(name = "artist_id_fk"))
    private Artist artist;

    @PrePersist
    public void prePersist() {
        dateAdded = LocalDate.now();
    }

    public Painting() {
    }

    public Painting(Long paintingId, String title, Integer yearCreated, PaintingType paintingType, BigDecimal dimensionWidth, BigDecimal dimensionHeight, Long priceInCents, Boolean isSold, LocalDate dateAdded, Artist artist) {
        this.paintingId = paintingId;
        this.title = title;
        this.yearCreated = yearCreated;
        this.paintingType = paintingType;
        this.dimensionWidth = dimensionWidth;
        this.dimensionHeight = dimensionHeight;
        this.priceInCents = priceInCents;
        this.isSold = isSold;
        this.dateAdded = dateAdded;
        this.artist = artist;
    }

    public Long getPaintingId() {
        return paintingId;
    }

    public void setPaintingId(Long paintingId) {
        this.paintingId = paintingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(Integer yearCreated) {
        this.yearCreated = yearCreated;
    }

    public PaintingType getPaintingType() {
        return paintingType;
    }

    public void setPaintingType(PaintingType paintingType) {
        this.paintingType = paintingType;
    }

    public BigDecimal getDimensionWidth() {
        return dimensionWidth;
    }

    public void setDimensionWidth(BigDecimal dimensionWidth) {
        this.dimensionWidth = dimensionWidth;
    }

    public BigDecimal getDimensionHeight() {
        return dimensionHeight;
    }

    public void setDimensionHeight(BigDecimal dimensionHeight) {
        this.dimensionHeight = dimensionHeight;
    }

    public Long getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Long priceInCents) {
        this.priceInCents = priceInCents;
    }

    public Boolean getSold() {
        return isSold;
    }

    public void setSold(Boolean sold) {
        isSold = sold;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting painting = (Painting) o;
        return paintingId != null && paintingId.equals(painting.paintingId); // use primary key
    }

    @Override
    public int hashCode() {
        return paintingId != null ? paintingId.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Painting{" +
                "paintingId=" + paintingId +
                ", title='" + title + '\'' +
                ", yearCreated=" + yearCreated +
                ", paintingType=" + paintingType +
                ", dimensionWidth=" + dimensionWidth +
                ", dimensionHeight=" + dimensionHeight +
                ", priceInCents=" + priceInCents +
                ", isSold=" + isSold +
                ", dateAdded=" + dateAdded +
                '}';
    }
}
