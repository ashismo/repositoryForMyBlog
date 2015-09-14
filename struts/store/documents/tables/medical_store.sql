-- MySQL dump 10.13  Distrib 5.5.13, for Win32 (x86)
--
-- Host: localhost    Database: medical_store_final_db
-- ------------------------------------------------------
-- Server version	5.5.13

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
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `ATTACHMENT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TRANSACTION_DETAILS_ID` int(10) DEFAULT NULL,
  `ATTACHMENT_DESC` varchar(500) DEFAULT NULL,
  `ATTACHED_FILE` longblob,
  `FILE_NAME` varchar(100) DEFAULT NULL,
  `FILE_CONTENT_TYPE` varchar(100) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ATTACHMENT_ID`),
  KEY `FK__transaction_details` (`TRANSACTION_DETAILS_ID`),
  CONSTRAINT `FK__transaction_details` FOREIGN KEY (`TRANSACTION_DETAILS_ID`) REFERENCES `transaction_details` (`TRANSACTION_DETAILS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `COMPANY_ID` int(10) NOT NULL AUTO_INCREMENT,
  `COMPANY_NAME` varchar(100) DEFAULT NULL,
  `COMPANY_DESC` varchar(100) DEFAULT NULL,
  `COMPANY_ADDR1` varchar(100) DEFAULT NULL,
  `COMPANY_ADDR2` varchar(100) DEFAULT NULL,
  `STATE` varchar(20) DEFAULT NULL,
  `PIN` varchar(20) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `MOB1` varchar(20) DEFAULT NULL,
  `MOB2` varchar(20) DEFAULT NULL,
  `PHONE1` varchar(20) DEFAULT NULL,
  `PHONE2` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contacts`
--

DROP TABLE IF EXISTS `contacts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contacts` (
  `CONTACT_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CONTACT_NAME` varchar(100) DEFAULT NULL,
  `CONTACT_NO1` varchar(50) DEFAULT NULL,
  `CONTACT_NO2` varchar(50) DEFAULT NULL,
  `CONTACT_EMAIL` varchar(50) DEFAULT NULL,
  `CONTACT_FAX` varchar(50) DEFAULT NULL,
  `CONTACT_DESC` varchar(500) DEFAULT NULL,
  `CONTACT_DATE` date DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contacts`
--

LOCK TABLES `contacts` WRITE;
/*!40000 ALTER TABLE `contacts` DISABLE KEYS */;
/*!40000 ALTER TABLE `contacts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `CUSTOMER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CUSTOMER_NAME` varchar(50) DEFAULT NULL,
  `CUSTOMER_DESC` varchar(255) DEFAULT NULL,
  `CUSTOMER_ADDR1` varchar(100) DEFAULT NULL,
  `CUSTOMER_ADDR2` varchar(100) DEFAULT NULL,
  `STATE` varchar(20) DEFAULT NULL,
  `PIN` varchar(20) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `MOB1` varchar(20) DEFAULT NULL,
  `MOB2` varchar(20) DEFAULT NULL,
  `PHONE1` varchar(20) DEFAULT NULL,
  `PHONE2` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`CUSTOMER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctors` (
  `DOCTOR_ID` int(10) NOT NULL AUTO_INCREMENT,
  `DOCTOR_NAME` varchar(100) DEFAULT NULL,
  `DOCTOR_DESC` varchar(255) DEFAULT NULL,
  `DOCTOR_ADDR1` varchar(100) DEFAULT NULL,
  `DOCTOR_ADDR2` varchar(100) DEFAULT NULL,
  `REG_NO` varchar(100) DEFAULT NULL,
  `QUALIFICATION` varchar(100) DEFAULT NULL,
  `SPECIALITY` varchar(100) DEFAULT NULL,
  `STATE` varchar(50) DEFAULT NULL,
  `PIN` varchar(10) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `MOB1` varchar(20) DEFAULT NULL,
  `MOB2` varchar(20) DEFAULT NULL,
  `PHONE1` varchar(20) DEFAULT NULL,
  `PHONE2` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `DATE_OF_ASSOCIATION` date DEFAULT NULL,
  `DATE_OF_RELEASE` date DEFAULT NULL,
  `IS_ACTIVE` varchar(10) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`DOCTOR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice` (
  `INVOICE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `OWNER_ID` int(10) DEFAULT NULL,
  `CUSTOMER_ID` int(10) DEFAULT NULL,
  `DOCTOR_ID` int(10) DEFAULT NULL,
  `TOTAL_AMT` double DEFAULT NULL,
  `TOTAL_PAID` double DEFAULT NULL,
  `PAYMENT_MODE` varchar(50) DEFAULT NULL,
  `PURCHASE_DATE` date DEFAULT NULL,
  `CARD_NUMBER` varchar(25) DEFAULT NULL,
  `VAT` double DEFAULT NULL,
  `DISCOUNT` double DEFAULT NULL,
  `PRINT_INDICATOR` varchar(50) DEFAULT NULL,
  `BILL_NO` int(11) DEFAULT NULL,
  `DB_ADD_USER` int(10) DEFAULT NULL,
  `DB_UPD_USER` int(10) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`INVOICE_ID`),
  KEY `FK__owner` (`OWNER_ID`),
  KEY `FK__customer` (`CUSTOMER_ID`),
  KEY `FK__doctors` (`DOCTOR_ID`),
  CONSTRAINT `FK__customer` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__doctors` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctors` (`DOCTOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__owner` FOREIGN KEY (`OWNER_ID`) REFERENCES `owner` (`OWNER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invoice_details`
--

DROP TABLE IF EXISTS `invoice_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invoice_details` (
  `INVOICE_DETAILS_ID` int(10) NOT NULL AUTO_INCREMENT,
  `INVOICE_ID` int(10) DEFAULT NULL,
  `MEDICINE_DETAILS_ID` int(10) DEFAULT NULL,
  `SCHEDULE` varchar(100) DEFAULT NULL,
  `SOLDOUT_STOCK` int(11) DEFAULT NULL,
  `DB_ADD_USER` int(10) DEFAULT NULL,
  `DB_UPD_USER` int(10) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`INVOICE_DETAILS_ID`),
  KEY `FK__invoice` (`INVOICE_ID`),
  KEY `FK_invoice_details_medicine_details` (`MEDICINE_DETAILS_ID`),
  CONSTRAINT `FK_invoice_details_medicine_details` FOREIGN KEY (`MEDICINE_DETAILS_ID`) REFERENCES `medicine_details` (`MEDICINE_DETAILS_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__invoice` FOREIGN KEY (`INVOICE_ID`) REFERENCES `invoice` (`INVOICE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice_details`
--

LOCK TABLES `invoice_details` WRITE;
/*!40000 ALTER TABLE `invoice_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `med_rep`
--

DROP TABLE IF EXISTS `med_rep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `med_rep` (
  `MED_REP_ID` int(10) NOT NULL AUTO_INCREMENT,
  `WHOLESELLER_ID` int(10) DEFAULT '0',
  `MED_REP_NAME` varchar(100) DEFAULT '',
  `MED_REP_DESC` varchar(255) DEFAULT '',
  `MED_REP_ADDR1` varchar(100) DEFAULT '',
  `MED_REP_ADDR2` varchar(100) DEFAULT '',
  `STATE` varchar(20) DEFAULT '',
  `PIN` varchar(20) DEFAULT '',
  `FAX` varchar(20) DEFAULT '',
  `MOB1` varchar(20) DEFAULT '',
  `MOB2` varchar(20) DEFAULT '',
  `PHONE1` varchar(20) DEFAULT '',
  `PHONE2` varchar(20) DEFAULT '',
  `PAN_NO` varchar(20) DEFAULT '',
  `VOTER_ID_NO` varchar(20) DEFAULT '',
  `EMAIL` varchar(50) DEFAULT '',
  `DATE_OF_ASSOCIATION` date DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT '0',
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT '0',
  PRIMARY KEY (`MED_REP_ID`),
  KEY `FK_MED_REP_WholeSeller` (`WHOLESELLER_ID`),
  CONSTRAINT `FK_MED_REP_WholeSeller` FOREIGN KEY (`WHOLESELLER_ID`) REFERENCES `whole_seller` (`WHOLESELLER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `med_rep`
--

LOCK TABLES `med_rep` WRITE;
/*!40000 ALTER TABLE `med_rep` DISABLE KEYS */;
/*!40000 ALTER TABLE `med_rep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `MEDICINE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `MEDICINE_NAME` varchar(100) DEFAULT NULL,
  `COMPANY_ID` int(11) DEFAULT NULL,
  `MEDICINE_DESC` varchar(255) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`MEDICINE_ID`),
  KEY `MED_COMPANY` (`COMPANY_ID`),
  CONSTRAINT `MED_COMPANY` FOREIGN KEY (`COMPANY_ID`) REFERENCES `company` (`COMPANY_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine_details`
--

DROP TABLE IF EXISTS `medicine_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine_details` (
  `MEDICINE_DETAILS_ID` int(10) NOT NULL AUTO_INCREMENT,
  `MEDICINE_ID` int(10) DEFAULT NULL,
  `MED_REP_ID` int(10) DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  `UNIT_PRICE` double DEFAULT NULL,
  `SOLDOUT_UNIT_PRICE` double DEFAULT NULL,
  `MFG_DATE` date DEFAULT NULL,
  `EXP_DATE` date DEFAULT NULL,
  `BATCH_NAME` varchar(50) DEFAULT NULL,
  `MEDICINE_DESC` varchar(255) DEFAULT NULL,
  `SOLDOUT_STOCK` int(11) DEFAULT NULL,
  `MED_DOSE` varchar(50) DEFAULT NULL,
  `MED_WEIGHT` varchar(50) DEFAULT NULL,
  `MED_TYPE` varchar(50) DEFAULT NULL,
  `PURCHASE_DATE` date DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`MEDICINE_DETAILS_ID`),
  KEY `FK__medicine` (`MEDICINE_ID`),
  KEY `FK_medicine_details_med_rep` (`MED_REP_ID`),
  CONSTRAINT `FK_medicine_details_med_rep` FOREIGN KEY (`MED_REP_ID`) REFERENCES `med_rep` (`MED_REP_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__medicine` FOREIGN KEY (`MEDICINE_ID`) REFERENCES `medicine` (`MEDICINE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine_details`
--

LOCK TABLES `medicine_details` WRITE;
/*!40000 ALTER TABLE `medicine_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `medicine_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_details`
--

DROP TABLE IF EXISTS `order_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_details` (
  `ORDER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `MEDICINE_ID` int(10) DEFAULT '0',
  `MEDICINE_DETAILS` varchar(200) DEFAULT NULL,
  `ORDER_DATE` date DEFAULT NULL,
  `ORDER_EXECUTION_DATE` date DEFAULT NULL,
  `ORDER_DESC` varchar(500) DEFAULT NULL,
  `ORDER_STATUS` varchar(20) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`ORDER_ID`),
  KEY `FK_Order_medicine` (`MEDICINE_ID`),
  CONSTRAINT `FK_Order_medicine` FOREIGN KEY (`MEDICINE_ID`) REFERENCES `medicine` (`MEDICINE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_details`
--

LOCK TABLES `order_details` WRITE;
/*!40000 ALTER TABLE `order_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner`
--

DROP TABLE IF EXISTS `owner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner` (
  `OWNER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `OWNER_NAME` varchar(100) DEFAULT NULL,
  `OWNER_DESC` varchar(255) DEFAULT NULL,
  `OWNER_ADDR1` varchar(100) DEFAULT NULL,
  `OWNER_ADDR2` varchar(100) DEFAULT NULL,
  `SHOP_NO` varchar(100) DEFAULT NULL,
  `LICENCE_NO` varchar(100) DEFAULT NULL,
  `BABY_FOOD_LC_NO` varchar(100) DEFAULT NULL,
  `STATE` varchar(100) DEFAULT NULL,
  `PIN` varchar(100) DEFAULT NULL,
  `FAX` varchar(100) DEFAULT NULL,
  `MOB1` varchar(100) DEFAULT NULL,
  `MOB2` varchar(100) DEFAULT NULL,
  `PHONE1` varchar(100) DEFAULT NULL,
  `PHONE2` varchar(100) DEFAULT NULL,
  `EMAIL_ID` varchar(100) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `SHOP_NAME` varchar(100) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`OWNER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner`
--

LOCK TABLES `owner` WRITE;
/*!40000 ALTER TABLE `owner` DISABLE KEYS */;
/*!40000 ALTER TABLE `owner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `SCHEDULE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `SCHEDULE_DATE` date DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `WHOLESELLER_ID` int(11) DEFAULT NULL,
  `MED_REP_ID` int(11) DEFAULT NULL,
  `CUSTOMER_ID` int(11) DEFAULT NULL,
  `SCHEDULE_TIME` varchar(50) DEFAULT NULL,
  `SCHEDULE_DAY` varchar(50) DEFAULT NULL,
  `SCHEDULE_VALUE` varchar(50) DEFAULT NULL,
  `SCHEDULE_LOOKUP_ID` int(11) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`SCHEDULE_ID`),
  KEY `FK__doctors_schedule` (`DOCTOR_ID`),
  KEY `FK__whole_seller_schedule` (`WHOLESELLER_ID`),
  KEY `FK__med_rep_schedule` (`MED_REP_ID`),
  KEY `FK__customer_schedule` (`CUSTOMER_ID`),
  CONSTRAINT `FK__customer_schedule` FOREIGN KEY (`CUSTOMER_ID`) REFERENCES `customer` (`CUSTOMER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__doctors_schedule` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctors` (`DOCTOR_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__med_rep_schedule` FOREIGN KEY (`MED_REP_ID`) REFERENCES `med_rep` (`MED_REP_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__whole_seller_schedule` FOREIGN KEY (`WHOLESELLER_ID`) REFERENCES `whole_seller` (`WHOLESELLER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction_details`
--

DROP TABLE IF EXISTS `transaction_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction_details` (
  `TRANSACTION_DETAILS_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TOTAL_AMT` double DEFAULT NULL,
  `TOTAL_PAID` double DEFAULT NULL,
  `MED_REP_ID` int(11) DEFAULT NULL,
  `INVOICE_ID` int(11) DEFAULT NULL,
  `PAYMENT_DATE` date DEFAULT NULL,
  `PAYMENT_MODE` varchar(50) DEFAULT NULL,
  `CARD_NUMBER` varchar(50) DEFAULT NULL,
  `BUY_OR_SELL` varchar(50) DEFAULT NULL,
  `TRANSACTION_DESC` varchar(255) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`TRANSACTION_DETAILS_ID`),
  KEY `FK__med_rep1` (`MED_REP_ID`),
  KEY `FK__invoice1` (`INVOICE_ID`),
  CONSTRAINT `FK__invoice1` FOREIGN KEY (`INVOICE_ID`) REFERENCES `invoice` (`INVOICE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK__med_rep1` FOREIGN KEY (`MED_REP_ID`) REFERENCES `med_rep` (`MED_REP_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction_details`
--

LOCK TABLES `transaction_details` WRITE;
/*!40000 ALTER TABLE `transaction_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `USER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `EMAIL_ID` varchar(100) DEFAULT NULL,
  `MOBILE` varchar(20) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `DESCRIPTION` varchar(20) DEFAULT NULL,
  `ROLE` varchar(50) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(11) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(11) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'store','store','Store',NULL,NULL,NULL,NULL,NULL,'SuperAdmin','2012-11-25 12:01:57',0,'2012-11-25 12:01:59',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `whole_seller`
--

DROP TABLE IF EXISTS `whole_seller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `whole_seller` (
  `WHOLESELLER_ID` int(10) NOT NULL AUTO_INCREMENT,
  `WHOLESELLER_NAME` varchar(100) DEFAULT NULL,
  `WHOLESELLER_DESC` varchar(255) DEFAULT NULL,
  `WHOLESELLER_ADDR1` varchar(100) DEFAULT NULL,
  `WHOLESELLER_ADDR2` varchar(100) DEFAULT NULL,
  `STATE` varchar(20) DEFAULT NULL,
  `PIN` varchar(20) DEFAULT NULL,
  `FAX` varchar(20) DEFAULT NULL,
  `MOB1` varchar(20) DEFAULT NULL,
  `MOB2` varchar(20) DEFAULT NULL,
  `PHONE1` varchar(20) DEFAULT NULL,
  `PHONE2` varchar(20) DEFAULT NULL,
  `EMAIL_ID` varchar(50) DEFAULT NULL,
  `WEBSITE` varchar(100) DEFAULT NULL,
  `DB_ADD_TS` timestamp NULL DEFAULT NULL,
  `DB_ADD_USER` int(10) DEFAULT NULL,
  `DB_UPD_TS` timestamp NULL DEFAULT NULL,
  `DB_UPD_USER` int(10) DEFAULT NULL,
  PRIMARY KEY (`WHOLESELLER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `whole_seller`
--

LOCK TABLES `whole_seller` WRITE;
/*!40000 ALTER TABLE `whole_seller` DISABLE KEYS */;
/*!40000 ALTER TABLE `whole_seller` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-25 17:34:25
