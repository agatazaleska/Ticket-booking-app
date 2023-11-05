CREATE DATABASE  IF NOT EXISTS `cinema`;
USE `cinema`;

CREATE USER 'cinemauser'@'localhost' IDENTIFIED BY 'cinemauser';
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
