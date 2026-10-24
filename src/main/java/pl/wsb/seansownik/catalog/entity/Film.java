package pl.wsb.seansownik.catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "director", nullable = false, length = 100)
    private String director;

    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;

    @Column(name = "daily_rate", nullable = false, precision = 8, scale = 2)
    private BigDecimal dailyRate;

    @Column(name = "available_copies", nullable = false)
    private Integer availableCopies;

    protected Film() {
    }

    public Film(String title, String director, Integer releaseYear, BigDecimal dailyRate, Integer availableCopies) {
        this.title = title;
        this.director = director;
        this.releaseYear = releaseYear;
        this.dailyRate = dailyRate;
        this.availableCopies = availableCopies;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

}
