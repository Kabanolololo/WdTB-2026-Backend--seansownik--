package pl.wsb.seansownik.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "registered_on", nullable = false)
    private LocalDate registeredOn;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Embedded
    private ContactDetails contactDetails;

    protected Customer() {
    }

    public Customer(String fullName, LocalDate registeredOn, boolean active, ContactDetails contactDetails) {
        this.fullName = fullName;
        this.registeredOn = registeredOn;
        this.active = active;
        this.contactDetails = contactDetails;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public boolean isActive() {
        return active;
    }

    public ContactDetails getContactDetails() {
        return contactDetails;
    }

}
