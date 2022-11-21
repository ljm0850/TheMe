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
-- Table structure for table `Theme`
--

DROP TABLE IF EXISTS `Theme`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Theme` (
  `idx` int NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `emoticon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Theme`
--

LOCK TABLES `Theme` WRITE;
/*!40000 ALTER TABLE `Theme` DISABLE KEYS */;
INSERT INTO `Theme` VALUES (1,'2022-11-18 04:28:42','☕','조용한 화순 카페'),(2,'2022-11-18 04:32:06','?','산책하기 좋은 공원'),(3,'2022-11-18 04:32:26','?','수완지구 고기 맛집'),(4,'2022-11-18 04:33:11','?','광주 마라탕 맛집'),(5,'2022-11-18 04:34:45','?','광주 산책하기 좋은 곳'),(6,'2022-11-18 04:36:14','?','장덕동 코딩하기 좋은 카페'),(7,'2022-11-18 04:38:40','✨','나만 알고 싶은 산책 코스'),(8,'2022-11-18 04:44:45','☕','수완지구 커피가 맛있는 카페'),(9,'2022-11-18 04:45:48','?','제주도에서 꼭 먹어봐'),(10,'2022-11-18 05:02:22','?','사양이 좋은 PC방'),(11,'2022-11-18 05:06:41','?','홍대 회 맛집'),(12,'2022-11-18 05:41:29','?','충장로 데이트'),(13,'2022-11-18 05:48:23','?','전대 데이트 맛집'),(14,'2022-11-18 07:21:48','?','동네 친구들과 가기 좋은 술집'),(15,'2022-11-19 07:47:59','?','민트초코 홀릭'),(16,'2022-11-19 13:41:00','??','돼지고기 좋아 하시나요?'),(17,'2022-11-20 01:41:01','?','장덕동 고기 맛집'),(18,'2022-11-20 02:19:36','?','고기 고기'),(19,'2022-11-20 12:25:52','?','시그니쳐 메뉴가 인상적인 카페'),(20,'2022-11-21 01:12:42','?','풍경이 ');
/*!40000 ALTER TABLE `Theme` ENABLE KEYS */;
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
