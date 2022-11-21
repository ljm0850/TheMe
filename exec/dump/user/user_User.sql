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
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `alertCount` int NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `UK_cdd273rg61diywe30f4k0t5mo` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,0,'2022-11-18 04:26:01',NULL,NULL,'2508149111','라식한 버즈','http://k.kakaocdn.net/dn/X0owr/btrQuH2TWPN/KHgD7MbiI17CN5QfEGWQH0/img_640x640.jpg'),(2,0,'2022-11-18 04:26:11','프론트엔드를 맡고 있습니다',NULL,'2507847523','한국 다람쥐','https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/profile%2F1-1530177020664.jpg?alt=media'),(3,0,'2022-11-18 04:27:11','맛집왕',NULL,'2514941459','야행성 사과','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg'),(4,0,'2022-11-18 04:27:19','안녕하세요 싸피 7기입니다.',NULL,'2511168869','sezin','https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/profile%2F1-비엔나.jpg?alt=media'),(5,0,'2022-11-18 04:30:59',NULL,NULL,'2511177014','지루한 지구','http://k.kakaocdn.net/dn/dpk9l1/btqmGhA2lKL/Oz0wDuJn1YV2DIn92f6DVK/img_640x640.jpg'),(6,0,'2022-11-18 06:00:09',NULL,NULL,'2539527737','유연한 고수','http://k.kakaocdn.net/dn/bHQCGA/btrFfZGNzEY/vDjGItW0EcgI9VuVNwrcV1/img_640x640.jpg'),(7,0,'2022-11-20 02:27:21',NULL,NULL,'2541881383','달콤한 영웅','https://firebasestorage.googleapis.com/v0/b/theme-b8677.appspot.com/o/profile%2F1-1514257105601.jpg?alt=media'),(8,0,'2022-11-20 12:27:57',NULL,NULL,'2542663933','당찬 유튜버','http://k.kakaocdn.net/dn/8aAmF/btrFUT1pCzs/SvqEc4OgkEdUPRapD1gFtK/img_640x640.jpg'),(9,0,'2022-11-20 14:16:10',NULL,NULL,'2542805536','부서진 뚱이','http://k.kakaocdn.net/dn/bEXzAe/btrPVDgsK1C/lywmo2iBbKyWlYuJ8WISnk/img_640x640.jpg');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 12:44:15
