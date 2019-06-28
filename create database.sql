CREATE DATABASE  IF NOT EXISTS `projectmanager` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `projectmanager`;
-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: projectmanager
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `budget`
--

DROP TABLE IF EXISTS `budget`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `budget` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `excute` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `filler` char(8) COLLATE utf8_bin DEFAULT NULL,
  `checkdecision` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `checkadvice` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `content` text COLLATE utf8_bin,
  `name` char(20) COLLATE utf8_bin DEFAULT NULL,
  `checker` char(8) COLLATE utf8_bin DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `event` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `content` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `recorder` char(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plan` (
  `id` int(4) NOT NULL,
  `name` char(24) DEFAULT NULL,
  `filler` char(8) DEFAULT NULL,
  `content` text,
  `type` int(4) DEFAULT NULL,
  `checker` char(8) DEFAULT NULL,
  `excute` varchar(90) DEFAULT NULL,
  `checkadvice` varchar(45) DEFAULT NULL,
  `checkdecision` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `project` (
  `id` int(11) NOT NULL,
  `content` text COLLATE utf8_bin,
  `filler` char(8) COLLATE utf8_bin DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `name` char(20) COLLATE utf8_bin DEFAULT NULL,
  `checker` char(8) COLLATE utf8_bin DEFAULT NULL,
  `chance` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `excute` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `iner` char(8) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recorde`
--

DROP TABLE IF EXISTS `recorde`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `recorde` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `number` char(8) COLLATE utf8_bin NOT NULL,
  `name` char(8) COLLATE utf8_bin NOT NULL,
  `pwd` char(8) COLLATE utf8_bin NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-28 15:19:38
