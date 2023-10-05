-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: j9b306.p.ssafy.io    Database: B306
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `auth_user`
--

DROP TABLE IF EXISTS `auth_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auth_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auth_user`
--

LOCK TABLES `auth_user` WRITE;
/*!40000 ALTER TABLE `auth_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `auth_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `battle_record`
--

DROP TABLE IF EXISTS `battle_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `battle_record` (
  `battle_record_seq` int NOT NULL AUTO_INCREMENT,
  `battle_date` datetime(6) DEFAULT NULL,
  `battle_other_seq` int DEFAULT NULL,
  `battle_user_seq` int DEFAULT NULL,
  `winner_seq` int DEFAULT NULL,
  PRIMARY KEY (`battle_record_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `battle_record`
--

LOCK TABLES `battle_record` WRITE;
/*!40000 ALTER TABLE `battle_record` DISABLE KEYS */;
INSERT INTO `battle_record` VALUES (1,'2023-09-27 15:02:00.522854',7,6,7),(3,'2023-10-02 22:12:58.925097',7,6,7),(4,'2023-10-03 22:14:08.949446',6,7,6),(5,'2023-10-03 22:14:08.949446',7,13,13),(6,'2023-10-04 12:14:08.949446',13,7,13),(8,'2023-10-05 01:11:48.204022',6,7,6),(9,'2023-10-05 01:13:15.530977',17,7,17),(10,'2023-10-05 01:40:21.117663',17,7,17),(13,'2023-10-05 01:53:32.131904',6,7,6),(14,'2023-10-05 01:54:15.424662',26,7,26),(15,'2023-10-05 01:58:30.399464',26,7,26),(16,'2023-10-05 02:04:17.240191',23,7,23),(17,'2023-10-05 02:05:54.120735',6,7,6),(18,'2023-10-05 02:07:25.018147',26,7,26),(19,'2023-10-05 02:07:40.040518',17,7,17),(20,'2023-10-05 02:08:59.456078',23,7,23),(24,'2023-10-05 10:06:01.702962',6,7,6),(25,'2023-10-05 10:06:30.262524',26,7,26),(26,'2023-10-05 10:13:39.882619',6,7,6),(27,'2023-10-05 10:14:26.818539',26,17,26),(28,'2023-10-05 10:20:56.569385',6,7,6),(36,'2023-10-05 11:29:05.794492',26,49,26),(37,'2023-10-05 11:31:22.563181',7,49,7),(38,'2023-10-05 13:26:03.219646',6,52,6),(39,'2023-10-05 13:56:13.133865',26,55,26),(40,'2023-10-05 13:56:34.016178',6,55,6),(41,'2023-10-05 14:16:36.186447',52,57,52),(42,'2023-10-05 16:06:19.522241',23,7,23);
/*!40000 ALTER TABLE `battle_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cell`
--

DROP TABLE IF EXISTS `cell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cell` (
  `cell_seq` int NOT NULL AUTO_INCREMENT,
  `cell_exp` bigint DEFAULT NULL,
  `cell_latest_exp` int DEFAULT NULL,
  `cell_name` varchar(255) DEFAULT NULL,
  `user_seq` int DEFAULT NULL,
  PRIMARY KEY (`cell_seq`),
  KEY `FKqft6d1hdqhjjnhsn8oorvqkla` (`user_seq`),
  CONSTRAINT `FKqft6d1hdqhjjnhsn8oorvqkla` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell`
--

LOCK TABLES `cell` WRITE;
/*!40000 ALTER TABLE `cell` DISABLE KEYS */;
INSERT INTO `cell` VALUES (1,10200,5000,'조영헌몽',6),(2,8000,2000,'곰탱몽',7),(13,16680,16680,'세포몽',17),(19,16680,16680,'dutong',23),(22,16680,16680,'mongmong',26),(43,47,47,'testCell',52);
/*!40000 ALTER TABLE `cell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_admin_log`
--

DROP TABLE IF EXISTS `django_admin_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_admin_log` (
  `id` int NOT NULL AUTO_INCREMENT,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint unsigned NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`),
  CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`),
  CONSTRAINT `django_admin_log_chk_1` CHECK ((`action_flag` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_admin_log`
--

LOCK TABLES `django_admin_log` WRITE;
/*!40000 ALTER TABLE `django_admin_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_admin_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_content_type`
--

DROP TABLE IF EXISTS `django_content_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_content_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_content_type`
--

LOCK TABLES `django_content_type` WRITE;
/*!40000 ALTER TABLE `django_content_type` DISABLE KEYS */;
INSERT INTO `django_content_type` VALUES (1,'admin','logentry'),(3,'auth','group'),(2,'auth','permission'),(4,'auth','user'),(5,'contenttypes','contenttype'),(6,'sessions','session');
/*!40000 ALTER TABLE `django_content_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_migrations`
--

DROP TABLE IF EXISTS `django_migrations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_migrations` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_migrations`
--

LOCK TABLES `django_migrations` WRITE;
/*!40000 ALTER TABLE `django_migrations` DISABLE KEYS */;
INSERT INTO `django_migrations` VALUES (1,'contenttypes','0001_initial','2023-09-22 08:26:43.820676'),(2,'auth','0001_initial','2023-09-22 08:26:58.770251'),(3,'admin','0001_initial','2023-09-22 08:27:01.871149'),(4,'admin','0002_logentry_remove_auto_add','2023-09-22 08:27:02.046438'),(5,'admin','0003_logentry_add_action_flag_choices','2023-09-22 08:27:02.262920'),(6,'contenttypes','0002_remove_content_type_name','2023-09-22 08:27:04.752947'),(7,'auth','0002_alter_permission_name_max_length','2023-09-22 08:27:07.154094'),(8,'auth','0003_alter_user_email_max_length','2023-09-22 08:27:07.786714'),(9,'auth','0004_alter_user_username_opts','2023-09-22 08:27:08.105107'),(10,'auth','0005_alter_user_last_login_null','2023-09-22 08:27:10.353571'),(11,'auth','0006_require_contenttypes_0002','2023-09-22 08:27:10.651842'),(12,'auth','0007_alter_validators_add_error_messages','2023-09-22 08:27:10.833852'),(13,'auth','0008_alter_user_username_max_length','2023-09-22 08:27:13.816585'),(14,'auth','0009_alter_user_last_name_max_length','2023-09-22 08:27:16.187539'),(15,'auth','0010_alter_group_name_max_length','2023-09-22 08:27:16.803365'),(16,'auth','0011_update_proxy_permissions','2023-09-22 08:27:17.054259'),(17,'auth','0012_alter_user_first_name_max_length','2023-09-22 08:27:18.937099'),(18,'sessions','0001_initial','2023-09-22 08:27:19.447752');
/*!40000 ALTER TABLE `django_migrations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `django_session`
--

DROP TABLE IF EXISTS `django_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL,
  PRIMARY KEY (`session_key`),
  KEY `django_session_expire_date_a5c62663` (`expire_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `django_session`
--

LOCK TABLES `django_session` WRITE;
/*!40000 ALTER TABLE `django_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `django_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_list`
--

DROP TABLE IF EXISTS `exercise_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_list` (
  `exercise_list_seq` tinyint NOT NULL AUTO_INCREMENT,
  `exercise_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`exercise_list_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_list`
--

LOCK TABLES `exercise_list` WRITE;
/*!40000 ALTER TABLE `exercise_list` DISABLE KEYS */;
INSERT INTO `exercise_list` VALUES (1,'사이클링'),(2,'수영'),(3,'걷기'),(4,'런닝'),(5,'런닝머신'),(6,'댄스'),(7,'에어로빅'),(8,'체조'),(9,'필라테스'),(10,'요가'),(11,'골프'),(12,'볼링'),(13,'탁구'),(14,'등산'),(15,'태보'),(16,'암벽등반'),(17,'헬스'),(18,'승마'),(19,'자전거'),(20,'캐치볼'),(21,'풋살');
/*!40000 ALTER TABLE `exercise_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exercise_record`
--

DROP TABLE IF EXISTS `exercise_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_record` (
  `exercise_record_seq` int NOT NULL AUTO_INCREMENT,
  `exercise_avg_bpm` int DEFAULT NULL,
  `exercise_distance` int DEFAULT NULL,
  `exercise_end` datetime(6) DEFAULT NULL,
  `exercise_max_bpm` int DEFAULT NULL,
  `exercise_reco` bit(1) DEFAULT NULL,
  `exercise_review` int DEFAULT NULL,
  `exercise_start` datetime(6) DEFAULT NULL,
  `exercise_weather` int DEFAULT NULL,
  `exercise_list_seq` tinyint DEFAULT NULL,
  `user_seq` int DEFAULT NULL,
  PRIMARY KEY (`exercise_record_seq`),
  KEY `FKl3gnw9lyfxyxpefu2bs32fjyi` (`exercise_list_seq`),
  KEY `FKl6dato62ionuobd9ysae52f8o` (`user_seq`),
  CONSTRAINT `FKl3gnw9lyfxyxpefu2bs32fjyi` FOREIGN KEY (`exercise_list_seq`) REFERENCES `exercise_list` (`exercise_list_seq`),
  CONSTRAINT `FKl6dato62ionuobd9ysae52f8o` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise_record`
--

LOCK TABLES `exercise_record` WRITE;
/*!40000 ALTER TABLE `exercise_record` DISABLE KEYS */;
INSERT INTO `exercise_record` VALUES (1,140,0,'2023-10-05 19:57:33.466000',160,_binary '',5,'2023-10-05 09:57:33.466000',1,1,6),(2,135,0,'2023-10-05 11:57:33.466000',159,_binary '',5,'2023-10-05 09:57:33.466000',1,1,7),(5,120,0,'2023-10-05 10:09:36.001000',158,_binary '',4,'2023-10-05 09:09:36.001000',3,3,17),(6,120,0,'2023-10-05 10:09:36.001000',158,_binary '',4,'2023-10-05 09:09:36.001000',3,3,23),(7,135,0,'2023-10-05 10:09:36.001000',159,_binary '',4,'2023-10-05 09:09:36.001000',3,3,26),(8,120,0,'2023-10-05 22:24:00.000000',135,_binary '',5,'2023-10-05 22:23:30.000000',1,3,52),(9,119,0,'2023-10-04 19:33:19.914000',149,_binary '',4,'2023-10-04 18:33:19.914000',2,11,7),(10,129,0,'2023-10-02 19:33:19.914000',159,_binary '',5,'2023-10-02 18:33:19.914000',5,8,7),(11,129,0,'2023-09-27 19:33:19.914000',159,_binary '',4,'2023-09-27 18:33:19.914000',1,6,7);
/*!40000 ALTER TABLE `exercise_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prefer_exercise`
--

DROP TABLE IF EXISTS `prefer_exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prefer_exercise` (
  `prefer_exercise_seq` int NOT NULL AUTO_INCREMENT,
  `exercise_list_seq` tinyint DEFAULT NULL,
  `user_seq` int DEFAULT NULL,
  PRIMARY KEY (`prefer_exercise_seq`),
  KEY `FK32clb6t804rs4uajyq5qdv87l` (`exercise_list_seq`),
  KEY `FKp0lwsnw4oh7i9upxc9dwkpi7` (`user_seq`),
  CONSTRAINT `FK32clb6t804rs4uajyq5qdv87l` FOREIGN KEY (`exercise_list_seq`) REFERENCES `exercise_list` (`exercise_list_seq`),
  CONSTRAINT `FKp0lwsnw4oh7i9upxc9dwkpi7` FOREIGN KEY (`user_seq`) REFERENCES `user` (`user_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prefer_exercise`
--

LOCK TABLES `prefer_exercise` WRITE;
/*!40000 ALTER TABLE `prefer_exercise` DISABLE KEYS */;
INSERT INTO `prefer_exercise` VALUES (1,1,6),(6,3,7),(16,1,17),(22,5,23),(25,8,26),(46,4,52);
/*!40000 ALTER TABLE `prefer_exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_seq` int NOT NULL AUTO_INCREMENT,
  `resting_bpm` int DEFAULT NULL,
  `active_bpm` int DEFAULT NULL,
  `age` int NOT NULL,
  `body_fat_percentage` int DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `height` int NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `weight` int NOT NULL,
  `tension` int DEFAULT NULL,
  PRIMARY KEY (`user_seq`),
  UNIQUE KEY `UK_ob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (6,65,115,26,17,'dudgjs3043@daum.net',180,'숩인짱','1234',100,2),(7,80,130,26,23,'gjs0003@naver.com',170,'곰탱몽','1234',68,2),(17,70,100,26,23,'dudgjs3043@daum.netttt',173,'vnglglgl','123123',66,1),(23,73,99,28,25,'engus5860@naver.com',185,'dugal','1234',86,1),(26,84,110,24,30,'engus123@naver.com',180,'dubdub','123',100,3),(52,80,120,23,20,'test@naver.com',166,'tester','1111',60,1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'B306'
--

--
-- Dumping routines for database 'B306'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-05 17:08:47
