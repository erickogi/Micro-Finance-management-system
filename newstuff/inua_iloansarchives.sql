-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-12 20:24:19
