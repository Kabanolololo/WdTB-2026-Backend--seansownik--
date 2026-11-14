-- Dane startowe aplikacji. Tabele tworzy Hibernate z encji, a ten plik Spring Boot uruchamia zaraz potem.
-- LAB 01: dopiszcie tu konta wszystkich osób z zespołu (reguła R1.03).
-- LAB 02: dopiszcie dane do swoich tabel (kolejność: najpierw tabele, na które wskazują klucze obce).
insert into user_account (email, display_name, role, created_at) values
    ('ola@student.merito.pl',   'Ola',   'USER',    current_timestamp),
    ('kuba@student.merito.pl',  'Kuba',  'USER',    current_timestamp),
    ('firma@example.com',       'Firma', 'PARTNER', current_timestamp),
    ('admin@example.com',       'Admin', 'ADMIN',   current_timestamp),
    ('s953141@student.wroclaw.merito.pl', 'Paweł Muszyński', 'USER', current_timestamp),
    ('s105701@student.wroclaw.merito.pl', 'Dominik Micor', 'USER', current_timestamp),
    ('s933079@student.wroclaw.merito.pl', 'Hubert Nosko', 'USER', current_timestamp);


-- ===== LAB 03 · konta klientów =====
insert into user_account (email, display_name, role, created_at) values
    ('anna.kowalska@example.com', 'Anna Kowalska', 'USER', current_timestamp),
    ('jan.nowak@example.com', 'Jan Nowak', 'USER', current_timestamp),
    ('ewa.wisniewska@example.com', 'Ewa Wiśniewska', 'USER', current_timestamp);

-- ===== LAB 02 · film =====
insert into film (title, director, release_year, daily_rate, available_copies) values
    ('Incepcja', 'Christopher Nolan', 2010, 8.00, 4),
    ('Amelia', 'Jean-Pierre Jeunet', 2001, 6.50, 2),
    ('Interstellar', 'Christopher Nolan', 2014, 9.00, 0);

-- ===== LAB 02 · customer =====
insert into customer (full_name, registered_on, active, email, phone, user_account_id) values
    ('Anna Kowalska', '2026-10-01', true, 'anna.kowalska@example.com', '600100200', 8),
    ('Jan Nowak', '2026-10-05', true, 'jan.nowak@example.com', null, 9),
    ('Ewa Wiśniewska', '2026-10-10', false, 'ewa.wisniewska@example.com', '600500600', 10);

-- ===== LAB 03 · rental =====
-- relacje: każde wypożyczenie wskazuje na istniejącego klienta i film.
insert into rental (rental_number, rented_on, due_on, total_price, status, customer_id, film_id) values
    ('WYP-2026-001', '2026-10-20', '2026-10-27', 56.00, 'RENTED', 1, 1),
    ('WYP-2026-002', '2026-10-10', '2026-10-13', 19.50, 'RETURNED', 2, 2),
    ('WYP-2026-003', '2026-10-01', '2026-10-08', 63.00, 'OVERDUE', 3, 3);

-- ===== LAB 03 · genre =====
insert into genre (name, description, display_order, active) values
    ('Science fiction', 'Filmy o nauce, technologii i możliwej przyszłości.', 1, true),
    ('Komedia romantyczna', 'Historie miłosne opowiedziane z humorem.', 2, true),
    ('Dramat', 'Historie skupione na przeżyciach i wyborach bohaterów.', 3, true);

-- ===== LAB 03 · film_genre =====
insert into film_genre (film_id, genre_id) values
    (1, 1),
    (2, 2),
    (2, 3),
    (3, 1),
    (3, 3);
