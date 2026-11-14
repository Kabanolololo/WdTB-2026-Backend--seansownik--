package pl.wsb.seansownik.rental;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import pl.wsb.rules.core.ProjectApp;
import pl.wsb.seansownik.catalog.entity.Film;
import pl.wsb.seansownik.catalog.entity.Genre;
import pl.wsb.seansownik.rental.repository.RentalRepository;

import static org.assertj.core.api.Assertions.assertThat;

class RentalRepositoryTest {

    private ConfigurableApplicationContext context;
    private RentalRepository rentals;
    private EntityManager em;
    private TransactionTemplate transaction;

    @BeforeEach
    void start() {
        context = ProjectApp.startWithHibernateSchema();
        rentals = context.getBean(RentalRepository.class);
        em = ProjectApp.entityManagerFactory(context).createEntityManager();
        transaction = new TransactionTemplate(context.getBean(PlatformTransactionManager.class));
    }

    @AfterEach
    void stop() {
        if (em != null) em.close();
        if (context != null) context.close();
    }

    @Test
    void findsOnlyRentalsOfTheRequestedCustomer() {
        transaction.executeWithoutResult(status -> {
            var result = rentals.findByCustomer_FullName("Anna Kowalska");

            assertThat(result).extracting(rental -> rental.getRentalNumber())
                    .containsExactly("WYP-2026-001");
            var rental = result.getFirst();
            assertThat(rental.getFilm().getTitle()).isEqualTo("Incepcja");
            assertThat(rental.getCustomer().getAccount().getEmail()).isEqualTo("anna.kowalska@example.com");
            assertThat(rental.getCustomer().getRentals()).containsExactly(rental);
        });
    }

    @Test
    void returnsNoRentalsForUnknownCustomer() {
        assertThat(rentals.findByCustomer_FullName("Nieznany klient")).isEmpty();
    }

    @Test
    void filmsCanShareGenresAndHaveMoreThanOneGenre() {
        var films = em.createQuery("select f from Film f order by f.title", Film.class).getResultList();

        assertThat(films.get(0).getGenres()).extracting(Genre::getName)
                .containsExactlyInAnyOrder("Komedia romantyczna", "Dramat");
        assertThat(films.get(1).getGenres()).extracting(Genre::getName)
                .containsExactly("Science fiction");
        assertThat(films.get(2).getGenres()).extracting(Genre::getName)
                .containsExactlyInAnyOrder("Science fiction", "Dramat");
    }
}
