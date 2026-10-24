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


-- ===== LAB 02 · film =====
insert into film (title, director, release_year, daily_rate, available_copies) values
    ('Incepcja', 'Christopher Nolan', 2010, 8.00, 4),
    ('Amelia', 'Jean-Pierre Jeunet', 2001, 6.50, 2),
    ('Interstellar', 'Christopher Nolan', 2014, 9.00, 0);

-- ===== LAB 02 · customer =====
insert into customer (full_name, registered_on, active, email, phone) values
    ('Anna Kowalska', '2026-10-01', true, 'anna.kowalska@example.com', '600100200'),
    ('Jan Nowak', '2026-10-05', true, 'jan.nowak@example.com', null),
    ('Ewa Wiśniewska', '2026-10-10', false, 'ewa.wisniewska@example.com', '600500600');

-- ===== LAB 02 · rental =====
-- Powiazanie wypozyczen z filmami i klientami.
insert into rental (rental_number, rented_on, due_on, total_price, status, film_id, customer_id) values
    ('WYP-2026-001', '2026-10-20', '2026-10-27', 56.00, 'RENTED',
     (select id from film where title = 'Incepcja' and director = 'Christopher Nolan'),
     (select id from customer where email = 'anna.kowalska@example.com')),
    ('WYP-2026-002', '2026-10-10', '2026-10-13', 19.50, 'RETURNED',
     (select id from film where title = 'Amelia' and director = 'Jean-Pierre Jeunet'),
     (select id from customer where email = 'jan.nowak@example.com')),
    ('WYP-2026-003', '2026-10-01', '2026-10-08', 63.00, 'OVERDUE',
     (select id from film where title = 'Interstellar' and director = 'Christopher Nolan'),
     (select id from customer where email = 'ewa.wisniewska@example.com'));
