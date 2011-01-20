-- MySQL dump 10.13  Distrib 5.5.8, for Win64 (x86)
--
-- Host: localhost    Database: ttms
-- ------------------------------------------------------
-- Server version	5.5.8

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `court`
--

DROP TABLE IF EXISTS `court`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `court` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `court`
--

LOCK TABLES `court` WRITE;
/*!40000 ALTER TABLE `court` DISABLE KEYS */;
INSERT INTO `court` VALUES (3,'Court no:1'),(2,'Court no:2'),(4,'Court no:3'),(5,'Court no:4'),(6,'Court no:5'),(7,'Court no:6'),(8,'Court no:7'),(9,'Court no:8'),(10,'Court no:9'),(11,'Court no:10');
/*!40000 ALTER TABLE `court` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `draw`
--

DROP TABLE IF EXISTS `draw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `draw` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `awayDraw_id` bigint(20) DEFAULT NULL,
  `awayTeam_id` bigint(20) DEFAULT NULL,
  `homeDraw_id` bigint(20) DEFAULT NULL,
  `homeTeam_id` bigint(20) DEFAULT NULL,
  `winnerTeam_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `draw`
--

LOCK TABLES `draw` WRITE;
/*!40000 ALTER TABLE `draw` DISABLE KEYS */;
INSERT INTO `draw` VALUES (1,NULL,NULL,NULL,NULL,NULL),(2,NULL,3,NULL,2,NULL),(3,NULL,NULL,2,NULL,NULL),(4,NULL,NULL,NULL,NULL,NULL),(5,NULL,NULL,NULL,NULL,NULL),(6,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,NULL,NULL,NULL),(8,NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,NULL,NULL),(10,NULL,35,NULL,22,NULL),(11,NULL,40,NULL,24,NULL),(12,NULL,43,NULL,38,NULL),(13,NULL,27,NULL,34,NULL),(14,NULL,6,NULL,13,NULL),(15,NULL,NULL,NULL,47,NULL),(16,11,NULL,10,NULL,NULL),(17,13,NULL,12,NULL,NULL),(18,15,NULL,14,NULL,NULL),(19,17,NULL,16,NULL,NULL),(20,NULL,NULL,18,NULL,NULL),(21,20,NULL,19,NULL,NULL),(22,NULL,13,NULL,34,NULL),(23,NULL,40,NULL,35,NULL),(24,NULL,6,NULL,22,NULL),(25,NULL,27,NULL,43,NULL),(26,NULL,47,NULL,38,NULL),(27,NULL,NULL,NULL,24,NULL),(28,23,NULL,22,NULL,NULL),(29,25,NULL,24,NULL,NULL),(30,27,NULL,26,NULL,NULL),(31,29,NULL,28,NULL,NULL),(32,NULL,NULL,30,NULL,NULL),(33,32,NULL,31,NULL,NULL),(34,NULL,NULL,NULL,NULL,NULL),(35,NULL,26,NULL,9,NULL),(36,NULL,21,NULL,29,NULL),(37,NULL,11,NULL,20,NULL),(38,NULL,32,NULL,5,NULL),(39,NULL,51,NULL,33,NULL),(40,36,NULL,35,NULL,NULL),(41,38,NULL,37,NULL,NULL),(42,NULL,NULL,39,NULL,NULL),(43,41,NULL,40,NULL,NULL),(44,NULL,NULL,42,NULL,NULL),(45,44,NULL,43,NULL,NULL),(46,NULL,23,NULL,37,NULL),(47,NULL,42,NULL,31,NULL),(48,NULL,25,NULL,46,NULL),(49,NULL,41,NULL,39,NULL),(50,47,NULL,46,NULL,NULL),(51,49,NULL,48,NULL,NULL),(52,51,NULL,50,NULL,NULL),(53,NULL,48,NULL,17,NULL),(54,NULL,28,NULL,49,NULL),(55,NULL,7,NULL,18,NULL),(56,54,NULL,53,NULL,NULL),(57,NULL,NULL,55,NULL,NULL),(58,57,NULL,56,NULL,NULL),(59,NULL,30,NULL,16,NULL),(60,NULL,8,NULL,50,NULL),(61,NULL,12,NULL,4,NULL),(62,NULL,NULL,NULL,19,NULL),(63,60,NULL,59,NULL,NULL),(64,62,NULL,61,NULL,NULL),(65,64,NULL,63,NULL,NULL);
/*!40000 ALTER TABLE `draw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game`
--

DROP TABLE IF EXISTS `game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `awayTeamScore` int(11) NOT NULL,
  `homeTeamScore` int(11) NOT NULL,
  `setid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK21C0128E9AA4FA` (`setid`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game`
--

LOCK TABLES `game` WRITE;
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` VALUES (34,2,2,NULL),(33,3,3,NULL),(32,31,31,8),(5,2,2,8),(31,1,1,8),(38,1,1,15),(37,2,2,15),(36,3,3,15),(35,1,1,NULL),(39,2,1,16),(42,0,0,NULL),(41,0,1,16),(43,0,0,NULL),(44,0,0,16);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matchstatistics`
--

DROP TABLE IF EXISTS `matchstatistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matchstatistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `aces` int(11) NOT NULL,
  `doubleFauls` int(11) NOT NULL,
  `firstSPtsWon` int(11) NOT NULL,
  `returnPtsWon` int(11) NOT NULL,
  `secondSPtsWon` int(11) NOT NULL,
  `totalPtsWon` int(11) NOT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK97316CA824EDBC5F` (`team_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matchstatistics`
--

LOCK TABLES `matchstatistics` WRITE;
/*!40000 ALTER TABLE `matchstatistics` DISABLE KEYS */;
INSERT INTO `matchstatistics` VALUES (1,0,0,0,0,0,0,NULL),(2,0,0,0,0,0,0,NULL),(3,0,0,0,0,0,0,NULL),(5,32,0,0,0,0,0,NULL);
/*!40000 ALTER TABLE `matchstatistics` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membershiprequest`
--

DROP TABLE IF EXISTS `membershiprequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `membershiprequest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membershiprequest`
--

LOCK TABLES `membershiprequest` WRITE;
/*!40000 ALTER TABLE `membershiprequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `membershiprequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mset`
--

DROP TABLE IF EXISTS `mset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `awayTeamScore` int(11) NOT NULL,
  `homeTeamScore` int(11) NOT NULL,
  `matchid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3348D5DF39840` (`matchid`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mset`
--

LOCK TABLES `mset` WRITE;
/*!40000 ALTER TABLE `mset` DISABLE KEYS */;
INSERT INTO `mset` VALUES (1,0,0,NULL),(5,0,0,NULL),(3,0,1,NULL),(15,1,2,NULL),(7,1,1,NULL),(8,1,2,2),(16,1,2,2);
/*!40000 ALTER TABLE `mset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registereduser`
--

DROP TABLE IF EXISTS `registereduser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registereduser` (
  `usertype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registereduser`
--

LOCK TABLES `registereduser` WRITE;
/*!40000 ALTER TABLE `registereduser` DISABLE KEYS */;
INSERT INTO `registereduser` VALUES ('umpire',1,'Umpire','u','ump','u'),('registereduser',2,'Registered','ru','User','ru'),('manager',3,'Manager','m','man','m'),('player',4,'Player','p','pl','p'),('referee',5,'Referee','r','rf','r'),('player',16,'Çağla','cagla','Büyükakçay','cagla'),('manager',7,'Fırat','fir','Şahindal','fir'),('player',17,'Irina','irina','Begu','irina'),('player',10,'John','john','Andrews','john'),('player',11,'Boris','boris','Becker','boris'),('player',12,'Mike','mike','Bryan','mike'),('player',13,'Roy','roy','Emerson','roy'),('player',14,'Phil','ph','Dent','phil'),('player',15,'Albert','alb','Costa','alb'),('player',18,'Alice','ali','Canepa','ali'),('player',19,'Maret','mar','Ani','mar'),('manager',20,'Pat','pat','Du Pre','pat'),('manager',21,'Thierry','champ','Champion','champ'),('referee',22,'Tim','tim','Gullikson','tim'),('referee',23,'Richard','rich','Gasquet','rich'),('referee',24,'Neale','nea','Fraser','nea'),('referee',25,'Robert','rob','Falkenburg','rob'),('umpire',26,'Jan','jan','Gunnarsson','jan'),('umpire',27,'Lew','hoad','Hoad','hoad'),('umpire',28,'Nicolas','nico','Kiefer','nico'),('umpire',29,'Rene','rene','Lacoste','rene'),('umpire',30,'Johan','joh','Kriek','joh'),('registereduser',31,'Kristie','kris','Boogert','kris'),('registereduser',32,'Angela','angel','Buxton','angel'),('registereduser',33,'Belinda','bel','Cortwell','bel'),('player',34,'Melinda','mel','Czink','mel'),('player',35,'Lottie','lot','Dod','lot'),('player',36,'Sandra','san','Dopfer','san'),('player',37,'Jo','jo','Durie','jo'),('player',38,'Robyn','eb','Ebbern','eb'),('player',39,'Chris','chris','Evert','chris'),('player',40,'Patty','fen','Fendick','fen'),('player',41,'Donna','don','Faber','don'),('player',42,'Sara','sara','Errani','sara'),('player',43,'Eva','eva','Dyrberg','eva'),('player',44,'Amy','amy','Frazier','amy'),('player',45,'Arthur','art','Ashe','art'),('player',46,'Paul','paul','Annacone','paul'),('player',47,'Victor','vic','Amaya','vic'),('player',48,'Mario','mario','Ancic','mario'),('player',49,'Juan','juan','Aguileri','juan'),('player',50,'Andre','and','Agassi','and'),('player',51,'Luis','luis','Ayala','luis'),('player',52,'Jeremy','jerr','Bates','jerr'),('player',53,'James','jam','Blake','jam'),('player',54,'Galo','gal','Blanco','gal'),('player',55,'Tom','tom','Brown','tom'),('player',56,'Bob','bob','Bryan','bob'),('player',57,'Phil','ph','Dent','ph'),('player',58,'Mark','mark','Cox','mark'),('player',59,'Ross','ross','Case','ross'),('player',60,'Roger','rog','Federer','rog'),('player',61,'Ken','ken','Flach','ken'),('player',62,'Rafael','raf','Nadal','raf');
/*!40000 ALTER TABLE `registereduser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registereduser_team`
--

DROP TABLE IF EXISTS `registereduser_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registereduser_team` (
  `RegisteredUser_id` bigint(20) NOT NULL,
  `trackList_id` bigint(20) NOT NULL,
  KEY `FK5AB625EF650E583F` (`RegisteredUser_id`),
  KEY `FK5AB625EFA14F2ED3` (`trackList_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registereduser_team`
--

LOCK TABLES `registereduser_team` WRITE;
/*!40000 ALTER TABLE `registereduser_team` DISABLE KEYS */;
INSERT INTO `registereduser_team` VALUES (2,1),(3,9),(3,4),(3,12),(3,33),(5,26),(5,16);
/*!40000 ALTER TABLE `registereduser_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team` (
  `teamtype` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tournament_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK27B67D1A0F591F` (`tournament_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES ('singles',12,5),('singles',19,5),('doubles',18,4),('singles',4,5),('doubles',5,6),('singles',6,2),('doubles',7,4),('singles',8,5),('doubles',9,6),('doubles',11,6),('singles',13,2),('doubles',17,4),('singles',16,5),('doubles',20,6),('doubles',21,6),('singles',22,2),('doubles',23,3),('singles',24,2),('doubles',25,3),('doubles',26,6),('singles',27,2),('doubles',28,4),('doubles',29,6),('singles',30,5),('doubles',31,3),('doubles',32,6),('doubles',33,6),('singles',34,2),('singles',35,2),('doubles',48,4),('doubles',37,3),('singles',38,2),('doubles',39,3),('singles',40,2),('doubles',41,3),('doubles',42,3),('singles',43,2),('doubles',49,4),('doubles',46,3),('singles',47,2),('singles',50,5),('doubles',51,6);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_registereduser`
--

DROP TABLE IF EXISTS `team_registereduser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `team_registereduser` (
  `teams_id` bigint(20) NOT NULL,
  `players_id` bigint(20) NOT NULL,
  KEY `FKC2BE878F2177FC2E` (`players_id`),
  KEY `FKC2BE878F17510A66` (`teams_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_registereduser`
--

LOCK TABLES `team_registereduser` WRITE;
/*!40000 ALTER TABLE `team_registereduser` DISABLE KEYS */;
INSERT INTO `team_registereduser` VALUES (1,4),(18,61),(3,4),(4,10),(5,10),(5,17),(6,17),(7,11),(7,10),(8,11),(9,19),(9,18),(10,19),(11,13),(12,15),(13,18),(16,62),(17,39),(17,62),(18,12),(19,61),(20,34),(20,15),(21,61),(21,16),(22,34),(23,34),(23,48),(24,35),(25,35),(25,43),(26,59),(26,40),(27,36),(28,59),(28,13),(29,36),(29,62),(30,59),(31,37),(31,19),(32,37),(32,56),(33,38),(33,12),(34,38),(35,16),(36,38),(36,51),(37,16),(37,18),(38,39),(39,39),(39,46),(40,40),(41,40),(41,17),(42,36),(42,44),(43,41),(44,41),(44,50),(45,42),(46,42),(46,51),(47,42),(48,15),(48,14),(49,53),(49,52),(50,53),(51,53),(51,43);
/*!40000 ALTER TABLE `team_registereduser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmatch`
--

DROP TABLE IF EXISTS `tmatch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmatch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `awayTeamScore` int(11) NOT NULL,
  `homeTeamScore` int(11) NOT NULL,
  `mDate` date NOT NULL,
  `mTime` varchar(255) NOT NULL,
  `report` varchar(255) DEFAULT NULL,
  `court_id` bigint(20) DEFAULT NULL,
  `statistics_id` bigint(20) DEFAULT NULL,
  `tournament_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCC2001D11A0F591F` (`tournament_id`),
  KEY `FKCC2001D1C024DD9A` (`statistics_id`),
  KEY `FKCC2001D1C55A36B5` (`court_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmatch`
--

LOCK TABLES `tmatch` WRITE;
/*!40000 ALTER TABLE `tmatch` DISABLE KEYS */;
INSERT INTO `tmatch` VALUES (2,12,2,'2011-02-01','12:12','çok şükür2',8,5,6);
/*!40000 ALTER TABLE `tmatch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmatch_referee`
--

DROP TABLE IF EXISTS `tmatch_referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmatch_referee` (
  `matches_id` bigint(20) NOT NULL,
  `referees_id` bigint(20) NOT NULL,
  KEY `FKC9AA20B247EB44C2` (`referees_id`),
  KEY `FKC9AA20B24B568647` (`matches_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmatch_referee`
--

LOCK TABLES `tmatch_referee` WRITE;
/*!40000 ALTER TABLE `tmatch_referee` DISABLE KEYS */;
INSERT INTO `tmatch_referee` VALUES (2,23),(2,22);
/*!40000 ALTER TABLE `tmatch_referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmatch_team`
--

DROP TABLE IF EXISTS `tmatch_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmatch_team` (
  `matches_id` bigint(20) NOT NULL,
  `teams_id` bigint(20) NOT NULL,
  KEY `FKE6BFE82B4B568647` (`matches_id`),
  KEY `FKE6BFE82B17510A66` (`teams_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmatch_team`
--

LOCK TABLES `tmatch_team` WRITE;
/*!40000 ALTER TABLE `tmatch_team` DISABLE KEYS */;
INSERT INTO `tmatch_team` VALUES (2,33),(2,51);
/*!40000 ALTER TABLE `tmatch_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tmatch_umpire`
--

DROP TABLE IF EXISTS `tmatch_umpire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tmatch_umpire` (
  `matches_id` bigint(20) NOT NULL,
  `umpires_id` bigint(20) NOT NULL,
  KEY `FK6F2E02B24B568647` (`matches_id`),
  KEY `FK6F2E02B2610E45F4` (`umpires_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tmatch_umpire`
--

LOCK TABLES `tmatch_umpire` WRITE;
/*!40000 ALTER TABLE `tmatch_umpire` DISABLE KEYS */;
INSERT INTO `tmatch_umpire` VALUES (2,28),(2,27);
/*!40000 ALTER TABLE `tmatch_umpire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament`
--

DROP TABLE IF EXISTS `tournament`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `endDate` date NOT NULL,
  `name` varchar(255) NOT NULL,
  `prize` double NOT NULL,
  `report` varchar(255) DEFAULT NULL,
  `startDate` date NOT NULL,
  `type` varchar(255) NOT NULL,
  `draw_id` bigint(20) DEFAULT NULL,
  `manager_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3B743609ECB67EFF` (`draw_id`),
  KEY `FK3B743609CF2F20B5` (`manager_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament`
--

LOCK TABLES `tournament` WRITE;
/*!40000 ALTER TABLE `tournament` DISABLE KEYS */;
INSERT INTO `tournament` VALUES (7,'2011-02-01','Friendly Match',1,'','2010-12-31','Men\'s Singles',9,3),(2,'2011-02-27','Women\'s Tournament',10000,'','2011-02-08','Women\'s Singles',33,3),(3,'2011-03-11','Women\'s Doubles Tournament',20000,'','2011-02-10','Women\'s Doubles',52,3),(4,'2011-03-02','Men\'s Doubles Tournament',20000,'','2011-02-03','Men\'s Doubles',58,3),(5,'2011-05-02','Men\'s Tournament',10000,'','2011-04-03','Men\'s Singles',65,3),(6,'2011-06-11','Mixed Doubles Tournament',20000,'','2011-04-24','Mixed Doubles',45,3),(8,'2059-12-31','Fırat\'s Tournament',9999999,'Saygılar.','1985-03-20','Women\'s Doubles',34,7);
/*!40000 ALTER TABLE `tournament` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament_court`
--

DROP TABLE IF EXISTS `tournament_court`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament_court` (
  `tournaments_id` bigint(20) NOT NULL,
  `courts_id` bigint(20) NOT NULL,
  KEY `FKE70C8FB532C1D2D8` (`courts_id`),
  KEY `FKE70C8FB527E7EBBE` (`tournaments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament_court`
--

LOCK TABLES `tournament_court` WRITE;
/*!40000 ALTER TABLE `tournament_court` DISABLE KEYS */;
INSERT INTO `tournament_court` VALUES (2,8),(3,3),(4,6),(4,2),(4,5),(4,8),(4,10),(3,5),(3,2),(3,9),(3,10),(2,9),(2,3),(2,10),(5,7),(5,10),(5,9),(5,6),(6,2),(6,5),(6,7),(6,9),(6,10),(6,8),(7,6),(7,2),(8,4),(8,3);
/*!40000 ALTER TABLE `tournament_court` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament_referee`
--

DROP TABLE IF EXISTS `tournament_referee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament_referee` (
  `tournaments_id` bigint(20) NOT NULL,
  `referees_id` bigint(20) NOT NULL,
  KEY `FK8CBEE0CA47EB44C2` (`referees_id`),
  KEY `FK8CBEE0CA27E7EBBE` (`tournaments_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament_referee`
--

LOCK TABLES `tournament_referee` WRITE;
/*!40000 ALTER TABLE `tournament_referee` DISABLE KEYS */;
INSERT INTO `tournament_referee` VALUES (2,22),(2,24),(3,24),(3,23),(3,25),(4,22),(4,23),(4,25),(5,25),(5,22),(5,24),(6,25),(6,22),(6,23),(7,23),(7,22),(8,5);
/*!40000 ALTER TABLE `tournament_referee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournament_umpire`
--

DROP TABLE IF EXISTS `tournament_umpire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournament_umpire` (
  `tournaments_id` bigint(20) NOT NULL,
  `umpires_id` bigint(20) NOT NULL,
  KEY `FK64F4DF9A27E7EBBE` (`tournaments_id`),
  KEY `FK64F4DF9A610E45F4` (`umpires_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournament_umpire`
--

LOCK TABLES `tournament_umpire` WRITE;
/*!40000 ALTER TABLE `tournament_umpire` DISABLE KEYS */;
INSERT INTO `tournament_umpire` VALUES (2,30),(2,29),(2,28),(3,26),(3,1),(3,27),(4,28),(4,30),(4,27),(4,1),(5,29),(5,27),(5,1),(5,30),(6,28),(6,29),(6,27),(7,28),(7,27),(8,29),(8,27);
/*!40000 ALTER TABLE `tournament_umpire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tournamentjoinrequest`
--

DROP TABLE IF EXISTS `tournamentjoinrequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tournamentjoinrequest` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `manager_id` bigint(20) DEFAULT NULL,
  `team_id` bigint(20) DEFAULT NULL,
  `tournament_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9F7AEC3C1A0F591F` (`tournament_id`),
  KEY `FK9F7AEC3C24EDBC5F` (`team_id`),
  KEY `FK9F7AEC3CCF2F20B5` (`manager_id`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tournamentjoinrequest`
--

LOCK TABLES `tournamentjoinrequest` WRITE;
/*!40000 ALTER TABLE `tournamentjoinrequest` DISABLE KEYS */;
/*!40000 ALTER TABLE `tournamentjoinrequest` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-01-20 17:29:34
