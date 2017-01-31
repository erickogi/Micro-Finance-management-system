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
-- Table structure for table `accounttypes`
--

DROP TABLE IF EXISTS `accounttypes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounttypes` (
  `autoid` int(100) NOT NULL AUTO_INCREMENT,
  `id` varchar(100) NOT NULL,
  `name` varchar(70) NOT NULL,
  `rate` varchar(100) NOT NULL,
  `appfee` varchar(100) NOT NULL,
  `penalt` varchar(100) NOT NULL,
  `balance` varchar(100) NOT NULL,
  `ratePer` varchar(100) NOT NULL DEFAULT '0',
  `rateTime` varchar(100) NOT NULL DEFAULT '0',
  `applicable` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`autoid`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounttypes`
--

LOCK TABLES `accounttypes` WRITE;
/*!40000 ALTER TABLE `accounttypes` DISABLE KEYS */;
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
  `givenOn` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paidon` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`autoid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loansarchives`
--

LOCK TABLES `loansarchives` WRITE;
/*!40000 ALTER TABLE `loansarchives` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orgaccount`
--

LOCK TABLES `orgaccount` WRITE;
/*!40000 ALTER TABLE `orgaccount` DISABLE KEYS */;
/*!40000 ALTER TABLE `orgaccount` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_user`
--

LOCK TABLES `system_user` WRITE;
/*!40000 ALTER TABLE `system_user` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useracctypes`
--

LOCK TABLES `useracctypes` WRITE;
/*!40000 ALTER TABLE `useracctypes` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroups`
--

LOCK TABLES `usergroups` WRITE;
/*!40000 ALTER TABLE `usergroups` DISABLE KEYS */;
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
INSERT INTO `users` VALUES (450,'2075','Mr. tr','rty','Regular Acc1','errtr','789','try','ry','ryt','ryturytu','Single','','','','','Regular Acc','1','2016-12-22 12:53:08','null'),(60,'2466','null Mr. eric','kogi','Regular Acc1','rtf','12','tyu','yii','hjk','ghj','Single','','','','','Regular Acc','1','2017-01-17 14:26:33','null'),(45,'2907','Mr. eric','kogi','Regular Acc1','erickogi14@gmail.com','o714406984','48 Ithanga','Muranga','Thika','Developer','Single','','','','','Regular Acc','1','2016-12-22 08:55:12','C:\\Users\\kimani kogi\\Pictures\\study.png'),(8,'3569','null gg','jk','Regular Acc1','','8','jj','kk','kk','jj','Single','','','','','Regular Acc','1','2016-12-16 08:26:34','null'),(70,'3787','Mr. john','kamau','Regular Acc1','john@gmail.com','071234567','87 eldoret','uasin gishu','keses','lecturer','Single','','','','','Regular Acc','1','2017-01-29 07:47:33','C:\\Users\\kimani kogi\\Pictures\\web.png'),(4,'4226','Mr. we','e','Regular Acc1','r','4','r','r','r','r','Single','','','','','Regular Acc','1','2016-12-08 14:44:47','null'),(20,'5013','Mr. Peter','Mbaluka','Regular Acc1','peter@yahoo.com','0730987654','67 ithanga','Makueni','Makueni','Farmer','Single','','','','','Regular Acc','1','2017-01-29 07:53:56','C:\\Users\\kimani kogi\\Pictures\\oop.jpg'),(30,'5355','Mr. Kyambi','Dominic','Regular Acc1','kyambo@gmail.com','072378647','45 uf','Machakos','Machakos','Student','Single','','','','','Regular Acc','1','2017-01-25 11:02:18','C:\\Users\\kimani kogi\\Pictures\\study.png');
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

-- Dump completed on 2017-02-07 12:46:42
