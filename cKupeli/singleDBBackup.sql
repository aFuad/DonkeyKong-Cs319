-- MySQL dump 10.13  Distrib 5.7.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: donkeykong
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `matrix`
--

DROP TABLE IF EXISTS `matrix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matrix` (
  `matrixRow` int(11) NOT NULL,
  `matrixCol` int(11) NOT NULL,
  `matrixData` varchar(20) NOT NULL,
  UNIQUE KEY `matrixRowCol` (`matrixRow`,`matrixCol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matrix`
--

LOCK TABLES `matrix` WRITE;
/*!40000 ALTER TABLE `matrix` DISABLE KEYS */;
INSERT INTO `matrix` VALUES (0,0,'Space'),(0,1,'Space'),(0,2,'Space'),(0,3,'Space'),(0,4,'Space'),(1,0,'Space'),(1,1,'Space'),(1,2,'Player'),(1,3,'BarrelBlue'),(1,4,'BarrelBlue'),(2,0,'BarrelRed'),(2,1,'BarrelRed'),(2,2,'Space'),(2,3,'Space'),(2,4,'Space'),(3,0,'Monkey'),(3,1,'Platform'),(3,2,'Platform'),(3,3,'Oil'),(3,4,'FireElemental'),(4,0,'FireElemental'),(4,1,'Ladder'),(4,2,'Ladder'),(4,3,'Space'),(4,4,'Space');
/*!40000 ALTER TABLE `matrix` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matrixlength`
--

DROP TABLE IF EXISTS `matrixlength`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matrixlength` (
  `rowLength` int(11) NOT NULL DEFAULT '0',
  `colLength` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matrixlength`
--

LOCK TABLES `matrixlength` WRITE;
/*!40000 ALTER TABLE `matrixlength` DISABLE KEYS */;
INSERT INTO `matrixlength` VALUES (5,5);
/*!40000 ALTER TABLE `matrixlength` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `highestScore` int(11) NOT NULL DEFAULT '0',
  `scoreID` int(11) NOT NULL,
  PRIMARY KEY (`scoreID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (6121,1);
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-17  1:10:20
