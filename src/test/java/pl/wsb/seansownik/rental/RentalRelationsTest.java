package pl.wsb.seansownik.rental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import pl.wsb.seansownik.SeansownikApplication;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RentalRelationsTest {

    private static ConfigurableApplicationContext context;
    private static JdbcTemplate jdbc;

    @BeforeAll
    static void startApplication() {
        context = new SpringApplicationBuilder(SeansownikApplication.class)
                .web(WebApplicationType.NONE)
                .run("--spring.datasource.url=jdbc:h2:mem:rental-relations;DATABASE_TO_LOWER=TRUE",
                        "--spring.h2.console.enabled=false", "--spring.jpa.show-sql=false",
                        "--logging.level.root=WARN");
        jdbc = context.getBean(JdbcTemplate.class);
    }

    @AfterAll
    static void stopApplication() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    void seedRentalsReferToTheirFilmsAndCustomers() {
        assertThat(jdbc.queryForList("""
                select r.rental_number || ': ' || f.title || ' / ' || c.full_name
                from rental r
                join film f on f.id = r.film_id
                join customer c on c.id = r.customer_id
                order by r.rental_number
                """, String.class)).containsExactly(
                "WYP-2026-001: Incepcja / Anna Kowalska",
                "WYP-2026-002: Amelia / Jan Nowak",
                "WYP-2026-003: Interstellar / Ewa Wiśniewska");
    }

    @Test
    void rentalRequiresBothFilmAndCustomer() {
        assertThatThrownBy(() -> jdbc.update("update rental set film_id = null where rental_number = 'WYP-2026-001'"))
                .isInstanceOf(DataIntegrityViolationException.class);
        assertThatThrownBy(() -> jdbc.update("update rental set customer_id = null where rental_number = 'WYP-2026-001'"))
                .isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    void rentalRejectsNonexistentFilmAndCustomer() {
        assertThatThrownBy(() -> jdbc.update("update rental set film_id = -1 where rental_number = 'WYP-2026-001'"))
                .isInstanceOf(DataIntegrityViolationException.class);
        assertThatThrownBy(() -> jdbc.update("update rental set customer_id = -1 where rental_number = 'WYP-2026-001'"))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
