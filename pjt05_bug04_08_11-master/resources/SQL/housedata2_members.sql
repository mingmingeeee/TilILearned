CREATE DATABASE  IF NOT EXISTS `housedata2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `housedata2`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: housedata2
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `members`
--

DROP TABLE IF EXISTS `members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `members` (
  `user_id` varchar(16) NOT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(16) NOT NULL,
  `email_id` varchar(20) DEFAULT NULL,
  `email_domain` varchar(45) DEFAULT NULL,
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `is_admin` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `members`
--

LOCK TABLES `members` WRITE;
/*!40000 ALTER TABLE `members` DISABLE KEYS */;
INSERT INTO `members` VALUES ('admin','관리자','1234','admin','google.com','2022-09-28 00:57:35',1),('Aram1234','남아람','1234','Aram1234','kakao.com','2022-10-11 23:59:20',0),('borabora','설보라','1234','borabora21','kakao.com','2022-10-11 23:49:18',0),('damidami','최다미','1234','ssafy1234','naver.com','2022-10-11 23:52:18',0),('danoh55','은단오','1234','danoh55','naver.com','2022-10-12 00:00:18',0),('dongcheol2','임동철','1234','Dong-cheol','naver.com','2022-10-11 23:46:29',0),('Donghyuk','박동혁','1234','Donghyuk3456','kakao.com','2022-10-11 23:50:40',0),('gijuhan1997','한기주','1234','gijuhan1997','naver.com','2022-10-12 00:39:23',0),('goonghyuk','남궁혁','1234','goonggod','kakao.com','2022-10-11 23:56:11',0),('haluday1','하루','1234','day1','google.com','2022-10-12 00:02:05',0),('hangil','성한길','1234','Han-gilSung','naver.com','2022-10-11 23:48:48',0),('Hyeeun','심혜은','1234','Hyeeun1234','naver.com','2022-10-11 23:53:30',0),('moooon','문광일','moooon123','moooon0815','선택','2022-10-11 23:58:28',0),('narajang3456','장나라','1234','narajang3456','ssafy.com','2022-10-11 23:51:25',0),('Ryuho','류호','1234',' Ryuho8756','naver.com','2022-10-11 23:49:56',0),('saeron','김새론','1234','ssafy5555','naver.com','2022-10-11 23:47:08',0),('Saetbyul','임샛별','1234','start1234','google.com','2022-10-11 23:48:22',0),('ssafy','김싸피','1234','ssafy','ssafy.com','2022-09-28 00:57:35',0),('ssafy2','이싸피','1234','ssafy2','google.com','2022-09-28 05:09:28',0),('troment2','안효인','1234','troment2','ssafy.com','2022-09-28 05:26:01',0),('wonsik','송원식','1234','Won-sikSong ','ssafy.com','2022-10-11 23:47:47',0);
/*!40000 ALTER TABLE `members` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-10-12 17:11:01
