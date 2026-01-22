INSERT INTO member
VALUES (DEFAULT, 'MEM-00001', 'Alice Smith', 'alice@mail.com', NULL, 'BASIC', '2024-01-01', 'Bob', '111'),
       (DEFAULT, 'MEM-00002', 'John Doe', 'john@mail.com', NULL, 'PREMIUM', '2024-01-05', 'Jane', '222'),
       (DEFAULT, 'MEM-00003', 'Maria Lee', 'maria@mail.com', NULL, 'VIP', '2024-02-01', 'Tom', '333'),
       (DEFAULT, 'MEM-00004', 'David Kim', 'david@mail.com', NULL, 'PREMIUM', '2024-02-10', 'Sara', '444'),
       (DEFAULT, 'MEM-00005', 'Emma Brown', 'emma@mail.com', NULL, 'BASIC', '2024-03-01', 'Mike', '555');

INSERT INTO fitness_class
VALUES (DEFAULT, 'Yoga', 'Relaxing yoga', 'Anna', 'MONDAY', '09:00', 60, 10, 'BEGINNER', 'BASIC'),
       (DEFAULT, 'HIIT', 'High intensity', 'Mark', 'TUESDAY', '18:00', 45, 8, 'ADVANCED', 'PREMIUM'),
       (DEFAULT, 'Pilates', 'Core strength', 'Linda', 'WEDNESDAY', '10:00', 60, 12, 'INTERMEDIATE', 'BASIC'),
       (DEFAULT, 'CrossFit', 'Extreme workout', 'Chris', 'FRIDAY', '19:00', 90, 6, 'ADVANCED', 'VIP');
INSERT INTO enrollment
VALUES (1, 1, DEFAULT, 'ATTENDED', 5, 'Great'),
       (1, 2, DEFAULT, 'NO_SHOW', NULL, NULL),
       (1, 3, DEFAULT, 'ATTENDED', 4, 'Nice'),

       (2, 1, DEFAULT, 'ATTENDED', 4, NULL),
       (2, 2, DEFAULT, 'ATTENDED', 5, 'Hard but good'),

       (3, 2, DEFAULT, 'ATTENDED', 5, 'Loved it'),
       (3, 4, DEFAULT, 'ENROLLED', NULL, NULL),

       (4, 3, DEFAULT, 'CANCELLED', NULL, NULL),
       (5, 1, DEFAULT, 'ENROLLED', NULL, NULL);
