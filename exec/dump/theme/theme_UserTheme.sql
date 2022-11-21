-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: k7c203.p.ssafy.io    Database: theme
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
-- Table structure for table `UserTheme`
--

DROP TABLE IF EXISTS `UserTheme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `UserTheme` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `challenge` bit(1) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  `openType` int NOT NULL,
  `user_idx` int DEFAULT NULL,
  `parent_theme_idx` int DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `FKlfh2pkomon6u20llyascoiaij` (`parent_theme_idx`),
  CONSTRAINT `FKlfh2pkomon6u20llyascoiaij` FOREIGN KEY (`parent_theme_idx`) REFERENCES `Theme` (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserTheme`
--

LOCK TABLES `UserTheme` WRITE;
/*!40000 ALTER TABLE `UserTheme` DISABLE KEYS */;
INSERT INTO `UserTheme` VALUES (1,_binary '\0','2022-11-18 04:28:42','','2022-11-18 04:28:42',0,1,1),(2,_binary '\0','2022-11-18 04:32:06','','2022-11-18 04:32:06',0,3,2),(3,_binary '\0','2022-11-18 04:32:26','','2022-11-18 04:32:26',0,5,3),(4,_binary '\0','2022-11-18 04:33:11','','2022-11-18 04:33:11',0,3,4),(5,_binary '\0','2022-11-18 04:34:46','','2022-11-18 04:34:46',0,2,5),(8,_binary '\0','2022-11-18 04:44:45','','2022-11-18 04:44:45',0,5,8),(9,_binary '\0','2022-11-18 04:45:48','','2022-11-18 04:45:48',0,3,9),(10,_binary '\0','2022-11-18 05:02:23','','2022-11-18 05:02:23',0,4,10),(11,_binary '\0','2022-11-18 05:06:40','','2022-11-18 05:06:40',0,3,11),(13,_binary '\0','2022-11-18 05:41:29','','2022-11-18 05:41:29',0,3,12),(14,_binary '\0','2022-11-18 05:48:23','','2022-11-18 05:48:23',0,3,13),(15,_binary '\0','2022-11-18 05:49:00','','2022-11-18 05:49:00',0,1,4),(16,_binary '\0','2022-11-18 06:02:23','','2022-11-18 06:02:23',0,3,1),(17,_binary '\0','2022-11-18 06:02:42','','2022-11-18 06:02:42',0,3,5),(20,_binary '\0','2022-11-18 07:21:49','','2022-11-18 07:21:49',0,2,14),(21,_binary '\0','2022-11-18 12:32:32','','2022-11-18 12:32:32',0,4,5),(22,_binary '\0','2022-11-18 12:39:17','','2022-11-18 12:39:17',0,4,11),(23,_binary '\0','2022-11-18 13:33:05','','2022-11-18 13:33:05',0,2,9),(24,_binary '\0','2022-11-19 01:40:04','','2022-11-19 01:40:04',0,3,8),(25,_binary '\0','2022-11-19 07:48:00','','2022-11-19 07:48:00',0,2,15),(28,_binary '\0','2022-11-20 01:41:01','','2022-11-20 01:41:01',0,4,17),(29,_binary '\0','2022-11-20 01:58:18','','2022-11-20 01:58:18',0,4,8),(30,_binary '\0','2022-11-20 02:19:38','','2022-11-20 02:19:38',0,2,18),(31,_binary '\0','2022-11-20 02:28:12','','2022-11-20 02:28:12',0,7,18),(32,_binary '\0','2022-11-20 02:37:19','','2022-11-20 02:37:19',0,4,3),(33,_binary '\0','2022-11-20 04:09:19','','2022-11-20 04:09:19',0,2,2),(34,_binary '\0','2022-11-20 04:21:20','','2022-11-20 04:21:20',0,5,18),(35,_binary '\0','2022-11-20 11:49:01','','2022-11-20 11:49:01',0,2,13),(36,_binary '\0','2022-11-20 12:25:55','','2022-11-20 12:25:55',0,2,19),(37,_binary '\0','2022-11-20 12:29:26','','2022-11-20 12:29:26',0,8,19),(38,_binary '\0','2022-11-21 01:12:42','','2022-11-21 01:12:42',0,8,20);
/*!40000 ALTER TABLE `UserTheme` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 12:45:04
