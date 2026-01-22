-- V1__Init_Member_FitnessClass_Enrollment.sql

-- ==============================
-- SEQUENCES
-- ==============================
CREATE SEQUENCE member_id_seq START 1;
CREATE SEQUENCE fitness_id_seq START 1;

-- ==============================
-- MEMBER TABLE
-- ==============================
CREATE TABLE member
(
    member_id               BIGINT PRIMARY KEY DEFAULT nextval('member_id_seq'),
    member_number           VARCHAR(20)  NOT NULL UNIQUE,
    full_name               VARCHAR(100) NOT NULL,
    email                   VARCHAR(255) NOT NULL UNIQUE,
    phone_number            TEXT,
    membership_tier         VARCHAR(20)  NOT NULL
        CHECK (membership_tier IN ('BASIC', 'PREMIUM', 'VIP')),
    join_date               DATE         NOT NULL,
    emergency_contact_name  VARCHAR(255) NOT NULL,
    emergency_contact_phone VARCHAR(50)  NOT NULL
);

-- ==============================
-- FITNESS CLASS TABLE
-- ==============================
CREATE TABLE fitness_class
(
    fitness_class_id         BIGINT PRIMARY KEY DEFAULT nextval('fitness_id_seq'),
    class_name               VARCHAR(100) NOT NULL,
    description              TEXT,
    instructor_name          VARCHAR(255) NOT NULL,
    day_of_the_week          VARCHAR(20)  NOT NULL
        CHECK (day_of_the_week IN (
                                   'MONDAY', 'TUESDAY', 'WEDNESDAY',
                                   'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY'
            )),
    start_time               TIME         NOT NULL,
    duration_in_minutes      INT          NOT NULL CHECK (duration_in_minutes BETWEEN 30 AND 120),
    max_capacity             INT          NOT NULL CHECK (max_capacity > 0),
    difficulty_level         VARCHAR(20)  NOT NULL
        CHECK (difficulty_level IN ('BEGINNER', 'INTERMEDIATE', 'ADVANCED')),
    required_membership_tier VARCHAR(20)  NOT NULL
        CHECK (required_membership_tier IN ('BASIC', 'PREMIUM', 'VIP'))
);

-- ==============================
-- ENROLLMENT TABLE
-- ==============================
CREATE TABLE enrollment
(
    member_id         BIGINT      NOT NULL,
    fitness_class_id  BIGINT      NOT NULL,
    enrollment_date   DATE DEFAULT CURRENT_DATE,
    attendance_status VARCHAR(20) NOT NULL,
    rating            INT,
    feedback          TEXT,
    PRIMARY KEY (member_id, fitness_class_id),
    CONSTRAINT fk_enrollment_member FOREIGN KEY (member_id)
        REFERENCES member (member_id) ON DELETE CASCADE,
    CONSTRAINT fk_enrollment_fitness FOREIGN KEY (fitness_class_id)
        REFERENCES fitness_class (fitness_class_id) ON DELETE CASCADE
);
