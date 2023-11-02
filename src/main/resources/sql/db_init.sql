CREATE DATABASE  IF NOT EXISTS `cinema`;
USE `cinema`;

-- CREATE USER 'cinemauser'@'localhost' IDENTIFIED BY 'cinemauser';
GRANT ALL PRIVILEGES ON cinema.* TO 'cinemauser'@'localhost';

DROP TABLE IF EXISTS `reservation_detail`;
DROP TABLE IF EXISTS `seat_availability`;
DROP TABLE IF EXISTS `reservation`;
DROP TABLE IF EXISTS `screening`;
DROP TABLE IF EXISTS `seat`;
DROP TABLE IF EXISTS `room`;
DROP TABLE IF EXISTS `ticket_price`;

CREATE TABLE `room` (
  `number` int NOT NULL,
  `total_rows` int NOT NULL,
  `total_seats_in_one_row` int NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_number` int NOT NULL,
  `row` varchar(45) NOT NULL,
  `seat_number` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`room_number`) REFERENCES `room`(`number`) ON DELETE CASCADE,
  UNIQUE KEY `unique_seat` (`room_number`, `row`, `seat_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `screening` (
  `id` int NOT NULL AUTO_INCREMENT,
  `movie_title` varchar(45) NOT NULL,
  `room_number` int NOT NULL,
  `date` date NOT NULL,
  `start_time` time NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`room_number`) REFERENCES `room`(`number`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `screening_id` int NOT NULL,
  `customer_name` varchar(45) NOT NULL,
  `customer_surname` varchar(45) NOT NULL,
  `total_cost` float NOT NULL,
  `expiration_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`screening_id`) REFERENCES `screening`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `reservation_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `seat_id` int NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`reservation_id`) REFERENCES `reservation`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`seat_id`) REFERENCES `seat`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seat_availability` (
  `id` int NOT NULL AUTO_INCREMENT,
  `screening_id` int NOT NULL,
  `seat_id` int NOT NULL,
  `available` bool NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`screening_id`) REFERENCES `screening`(`id`) ON DELETE CASCADE,
  FOREIGN KEY (`seat_id`) REFERENCES `seat`(`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ticket_price` (
  `type` varchar(45) NOT NULL,
  `price` float NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Sample data for the cinema
--

INSERT INTO `ticket_price` VALUES
    ('adult', 25.0),
    ('student', 18.0),
    ('child', 12.5);

INSERT INTO `room` VALUES
    (1, 3, 4),
    (2, 4, 4);

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

INSERT INTO `screening` VALUES
	(1,'Niebezpieczny kochanek',1,'2023-11-30','11:00'),
	(2,'O psie, który jeździł koleją',2,'2023-11-30','13:30'),
	(3,'O psie, który jeździł koleją',2,'2023-11-29','14:00'),
	(4,'O psie, który jeździł koleją',2,'2023-11-29','18:50'),
	(5,'Oppenheimer',1,'2023-11-30','15:20'),
	(6,'Różyczka 2',1,'2023-11-29','19:50'),
	(7,'Superposition',1,'2023-11-30','21:30'),
	(8,'Superposition',1,'2023-11-29','14:35');

INSERT INTO `reservation` VALUES
    (1, 1, 'Andrzej', 'Kowalski', 50.0, '2023-10-31 16:25:25'),
    (2, 2, 'Mikołaj', 'Szkaradek', 75.0, '2023-10-30 10:17:59'),
    (3, 2, 'Agata', 'Załęska', 50.0, '2023-11-01 14:30:12'),
    (4, 3, 'Jacek', 'Nowak', 25.50, '2023-11-02 17:50:45'),
    (5, 8, 'Mateusz', 'Łakomiec', 100.0, '2023-11-02 19:40:45');

INSERT INTO `reservation_detail` VALUES
    (1, 1, 1),
    (2, 1, 2),
    (3, 2, 11),
    (4, 2, 12),
    (5, 3, 21),
    (6, 4, 8),
    (7, 4, 9),
    (8, 4, 10),
    (9, 5, 18),
    (10, 5, 19);

INSERT INTO `seat_availability` (`screening_id`, `seat_id`, `available`)
SELECT s.id, seat.id, true
FROM `screening` s
JOIN `seat` ON seat.room_number = s.room_number;
