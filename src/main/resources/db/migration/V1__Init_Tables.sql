-- 1. Create Sequences for IDs
CREATE SEQUENCE citizen_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE passport_id_seq START WITH 1 INCREMENT BY 1;

-- 2. Create Citizen Table
CREATE TABLE citizen (
                         citizen_id BIGINT PRIMARY KEY DEFAULT nextval('citizen_id_seq'),
                         first_name VARCHAR(50) NOT NULL,       -- max 50, required
                         last_name VARCHAR(50) NOT NULL,        -- max 50, required
                         date_of_birth DATE NOT NULL,           -- required
                         email VARCHAR(255) NOT NULL UNIQUE     -- unique, required
);

CREATE TABLE passport (
                          passport_id BIGINT PRIMARY KEY DEFAULT nextval('passport_id_seq'),
                          passport_number VARCHAR(9) NOT NULL UNIQUE,
                          issue_date DATE NOT NULL,
                          expiry_date DATE NOT NULL,
                          issuing_country VARCHAR(100) NOT NULL,
                          citizen_id BIGINT NOT NULL REFERENCES citizen(citizen_id),
                          CONSTRAINT ck_passport_number_length CHECK (char_length(passport_number) = 9)
);