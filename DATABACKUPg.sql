-- MySQL dump 10.13  Distrib 5.7.13, for Win64 (x86_64)
--
-- Host: localhost    Database: inua
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `bank` varchar(100) NOT NULL DEFAULT '0',
  `cashinoffice` varchar(100) NOT NULL DEFAULT '0',
  `mpesa` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'13000.0','13500.0','144366.0');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounttypes`
--

DROP TABLE IF EXISTS `accounttypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounttypes` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `name` varchar(70) NOT NULL,
  `sname` varchar(70) NOT NULL,
  `rate` varchar(100) NOT NULL,
  `appfee` varchar(100) NOT NULL,
  `penalt` varchar(100) NOT NULL,
  `balance` varchar(100) NOT NULL,
  `ratePer` varchar(100) NOT NULL DEFAULT '0',
  `rateTime` varchar(100) NOT NULL DEFAULT '0',
  `applicable` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounttypes`
--

LOCK TABLES `accounttypes` WRITE;
/*!40000 ALTER TABLE `accounttypes` DISABLE KEYS */;
INSERT INTO `accounttypes` VALUES (1,'1','INUA BUSINESS LOAN','IBLl','0.2','0.04','1.4285714285714286','3','0','0','NA'),(2,'2','ASSET FINANCE','AF','0.2','0.04','0.1','3','0','0','NA'),(3,'3','KUAMUA JAHAZI LOAN','KJL','0.15','0.02','1.0','10','Weekly','4','A'),(4,'5','BUSINESS SUPER','BS','0.2','0.04','0.1','0','0','0','NA'),(5,'6','SCHOOL FEES LOAN','SFL','0.2','0.25','0.1','10','Weekly','12','A'),(6,'7','INUA BUSINESSx LOAN','IBLx','0.2','0.04','0.09999999999999999','3','Monthly','1','A'),(7,'8','INUA BUSINESSx LOANM','IBLxM','0.5','0.08','1.0','4','Weekly','1','A');
/*!40000 ALTER TABLE `accounttypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applications`
--

DROP TABLE IF EXISTS `applications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applications` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `type` varchar(70) NOT NULL,
  `amount` varchar(70) DEFAULT NULL,
  `status` varchar(70) DEFAULT NULL,
  `guranters` varchar(70) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applications`
--

LOCK TABLES `applications` WRITE;
/*!40000 ALTER TABLE `applications` DISABLE KEYS */;
/*!40000 ALTER TABLE `applications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guranters`
--

DROP TABLE IF EXISTS `guranters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guranters` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `name` varchar(70) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `gurantee` varchar(100) NOT NULL,
  PRIMARY KEY (`autoid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guranters`
--

LOCK TABLES `guranters` WRITE;
/*!40000 ALTER TABLE `guranters` DISABLE KEYS */;
/*!40000 ALTER TABLE `guranters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iloans`
--

DROP TABLE IF EXISTS `iloans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iloans` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `customerid` varchar(100) NOT NULL,
  `loanid` varchar(100) NOT NULL,
  `loantype` varchar(70) NOT NULL,
  `applicablestatus` varchar(10) NOT NULL,
  `applicationfee` varchar(70) DEFAULT NULL,
  `periodtype` varchar(70) DEFAULT NULL,
  `frequencyperperiod` varchar(70) DEFAULT NULL,
  `datesupposed` datetime DEFAULT NULL,
  `installmentamount` varchar(70) DEFAULT NULL,
  `installmentsno` varchar(70) DEFAULT NULL,
  `loanAmount` varchar(70) DEFAULT NULL,
  `loanRequested` varchar(70) DEFAULT NULL,
  `loanbalance` varchar(70) DEFAULT NULL,
  `moreorlesspaid` varchar(100) DEFAULT NULL,
  `todaypay` varchar(1000) DEFAULT NULL,
  `paymentstatus` varchar(100) DEFAULT NULL,
  `defaultstatus` varchar(100) DEFAULT NULL,
  `balancebf` varchar(100) NOT NULL DEFAULT '0',
  `extra` varchar(100) NOT NULL DEFAULT 'null',
  `loangivenOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paidon` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iloans`
--

LOCK TABLES `iloans` WRITE;
/*!40000 ALTER TABLE `iloans` DISABLE KEYS */;
INSERT INTO `iloans` VALUES (1,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','12500.0','Weekly','1','2017-04-18 00:00:00','5000.0','12','60000.0','50000','60000.0','0.0','5000.0','paid','nd','0.0','0.0','2017-04-11 15:44:05','2017-04-11 15:44:05'),(2,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','12500.0','Weekly','1','2017-04-25 00:00:00','5000.0','11','60000.0','50000','55000.0','-4000.0','1000.0','nfp','nd','0.0','0.0','2017-04-11 15:45:08','2017-04-11 15:45:08');
/*!40000 ALTER TABLE `iloans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iloansarchives`
--

DROP TABLE IF EXISTS `iloansarchives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iloansarchives` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `customerid` varchar(100) NOT NULL,
  `loanid` varchar(100) NOT NULL,
  `loantype` varchar(70) NOT NULL,
  `applicablestatus` varchar(10) NOT NULL,
  `applicationfee` varchar(70) DEFAULT NULL,
  `periodtype` varchar(70) DEFAULT NULL,
  `frequencyperperiod` varchar(70) DEFAULT NULL,
  `datesupposed` datetime DEFAULT NULL,
  `installmentamount` varchar(70) DEFAULT NULL,
  `installmentsno` varchar(70) DEFAULT NULL,
  `loanAmount` varchar(70) DEFAULT NULL,
  `loanRequested` varchar(70) DEFAULT NULL,
  `loanbalance` varchar(70) DEFAULT NULL,
  `moreorlesspaid` varchar(100) DEFAULT NULL,
  `todaypay` varchar(1000) DEFAULT NULL,
  `paymentstatus` varchar(100) DEFAULT NULL,
  `defaultstatus` varchar(100) DEFAULT NULL,
  `balancebf` varchar(100) NOT NULL DEFAULT '0',
  `extra` varchar(100) NOT NULL DEFAULT 'null',
  `loangivenOn` varchar(100) DEFAULT NULL,
  `paidon` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iloansarchives`
--

LOCK TABLES `iloansarchives` WRITE;
/*!40000 ALTER TABLE `iloansarchives` DISABLE KEYS */;
INSERT INTO `iloansarchives` VALUES (1,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1750.0','Weekly','1','2017-04-10 00:00:00','700.0','12','8400.0','7000','8400.0','0.0','700.0','paid','nd','0.0','0.0','2017-03-31 21:40:16.0','2017-04-01 18:07:36'),(2,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1750.0','Weekly','1','2017-04-17 00:00:00','700.0','11','8400.0','7000','7700.0','6300.0','7000.0','pm','nd','0.0','0.0','2017-04-01 18:05:53.0','2017-04-01 18:07:36'),(3,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1750.0','Weekly','1','2017-04-24 00:00:00','700.0','10','8400.0','7000','700.0','6300.0','700.0','pm','nd','6300.0','0.0','2017-04-01 18:07:13.0','2017-04-01 18:07:36'),(4,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1750.0','Weekly','1','2017-05-01 00:00:00','700.0','9','8400.0','7000','0.0','0.0','0.0','np','nd','6300.0','0.0','2017-04-01 18:07:33.0','2017-04-01 18:07:36'),(5,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','5000.0','Weekly','1','2017-04-10 00:00:00','2000.0','12','24000.0','20000','24000.0','2000.0','0.0','np','dl','0.0','0.0','2017-04-02 09:16:27.0','2017-04-15 02:49:03'),(6,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','5000.0','Weekly','1','2017-04-22 00:00:00','2000.0','11','24000.0','20000','-1990.0','0.0','0.0','np','nd','23990.0','0.0','2017-04-02 09:16:27.0','2017-04-15 02:49:04'),(7,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1250.0','Weekly','1','2017-04-05 00:00:00','500.0','12','6000.0','5000','6000.0','0.0','500.0','paid','nd','0.0','0.0','2017-03-28 07:51:05.0','2017-04-14 07:59:37'),(8,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1250.0','Weekly','1','2017-04-12 00:00:00','500.0','11','6000.0','5000','5500.0','0.0','0.0','np','dl','0.0','0.0','2017-03-28 07:53:24.0','2017-04-14 07:59:38'),(9,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1250.0','Weekly','1','2017-04-21 00:00:00','500.0','10','6000.0','5000','-499.0','0.0','0.0','np','nd','5499.0','0.0','2017-03-28 07:53:24.0','2017-04-14 07:59:40'),(10,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1250.0','Weekly','1','2017-04-13 00:00:00','1750.0','3','5250.0','5000','5250.0','3642.0','5400','pm','nd','0.0','0.0','2017-04-05 11:33:52.0','2017-04-18 11:36:17'),(11,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1250.0','Weekly','1','2017-04-25 00:00:00','1750.0','2','5250.0','5000','-142.0','0.0','0.0','np','nd','3642.0','0.0','2017-04-05 11:33:52.0','2017-04-18 11:36:17'),(12,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','600.0','Weekly','1','2017-04-11 00:00:00','400','4','2400.0','2800','1600.0','200.0','400.0','pm','nd','200','0.0','2017-02-24 00:00:00.0','2017-04-10 14:51:24'),(13,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','600.0','Weekly','1','2017-04-18 00:00:00','400','3','2400.0','2800','1200.0','1000.0','1200.0','pm','nd','200.0','0.0','2017-04-10 14:45:23.0','2017-04-10 14:51:25'),(14,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','600.0','Weekly','1','2017-04-25 00:00:00','400','2','2400.0','2800','0.0','0.0','0.0','np','nd','1000.0','0.0','2017-04-10 14:51:20.0','2017-04-10 14:51:25'),(15,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1000.0','Weekly','1','2017-04-17 00:00:00','400.0','12','4800.0','4000','4800.0','4400.0','4800.0','pm','nd','0.0','0.0','2017-04-10 16:26:00.0','2017-04-10 16:26:31'),(16,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','1000.0','Weekly','1','2017-04-24 00:00:00','400.0','11','4800.0','4000','0.0','0.0','0.0','np','nd','4400.0','0.0','2017-04-10 16:26:27.0','2017-04-10 16:26:31');
/*!40000 ALTER TABLE `iloansarchives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iloansdefaulters`
--

DROP TABLE IF EXISTS `iloansdefaulters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iloansdefaulters` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `iloanautoid` varchar(100) NOT NULL,
  `customerid` varchar(100) NOT NULL,
  `loanid` varchar(100) NOT NULL,
  `loantype` varchar(70) NOT NULL,
  `applicablestatus` varchar(10) NOT NULL,
  `applicationfee` varchar(70) DEFAULT NULL,
  `periodtype` varchar(70) DEFAULT NULL,
  `frequencyperperiod` varchar(70) DEFAULT NULL,
  `datesupposed` datetime DEFAULT NULL,
  `installmentamount` varchar(70) DEFAULT NULL,
  `installmentsno` varchar(70) DEFAULT NULL,
  `loanAmount` varchar(70) DEFAULT NULL,
  `loanRequested` varchar(70) DEFAULT NULL,
  `loanbalance` varchar(70) DEFAULT NULL,
  `moreorlesspaid` varchar(100) DEFAULT NULL,
  `todaypay` varchar(1000) DEFAULT NULL,
  `paymentstatus` varchar(100) DEFAULT NULL,
  `defaultstatus` varchar(100) DEFAULT NULL,
  `balancebf` varchar(100) NOT NULL DEFAULT '0',
  `extra` varchar(100) NOT NULL DEFAULT 'null',
  `loangivenOn` varchar(70) DEFAULT NULL,
  `paidon` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iloansdefaulters`
--

LOCK TABLES `iloansdefaulters` WRITE;
/*!40000 ALTER TABLE `iloansdefaulters` DISABLE KEYS */;
/*!40000 ALTER TABLE `iloansdefaulters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loans` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `loanid` varchar(100) NOT NULL,
  `loantype` varchar(70) NOT NULL,
  `applicable` varchar(10) NOT NULL,
  `installmentamount` varchar(70) DEFAULT NULL,
  `installmentsno` varchar(70) DEFAULT NULL,
  `periodtype` varchar(70) DEFAULT NULL,
  `frequencyperperiod` varchar(70) DEFAULT NULL,
  `targetdate` datetime DEFAULT NULL,
  `loanAmount` varchar(70) DEFAULT NULL,
  `loanRequested` varchar(70) DEFAULT NULL,
  `loanbalance` varchar(70) DEFAULT NULL,
  `defaultacc` varchar(100) DEFAULT NULL,
  `applicationfee` varchar(70) DEFAULT NULL,
  `todaypay` varchar(1000) DEFAULT NULL,
  `nxp` varchar(100) NOT NULL DEFAULT 'np',
  `tp` varchar(100) NOT NULL DEFAULT 'np',
  `givenOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paidon` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (7,'1','1INUA BUSINESS LOAN','INUA BUSINESS LOAN','NA','360.0','2','Weekly','1','2017-04-06 00:00:00','720.0','600','720.0','null','24.0','0','np','np','2017-03-29 09:13:41','2017-03-29 09:13:41');
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loansarchives`
--

DROP TABLE IF EXISTS `loansarchives`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loansarchives` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `loanid` varchar(100) NOT NULL,
  `loantype` varchar(70) NOT NULL,
  `applicable` varchar(10) NOT NULL,
  `installmentamount` varchar(70) DEFAULT NULL,
  `installmentsno` varchar(70) DEFAULT NULL,
  `periodtype` varchar(70) DEFAULT NULL,
  `frequencyperperiod` varchar(70) DEFAULT NULL,
  `targetdate` datetime DEFAULT NULL,
  `loanAmount` varchar(70) DEFAULT NULL,
  `loanRequested` varchar(70) DEFAULT NULL,
  `loanbalance` varchar(70) DEFAULT NULL,
  `defaultacc` varchar(100) DEFAULT NULL,
  `applicationfee` varchar(70) DEFAULT NULL,
  `todaypay` varchar(1000) DEFAULT NULL,
  `nxp` varchar(100) NOT NULL DEFAULT 'np',
  `givenOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paidon` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loansarchives`
--

LOCK TABLES `loansarchives` WRITE;
/*!40000 ALTER TABLE `loansarchives` DISABLE KEYS */;
INSERT INTO `loansarchives` VALUES (1,'1','1INUA BUSINESS LOAN','INUA BUSINESS LOAN','NA','15000.0','4','Monthly','1','2017-03-20 00:00:00','60000.0','50000','60000.0','INUA BUSINESS LOAN','2000.0','0','np','2017-02-19 09:28:41','2017-02-19 09:28:41'),(2,'1','1INUA BUSINESS LOAN','INUA BUSINESS LOAN','NA','15000.0','3','Monthly','1','2017-04-20 00:00:00','60000.0','50000','47000.0','INUA BUSINESS LOAN','null','13000','np','2017-02-19 09:28:41','2017-02-19 09:30:22'),(3,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','12','Weekly','1','2017-02-28 00:00:00','48000.0','40000','48000.0','SCHOOL FEES LOAN','10000.0','0','np','2017-02-21 11:47:43','2017-02-21 11:47:43'),(4,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','11','Weekly','1','2017-03-07 00:00:00','48000.0','40000','44000.0','SCHOOL FEES LOAN','null','4000','np','2017-02-21 11:47:43','2017-02-21 11:50:59'),(5,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','10','Weekly','1','2017-03-14 00:00:00','48000.0','40000','0.0','SCHOOL FEES LOAN','null','44000','np','2017-02-21 11:47:43','2017-02-21 12:11:55'),(6,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','12','Weekly','1','2017-03-02 00:00:00','48000.0','40000','48000.0','SCHOOL FEES LOAN','10000.0','0','np','2017-02-22 21:17:46','2017-02-22 21:17:46'),(7,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','11','Weekly','1','2017-03-09 00:00:00','48000.0','40000','43000.0','SCHOOL FEES LOAN','null','5000','np','2017-02-22 21:17:46','2017-02-22 22:31:02'),(8,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','10','Weekly','1','2017-03-16 00:00:00','48000.0','40000','40000.0','SCHOOL FEES LOAN','null','3000','np','2017-02-22 21:17:46','2017-02-22 22:44:23'),(9,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','9','Weekly','1','2017-03-23 00:00:00','48000.0','40000','39000.0','SCHOOL FEES LOAN','null','1000','np','2017-02-22 21:17:46','2017-02-22 22:47:31'),(10,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','8','Weekly','1','2017-03-30 00:00:00','48000.0','40000','35000.0','SCHOOL FEES LOAN','null','4000','np','2017-02-22 21:17:46','2017-02-22 22:48:53'),(11,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','7','Weekly','1','2017-04-06 00:00:00','48000.0','40000','30000.0','SCHOOL FEES LOAN','null','5000','np','2017-02-22 21:17:46','2017-02-22 22:50:18'),(12,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','6','Weekly','1','2017-04-13 00:00:00','48000.0','40000','25000.0','SCHOOL FEES LOAN','null','5000','np','2017-02-22 21:17:46','2017-02-22 22:51:17'),(13,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','5','Weekly','1','2017-04-20 00:00:00','48000.0','40000','23000.0','SCHOOL FEES LOAN','null','2000','np','2017-02-22 21:17:46','2017-02-23 02:37:56'),(14,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','4','Weekly','1','2017-04-27 00:00:00','48000.0','40000','18600.0','SCHOOL FEES LOAN','null','4400','np','2017-02-22 21:17:46','2017-02-23 02:40:22'),(15,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','3','Weekly','1','2017-05-04 00:00:00','48000.0','40000','15000.0','SCHOOL FEES LOAN','null','3600','np','2017-02-22 21:17:46','2017-02-23 02:41:05'),(16,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','2','Weekly','1','2017-05-11 00:00:00','48000.0','40000','11000.0','SCHOOL FEES LOAN','null','4000','np','2017-02-22 21:17:46','2017-02-23 02:42:14'),(17,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','1','Weekly','1','2017-05-18 00:00:00','48000.0','40000','3000.0','SCHOOL FEES LOAN','null','8000','np','2017-02-22 21:17:46','2017-02-23 02:43:10'),(18,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','0','Weekly','1','2017-05-25 00:00:00','48000.0','40000','-4000.0','SCHOOL FEES LOAN','null','3000','np','2017-02-22 21:17:46','2017-02-23 02:56:02'),(19,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','12','Weekly','1','2017-03-07 00:00:00','48000.0','40000','48000.0','SCHOOL FEES LOAN','10000.0','0','paid','2017-02-28 14:52:23','2017-02-28 14:52:23'),(20,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','11','Weekly','1','2017-03-14 00:00:00','48000.0','40000','44000.0','SCHOOL FEES LOAN','null','4000.0','pm','2017-02-28 14:52:23','2017-02-28 16:55:42'),(21,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','10','Weekly','1','2017-03-21 00:00:00','48000.0','40000','40000.0','SCHOOL FEES LOAN','null','5200.0','paid','2017-02-28 14:52:23','2017-03-17 17:37:22'),(22,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','9','Weekly','1','2017-03-28 00:00:00','48000.0','40000','36000.0','SCHOOL FEES LOAN','null','3200','pm','2017-02-28 14:52:23','2017-03-17 17:38:47'),(23,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','8','Weekly','1','2017-04-04 00:00:00','48000.0','40000','30800.0','SCHOOL FEES LOAN','null','5200','pm','2017-02-28 14:52:23','2017-02-28 17:41:07'),(24,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','7','Weekly','1','2017-04-11 00:00:00','48000.0','40000','25800.0','SCHOOL FEES LOAN','null','3800','paid','2017-02-28 14:52:23','2017-02-28 18:19:47'),(25,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','6','Weekly','1','2017-04-18 00:00:00','48000.0','40000','21800.0','SCHOOL FEES LOAN','null','3000','pm','2017-02-28 14:52:23','2017-02-28 18:20:48'),(26,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','5','Weekly','1','2017-04-25 00:00:00','48000.0','40000','17800.0','SCHOOL FEES LOAN','null','5200.0','paid','2017-02-28 14:52:23','2017-04-21 18:31:25'),(27,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','4','Weekly','1','2017-05-02 00:00:00','48000.0','40000','13800.0','SCHOOL FEES LOAN','null','3200','pm','2017-02-28 14:52:23','2017-03-02 09:36:00'),(28,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','3','Weekly','1','2017-05-09 00:00:00','48000.0','40000','3800.0','SCHOOL FEES LOAN','null','10000','pm','2017-02-28 14:52:23','2017-03-02 11:11:01'),(29,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','2','Weekly','1','2017-05-16 00:00:00','48000.0','40000','3800.0','SCHOOL FEES LOAN','null','2000.0','pm','2017-02-28 14:52:23','2017-05-12 12:09:39'),(30,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','1','Weekly','1','2017-05-23 00:00:00','48000.0','40000','1800.0','SCHOOL FEES LOAN','null','2600.0','paid','2017-02-28 14:52:23','2017-05-19 12:16:43'),(31,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','0','Weekly','1','2017-05-30 00:00:00','48000.0','40000','1800.0','SCHOOL FEES LOAN','null','4000.0','nfp','2017-02-28 14:52:23','2017-05-26 14:17:18'),(32,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','4000.0','-1','Weekly','1','2017-06-06 00:00:00','48000.0','40000','0.0','SCHOOL FEES LOAN','null','1800','np','2017-02-28 14:52:23','2017-03-11 23:55:34'),(33,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','pm','2017-04-21 11:19:02','2017-04-21 11:19:02'),(34,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','2100','np','2017-04-21 11:19:02','2017-04-21 11:20:07'),(35,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','paid','2017-03-13 11:27:57','2017-03-13 11:27:57'),(36,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','1400.0','SCHOOL FEES LOAN','null','700','paid','2017-03-13 11:27:57','2017-03-13 11:28:26'),(37,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','1','Weekly','1','2017-04-04 00:00:00','2100.0','2000','700.0','SCHOOL FEES LOAN','null','700','paid','2017-03-13 11:27:57','2017-03-13 11:28:57'),(38,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','0','Weekly','1','2017-04-11 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','700','np','2017-03-13 11:27:57','2017-03-13 11:29:30'),(39,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','pm','2017-03-13 12:07:18','2017-03-13 12:07:18'),(40,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','2100','np','2017-03-13 12:07:18','2017-03-13 12:07:48'),(41,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','pm','2017-03-13 12:41:19','2017-03-13 12:41:19'),(42,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','2100','np','2017-03-13 12:41:19','2017-03-13 12:41:41'),(43,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','pm','2017-03-13 12:56:26','2017-03-13 12:56:26'),(44,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','2100','np','2017-03-13 12:56:26','2017-03-13 12:56:59'),(45,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','pm','2017-03-13 12:56:26','2017-03-13 12:56:26'),(46,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','2100','np','2017-03-13 12:56:26','2017-03-13 13:31:46'),(47,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','3','Weekly','1','2017-03-21 00:00:00','2100.0','2000','2100.0','SCHOOL FEES LOAN','500.0','0','pm','2017-03-13 12:56:26','2017-03-13 12:56:26'),(48,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','700.0','2','Weekly','1','2017-03-28 00:00:00','2100.0','2000','0.0','SCHOOL FEES LOAN','null','2100','np','2017-03-13 12:56:26','2017-03-13 13:45:57'),(49,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','7000.0','3','Weekly','1','2017-03-21 00:00:00','21000.0','20000','21000.0','SCHOOL FEES LOAN','5000.0','0','np','2017-03-13 22:58:07','2017-03-13 22:58:07'),(50,'1','1SCHOOL FEES LOAN','SCHOOL FEES LOAN','A','7000.0','2','Weekly','1','2017-03-28 00:00:00','21000.0','20000','0.0','SCHOOL FEES LOAN','null','2600.0','np','2017-03-13 22:58:07','2017-03-16 00:37:04');
/*!40000 ALTER TABLE `loansarchives` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orgaccount`
--

DROP TABLE IF EXISTS `orgaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orgaccount` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `cashin` varchar(100) DEFAULT NULL,
  `cashout` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `mode` varchar(100) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orgaccount`
--

LOCK TABLES `orgaccount` WRITE;
/*!40000 ALTER TABLE `orgaccount` DISABLE KEYS */;
INSERT INTO `orgaccount` VALUES (1,'0.0','0','Registration Fee','Cash','2017-02-02 15:00:14'),(2,'0.0','0','Registration Fee','Cash','2017-02-02 15:02:25'),(3,'0.0','0','Registration Fee','Cash','2017-02-02 15:07:05'),(4,'0.0','0','Registration Fee','Cash','2017-02-02 15:09:08'),(5,'0.0','0','Registration Fee','Cash','2017-02-02 15:12:16'),(6,'0.0','0','Registration Fee','Cash','2017-02-02 15:14:13'),(7,'0.0','0','Registration Fee','Cash','2017-02-02 15:17:03'),(8,'0.0','0','Registration Fee','Cash','2017-02-02 15:18:58'),(9,'0.0','0','Registration Fee','Cash','2017-02-02 15:22:51'),(10,'0.0','0','Registration Fee','Cash','2017-02-02 20:30:12'),(11,'40.0','0','Loan Application Fee','Mpesa','2017-02-13 20:11:06'),(12,'0','1000.0','LOAN GIVEN','Cash','2017-02-13 20:11:08'),(13,'0','1000.0','LOAN GIVEN','Cash','2017-02-13 20:11:08'),(14,'0.0','0','LOAN PENALTY','Cash','2017-02-14 10:35:43'),(15,'2400.0','0','Loan Installments','Cash','2017-02-14 10:35:50'),(16,'200.0','0','Registration Fee','Mpesa','2017-02-19 09:26:46'),(17,'50000.0','0','Customer Account Deposit','Cash','2017-02-19 09:27:17'),(18,'2000.0','0','Loan Application Fee','Mpesa','2017-02-19 09:28:39'),(19,'0','50000.0','LOAN GIVEN','Mpesa','2017-02-19 09:28:43'),(20,'13000.0','0','Loan Installments','Cash','2017-02-19 09:30:25'),(21,'12250.0','0','Loan Application Fee','Mpesa','2017-02-19 09:53:19'),(22,'12250.0','0','Loan Application Fee','Mpesa','2017-02-19 09:53:50'),(23,'0','49000.0','LOAN GIVEN','Mpesa','2017-02-19 09:53:55'),(24,'5000.0','0','Loan Installments','Cash','2017-02-19 09:55:21'),(25,'3500.0','0','Loan Installments','Mpesa','2017-02-19 10:35:22'),(26,'38662.5','0','Loan Installments','Cash','2017-02-21 11:35:15'),(27,'10000.0','0','Loan Application Fee','Mpesa','2017-02-21 11:47:22'),(28,'10000.0','0','Loan Application Fee','Cash','2017-02-21 11:47:37'),(29,'0','40000.0','LOAN GIVEN','Cash','2017-02-21 11:47:45'),(30,'0','40000.0','LOAN GIVEN','Cash','2017-02-21 11:47:45'),(31,'4000.0','0','Loan Installments','Mpesa','2017-02-21 11:51:02'),(32,'44000.0','0','Loan Installments','Mpesa','2017-02-21 11:52:04'),(33,'44000.0','0','Loan Installments','Mpesa','2017-02-21 12:03:08'),(34,'44000.0','0','Loan Installments','Cash','2017-02-21 12:05:25'),(35,'44000.0','0','Loan Installments','Mpesa','2017-02-21 12:08:18'),(36,'44000.0','0','Loan Installments','Mpesa','2017-02-21 12:11:57'),(37,'12500.0','0','Loan Application Fee','Cash','2017-02-21 12:26:31'),(38,'0','50000.0','LOAN GIVEN','Cash','2017-02-21 12:26:39'),(39,'0','50000.0','LOAN GIVEN','Cash','2017-02-21 12:26:39'),(40,'17500.0','0','Loan Installments','Cash','2017-02-21 12:27:50'),(41,'1500.0','0','Loan Installments','Cash','2017-02-21 12:28:53'),(42,'7500.0','0','Loan Application Fee','Cash','2017-02-22 12:09:51'),(43,'0','30000.0','LOAN GIVEN','Mpesa','2017-02-22 12:09:59'),(44,'9000.0','0','Loan Installments','Mpesa','2017-02-22 12:11:32'),(45,'10000.0','0','Loan Application Fee','Mpesa','2017-02-22 21:17:10'),(46,'10000.0','0','Loan Application Fee','Mpesa','2017-02-22 21:17:25'),(47,'10000.0','0','Loan Application Fee','Mpesa','2017-02-22 21:17:44'),(48,'0','40000.0','LOAN GIVEN','Cash','2017-02-22 21:17:48'),(49,'0','40000.0','LOAN GIVEN','Cash','2017-02-22 21:17:48'),(50,'5000.0','0','Loan Installments','Cash','2017-02-22 21:18:41'),(51,'3000.0','0','Loan Installments','Cash','2017-02-22 21:19:56'),(52,'3000.0','0','Loan Installments','Cash','2017-02-22 22:26:21'),(53,'5000.0','0','Loan Installments','Cash','2017-02-22 22:31:07'),(54,'3000.0','0','Loan Installments','Mpesa','2017-02-22 22:32:05'),(55,'3000.0','0','Loan Installments','Cash','2017-02-22 22:44:32'),(56,'1000.0','0','Loan Installments','Mpesa','2017-02-22 22:47:35'),(57,'4000.0','0','Loan Installments','Cash','2017-02-22 22:48:57'),(58,'5000.0','0','Loan Installments','Cash','2017-02-22 22:50:20'),(59,'5000.0','0','Loan Installments','Cash','2017-02-22 22:51:19'),(60,'2000.0','0','Loan Installments','Cash','2017-02-23 02:38:26'),(61,'4400.0','0','Loan Installments','Cash','2017-02-23 02:40:23'),(62,'3600.0','0','Loan Installments','Cash','2017-02-23 02:41:09'),(63,'4000.0','0','Loan Installments','Cash','2017-02-23 02:42:18'),(64,'8000.0','0','Loan Installments','Cash','2017-02-23 02:43:13'),(65,'-1000.0','0','Loan Installments','Cash','2017-02-23 02:49:07'),(66,'0.0','0','Loan Installments','Mpesa','2017-02-23 02:51:21'),(67,'3000.0','0','Loan Installments','Mpesa','2017-02-23 02:53:27'),(68,'3000.0','0','Loan Installments','Mpesa','2017-02-23 02:56:05'),(69,'10000.0','0','Loan Application Fee','Mpesa','2017-01-01 10:53:52'),(70,'0','40000.0','LOAN GIVEN','Cash','2017-01-01 10:53:55'),(71,'0','40000.0','LOAN GIVEN','Cash','2017-01-01 10:53:55'),(72,'5000.0','0','Loan Installments','Cash','2017-01-01 10:54:53'),(73,'6000.0','0','Loan Installments','Cash','2017-02-23 16:17:22'),(74,'10000.0','0','Loan Application Fee','Mpesa','2017-02-28 14:52:20'),(75,'0','40000.0','LOAN GIVEN','Mpesa','2017-02-28 14:52:25'),(76,'4000.0','0','Loan Installments','Cash','2017-02-28 14:53:44'),(77,'1200.0','0','LOAN PENALTY','Mpesa','2017-03-17 16:29:43'),(78,'4000.0','0','Loan Installments','Mpesa','2017-03-17 16:29:43'),(79,'1200.0','0','LOAN PENALTY','Cash','2017-03-17 16:32:17'),(80,'4000.0','0','Loan Installments','Cash','2017-03-17 16:32:22'),(81,'1200.0','0','LOAN PENALTY','Cash','2017-03-17 16:33:27'),(82,'4000.0','0','Loan Installments','Cash','2017-03-17 16:33:31'),(83,'4000.0','0','Loan Installments','Mpesa','2017-02-28 16:55:44'),(84,'0.0','0','LOAN PENALTY','Mpesa','2017-03-17 16:57:40'),(85,'4000.0','0','Loan Installments','Mpesa','2017-03-17 16:57:40'),(86,'0.0','0','LOAN PENALTY','Cash','2017-03-17 17:09:19'),(87,'4000.0','0','Loan Installments','Cash','2017-03-17 17:09:23'),(88,'1200.0','0','LOAN PENALTY','Mpesa','2017-03-17 17:37:37'),(89,'4000.0','0','Loan Installments','Mpesa','2017-03-17 17:37:37'),(90,'3200.0','0','Loan Installments','Mpesa','2017-03-17 17:38:49'),(91,'5200.0','0','Loan Installments','Cash','2017-02-28 17:41:09'),(92,'3800.0','0','Loan Installments','Cash','2017-02-28 18:19:49'),(93,'3000.0','0','Loan Installments','Cheque','2017-02-28 18:20:49'),(94,'1200.0','0','LOAN PENALTY','Mpesa','2017-04-21 19:39:32'),(95,'4000.0','0','Loan Installments','Mpesa','2017-04-21 19:39:32'),(96,'3200.0','0','Loan Installments','Cash','2017-03-02 09:36:02'),(97,'10000.0','0','Loan Installments','Cheque','2017-03-02 11:11:03'),(98,'600.0','0','LOAN PENALTY','Cash','2017-05-19 12:17:41'),(99,'2000.0','0','Loan Installments','Cash','2017-05-19 12:17:42'),(100,'20000.0','0','Customer Account Deposit','Mpesa','2017-03-03 16:32:30'),(101,'60000.0','0','Customer Account Deposit','Cash','2017-03-03 17:00:18'),(102,'1800.0','0','Loan Installments','Mpesa','2017-03-11 23:55:38'),(103,'800.0','0','Loan Application Fee','Cash','2017-03-12 00:04:13'),(104,'0','20000.0','LOAN GIVEN','Cash','2017-03-12 00:04:16'),(105,'0','20000.0','LOAN GIVEN','Cash','2017-03-12 00:04:16'),(106,'5000.0','0','Loan Application Fee','Mpesa','2017-03-12 00:06:34'),(107,'0','20000.0','LOAN GIVEN','Cash','2017-03-12 00:06:36'),(108,'0','20000.0','LOAN GIVEN','Cash','2017-03-12 00:06:36'),(109,'2000.0','0','Loan Installments','Cash','2017-03-12 00:07:05'),(110,'2500.0','0','Loan Installments','Mpesa','2017-03-12 00:07:44'),(111,'1500.0','0','Loan Installments','Mpesa','2017-03-12 00:08:19'),(112,'1500.0','0','Loan Installments','Mpesa','2017-03-12 00:15:28'),(113,'500.0','0','Loan Application Fee','Mpesa','2017-04-21 11:19:00'),(114,'0','2000.0','LOAN GIVEN','Mpesa','2017-04-21 11:19:03'),(115,'2100.0','0','Loan Installments','Cash','2017-04-21 11:20:09'),(116,'500.0','0','Loan Application Fee','Mpesa','2017-03-13 11:27:56'),(117,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 11:27:59'),(118,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 11:27:59'),(119,'700.0','0','Loan Installments','Cash','2017-03-13 11:28:28'),(120,'700.0','0','Loan Installments','Cash','2017-03-13 11:28:59'),(121,'700.0','0','Loan Installments','Cash','2017-03-13 11:29:32'),(122,'500.0','0','Loan Application Fee','Mpesa','2017-03-13 12:07:16'),(123,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 12:07:19'),(124,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 12:07:19'),(125,'2100.0','0','Loan Installments','Cash','2017-03-13 12:07:51'),(126,'500.0','0','Loan Application Fee','Mpesa','2017-03-13 12:41:19'),(127,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 12:41:21'),(128,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 12:41:21'),(129,'2100.0','0','Loan Installments','Cash','2017-03-13 12:41:43'),(130,'500.0','0','Loan Application Fee','Mpesa','2017-03-13 12:56:26'),(131,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 12:56:27'),(132,'0','2000.0','LOAN GIVEN','Cash','2017-03-13 12:56:27'),(133,'2100.0','0','Loan Installments','Cash','2017-03-13 12:57:03'),(134,'2100.0','0','Loan Installments','Cash','2017-03-13 13:31:48'),(135,'2100.0','0','Loan Installments','Cash','2017-03-13 13:45:59'),(136,'1600.0','0','Loan Application Fee','Cash','2017-03-13 16:21:29'),(137,'0','40000.0','LOAN GIVEN','Mpesa','2017-03-13 16:21:32'),(138,'4800.0','0','LOAN PENALTY','Mpesa','2017-03-13 16:29:20'),(139,'12000.0','0','Loan Installments','Mpesa','2017-03-13 16:29:20'),(140,'4800.0','0','LOAN PENALTY','Cash','2017-03-13 16:31:25'),(141,'12000.0','0','Loan Installments','Cash','2017-03-13 16:31:25'),(142,'5000.0','0','Loan Application Fee','Mpesa','2017-03-13 22:57:53'),(143,'5000.0','0','Loan Application Fee','Mpesa','2017-03-13 22:58:07'),(144,'0','20000.0','LOAN GIVEN','Cash','2017-03-13 22:58:08'),(145,'0','20000.0','LOAN GIVEN','Cash','2017-03-13 22:58:08'),(146,'2100.0','0','LOAN PENALTY','Cash','2017-03-24 23:00:46'),(147,'7000.0','0','Loan Installments','Cash','2017-03-24 23:00:46'),(148,'2800.0','0','LOAN PENALTY','Cash','2017-03-25 00:27:54'),(149,'7000.0','0','Loan Installments','Cash','2017-03-25 00:27:55'),(150,'2800.0','0','LOAN PENALTY','Cash','2017-03-25 00:29:07'),(151,'7000.0','0','Loan Installments','Cash','2017-03-25 00:29:08'),(152,'2800.0','0','LOAN PENALTY','Cash','2017-03-25 00:32:56'),(153,'7000.0','0','Loan Installments','Cash','2017-03-25 00:32:57'),(154,'5000.0','0','Loan Installments','Mpesa','2017-03-16 00:37:07'),(155,'600.0','0','LOAN PENALTY','Cash','2017-03-24 00:42:34'),(156,'2000.0','0','Loan Installments','Cash','2017-03-24 00:42:34'),(157,'600.0','0','LOAN PENALTY','Cash','2017-03-24 00:45:08'),(158,'2000.0','0','Loan Installments','Cash','2017-03-24 00:45:09'),(159,'600.0','0','LOAN PENALTY','Mpesa','2017-03-24 00:46:43'),(160,'2000.0','0','Loan Installments','Mpesa','2017-03-24 00:46:43'),(161,'600.0','0','LOAN PENALTY','Cash','2017-03-24 00:55:19'),(162,'2000.0','0','Loan Installments','Cash','2017-03-24 00:55:19'),(163,'600.0','0','LOAN PENALTY','Cash','2017-03-24 00:59:00'),(164,'2000.0','0','Loan Installments','Cash','2017-03-24 00:59:00'),(165,'600.0','0','LOAN PENALTY','Mpesa','2017-03-24 01:03:44'),(166,'2000.0','0','Loan Installments','Mpesa','2017-03-24 01:03:45'),(167,'600.0','0','LOAN PENALTY','Cash','2017-03-24 01:08:42'),(168,'2000.0','0','Loan Installments','Cash','2017-03-24 01:08:43'),(169,'5000.0','0','Loan Application Fee','Mpesa','2017-03-24 01:20:23'),(170,'5000.0','0','Loan Application Fee','Cash','2017-03-24 01:20:38'),(171,'5000.0','0','Loan Application Fee','Cash','2017-03-24 01:20:52'),(172,'0','20000.0','LOAN GIVEN','Cash','2017-03-24 01:20:56'),(173,'0','20000.0','LOAN GIVEN','Cash','2017-03-24 01:20:56'),(174,'8000.0','0','Loan Installments','Cash','2017-03-24 01:21:28'),(175,'2000.0','0','Loan Application Fee','Cash','2017-03-15 06:53:12'),(176,'4000.0','0','Loan Installments','Cash','2017-03-15 06:53:12'),(177,'0','8000.0','LOAN GIVEN','Cash','2017-03-15 06:53:12'),(178,'2000.0','0','Loan Application Fee','Mode','2017-03-15 07:05:01'),(179,'4000.0','0','Loan Installments','Mode','2017-03-15 07:05:01'),(180,'0','8000.0','LOAN GIVEN','Mode','2017-03-15 07:05:01'),(181,'2000.0','0','Loan Application Fee','Mode','2017-03-15 07:16:06'),(182,'4000.0','0','Loan Installments','Mpesa','2017-03-15 07:16:06'),(183,'7500.0','0','Loan Application Fee','Mpesa','2017-03-16 10:18:07'),(184,'7500.0','0','Loan Application Fee','Cash','2017-03-16 10:18:18'),(185,'0','30000.0','LOAN GIVEN','Cash','2017-03-16 10:18:20'),(186,'0','30000.0','LOAN GIVEN','Cash','2017-03-16 10:18:20'),(187,'100.0','0','Loan Application Fee','Cash','2017-03-16 10:19:20'),(188,'0','5000.0','LOAN GIVEN','Mpesa','2017-03-16 10:19:22'),(189,'8000.0','0','Loan Installments','Cash','2017-03-20 14:37:06'),(190,'1800.0','0','Loan Application Fee','Mpesa','2017-03-27 18:40:07'),(191,'0','45000.0','LOAN GIVEN','Mpesa','2017-03-27 18:40:10'),(192,'20.0','0','Loan Application Fee','Cash','2017-03-29 08:10:45'),(193,'20.0','0','Loan Application Fee','Cash','2017-03-29 08:14:34'),(194,'0','500.0','LOAN GIVEN','Cash','2017-03-29 08:14:43'),(195,'0','500.0','LOAN GIVEN','Cash','2017-03-29 08:14:44'),(196,'24.0','0','Loan Application Fee','Cash','2017-03-29 08:19:11'),(197,'24.0','0','Loan Application Fee','Cash','2017-03-29 08:19:31'),(198,'24.0','0','Loan Application Fee','Cash','2017-03-29 09:13:40'),(199,'0','600.0','LOAN GIVEN','Cash','2017-03-29 09:13:42'),(200,'0','600.0','LOAN GIVEN','Cash','2017-03-29 09:13:42'),(201,'1750.0','0','Loan Application Fee','Cash','2017-03-31 21:36:58'),(202,'1750.0','0','Loan Application Fee','Cash','2017-03-31 21:39:00'),(203,'1750.0','0','Loan Application Fee','Cash','2017-03-31 21:40:14'),(204,'0','7000.0','LOAN GIVEN','Cash','2017-03-31 21:40:18'),(205,'0','7000.0','LOAN GIVEN','Cash','2017-03-31 21:40:18'),(206,'700.0','0','Loan Installments','Cash','2017-04-01 15:23:53'),(207,'700.0','0','Loan Installments','Cash','2017-04-01 15:25:52'),(208,'700.0','0','Loan Installments','Cash','2017-04-01 15:27:25'),(209,'1000.0','0','Loan Installments','Cash','2017-04-01 15:28:16'),(210,'400.0','0','Loan Installments','Cash','2017-04-01 15:29:07'),(211,'700.0','0','Loan Installments','Cash','2017-04-01 15:39:46'),(212,'1000.0','0','Loan Installments','Cash','2017-04-01 15:40:20'),(213,'400.0','0','Loan Installments','Cash','2017-04-01 15:41:05'),(214,'400.0','0','Loan Installments','Cash','2017-04-01 15:44:38'),(215,'700.0','0','Loan Installments','Cash','2017-04-01 15:58:02'),(216,'700.0','0','Loan Installments','Cash','2017-04-01 15:58:17'),(217,'1000.0','0','Loan Installments','Cash','2017-04-01 15:59:40'),(218,'400.0','0','Loan Installments','Cash','2017-04-01 16:00:08'),(219,'300.0','0','Loan Installments','Cash','2017-04-01 16:00:31'),(220,'100.0','0','Loan Installments','Cash','2017-04-01 16:01:08'),(221,'200.0','0','Loan Installments','Cash','2017-04-01 16:05:22'),(222,'100.0','0','Loan Installments','Cash','2017-04-01 16:06:10'),(223,'700.0','0','Loan Installments','Cash','2017-04-01 16:48:10'),(224,'300.0','0','Loan Installments','Cash','2017-04-01 16:48:36'),(225,'300.0','0','Loan Installments','Cash','2017-04-01 16:52:16'),(226,'400.0','0','Loan Installments','Cash','2017-04-01 16:53:01'),(227,'700.0','0','Loan Installments','Cash','2017-04-01 16:55:47'),(228,'400.0','0','Loan Installments','Cash','2017-04-01 16:56:01'),(229,'300.0','0','Loan Installments','Cash','2017-04-01 16:56:19'),(230,'700.0','0','Loan Installments','Cash','2017-04-01 17:27:30'),(231,'300.0','0','Loan Installments','Cash','2017-04-01 17:28:04'),(232,'400.0','0','Loan Installments','Cash','2017-04-01 17:28:43'),(233,'300.0','0','Loan Installments','Cash','2017-04-01 17:29:17'),(234,'500.0','0','Loan Installments','Cash','2017-04-01 17:29:53'),(235,'600.0','0','Loan Installments','Cash','2017-04-01 17:31:38'),(236,'700.0','0','Loan Installments','Cash','2017-04-01 17:54:29'),(237,'300.0','0','Loan Installments','Cash','2017-04-01 17:54:48'),(238,'500.0','0','Loan Installments','Cash','2017-04-01 17:55:13'),(239,'600.0','0','Loan Installments','Cash','2017-04-01 17:55:53'),(240,'700.0','0','Loan Installments','Cash','2017-04-01 17:56:10'),(241,'700.0','0','Loan Installments','Cash','2017-04-01 17:56:28'),(242,'2100.0','0','Loan Installments','Cash','2017-04-01 17:56:57'),(243,'2100.0','0','Loan Installments','Cash','2017-04-01 17:58:10'),(244,'700.0','0','Loan Installments','Cash','2017-04-01 18:00:53'),(245,'7700.0','0','Loan Installments','Cash','2017-04-01 18:01:08'),(246,'700.0','0','Loan Installments','Cash','2017-04-01 18:05:55'),(247,'7000.0','0','Loan Installments','Cash','2017-04-01 18:07:15'),(248,'700.0','0','Loan Installments','Cash','2017-04-01 18:07:34'),(249,'5000.0','0','Loan Application Fee','Cash','2017-04-02 09:16:25'),(250,'0','20000.0','LOAN GIVEN','Cash','2017-04-02 09:16:28'),(251,'0','20000.0','LOAN GIVEN','Cash','2017-04-02 09:16:28'),(252,'4000.0','0','Loan Installments','Cash','2017-04-02 10:44:19'),(253,'4.0','0','LOAN PENALTY','Cash','2017-04-13 16:17:01'),(254,'1000.0','0','Loan Installments','Cash','2017-04-13 16:17:02'),(255,'8.0','0','LOAN PENALTY','Cash','2017-04-14 17:15:50'),(256,'1000.0','0','Loan Installments','Cash','2017-04-14 17:15:50'),(257,'4.0','0','LOAN PENALTY','Cash','2017-04-14 17:27:20'),(258,'1004.0','0','Loan Installments','Cash','2017-04-14 17:27:20'),(259,'4.0','0','LOAN PENALTY','Cash','2017-04-14 17:27:41'),(260,'2000.0','0','Loan Installments','Cash','2017-04-14 17:27:41'),(261,'4.0','0','LOAN PENALTY','Cash','2017-04-14 17:29:49'),(262,'1004.0','0','Loan Installments','Cash','2017-04-14 17:29:49'),(263,'4.0','0','LOAN PENALTY','Cash','2017-04-14 17:37:57'),(264,'1004.0','0','Loan Installments','Cash','2017-04-14 17:37:57'),(265,'8.0','0','LOAN PENALTY','Cash','2017-04-14 18:04:58'),(266,'2008.0','0','Loan Installments','Cash','2017-04-14 18:04:58'),(267,'8.0','0','LOAN PENALTY','Mpesa','2017-04-14 20:49:21'),(268,'2008.0','0','Loan Installments','Mpesa','2017-04-14 20:49:22'),(269,'8.0','0','LOAN PENALTY','Cash','2017-04-14 20:51:08'),(270,'2009.0','0','Loan Installments','Cash','2017-04-14 20:51:09'),(271,'8.0','0','LOAN PENALTY','Cash','2017-04-14 20:58:13'),(272,'2100.0','0','Loan Installments','Cash','2017-04-14 20:58:13'),(273,'8.0','0','LOAN PENALTY','Cash','2017-04-14 21:01:37'),(274,'2100.0','0','Loan Installments','Cash','2017-04-14 21:01:37'),(275,'10.0','0','LOAN PENALTY','Cash','2017-04-15 02:24:29'),(276,'2020.0','0','Loan Installments','Cash','2017-04-15 02:24:29'),(277,'10.0','0','LOAN PENALTY','Cash','2017-04-15 02:27:04'),(278,'2100.0','0','Loan Installments','Cash','2017-04-15 02:27:05'),(279,'10.0','0','LOAN PENALTY','Cash','2017-04-15 02:28:45'),(280,'2100.0','0','Loan Installments','Cash','2017-04-15 02:28:45'),(281,'10.0','0','LOAN PENALTY','Cash','2017-04-15 02:49:03'),(282,'26000.0','0','Loan Installments','Cash','2017-04-15 02:49:03'),(283,'1250.0','0','Loan Application Fee','Cash','2017-03-28 07:50:15'),(284,'1250.0','0','Loan Application Fee','Cash','2017-03-28 07:51:03'),(285,'0','5000.0','LOAN GIVEN','Cash','2017-03-28 07:51:07'),(286,'0','5000.0','LOAN GIVEN','Cash','2017-03-28 07:51:07'),(287,'500.0','0','Loan Installments','Cash','2017-03-28 07:53:26'),(288,'1.0','0','LOAN PENALTY','Cash','2017-04-14 07:59:32'),(289,'6000.0','0','Loan Installments','Cash','2017-04-14 07:59:33'),(290,'1250.0','0','Loan Application Fee','Mpesa','2017-04-05 11:33:49'),(291,'0','5000.0','LOAN GIVEN','Cash','2017-04-05 11:33:53'),(292,'0','5000.0','LOAN GIVEN','Cash','2017-04-05 11:33:53'),(293,'8.0','0','LOAN PENALTY','Cash','2017-04-18 11:36:14'),(294,'5400.0','0','Loan Installments','Cash','2017-04-18 11:36:15'),(295,'400.0','0','Loan Installments','Cash','2017-04-10 14:45:25'),(296,'1200.0','0','Loan Installments','Cash','2017-04-10 14:51:22'),(297,'1000.0','0','Loan Application Fee','Cash','2017-04-10 16:25:58'),(298,'0','4000.0','LOAN GIVEN','Cash','2017-04-10 16:26:01'),(299,'0','4000.0','LOAN GIVEN','Cash','2017-04-10 16:26:01'),(300,'4800.0','0','Loan Installments','Cash','2017-04-10 16:26:30'),(301,'5000.0','0','Customer Account Deposit','Cash','2017-04-11 11:22:02'),(302,'12500.0','0','Loan Application Fee','Cash','2017-04-11 15:43:14'),(303,'12500.0','0','Loan Application Fee','Cash','2017-04-11 15:44:03'),(304,'0','50000.0','LOAN GIVEN','Cash','2017-04-11 15:44:06'),(305,'0','50000.0','LOAN GIVEN','Cash','2017-04-11 15:44:07'),(306,'400.0','0','Loan Installments','Cash','2017-04-11 15:44:34'),(307,'4600.0','0','Loan Installments','Cash','2017-04-11 15:45:11'),(308,'500.0','0','Loan Installments','Cash','2017-04-11 16:02:23'),(309,'500.0','0','Loan Installments','Cash','2017-04-11 16:02:57');
/*!40000 ALTER TABLE `orgaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plot`
--

DROP TABLE IF EXISTS `plot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plot` (
  `PLOTNO` varchar(30) NOT NULL,
  `LOCATION` varchar(30) NOT NULL,
  `TOWN` varchar(30) NOT NULL,
  `COUNTY` varchar(30) NOT NULL,
  `RENTPERMONTH` varchar(30) NOT NULL,
  `ROOMSNO` varchar(30) NOT NULL,
  `CARETAKERNAME` varchar(30) NOT NULL,
  `CARETAKERNO` varchar(30) NOT NULL,
  `DATE` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plot`
--

LOCK TABLES `plot` WRITE;
/*!40000 ALTER TABLE `plot` DISABLE KEYS */;
/*!40000 ALTER TABLE `plot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prefrences`
--

DROP TABLE IF EXISTS `prefrences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prefrences` (
  `id` varchar(100) NOT NULL DEFAULT '1',
  `name` varchar(70) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `website` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prefrences`
--

LOCK TABLES `prefrences` WRITE;
/*!40000 ALTER TABLE `prefrences` DISABLE KEYS */;
INSERT INTO `prefrences` VALUES ('1','Inua Development','23432','inuadev.c@gmail.com','www.inuadevelopment.cp.ke','071234567','C:\\Users\\kimani kogi\\Desktop\\selli.png');
/*!40000 ALTER TABLE `prefrences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_user` (
  `id` int(100) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  `hash` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
INSERT INTO `system_user` VALUES (5,'t1emp3','A1234','V'),(6,'ADMIN','admin','ABEFGHIJKLMNOPQRSTUVWX');
/*!40000 ALTER TABLE `system_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `amount` varchar(70) NOT NULL,
  `typeoftransaction` varchar(70) NOT NULL,
  `typeofaccount` varchar(70) NOT NULL,
  `modeofpayment` varchar(70) NOT NULL,
  `withdrawn` varchar(70) DEFAULT NULL,
  `deposited` varchar(70) DEFAULT NULL,
  `balance` varchar(70) DEFAULT NULL,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=163 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (19,'20427097','15850','deposits','Regular Acc','0','null','15850','15850.0','2016-12-08 16:09:59'),(20,'12779240','700','deposits','Regular Acc','0','null','700','700.0','2016-12-08 17:01:11'),(21,'12742521','1600','deposits','Regular Acc','0','null','1600','1600.0','2016-12-13 08:56:36'),(22,'25163853','2400','deposits','Regular Acc','0','null','2400','2400.0','2016-12-13 08:57:21'),(23,'9424822','4900','deposits','Regular Acc','0','null','4900','4900.0','2016-12-13 08:58:08'),(24,'10250929','15000','deposits','Regular Acc','0','null','15000','15000.0','2016-12-13 09:00:23'),(25,'10469771','3800','deposits','Regular Acc','0','null','3800','3800.0','2016-12-13 09:02:22'),(26,'27578349','1400','deposits','Regular Acc','0','null','1400','1400.0','2016-12-13 09:03:53'),(27,'21918640','4400','deposits','Regular Acc','0','null','4400','4400.0','2016-12-13 09:04:37'),(28,'27549950','7850','deposits','Regular Acc','0','null','7850','7850.0','2016-12-13 09:05:15'),(29,'7026272','4900','deposits','Regular Acc','0','null','4900','4900.0','2016-12-13 09:06:07'),(30,'10775207','4700','deposits','Regular Acc','0','null','4700','4700.0','2016-12-13 09:06:55'),(31,'25637048','6300','deposits','Regular Acc','0','null','6300','6300.0','2016-12-13 09:07:54'),(32,'27821010','8600','deposits','Regular Acc','0','null','8600','8600.0','2016-12-13 09:08:35'),(33,'24113928','3000','deposits','Regular Acc','0','null','3000','3000.0','2016-12-13 09:09:16'),(34,'14588433','9300','deposits','Regular Acc','0','null','9300','9300.0','2016-12-13 09:10:01'),(35,'22452262','5500','deposits','Regular Acc','0','null','5500','5500.0','2016-12-13 09:10:41'),(36,'22159413','15400','deposits','Regular Acc','0','null','15400','15400.0','2016-12-13 09:11:38'),(37,'24193899','1600','deposits','Regular Acc','0','null','1600','1600.0','2016-12-13 09:12:24'),(38,'10250929','100','deposits','Regular Acc','0','null','100','15100.0','2016-12-13 16:10:16'),(39,'10775207','300','deposits','Regular Acc','0','null','300','5000.0','2016-12-13 16:11:07'),(40,'21918640','200','deposits','Regular Acc','0','null','200','4600.0','2016-12-13 16:11:40'),(41,'10469771','100','deposits','Regular Acc','0','null','100','3900.0','2016-12-13 16:12:22'),(42,'14588433','200','deposits','Regular Acc','0','null','200','9500.0','2016-12-13 16:14:24'),(43,'25163853','200','deposits','Regular Acc','0','null','200','2600.0','2016-12-13 16:16:01'),(44,'7026272','100','deposits','Regular Acc','0','null','100','5000.0','2016-12-13 16:16:52'),(45,'24113928','100','deposits','Regular Acc','0','null','100','3100.0','2016-12-13 16:17:45'),(46,'12742521','1400','deposits','Regular Acc','0','null','1400','3000.0','2016-12-13 16:18:15'),(47,'32980449','2500','deposits','Regular Acc','0','null','2500','2500.0','2016-12-14 12:59:13'),(48,'9222541','7900','deposits','Regular Acc','0','null','7900','7900.0','2016-12-14 12:59:58'),(49,'25157768','5950','deposits','Regular Acc','0','null','5950','5950.0','2016-12-14 13:00:48'),(50,'22335670','200','deposits','Regular Acc','0','null','200','200.0','2016-12-14 13:01:40'),(51,'13386506','15000','deposits','Regular Acc','0','null','15000','15000.0','2016-12-14 13:02:18'),(52,'31734701','600','deposits','Regular Acc','0','null','600','600.0','2016-12-14 13:02:53'),(53,'25558039','3100','deposits','Regular Acc','0','null','3100','3100.0','2016-12-14 13:03:55'),(54,'31734701','200','deposits','Regular Acc','0','null','200','800.0','2016-12-14 13:05:32'),(55,'22335670','200','deposits','Regular Acc','0','null','200','400.0','2016-12-14 13:06:30'),(56,'28332931','900','deposits','Regular Acc','0','null','900','900.0','2016-12-15 08:45:18'),(57,'26149193','10000','deposits','Regular Acc','0','null','10000','10000.0','2016-12-15 08:46:00'),(58,'28787875','28500','deposits','Regular Acc','0','null','28500','28500.0','2016-12-15 08:46:41'),(59,'28336943','15500','deposits','Regular Acc','0','null','15500','15500.0','2016-12-15 08:47:13'),(60,'24741762','7000','deposits','Regular Acc','0','null','7000','7000.0','2016-12-15 08:47:52'),(61,'21628042','4000','deposits','Regular Acc','0','null','4000','4000.0','2016-12-15 08:48:24'),(62,'23902305','1000','deposits','Regular Acc','0','null','1000','1000.0','2016-12-15 08:48:57'),(63,'30098110','12100','deposits','Regular Acc','0','null','12100','0','2016-12-15 08:50:01'),(64,'28336943','2000','deposits','Regular Acc','0','null','2000','17500.0','2016-12-23 13:09:45'),(65,'21918640','200','deposits','Regular Acc','19/12/2016','null','200','4800.0','2017-01-04 21:38:19'),(66,'21918640','200','deposits','Regular Acc','02/01/2017','null','200','5000.0','2017-01-04 21:39:03'),(67,'10469771','100','deposits','Regular Acc','19/12/2016','null','100','4000.0','2017-01-04 21:53:34'),(68,'24193899','200','deposits','Regular Acc','19/12/2016','null','200','1800.0','2017-01-04 21:54:58'),(69,'7026272','100','deposits','Regular Acc','19/12/2016','null','100','5100.0','2017-01-04 21:56:07'),(70,'7026272','200','deposits','Regular Acc','02/01/2017','null','200','5300.0','2017-01-04 21:56:43'),(71,'10775207','100','deposits','Regular Acc','19/12/2016','null','100','5100.0','2017-01-04 21:57:43'),(72,'12742521','200','deposits','Regular Acc','0','null','200','3200.0','2017-01-04 21:58:22'),(73,'25163853','100','deposits','Regular Acc','19/12/2016','null','100','2700.0','2017-01-04 21:59:32'),(74,'22159413','200','deposits','Regular Acc','19/12/2016','null','200','15600.0','2017-01-04 22:00:51'),(75,'22159413','200','deposits','Regular Acc','02/01/2017','null','200','15800.0','2017-01-04 22:01:27'),(76,'32980449','100','deposits','Regular Acc','16/12/2016','null','100','2600.0','2017-01-04 22:04:57'),(77,'32980449','200','deposits','Regular Acc','23/12/2016','null','200','2800.0','2017-01-04 22:05:58'),(78,'31734701','200','deposits','Regular Acc','16/12/2016','null','200','1000.0','2017-01-04 22:06:42'),(79,'31734701','200','deposits','Regular Acc','23/12/2016','null','200','1200.0','2017-01-04 22:07:28'),(80,'9222541','200','deposits','Regular Acc','23/12/2016','null','200','8100.0','2017-01-04 22:08:15'),(81,'22335670','200','deposits','Regular Acc','16/12/2016','null','200','600.0','2017-01-04 22:10:42'),(82,'22335670','200','deposits','Regular Acc','09/12/2016','null','200','600','2017-01-04 22:11:59'),(83,'24741762','200','deposits','Regular Acc','19/12/2016','null','200','7200.0','2017-01-05 22:41:52'),(84,'24741762','200','deposits','Regular Acc','26/12/2016','null','200','7400.0','2017-01-05 22:42:34'),(85,'24741762','200','deposits','Regular Acc','02/01/2017','null','200','7600.0','2017-01-05 22:43:08'),(86,'32980449','200','deposits','Regular Acc','0','null','200','3000.0','2017-01-05 23:47:17'),(87,'31734701','300','deposits','Regular Acc','0','null','300','1500.0','2017-01-05 23:47:50'),(88,'9222541','200','deposits','Regular Acc','0','null','200','8300.0','2017-01-05 23:48:22'),(89,'22335670','200','deposits','Regular Acc','0','null','200','1000.0','2017-01-05 23:49:00'),(90,'13386506','100','deposits','Regular Acc','0','null','100','15100.0','2017-01-05 23:49:30'),(91,'10775207','200','deposits','Regular Acc','0','null','200','5300.0','2017-01-06 01:01:08'),(92,'10775207','200','deposits','Regular Acc','0','null','200','5500.0','2017-01-09 05:07:42'),(93,'21918640','200','deposits','Regular Acc','0','null','200','5200.0','2017-01-09 05:08:25'),(94,'22159413','200','deposits','Regular Acc','0','null','200','16000.0','2017-01-09 05:09:07'),(95,'10469771','100','deposits','Regular Acc','0','null','100','4100.0','2017-01-09 05:09:43'),(96,'27549950','100','deposits','Regular Acc','0','null','100','7950.0','2017-01-09 05:50:53'),(97,'9424822','100','deposits','Regular Acc','0','null','100','5000.0','2017-01-09 05:51:35'),(98,'25163853','300','deposits','Regular Acc','02/01/2017','null','300','3000.0','2017-01-09 05:52:36'),(99,'25163853','200','deposits','Regular Acc','0','null','200','3200.0','2017-01-09 05:53:05'),(100,'7026272','200','deposits','Regular Acc','0','null','200','5500.0','2017-01-09 05:53:35'),(101,'24113928','100','deposits','Regular Acc','0','null','100','3200.0','2017-01-09 05:54:15'),(102,'27162088','400','deposits','Regular Acc','0','null','400','400.0','2017-01-13 05:32:41'),(103,'22335670','200','deposits','Regular Acc','0','null','200','1200.0','2017-01-13 05:33:11'),(104,'9222541','200','deposits','Regular Acc','0','null','200','8500.0','2017-01-13 05:33:43'),(105,'31734701','400','deposits','Regular Acc','0','null','400','1900.0','2017-01-13 05:34:13'),(106,'25157768','200','deposits','Regular Acc','0','null','200','6150.0','2017-01-13 05:34:48'),(107,'13386506','200','deposits','Regular Acc','0','null','200','15300.0','2017-01-13 05:36:32'),(108,'10775207','200','deposits','Regular Acc','0','null','200','5700.0','2017-01-18 13:37:12'),(109,'21918640','200','deposits','Regular Acc','0','null','200','5400.0','2017-01-18 13:37:52'),(110,'22159413','200','deposits','Regular Acc','0','null','200','16200.0','2017-01-18 13:46:14'),(111,'27549950','100','deposits','Regular Acc','0','null','100','8050.0','2017-01-18 13:46:44'),(112,'9424822','200','deposits','Regular Acc','0','null','200','5200.0','2017-01-18 13:47:12'),(113,'27821010','100','deposits','Regular Acc','0','null','100','8700.0','2017-01-18 13:47:44'),(114,'25163853','200','deposits','Regular Acc','0','null','200','3400.0','2017-01-18 13:48:15'),(115,'7026272','300','deposits','Regular Acc','0','null','300','5800.0','2017-01-18 13:48:40'),(116,'27578349','300','deposits','Regular Acc','0','null','300','1700.0','2017-01-18 13:49:15'),(117,'24113928','200','deposits','Regular Acc','0','null','200','3400.0','2017-01-18 13:49:47'),(118,'12742521','200','deposits','Regular Acc','0','null','200','3400.0','2017-01-18 13:50:25'),(119,'34200400','300','deposits','Regular Acc','14/01/2017','null','300','300.0','2017-01-19 09:59:48'),(120,'30766762','300','deposits','Regular Acc','0','null','300','300.0','2017-01-19 10:00:36'),(121,'24193899','200','deposits','Regular Acc','0','null','200','2000.0','2017-01-23 11:43:24'),(122,'21918640','200','deposits','Regular Acc','0','null','200','5600.0','2017-01-23 12:11:43'),(123,'25163853','100','deposits','Regular Acc','0','null','100','3500.0','2017-01-23 12:12:28'),(124,'9424822','100','deposits','Regular Acc','0','null','100','5300.0','2017-01-23 12:24:28'),(125,'10775207','200','deposits','Regular Acc','0','null','200','5900.0','2017-01-23 12:25:39'),(126,'12742521','200','deposits','Regular Acc','0','null','200','3600.0','2017-01-23 12:26:30'),(127,'7026272','200','deposits','Regular Acc','0','null','200','6000.0','2017-01-23 12:27:57'),(128,'10469771','100','deposits','Regular Acc','0','null','100','4200.0','2017-01-23 12:30:00'),(129,'25637048','100','deposits','Regular Acc','0','null','100','6400.0','2017-01-23 12:31:05'),(130,'22159413','200','deposits','Regular Acc','0','null','200','16400.0','2017-01-23 12:33:09'),(131,'22452262','5500','withdrawal','Regular Acc','LOAN PAYMENT','5500','null','0.0','2017-01-23 12:36:07'),(132,'26976564','100','deposits','Regular Acc','0','null','100','100.0','2017-01-24 13:04:46'),(141,'13386506','100','deposits','Regular Acc','0','null','100','15400.0','2017-01-27 11:05:07'),(142,'25157768','100','deposits','Regular Acc','0','null','100','6250.0','2017-01-27 11:05:46'),(143,'32980449','100','deposits','Regular Acc','0','null','100','3100.0','2017-01-27 11:06:17'),(144,'31734701','100','deposits','Regular Acc','0','null','100','2000.0','2017-01-27 11:06:56'),(145,'22335670','200','deposits','Regular Acc','0','null','200','1400.0','2017-01-27 11:08:02'),(146,'27162088','500','deposits','Regular Acc','0','null','500','900.0','2017-01-27 11:08:33'),(147,'20427097','100','deposits','Regular Acc','0','null','100','15950.0','2017-01-31 08:43:19'),(148,'10775207','100','deposits','Regular Acc','0','null','100','6000.0','2017-01-31 08:43:51'),(149,'21918640','400','deposits','Regular Acc','0','null','400','6000.0','2017-01-31 08:44:29'),(150,'10469771','100','deposits','Regular Acc','0','null','100','4300.0','2017-01-31 08:44:58'),(151,'9424822','200','deposits','Regular Acc','0','null','200','5500.0','2017-01-31 08:45:27'),(152,'27821010','200','deposits','Regular Acc','0','null','200','8900.0','2017-01-31 08:45:54'),(153,'25163853','100','deposits','Regular Acc','0','null','100','3600.0','2017-01-31 08:46:42'),(154,'7026272','300','deposits','Regular Acc','0','null','300','6300.0','2017-01-31 08:47:41'),(155,'24193899','1200','deposits','Regular Acc','0','null','1200','3200.0','2017-01-31 08:48:37'),(156,'27578349','100','deposits','Regular Acc','0','null','100','1800.0','2017-01-31 08:49:20'),(157,'24113928','100','deposits','Regular Acc','0','null','100','3500.0','2017-01-31 08:51:08'),(158,'30598429','500','deposits','Regular Acc','0','null','500','500.0','2017-01-31 08:52:15'),(159,'1','50000','deposits','Regular Saving','Cash-ff44','null','50000','50000.0','2017-02-19 09:27:15'),(160,'1','20000','deposits','Regular Saving','Mpesa-7t5','null','20000','70000.0','2017-03-03 16:32:27'),(161,'1','60000','deposits','Regular Saving','Cash-g6hjv','null','60000','130000.0','2017-03-03 17:00:16'),(162,'1','5000','deposits','Regular Saving','Cash-Rtwe3','null','5000','135000.0','2017-04-11 11:22:01');
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useracctypes`
--

DROP TABLE IF EXISTS `useracctypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useracctypes` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `accountname` varchar(70) NOT NULL,
  `appfee` varchar(70) NOT NULL,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useracctypes`
--

LOCK TABLES `useracctypes` WRITE;
/*!40000 ALTER TABLE `useracctypes` DISABLE KEYS */;
INSERT INTO `useracctypes` VALUES (4,'1','Regular Saving','200'),(5,'2','Super','0');
/*!40000 ALTER TABLE `useracctypes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroups`
--

DROP TABLE IF EXISTS `usergroups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usergroups` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `groupname` varchar(70) NOT NULL,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroups`
--

LOCK TABLES `usergroups` WRITE;
/*!40000 ALTER TABLE `usergroups` DISABLE KEYS */;
INSERT INTO `usergroups` VALUES (1,'1','Blessed'),(2,'2','Joy'),(3,'3','Individual');
/*!40000 ALTER TABLE `usergroups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(100) NOT NULL DEFAULT '0',
  `accountno` varchar(100) NOT NULL DEFAULT '0',
  `fname` varchar(100) NOT NULL DEFAULT '0',
  `sname` varchar(100) NOT NULL DEFAULT '0',
  `uniq` varchar(100) NOT NULL DEFAULT '0',
  `email` varchar(70) NOT NULL DEFAULT '0',
  `phone` varchar(70) NOT NULL DEFAULT '0',
  `address` varchar(70) NOT NULL DEFAULT '0',
  `county` varchar(70) NOT NULL DEFAULT '0',
  `town` varchar(70) NOT NULL DEFAULT '0',
  `occupation` varchar(70) NOT NULL DEFAULT '0',
  `maritalstatus` varchar(70) NOT NULL DEFAULT '0',
  `spaucefname` varchar(70) NOT NULL DEFAULT '0',
  `spaucesname` varchar(70) NOT NULL DEFAULT '0',
  `spauceid` varchar(70) NOT NULL DEFAULT '0',
  `spaucephone` varchar(70) NOT NULL DEFAULT '0',
  `acounttype` varchar(70) NOT NULL DEFAULT '0',
  `group` varchar(70) NOT NULL DEFAULT 'null',
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `imgurl` varchar(70) NOT NULL DEFAULT 'null',
  PRIMARY KEY (`accountno`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (25028998,'1041','Mr. JAMES','KIWARA','SuperIndividual','0','0726600567','O','NAKURU','NAKURU','BODABODA OPERATOR (JUJA)','Married','','','','','Super','Individual','2017-02-02 15:17:03','null'),(12742521,'1045','Ms LILIAN ','NJERI','Regular AccBlessed','0','0704391656','0','NAKURU','NAKURU','SHOPKEEPER - NEAR KEJOS HOTEL','Single','','','','','Regular Acc','Blessed','2016-12-08 17:15:11','null'),(32114332,'1271','Mr. NDUNGU','JOSEPH MWAI','SUPER ACCOUNTINDIVIDUALS','0','0707401617','0','NAKURU','NAKURU','ANIMAL FEEDS/AGROVET','Single','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 13:21:40','null'),(24741762,'1347','Mr. CALEB','SICHANGI','Regular AccIndividuals','0','0704895419','0','NAKURU','NAKURU','PAINTER','Married','','','','','Regular Acc','Individuals','2016-12-14 14:20:36','null'),(24193899,'1490','Mrs JAQULINE','AKOTH','Regular AccBlessed','0','0718589758','0','NAKURU','NAKURU','CHIPS SELLER','Married','','','','','Regular Acc','Blessed','2016-12-08 17:21:15','null'),(12779240,'1552','Mrs SHIPROSA','WANJIRU','Regular AccBlessed','0','0717493234','0','NAKURU','NAKURU','FRUITS VENDOR - MOBILE','Married','','','','','Regular Acc','Blessed','2016-12-08 16:59:57','null'),(24946568,'1809','Ms KARIUKI','FLORENCE WAMBUI','SUPER ACCOUNTINDIVIDUALS','0','0723675799','0','NAKURU','NAKURU','FURNITURE WORKSHOP LANGA MARKET','Single','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 15:43:42','null'),(22159413,'1935','Mr. JOASH','MISATI OTOTO','Regular AccBlessed','0','0726964517','0','NAKURU','NAKURU','TIMBER YARD','Married','','','','','Regular Acc','Blessed','2016-12-08 17:23:42','null'),(28336943,'2037','Mr. JULIUS','ETIRA EBENYO','Regular AccIndividuals','0','0717746059','0','NAKURU','NAKURU','CHEF','Single','','','','','Regular Acc','Individuals','2016-12-14 14:23:16','null'),(34200400,'2265','Mr. CHARLES','ONYANGO WANGIGE','Regular AccIndividuals','0','0791019722','0','NAKURU','NAKURU','JUAKALI (MJENGO)','Single','','','','','Regular Acc','Individuals','2017-01-11 04:47:16','null'),(27821010,'2386','Mr. EDWIN','OBURA OPONDO','Regular AccBlessed','0','0728498477','0','NAKURU','NAKURU','BODABODA OPERATOR - MOBILE','Single','','','','','Regular Acc','Blessed','2016-12-08 17:07:03','null'),(25836094,'2464','Mr. GRIFFIN','ODHIAMBO','Regular AccIndividuals','0','0711536362','0','NAKURU','NAKURU','PAINTER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:40:24','null'),(24530835,'2568','Mr. JOSEPH','WANJAU MAHINDA','SuperIndividual','0','0721169585','0','NAKURU','NAKURU','BUTCHERY BUSINESS','Married','','','','','Super','Individual','2017-02-02 15:18:58','null'),(27578349,'2615','Mrs EBBY','NASIMIYU','Regular AccBlessed','0','0724976139','0','NAKURU','NAKURU','FISH MONGER','Married','','','','','Regular Acc','Blessed','2016-12-08 17:19:30','null'),(22452262,'2747','Mr. DAVID','GACHIENGU NGAII','Regular AccBlessed','0','0723127706','0','NAKURU','NAKURU','MATATU DRIVER - KST','Single','','','','','Regular Acc','Blessed','2016-12-08 17:27:27','null'),(25558681,'2771','Mr. BEDAN','NJUGUNA ','SuperIndividual','0','0722112630','0','NAKURU','NAKURU','ISSUARANCE SALES EXECUTIVE','Married','','','','','Super','Individual','2017-02-02 15:02:25','null'),(26976564,'2807','Ms JULIA','GICHEHA','Regular AccIndividuals','0','0700340111','0','NAKURU','NAKURU','SALONIST/HAIRDRESSER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:36:04','null'),(24113928,'2939','Mr. WILSON','OKOTH','Regular AccBlessed','0','0712211848','0','NAKURU','NAKURU','JUA KALI','Married','','','','','Regular Acc','Blessed','2016-12-08 17:18:16','null'),(27543018,'2981','Mrs HARRIET','NDUTA ','SuperIndividual','0','0726065086','0','NAKURU','NAKURU','FRUIT VENDOR','Widowed','','','','','Super','Individual','2017-02-02 15:14:13','null'),(21628042,'2989','Mr. ZAKAYO','OMONDI','Regular AccIndividuals','0','0728342457','0','NAKURU','NAKURU','PAINTER','Married','MILDRED','ANYANGO','23902305','0700114858','Regular Acc','Individuals','2016-12-14 14:15:46','null'),(11596338,'3010','Mr. KENNEDY','SIDWAKA','Regular AccIndividuals','0','0723151654','0','NAKURU','NAKURU','PAINTER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:39:05','null'),(28787875,'3020','Mr. LABAN','MWANIKI','Regular AccIndividuals','0','0725826166','0','NAKURU','NAKURU','ELECTRICIAN','Married','','','','','Regular Acc','Individuals','2016-12-14 14:32:58','null'),(22335670,'3079','Mrs NANCY','WANJIRU WANJOHI','Regular AccJoy','0','0722506228','0','NAKURU','NAKURU','MALI MALI SHOP ','Single','','','','','Regular Acc','Joy','2016-12-14 12:53:08','null'),(31753384,'3140','Mr. MOSES','OLUOCH','Regular AccIndividuals','0','0723323546','0','NAKURU','NAKURU','PAINTER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:37:47','null'),(30598429,'3299','Mr. ABEL','OBINO MAKORI','Regular AccBlessed','0','0713067411','0','NAKURU','NAKURU','BODABODA OPERATOR(MOTORBIKE)','Married','','','','','Regular Acc','Blessed','2017-01-09 06:02:25','null'),(30098110,'3515','Mr. KENNEDY','OUMA OHIDI','Regular AccIndividuals','0','0712206165','0','NAKURU','NAKURU','HIGH SCHOOL TEACHER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:44:15','null'),(28332931,'3522','Mr. EVANS','OCHIENG','Regular AccIndividuals','0','0791613090','0','NAKURU','NAKURU','PAINTER/JUA KALI','Single','','','','','Regular Acc','Individuals','2016-12-14 14:37:42','null'),(26149193,'3693','Mr. GODWIN','SIRENGO ANYOSO','Regular AccIndividuals','0','0729291239','0','NAKURU','NAKURU','TAILOR','Married','','','','','Regular Acc','Individuals','2016-12-14 14:34:18','null'),(22609756,'3701','Mrs EVELYN','NABS SIKITU','SuperIndividual','0','0725556252','0','NAKURU','NAKURU','SALOONIST MAMA NANAS SALOON','Married','0','0','0','','Super','Individual','2017-02-02 15:07:05','null'),(9424822,'3780','Mr. NAFTALI','MULO OCHIENG','Regular AccBlessed','0','0726866679','0','NAKURU','NAKURU','WELDER','Married','','','','','Regular Acc','Blessed','2016-12-08 17:29:37','null'),(30545349,'3806','Mrs NAOMI','WANJIRU GICHEHA','Regular AccIndividuals','0','0714037635','0','NAKURU','NAKURU','PRINTING AND STATIONERIES','Married','','','','','Regular Acc','Individuals','2016-12-14 14:17:42','null'),(21972374,'3876','Mr. JOHN','WANJOHI MATHENGE','SuperIndividual','O','0715974907','O','NAKURU','NAKURU','GYM INSTRUCTOR','Married','','','','','Super','Individual','2017-02-02 15:12:16','null'),(27162088,'4003','Mrs SARAH','ADHIAMBO','Regular AccJoy','0','0716398008','0','NAKURU','NAKURU','FOOD KIOSK PONDA MALI','Married','','','','','Regular Acc','Joy','2017-01-08 22:27:41','null'),(25157768,'4092','Mr. MARTIN','ODHIAMBO ONALA','Regular AccJoy','0','0710447124','0','NAKURU','NAKURU','CARPENTER','Single','','','','','Regular Acc','Joy','2016-12-14 12:55:41','null'),(29844293,'4154','Mrs LEAH','WANJIRU NGATIA','SuperIndividual','0','0727217179','0','NAKURU','NAKURU','BOUTIQUE BUSINESS ELDORET STAGE','Married','','','','','Super','Individual','2017-02-02 15:00:14','null'),(28772299,'4210','Mr. GISEMBA','EDWIN OKEMWA','SUPER ACCOUNTINDIVIDUALS','0','0725667412','0','NAKURU','NAKURU','OJEEZ HOTEL SATELITE','Married','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 15:39:28','null'),(23902305,'4244','Mrs MILDRED','ANYANGO','Regular AccIndividuals','0','0700114858','0','NAKURU','NAKURU','VEGETABLE SELLER','Married','ABONYO','ZAKAYO OMONDI','21628042','0728342457','Regular Acc','Individuals','2016-12-14 14:13:32','null'),(9222541,'4309','Mr. SIMON','OUMA ASISA','Regular AccJoy','0','0733528055','0','NAKURU','NAKURU','TAILOR','Married','','','','','Regular Acc','Joy','2016-12-14 12:56:57','null'),(24116760,'4535','Ms WAMBUI','MINAH','SUPER ACCOUNTINDIVIDUALS','0','0725321908','0','NAKURU','NAKURU','FOOD KIOSK BEHIND LEGACY HOTEL','Single','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 16:28:39','null'),(32980449,'4575','Mr. DAVID','OUMA','Regular AccJoy','0','0711541552','0','NAKURU','NAKURU','TAILOR','Married','','','','','Regular Acc','Joy','2016-12-14 12:58:17','null'),(7026272,'4679','Mr. SAMSON','OCHIENG','Regular AccBlessed','0','0722638071','0','NAKURU','NAKURU','CAR SPARE PARTS','Married','','','','','Regular Acc','Blessed','2016-12-09 10:41:52','null'),(22929054,'4843','Mr. CHARLES ','AKOA ','SuperIndividual','0','0710908273','0','NAKURU','NAKURU','ELECTRICIAN','Married','','','','','Super','Individual','2017-02-02 15:09:08','null'),(1,'4877','Mr. Kogi','Eric','Regular SavingBlessed','er','1','1','w','w','w','Single','','','','','Regular Saving','Blessed','2017-02-19 09:26:48','C:\\Users\\kimani kogi\\Documents\\eric.jpg'),(30766762,'5155','Mr. ANTHONY','ODOYO','Regular AccIndividuals','0','0705845670','0','NAKURU','NAKURU','JUA KALI','Single','','','','','Regular Acc','Individuals','2016-12-14 14:40:35','null'),(25637048,'5169','Mr. DANIEL','OTIENO MBOLA','Regular AccBlessed','0','0729718014','0','NAKURU','NAKURU','COMPLY CARPENTER','Divorced','','','','','Regular Acc','Blessed','2016-12-08 17:34:00','null'),(30090900,'5223','Mr. BAVON','OTIENO','Regular AccIndividuals','0','0707690413','0','NAKURU','NAKURU','PAINTER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:41:41','null'),(10469771,'5267','Mr. GIDEON','OMBASO MAKORI','Regular AccBlessed','0','0723267789','0','NAKURU','NAKURU','CARPENTER','Married','','','','','Regular Saving','Blessed','2016-12-08 17:13:42','null'),(27549950,'5274','Mrs GRACE','WANJAA','Regular AccBlessed','0','0710497761','0','NAKURU','NAKURU','ICE CREAM BUSINESS - NEAR KEJOS HOTEL KANU STREET','Married','','','','','Regular Acc','Blessed','2016-12-08 17:11:59','null'),(25163853,'5354','Mrs MARY','ANYANGO ODUOR','Regular AccBlessed','0','0723849174','0','NAKURU','NAKURU','MTUMBA SELLER','Married','','','','','Regular Acc','Blessed','2016-12-08 17:25:55','null'),(29696017,'5417','Mr. FREDRICK','NJOROGE','Regular AccIndividuals','0','0702769106','0','NAKURU','NAKURU','PAINTER','Married','','','','','Regular Acc','Individuals','2016-12-14 14:39:20','null'),(32134842,'5420','Ms NYAMBURA','PENINAH','SUPER ACCOUNTINDIVIDUALS','0','0701912834','0','NAKURU','NAKURU','SHOES SELLER','Single','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 16:34:46','null'),(31734701,'5536','Mr. PHILLIP','OMONDI ONALA','Regular AccJoy','0','0700110039','0','NAKURU','NAKURU','TILES FIXER/MASON','Single','','','','','Regular Acc','Joy','2016-12-14 12:35:13','null'),(10250929,'5542','Mr. RAPHAEL','OCHIENG WARINDU','Regular AccBlessed','0','0726866769','0','NAKURU','NAKURU','SEAT COVER TAILORING','Married','','','','','Regular Acc','Blessed','2016-12-08 17:40:52','null'),(32181755,'5570','Mr. OKUMU','SAMWEL','SUPER ACCOUNTINDIVIDUALS','0','0711104792','0','NAKURU','NAKURU','STUDENT NAKURU TEACHERS','Single','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 16:14:08','null'),(11560690,'5586','Mr. THOMAS','WEKESA','Regular AccIndividuals','0','0710200995','0','NAKURU','NAKURU','PAINTER','Single','','','','','Regular Acc','Individuals','2016-12-15 08:42:48','null'),(27685847,'5722','Ms WANJA','RUTH JUMA','SUPER ACCOUNTINDIVIDUALS','0','0717655337','0','NAKURU','NAKURU','SHOES STALL OPPOSITE MUKO HOTEL','Single','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 17:06:01','null'),(13386506,'5748','Mr. ISAACK','OKEYO','Regular AccJoy','0','0724683675','0','NAKURU','NAKURU','CARPENTRY AND JOINERY','Married','','','','','Regular Acc','Joy','2016-12-14 12:50:04','null'),(25558039,'5804','Ms MERCY','MUTHONI WANJOHI','Regular AccJoy','0','0722121713','0','NAKURU','NAKURU','PROMOTIONS','Single','','','','','Regular Acc','Joy','2016-12-14 12:33:42','null'),(14588433,'5839','Mr. CHARLES','OCHIENG WESONGA','Regular AccBlessed','0','0729725870','0','NAKURU','NAKURU','TUK TUK DRIVER','Married','','','','','Regular Acc','Blessed','2016-12-08 17:38:48','null'),(24024715,'5922','Mr. ODERO','LINUS ODHIAMBO','SUPER ACCOUNTINDIVIDUALS','0','0716844450','0','NAKURU','NAKURU','BANKER AT KCB GILGIL','Married','','','','','SUPER ACCOUNT','INDIVIDUALS','2017-01-24 16:23:37','null'),(20427097,'5951','Ms Silvia','Awour','Regular AccBlessed','silvia@gmail.com','0727303219','nakuru','nakuru','nakuru','Ice Cream Vendor','Married','','','','','Regular Acc','Blessed','2016-12-08 16:07:03','null'),(21918640,'5994','Mr. MICHAEL','SEWE AJUMA','Regular AccBlessed','0','0722282082','0','NAKURU','NAKURU','TUK TUK DRIVER','Married','','','','','Regular Acc','Blessed','2016-12-08 17:42:29','null'),(20395229,'5995','Mr. SAMWEL ','KIROKO NATANI','SuperIndividual','0','0733870610','0','NAKURU','NAKURU','COCACOLA KIOSK OPERATOR','Married','','','','','Super','Individual','2017-02-02 15:22:51','null'),(10775207,'5999','Mr. SHADRACK','OTIENO','Regular AccBlessed','0','0712774075','0','NAKURU','NAKURU','JUA KALI','Divorced','','','','','Regular Acc','Blessed','2016-12-08 17:31:45','null');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'inua'
--

--
-- Dumping routines for database 'inua'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-12  9:42:46
