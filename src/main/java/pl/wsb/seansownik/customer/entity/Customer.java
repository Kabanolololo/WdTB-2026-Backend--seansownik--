package pl.wsb.seansownik.customer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import pl.wsb.seansownik.account.domain.UserAccount;
import pl.wsb.seansownik.rental.entity.Rental;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_account_id", unique = true)
    private UserAccount account;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Rental> rentals = new ArrayList<>();

    public UserAccount getAccount() {
        return account;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public Customer(String fullName, LocalDate registeredOn, boolean active,
                    ContactDetails contactDetails, UserAccount account) {
        this(fullName, registeredOn, active, contactDetails);
        this.account = account;
    }

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
