-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: k7c203.p.ssafy.io    Database: user
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `Follow`
--

DROP TABLE IF EXISTS `Follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Follow` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `themeIdx` int NOT NULL,
  `follow_user_id` int DEFAULT NULL,
  `following_user_id` int DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `FK9c34cteonf9ghw9n2v88pkaqv` (`follow_user_id`),
  KEY `FK7wewa8vwhiewnx6f5lfo3cypf` (`following_user_id`),
  CONSTRAINT `FK7wewa8vwhiewnx6f5lfo3cypf` FOREIGN KEY (`following_user_id`) REFERENCES `User` (`idx`),
  CONSTRAINT `FK9c34cteonf9ghw9n2v88pkaqv` FOREIGN KEY (`follow_user_id`) REFERENCES `User` (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Follow`
--

LOCK TABLES `Follow` WRITE;
/*!40000 ALTER TABLE `Follow` DISABLE KEYS */;
INSERT INTO `Follow` VALUES (3,1,4,1),(5,11,4,3),(7,13,1,3),(8,1,3,1),(14,2,2,3),(17,9,2,3),(18,17,2,3),(20,10,2,4),(21,21,2,4),(22,22,2,4),(23,28,2,4),(24,15,4,1),(25,9,4,3),(26,14,4,3),(27,10,3,4),(29,21,5,4),(32,9,5,3),(37,16,8,3),(41,4,8,3),(42,11,8,3),(43,9,8,3);
/*!40000 ALTER TABLE `Follow` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 12:44:16
