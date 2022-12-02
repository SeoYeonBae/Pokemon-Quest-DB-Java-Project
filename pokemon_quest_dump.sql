-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pokemon
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
-- Table structure for table `contact_type`
--

DROP TABLE IF EXISTS `contact_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact_type` (
  `pokemon_id` int NOT NULL,
  `type_id` int NOT NULL,
  PRIMARY KEY (`pokemon_id`,`type_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `contact_type_ibfk_1` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`pokemon_id`),
  CONSTRAINT `contact_type_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_type`
--

LOCK TABLES `contact_type` WRITE;
/*!40000 ALTER TABLE `contact_type` DISABLE KEYS */;
INSERT INTO `contact_type` VALUES (15,1),(5,2),(6,2),(7,2),(8,2),(9,2),(10,2),(20,2),(31,2),(42,2),(43,2),(44,2),(51,2),(52,2),(56,2),(57,2),(58,2),(64,2),(65,2),(1,3),(2,3),(4,3),(11,3),(12,3),(13,3),(14,3),(16,3),(17,3),(32,3),(33,3),(37,3),(24,4),(59,4),(60,4),(25,5),(36,5),(40,5),(41,5),(15,6),(18,6),(19,6),(21,6),(23,6),(25,6),(26,6),(29,6),(30,6),(46,6),(47,6),(49,6),(50,6),(53,6),(54,6),(55,6),(63,6),(68,6),(4,7),(3,8),(27,8),(28,8),(38,8),(39,8),(45,8),(48,8),(3,9),(5,9),(6,9),(8,9),(11,9),(12,9),(20,9),(24,9),(26,9),(21,10),(23,10),(18,11),(19,11),(22,11),(61,11),(62,11),(69,11),(34,12),(35,12),(7,13),(9,13),(10,13),(66,13),(1,14),(2,14),(13,14),(14,14),(16,14),(17,14),(22,14),(67,14);
/*!40000 ALTER TABLE `contact_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gender`
--

DROP TABLE IF EXISTS `gender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gender` (
  `gender_id` int NOT NULL AUTO_INCREMENT,
  `gender` char(1) DEFAULT NULL,
  PRIMARY KEY (`gender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gender`
--

LOCK TABLES `gender` WRITE;
/*!40000 ALTER TABLE `gender` DISABLE KEYS */;
INSERT INTO `gender` VALUES (1,'m'),(2,'f');
/*!40000 ALTER TABLE `gender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `poke_character`
--

DROP TABLE IF EXISTS `poke_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `poke_character` (
  `character_id` int NOT NULL AUTO_INCREMENT,
  `poke_character` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`character_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `poke_character`
--

LOCK TABLES `poke_character` WRITE;
/*!40000 ALTER TABLE `poke_character` DISABLE KEYS */;
INSERT INTO `poke_character` VALUES (1,'말을 잘 들음'),(2,'밥을 많이 먹음'),(3,'시끄러움'),(4,'말이 없음'),(5,'땀을 많이 흘림'),(6,'씻기를 싫어함'),(7,'분홍색을 좋아함'),(8,'몰래 먹는 것을 즐김'),(9,'쓰다듬어 주는 것을 좋아함'),(10,'혼자 있는 것을 즐김'),(11,'책 읽는 것을 좋아함'),(12,'발바닥을 보여주지 않으려 함'),(13,'구름을 좋아함'),(14,'말랑한 것을 좋아함'),(15,'종종 알 수 없는 말을 함'),(16,'종종 자신을 사람으로 착각함'),(17,'예쁜 돌멩이를 모음'),(18,'꼬마 버스 타요를 좋아함'),(19,'항상 담요를 가지고 다님'),(20,'친구들 사진 찍는 것을 좋아함'),(21,'음료수를 항상 빨대로 마심'),(22,'작고 귀여운 것을 좋아함'),(23,'화가 나면 쉬를 쌈'),(24,'감기에 잘 걸림'),(25,'하늘색 솜사탕을 좋아함'),(26,'놀이동산을 좋아함'),(27,'거울을 자주 봄'),(28,'영화 보는 것을 좋아함'),(29,'노란색이 들어간 물건을 모음'),(30,'칭찬을 자주 해주어야 함'),(31,'놀아주지 않으면 쉽게 토라짐'),(32,'아직 20세기에 사는 줄 알고 있음'),(33,'항상 비련의 여주인공 꿈을 꿈'),(34,'왕꿈틀이 젤리를 좋아함'),(35,'언젠간 복권에 당첨될 거라고 믿음'),(36,'제일 좋아하는 간식은 크림빵');
/*!40000 ALTER TABLE `poke_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pokemon`
--

DROP TABLE IF EXISTS `pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pokemon` (
  `pokemon_id` int NOT NULL AUTO_INCREMENT,
  `poke_name` varchar(20) DEFAULT NULL,
  `height` decimal(3,1) DEFAULT NULL,
  `weight` decimal(4,1) DEFAULT NULL,
  `book_picture` varchar(50) DEFAULT NULL,
  `picture` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`pokemon_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pokemon`
--

LOCK TABLES `pokemon` WRITE;
/*!40000 ALTER TABLE `pokemon` DISABLE KEYS */;
INSERT INTO `pokemon` VALUES (1,'이상해씨',0.7,6.9,'src/res/작은이상해씨.png','src/res/큰이상해씨.png'),(2,'이상해풀',1.0,13.0,'src/res/작은이상해풀.png','src/res/큰이상해풀.png'),(3,'리자몽',0.7,6.9,'src/res/작은리자몽.png','src/res/큰리자몽.png'),(4,'뿔충이',0.7,6.9,'src/res/작은뿔충이.png','src/res/큰뿔충이.png'),(5,'구구',0.7,6.9,'src/res/작은구구.png','src/res/큰구구.png'),(6,'피죤',0.7,6.9,'src/res/작은피죤.png','src/res/큰피죤.png'),(7,'삐삐',0.6,7.5,'src/res/작은삐삐.png','src/res/큰삐삐.png'),(8,'픽시',1.3,40.0,'src/res/작은픽시.png','src/res/큰픽시.png'),(9,'푸린',0.5,5.5,'src/res/작은푸린.png','src/res/큰푸린.png'),(10,'푸크린',1.0,12.0,'src/res/작은푸크린.png','src/res/큰푸크린.png'),(11,'주뱃',0.8,7.5,'src/res/작은주뱃.png','src/res/큰주뱃.png'),(12,'골뱃',1.6,55.0,'src/res/작은골뱃.png','src/res/큰골뱃.png'),(13,'뚜벅쵸',0.5,5.4,'src/res/작은뚜벅쵸.png','src/res/큰뚜벅쵸.png'),(14,'라플레시아',1.2,18.6,'src/res/작은라플레시아.png','src/res/큰라플레시아.png'),(15,'강챙이',1.3,54.0,'src/res/작은강챙이.png','src/res/큰강챙이.png'),(16,'모다피',0.7,4.0,'src/res/작은모다피.png','src/res/큰모다피.png'),(17,'우츠동',1.0,6.4,'src/res/작은우츠동.png','src/res/큰우츠동.png'),(18,'야돈',1.2,36.0,'src/res/작은야돈.png','src/res/큰야돈.png'),(19,'야도란',1.6,78.5,'src/res/작은야도란.png','src/res/큰야도란.png'),(20,'파오리',0.8,15.0,'src/res/작은파오리.png','src/res/큰파오리.png'),(21,'쥬레곤',1.7,120.0,'src/res/작은쥬레곤.png','src/res/큰쥬레곤.png'),(22,'아라리',0.4,2.5,'src/res/작은아라리.png','src/res/큰아라리.png'),(23,'라프라스',2.5,220.0,'src/res/작은라프라스.png','src/res/큰라프라스.png'),(24,'망나뇽',2.2,210.0,'src/res/작은망나뇽.png','src/res/큰망나뇽.png'),(25,'우파',0.4,8.5,'src/res/작은우파.png','src/res/큰우파.png'),(26,'만타인',2.1,220.0,'src/res/작은만타인.png','src/res/큰만타인.png'),(27,'파이리',0.6,8.5,'src/res/작은파이리.png','src/res/큰파이리.png'),(28,'리자드',1.1,19.0,'src/res/작은리자드.png','src/res/큰리자드.png'),(29,'꼬부기',0.7,6.9,'src/res/작은꼬부기.png','src/res/큰꼬부기.png'),(30,'어니부기',0.7,6.9,'src/res/작은어니부기.png','src/res/큰어니부기.png'),(31,'꼬렛',0.3,3.5,'src/res/작은꼬렛.png','src/res/큰꼬렛.png'),(32,'아보',2.0,6.9,'src/res/작은아보.png','src/res/큰아보.png'),(33,'아보크',3.5,65.0,'src/res/작은아보크.png','src/res/큰아보크.png'),(34,'피카츄',0.4,6.0,'src/res/작은피카츄.png','src/res/큰피카츄.png'),(35,'라이츄',0.8,30.0,'src/res/작은라이츄.png','src/res/큰라이츄.png'),(36,'모래두지',0.6,12.0,'src/res/작은모래두지.png','src/res/큰모래두지.png'),(37,'니드런',0.4,7.0,'src/res/작은니드런.png','src/res/큰니드런.png'),(38,'식스테일',0.6,9.9,'src/res/작은식스테일.png','src/res/큰식스테일.png'),(39,'나인테일',1.1,19.9,'src/res/작은나인테일.png','src/res/큰나인테일.png'),(40,'디그다',0.2,0.8,'src/res/작은디그다.png','src/res/큰디그다.png'),(41,'닥트리오',0.7,33.3,'src/res/작은닥트리오.png','src/res/큰닥트리오.png'),(42,'나옹',0.4,4.2,'src/res/작은나옹.png','src/res/큰나옹.png'),(43,'페르시온',1.0,32.0,'src/res/작은페르시온.png','src/res/큰페르시온.png'),(44,'고라파덕',0.8,19.6,'src/res/작은고라파덕.png','src/res/큰고라파덕.png'),(45,'가디',0.7,19.0,'src/res/작은가디.png','src/res/큰가디.png'),(46,'발챙이',0.6,12.4,'src/res/작은발챙이.png','src/res/큰발챙이.png'),(47,'슈륙챙이',1.0,20.0,'src/res/작은슈륙챙이.png','src/res/큰슈륙챙이.png'),(48,'포니타',1.0,30.0,'src/res/작은포니타.png','src/res/큰포니타.png'),(49,'쥬쥬',1.1,90.0,'src/res/작은쥬쥬.png','src/res/큰쥬쥬.png'),(50,'탕구리',0.4,6.5,'src/res/작은탕구리.png','src/res/큰탕구리.png'),(51,'내루미',1.2,65.5,'src/res/작은내루미.png','src/res/큰내루미.png'),(52,'럭키',1.1,34.6,'src/res/작은럭키.png','src/res/큰럭키.png'),(53,'쏘드라',0.4,8.0,'src/res/작은쏘드라.png','src/res/큰쏘드라.png'),(54,'콘치',0.6,15.0,'src/res/작은콘치.png','src/res/큰콘치.png'),(55,'별가사리',0.8,34.5,'src/res/작은별가사리.png','src/res/큰별가사리.png'),(56,'메타몽',0.3,4.0,'src/res/작은메타몽.png','src/res/큰메타몽.png'),(57,'이브이',0.3,6.5,'src/res/작은이브이.png','src/res/큰이브이.png'),(58,'잠만보',2.1,460.0,'src/res/작은잠만보.png','src/res/큰잠만보.png'),(59,'미뇽',1.8,3.3,'src/res/작은미뇽.png','src/res/큰미뇽.png'),(60,'신뇽',4.0,16.5,'src/res/작은신뇽.png','src/res/큰신뇽.png'),(61,'뮤츠',2.0,122.0,'src/res/작은뮤츠.png','src/res/큰뮤츠.png'),(62,'뮤',0.4,4.0,'src/res/작은뮤.png','src/res/큰뮤.png'),(63,'치코리타',0.9,6.4,'src/res/작은치코리타.png','src/res/큰치코리타.png'),(64,'꼬리선',0.8,6.0,'src/res/작은꼬리선.png','src/res/큰꼬리선.png'),(65,'다꼬리',1.8,32.5,'src/res/작은다꼬리.png','src/res/큰다꼬리.png'),(66,'토게피',0.3,1.5,'src/res/작은토게피.png','src/res/큰토게피.png'),(67,'아르코',0.4,5.8,'src/res/작은아르코.png','src/res/큰아르코.png'),(68,'마릴',0.4,8.5,'src/res/작은마릴.png','src/res/큰마릴.png'),(69,'마자용',1.3,28.5,'src/res/작은마자용.png','src/res/큰마자용.png');
/*!40000 ALTER TABLE `pokemon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recommend`
--

DROP TABLE IF EXISTS `recommend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recommend` (
  `re_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `recommend` varchar(200) DEFAULT NULL,
  `reply` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`re_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `recommend_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recommend`
--

LOCK TABLES `recommend` WRITE;
/*!40000 ALTER TABLE `recommend` DISABLE KEYS */;
/*!40000 ALTER TABLE `recommend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type`
--

DROP TABLE IF EXISTS `type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type` (
  `type_id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type`
--

LOCK TABLES `type` WRITE;
/*!40000 ALTER TABLE `type` DISABLE KEYS */;
INSERT INTO `type` VALUES (1,'격투'),(2,'노말'),(3,'독'),(4,'드래곤'),(5,'땅'),(6,'물'),(7,'벌레'),(8,'불꽃'),(9,'비행'),(10,'얼음'),(11,'에스퍼'),(12,'전기'),(13,'페어리'),(14,'풀');
/*!40000 ALTER TABLE `type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `gender_id` int DEFAULT NULL,
  `id` varchar(20) DEFAULT NULL,
  `pw` varchar(30) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `gender_id` (`gender_id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`gender_id`) REFERENCES `gender` (`gender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,2,'manager','5098','배서연','010-2046-1540','tjdus2033@naver.com'),(2,2,'지지','0000','양지연','010-3955-5726','ygy911@naver.com'),(3,2,'소소','0000','이소영','010-1234-5678','star@naver.com'),(4,1,'꾹꾹','0000','남경국','010-1234-5678','guk@naver.com'),(5,1,'민민','0000','김경민','010-1234-5678','min@naver.com'),(6,1,'우우','0000','손찬우','010-1234-5678','woo@naver.com'),(7,1,'오오','0000','오승진','010-1234-5678','oh@naver.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_pokemon`
--

DROP TABLE IF EXISTS `user_pokemon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_pokemon` (
  `user_id` int NOT NULL,
  `pokemon_id` int NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `character_id` int NOT NULL,
  `poke_gender_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`pokemon_id`,`character_id`,`poke_gender_id`),
  KEY `pokemon_id` (`pokemon_id`),
  KEY `character_id` (`character_id`),
  KEY `poke_gender_id` (`poke_gender_id`),
  CONSTRAINT `user_pokemon_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `user_pokemon_ibfk_2` FOREIGN KEY (`pokemon_id`) REFERENCES `pokemon` (`pokemon_id`),
  CONSTRAINT `user_pokemon_ibfk_3` FOREIGN KEY (`character_id`) REFERENCES `poke_character` (`character_id`),
  CONSTRAINT `user_pokemon_ibfk_4` FOREIGN KEY (`poke_gender_id`) REFERENCES `gender` (`gender_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_pokemon`
--

LOCK TABLES `user_pokemon` WRITE;
/*!40000 ALTER TABLE `user_pokemon` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_pokemon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-02 18:20:29
