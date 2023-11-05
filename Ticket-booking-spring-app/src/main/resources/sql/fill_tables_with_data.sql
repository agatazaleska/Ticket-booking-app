USE `cinema`;

DELETE FROM `reservation_detail`;
DELETE FROM `seat_availability`;
DELETE FROM `reservation`;
DELETE FROM `screening`;
DELETE FROM `seat`;
DELETE FROM `room`;
DELETE FROM `ticket_price`;

INSERT INTO `ticket_price` VALUES
    ('adult', 25.0),
    ('student', 18.0),
    ('child', 12.5);

INSERT INTO `room` VALUES
    (1, 3, 4),
    (2, 4, 4),
    (3, 4, 4);

INSERT INTO `seat` VALUES
    (1, 1, 'A', 1),
    (2, 1, 'A', 2),
    (3, 1, 'A', 3),
    (4, 1, 'A', 4),
    (5, 1, 'B', 1),
    (6, 1, 'B', 2),
    (7, 1, 'B', 3),
    (8, 1, 'B', 4),
    (9, 1, 'C', 1),
    (10, 1, 'C', 2),
    (11, 1, 'C', 3),
    (12, 1, 'C', 4);

INSERT INTO `seat` VALUES
    (13, 2, 'A', 1),
    (14, 2, 'A', 2),
    (15, 2, 'A', 3),
    (16, 2, 'A', 4),
    (17, 2, 'B', 1),
    (18, 2, 'B', 2),
    (19, 2, 'B', 3),
    (20, 2, 'B', 4),
    (21, 2, 'C', 1),
    (22, 2, 'C', 2),
    (23, 2, 'C', 3),
    (24, 2, 'C', 4),
    (25, 2, 'D', 1),
    (26, 2, 'D', 2),
    (27, 2, 'D', 3),
    (28, 2, 'D', 4);

INSERT INTO `seat` VALUES
    (29, 3, 'A', 1),
    (30, 3, 'A', 2),
    (31, 3, 'A', 3),
    (32, 3, 'A', 4),
    (33, 3, 'B', 1),
    (34, 3, 'B', 2),
    (35, 3, 'B', 3),
    (36, 3, 'B', 4),
    (37, 3, 'C', 1),
    (38, 3, 'C', 2),
    (39, 3, 'C', 3),
    (40, 3, 'C', 4),
    (41, 3, 'D', 1),
    (42, 3, 'D', 2),
    (43, 3, 'D', 3),
    (44, 3, 'D', 4);

INSERT INTO `screening` VALUES
	(1,'Niebezpieczny kochanek',1,'2023-11-30','11:00'),
	(2,'O psie, który jeździł koleją',2,'2023-11-30','13:30'),
	(3,'O psie, który jeździł koleją',2,'2023-11-29','14:00'),
	(4,'O psie, który jeździł koleją',2,'2023-11-29','18:50'),
	(5,'Oppenheimer',1,'2023-11-30','15:20'),
	(6,'Różyczka 2',3,'2023-11-29','19:50'),
	(7,'Superposition',3,'2023-11-30','21:30'),
	(8,'Superposition',1,'2023-11-29','14:35');

INSERT INTO `seat_availability` (`screening_id`, `seat_id`, `available`)
SELECT s.id, seat.id, true
FROM `screening` s
JOIN `seat` ON seat.room_number = s.room_number;