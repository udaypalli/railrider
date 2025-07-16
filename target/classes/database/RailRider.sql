
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

create database railrider;
use railrider;

CREATE TABLE `trains` (
    `train_id` int(10) NOT NULL,
    `train_name` varchar(255) NOT NULL,
    `train_number` int(100) NOT NULL,
    `dep_station` varchar(255) NOT NULL,
    `arr_station` varchar(255) NOT NULL,
    `arr_time` varchar(255) NOT NULL,
    `dep_time`varchar(255) NOT NULL,
    `total_seats` int(255) NOT NULL,
    `fair` int(255) NOT NULL,
    `cancelled_dates` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `trains` (`train_id`, `train_name`, `train_number`, `dep_station`,`arr_station`,`arr_time`,`dep_time`,`total_seats`,`fair`) VALUES
(1,'SIDDHESHWAR EXP',12116,'solapur','mumbai','22:30','05:00',200,355),
(2,'HUTATMA EXPRESS',12158,'solapur','pune','06:30','10:30',400,130),
(3, 'Rajdhani Express', 12301, 'Delhi', 'Mumbai', '15:00', '08:00', 300, 1000),
(4, 'Shatabdi Express', 12010, 'Chennai', 'Bangalore', '10:00', '14:00', 250, 450),
(5, 'Duronto Express', 12221, 'Kolkata', 'Delhi', '20:00', '08:00', 350, 800),
(6, 'Gatimaan Express', 12049, 'Agra', 'New Delhi', '10:30', '13:30', 150, 600),
(7, 'Karnataka Express', 12627, 'Bangalore', 'New Delhi', '06:00', '12:00', 500, 700),
(8, 'Howrah Mail', 12809, 'Chennai', 'Kolkata', '21:00', '07:00', 400, 550),
(9, 'Goa Express', 12779, 'Mumbai', 'Goa', '08:00', '14:00', 300, 350),
(10, 'Pashchim Express', 12925, 'Amritsar', 'Bandra Terminus', '22:30', '16:30', 200, 450);


CREATE TABLE `tickets` (
    `ticket_id` int(255) NOT NULL,
    `user_id` int(255) NOT NULL,
    `train_id`int(255) NOT NULL,
    `prn` int(255) NOT NULL,
    `trans_id` varchar(255) NOT NULL,
    `journey_date` int(255) NOT NULL,
    `pname` varchar(255) NOT NULL,
    `age` int(255) NOT NULL,
    `gender` varchar(255) NOT NULL,
    `time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `payments` (
    `user_id` int(255) NOT NULL,
    `trans_id` varchar(255) NOT NULL,
    `amount` int(255) NOT NULL,
    `train_id` int(255) NOT NULL,
    `time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `seats` (
    `train_id` int(255) NOT NULL,
    `date`varchar(255) NOT NULL,
    `available_seats` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `admins` (
    `admin_id` int(255) NOT NULL,
    `email` varchar(255) NOT NULL,
    `pass` varchar(255) NOT NULL,
    `fname` varchar(255) NOT NULL,
    `lname`varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `admins` (`admin_id`, `email`, `pass`, `fname`,`lname`) VALUES
(1,'uday@gmail.com','uday123','uday','palli');

CREATE TABLE `users` (
    `user_id` int(255) NOT NULL,
    `fname` varchar(255) NOT NULL,
    `lname`varchar(255) NOT NULL,
    `phone_no`varchar(255) NOT NULL,
    `email`varchar(255) NOT NULL,
    `pass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE `trains`
  ADD PRIMARY KEY (`train_id`);

ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticket_id`);

ALTER TABLE `payments`
  ADD PRIMARY KEY (`trans_id`);

ALTER TABLE `admins`
  ADD PRIMARY KEY (`admin_id`);

ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

ALTER TABLE `trains`
  MODIFY `train_id` int(255) NOT NULL AUTO_INCREMENT;

ALTER TABLE `tickets`
  MODIFY `ticket_id` int(255) NOT NULL AUTO_INCREMENT;

ALTER TABLE `admins`
  MODIFY `admin_id` int(255) NOT NULL AUTO_INCREMENT;

ALTER TABLE `users`
  MODIFY `user_id` int(255) NOT NULL AUTO_INCREMENT;

COMMIT;