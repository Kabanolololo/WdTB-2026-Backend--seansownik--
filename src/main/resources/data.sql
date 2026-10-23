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
