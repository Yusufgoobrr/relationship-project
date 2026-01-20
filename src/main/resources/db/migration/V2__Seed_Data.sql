-- 1. Citizens with passports
INSERT INTO citizen (first_name, last_name, date_of_birth, email)
VALUES
    ('Alice', 'Johnson', '1990-05-12', 'alice.johnson@example.com'),
    ('Bob', 'Smith', '1985-09-20', 'bob.smith@example.com'),
    ('Carol', 'Davis', '1992-02-28', 'carol.davis@example.com');

-- 2. Citizens without passports
INSERT INTO citizen (first_name, last_name, date_of_birth, email)
VALUES
    ('David', 'Miller', '1988-11-05', 'david.miller@example.com'),
    ('Eve', 'Wilson', '1995-07-18', 'eve.wilson@example.com');

-- 3. Passports for first 3 citizens
INSERT INTO passport (passport_number, issue_date, expiry_date, issuing_country, citizen_id)
VALUES
    ('A12345678', '2015-01-01', '2025-01-01', 'USA', 1),
    ('B87654321', '2018-06-15', '2028-06-15', 'Canada', 2),
    ('C11223344', '2020-03-10', '2030-03-10', 'UK', 3);
