--
INSERT INTO artist (artist_id, stage_name, real_name, biography, birth_year, specialty_style, active_status)
VALUES (1, 'Leonardo', 'Leonardo da Vinci', 'Italian Renaissance polymath', 1810, 'Renaissance', FALSE),
       (2, 'Claude', 'Claude Monet', 'Founder of French Impressionist painting', 1840, 'Impressionism', FALSE),
       (3, 'Pablo', 'Pablo Picasso', 'Spanish painter, sculptor, and co-founder of Cubism', 1881, 'Cubism', TRUE);

--
INSERT INTO painting (painting_id, title, year_created, painting_type, dimension_width, dimension_height,
                      price_in_cents,
                      is_sold, date_added, artist_id)
VALUES (1, 'Water Lilies', 1906, 'OIL', 200.00, 180.00, 1000000, FALSE, '1906-01-01', 2),
       (2, 'Impression Sunrise', 1872, 'OIL', 100.00, 80.00, 500000, TRUE, '1872-01-01', 2),
       (3, 'Woman with a Parasol', 1875, 'OIL', 150.00, 130.00, 750000, FALSE, '1875-01-01', 2);

--
INSERT INTO painting (painting_id, title, year_created, painting_type, dimension_width, dimension_height,
                      price_in_cents,
                      is_sold, date_added, artist_id)
VALUES (4, 'Les Demoiselles', 1907, 'OIL', 243.00, 233.00, 2000000, TRUE, '1907-01-01', 3),
       (5, 'Guernica', 1937, 'OIL', 349.00, 776.00, 5000000, TRUE, '1937-01-01', 3),
       (6, 'The Weeping Woman', 1937, 'OIL', 60.00, 49.00, 1200000, FALSE, '1937-01-01', 3),
       (7, 'Girl Before a Mirror', 1932, 'ACRYLIC', 162.00, 130.00, 1800000, FALSE, '1932-01-01', 3),
       (8, 'Blue Nude', 1902, 'WATERCOLOR', 92.00, 151.00, 900000, TRUE, '1902-01-01', 3);
SELECT setval('artist_id_seq', (SELECT MAX(artist_id) FROM artist));
SELECT setval('painting_id_seq', (SELECT MAX(painting_id) FROM painting));