package pl.wsb.seansownik.rental.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rental")
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rental_number", nullable = false, unique = true, length = 30)
    private String rentalNumber;

    @Column(name = "rented_on", nullable = false)
    private LocalDate rentedOn;

    @Column(name = "due_on", nullable = false)
    private LocalDate dueOn;

    @Column(name = "total_price", nullable = false, precision = 8, scale = 2)
    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private RentalStatus status;

    protected Rental() {
    }

    public Rental(String rentalNumber, LocalDate rentedOn, LocalDate dueOn, BigDecimal totalPrice, RentalStatus status) {
        this.rentalNumber = rentalNumber;
        this.rentedOn = rentedOn;
        this.dueOn = dueOn;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getRentalNumber() {
        return rentalNumber;
    }

    public LocalDate getRentedOn() {
        return rentedOn;
    }

    public LocalDate getDueOn() {
        return dueOn;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public RentalStatus getStatus() {
        return status;
    }

}
