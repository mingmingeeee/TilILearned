CREATE DATABASE  IF NOT EXISTS `ssafytest`;
USE `ssafytest`;

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person`(
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `department_name` varchar(20) NOT NULL,
  `pay` int NOT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `person` WRITE;
INSERT INTO `person`
VALUES ('S001', '김싸피', '사무국', 5000),
	('S002', '이싸피', '서울캠퍼스', 4500),
	('S003', '박싸피', '부울경캠퍼스', 3700);
UNLOCK TABLES;