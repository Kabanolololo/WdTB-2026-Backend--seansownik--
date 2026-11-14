# Schemat bazy danych

Plik generują reguły projektu (panel Maven → Lifecycle → verify). Nie edytujcie go ręcznie.

Tabele: 6 (w tym tabele łączące @ManyToMany: film_genre) · kolumny: 31 · relacje: 2 × @ManyToOne, 1 × @OneToMany, 1 × @OneToOne, 1 × @ManyToMany

| Tabela | Encja | Kolumny | Klucze obce |
|---|---|---|---|
| customer | Customer | 7 | user_account_id |
| film | Film | 6 | — |
| genre | Genre | 5 | — |
| rental | Rental | 8 | customer_id, film_id |
| user_account | UserAccount | 5 | — |

```mermaid
erDiagram
    customer {
        Long id PK
        boolean active
        String email
        String full_name
        String phone
        LocalDate registered_on
        Long user_account_id FK
    }
    film {
        Long id PK
        Integer available_copies
        BigDecimal daily_rate
        String director
        Integer release_year
        String title
    }
    genre {
        Long id PK
        boolean active
        String description
        Integer display_order
        String name
    }
    rental {
        Long id PK
        Long customer_id FK
        LocalDate due_on
        Long film_id FK
        String rental_number
        LocalDate rented_on
        RentalStatus status
        BigDecimal total_price
    }
    user_account {
        Long id PK
        LocalDateTime created_at
        String display_name
        String email
        AccountRole role
    }
    customer ||--o{ rental : "customer_id"
    film ||--o{ rental : "film_id"
    film }o--o{ genre : "film_genre"
    user_account |o--o| customer : "user_account_id"
```
