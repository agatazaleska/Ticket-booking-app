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
  FOREIGN KEY (`room_number`) REFERENCES `room`(`number`) ON DELETE CASCADE
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
  `customer_name` varchar(45) NOT NULL,
  `customer_surname` varchar(45) NOT NULL,
  `total_cost` float NOT NULL,
  `expiration_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
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

--
-- Sample data for the cinema
--

INSERT INTO `room` VALUES
    (1, 15, 20),
    (2, 10, 15),
    (3, 13, 20);

INSERT INTO `screening` VALUES
	(1,'Niebezpieczny kochanek',1,'2023-10-31','17:50'),
	(2,'O psie, który jeździł koleją',2,'2023-10-31','17:50'),
	(3,'O psie, który jeździł koleją',2,'2023-10-31','17:50'),
	(4,'O psie, który jeździł koleją',2,'2023-10-31','17:50'),
	(5,'Oppenheimer',1,'2023-10-31','17:50'),
	(6,'Różyczka 2',1,'2023-10-31','17:50'),
	(7,'Superposition',3,'2023-10-31','17:50'),
	(8,'Superposition',3,'2023-10-31','17:50');

INSERT INTO `reservation` VALUES
    (1, 'Andrzej', 'Kowalski', 50.0, '2023-10-31 17:50:45');

-- INSERT INTO `reservation_detail` VALUES
-- INSERT INTO `screening` VALUES
-- INSERT INTO `room` VALUES
-- INSERT INTO `seat_availability` VALUES