CREATE SEQUENCE artist_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE painting_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE artist
(
    artist_id       BIGINT PRIMARY KEY DEFAULT nextval('artist_id_seq'),
    stage_name      VARCHAR(100) NOT NULL,
    real_name       VARCHAR(150),
    biography       TEXT,
    birth_year      INT          NOT NULL CHECK (birth_year between 1800 and 2026),
    specialty_style TEXT         NOT NULL,
    active_status   BOOLEAN            DEFAULT true
);

CREATE TABLE painting
(
    painting_id      BIGINT PRIMARY KEY     DEFAULT nextval('painting_id_seq'),
    title            VARCHAR(200)  NOT NULL,
    year_created     INT           NOT NULL,
    painting_type    VARCHAR(50)   NOT NULL CHECK (painting_type IN ('OIL', 'ACRYLIC', 'WATERCOLOR', 'MIXED', 'DIGITAL')),
    dimension_Width  NUMERIC(6, 2) NOT NULL CHECK (dimension_Width >= 0.0),
    dimension_height NUMERIC(6, 2) NOT NULL CHECK (dimension_height >= 0.0),
    price_in_cents   BIGINT        NOT NULL check (price_in_cents >= 0),
    is_sold          BOOLEAN       NOT NULL,
    date_added       DATE          NOT NULL DEFAULT now(),
    artist_id        BIGINT        NOT NULL,
    CONSTRAINT artist_id_fk FOREIGN KEY (artist_id) REFERENCES artist (artist_id)
);