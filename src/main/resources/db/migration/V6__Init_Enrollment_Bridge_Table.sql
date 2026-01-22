DROP TABLE IF EXISTS enrollment CASCADE;

CREATE TABLE enrollment
(
    member_id         BIGINT      NOT NULL,
    fitness_class_id  BIGINT      NOT NULL,

    enrollment_date   DATE        NOT NULL DEFAULT CURRENT_DATE,
    attendance_status VARCHAR(20) NOT NULL
        CHECK (attendance_status IN ('ENROLLED', 'ATTENDED', 'NO_SHOW', 'CANCELLED')),
    rating            INT CHECK (rating BETWEEN 1 AND 5),
    feedback          TEXT,

    PRIMARY KEY (member_id, fitness_class_id),

    CONSTRAINT fk_enrollment_member
        FOREIGN KEY (member_id)
            REFERENCES member (member_id)
            ON DELETE CASCADE,

    CONSTRAINT fk_enrollment_class
        FOREIGN KEY (fitness_class_id)
            REFERENCES fitness_class (fitness_class_id)
            ON DELETE CASCADE
);
