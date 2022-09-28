CREATE DATABASE  IF NOT EXISTS `ssafytest`;
USE `ssafytest`;

DROP TABLE IF EXISTS `note`;

CREATE TABLE `note` (
  `noteCode` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `price` int NOT NULL,
  `company` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`noteCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `note` WRITE;
INSERT INTO `note` VALUES ('1234','모델1',1234,'수정됨'),('16ZD95P-GX50K','2022 그램16',1475900,'엘지'),('NT550XDZ-AD5A','노트북 플러스2',729000,'삼성');
UNLOCK TABLES;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `pw` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES ('admin','admin'),('ssafy','ssafy');
UNLOCK TABLES;
