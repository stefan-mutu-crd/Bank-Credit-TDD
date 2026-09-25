DROP TABLE IF EXISTS loans;

DROP TABLE IF EXISTS clients;

DROP TABLE IF EXISTS banks;

CREATE TABLE banks
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    branch  VARCHAR(50)  NOT NULL,
    address VARCHAR(100) NOT NULL
);

CREATE TABLE clients
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    full_name  VARCHAR(100) NOT NULL,
    birth_date DATE         NOT NULL,
    bank_id    INT          NOT NULL,
    FOREIGN KEY (bank_id)
        REFERENCES banks (id)
);

CREATE TABLE loans
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    initial_sum   DECIMAL NOT NULL DEFAULT 0,
    refunded      DECIMAL NOT NULL DEFAULT 0,
    purpose       VARCHAR(250),
    date_of_issue DATE    NOT NULL,
    deadline      DATE    NOT NULL,
    percentage    INT     NOT NULL DEFAULT 0,
    client_id     INT     NOT NULL,
    FOREIGN KEY (client_id)
        REFERENCES clients (id)
);

INSERT INTO banks (branch, address)
VALUES ('Maib Centru', 'blv. Ștefan cel Mare'),
       ('Maib Buiucani', 'srt. Alba Iulia'),
       ('Maib Ciocana', 'blv. Mircea cel Batrân'),
       ('Maib Botanica', 'blv. Dacia');

-- Bank 1: 4 clients | Bank 2: 4 clients | Bank 3: 3 clients | Bank 4: 5 clients
INSERT INTO clients (full_name, birth_date, bank_id)
VALUES ('Ștefan Mutu', '1998-08-15', 1),      -- 1
       ('Radu Petrașcu', '1980-01-30', 1),    -- 2
       ('Gheorghe Lazăr', '1976-06-07', 1),   -- 3
       ('Ana Munteanu', '1992-03-22', 1),     -- 4
       ('Ion Ciobanu', '1985-11-03', 2),      -- 5
       ('Elena Rusu', '1990-07-19', 2),       -- 6
       ('Mihai Popescu', '1972-02-11', 2),    -- 7  (no loans)
       ('Cristina Țurcanu', '1988-12-05', 2), -- 8
       ('Vasile Botnaru', '1965-04-28', 3),   -- 9
       ('Ioana Cebotari', '1995-09-14', 3),   -- 10
       ('Andrei Moraru', '2001-01-09', 3),    -- 11
       ('Maria Grosu', '1979-10-02', 4),      -- 12
       ('Alexandru Rotaru', '2003-06-18', 4), -- 13
       ('Doina Ceban', '1958-08-30', 4),      -- 14 (no loans)
       ('Nicolae Sîrbu', '1983-05-25', 4),    -- 15
       ('Tatiana Lungu', '1997-03-07', 4); -- 16

INSERT INTO loans (initial_sum, refunded, purpose, date_of_issue, deadline, percentage, client_id)
VALUES (10000, 11000, 'Achiziționare frigider', '2025-05-01', '2026-05-01', 10, 1),      -- repaid      (total 11000)
       (5000, 2000, 'Achiziționare laptop', '2026-03-10', '2027-03-10', 12, 1),          -- in progress (total 5600)
       (50000, 30000, 'Achiziționare automobil', '2024-02-15', '2026-02-15', 8, 2),      -- expired     (total 54000)
       (20000, 23000, 'Reparație casă', '2023-06-01', '2025-06-01', 15, 3),              -- repaid      (total 23000)
       (150000, 40000, 'Renovare apartament', '2025-01-20', '2030-01-20', 6, 3),         -- in progress (total 159000)
       (8000, 3000, 'Achiziționare mobilă', '2025-09-01', '2026-09-01', 10, 4),          -- expired     (total 8800)
       (30000, 10000, 'Studii universitare', '2026-01-15', '2028-01-15', 9, 5),          -- in progress (total 32700)
       (12000, 5000, 'Vacanță în străinătate', '2024-10-01', '2025-10-01', 14, 6),       -- expired     (total 13680)
       (25000, 27750, 'Deschidere afacere', '2022-04-10', '2024-04-10', 11, 8),          -- repaid      (total 27750)
       (40000, 15000, 'Extindere afacere', '2025-11-05', '2027-11-05', 10, 8),           -- in progress (total 44000)
       (6000, 0, 'Tratament medical', '2025-03-01', '2026-03-01', 13, 9),                -- expired     (total 6780)
       (100000, 20000, 'Construcție casă', '2026-06-01', '2031-06-01', 7, 10),           -- in progress (total 107000)
       (15000, 16800, 'Achiziționare motocicletă', '2024-07-20', '2025-07-20', 12, 11),  -- repaid      (total 16800)
       (9000, 4000, 'Achiziționare telefon și electrocasnice', '2026-02-01', '2027-02-01', 10,
        11),                                                                             -- in progress (total 9900)
       (45000, 20000, 'Achiziționare teren agricol', '2023-09-01', '2025-09-01', 8, 12), -- expired     (total 48600)
       (7000, 2500, 'Cursuri de programare', '2026-04-10', '2027-04-10', 15, 13),        -- in progress (total 8050)
       (200000, 60000, 'Achiziționare apartament', '2024-05-15', '2034-05-15', 5, 15),   -- in progress (total 210000)
       (18000, 9000, 'Organizare nuntă', '2024-12-01', '2025-12-01', 12, 16),            -- expired     (total 20160)
       (3000, 3600, 'Achiziționare bicicletă', '2025-01-10', '2025-07-10', 20, 16); -- repaid      (total 3600)