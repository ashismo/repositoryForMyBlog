-- --------------------------------------------------------
-- Host:                         localhost
-- Server version:               5.6.22-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for cooperative
DROP DATABASE IF EXISTS `cooperative`;
CREATE DATABASE IF NOT EXISTS `cooperative` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `cooperative`;


-- Dumping structure for table cooperative.address
DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `addr_line1` varchar(50) NOT NULL,
  `addr_line2` varchar(50) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `country` varchar(20) NOT NULL,
  `pin_no` varchar(10) NOT NULL,
  `addr_type` varchar(10) NOT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.address: ~0 rows (approximately)
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;


-- Dumping structure for table cooperative.role_master
DROP TABLE IF EXISTS `role_master`;
CREATE TABLE IF NOT EXISTS `role_master` (
  `role_name` varchar(50) NOT NULL,
  `role_description` varchar(100) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.role_master: ~4 rows (approximately)
/*!40000 ALTER TABLE `role_master` DISABLE KEYS */;
REPLACE INTO `role_master` (`role_name`, `role_description`, `start_date`, `end_date`, `create_user`, `create_date`, `modify_user`, `modify_date`) VALUES
	('admin', 'admin role', NULL, NULL, NULL, NULL, NULL, NULL),
	('auditor', 'Auditor', NULL, NULL, NULL, NULL, NULL, NULL),
	('supervisor', 'Supervisor', NULL, NULL, NULL, NULL, NULL, NULL),
	('user', 'Normal User', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `role_master` ENABLE KEYS */;


-- Dumping structure for table cooperative.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email1` varchar(50) DEFAULT NULL,
  `email2` varchar(50) DEFAULT NULL,
  `phone1` varchar(50) NOT NULL,
  `phone2` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `modify_user` varchar(20) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.user: ~0 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`user_name`, `first_name`, `middle_name`, `last_name`, `email1`, `email2`, `phone1`, `phone2`, `start_date`, `end_date`, `create_user`, `create_date`, `modify_user`, `modify_date`) VALUES
	('ashish', 'Ashish', 'Kumar', 'Mondal', 'ashismo@gmail.com', NULL, '9830525559', NULL, '2015-11-15', NULL, 'ashish', '2015-11-15 00:23:21', NULL, NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table cooperative.user_address
DROP TABLE IF EXISTS `user_address`;
CREATE TABLE IF NOT EXISTS `user_address` (
  `user_name` varchar(50) NOT NULL,
  `address_id` int(11) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`,`address_id`),
  KEY `FK_user_address_address` (`address_id`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `FK_user_address_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_address_user` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.user_address: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;


-- Dumping structure for table cooperative.user_credential
DROP TABLE IF EXISTS `user_credential`;
CREATE TABLE IF NOT EXISTS `user_credential` (
  `user_name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `last_unsuccessful_login` timestamp NULL DEFAULT NULL,
  `unsuccessful_login_count` timestamp NULL DEFAULT NULL,
  `create_user` varchar(20) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `update_user` varchar(20) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  CONSTRAINT `FK_user_credential_user` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.user_credential: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_credential` DISABLE KEYS */;
REPLACE INTO `user_credential` (`user_name`, `password`, `last_login`, `last_unsuccessful_login`, `unsuccessful_login_count`, `create_user`, `create_date`, `update_user`, `update_date`) VALUES
	('ashish', '$2a$10$1Mmi61NcCubMNU9NJwiR4OYI8dOMCFOE3oc9VoKO8mqn3.0Kv.M6a', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `user_credential` ENABLE KEYS */;


-- Dumping structure for table cooperative.user_credential_otp
DROP TABLE IF EXISTS `user_credential_otp`;
CREATE TABLE IF NOT EXISTS `user_credential_otp` (
  `user_name` varchar(50) NOT NULL,
  `otp` varchar(10) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`),
  CONSTRAINT `FK_user_credential_otp_user` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.user_credential_otp: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_credential_otp` DISABLE KEYS */;
REPLACE INTO `user_credential_otp` (`user_name`, `otp`, `start_date`, `end_date`) VALUES
	('ashish', '123456', NULL, NULL);
/*!40000 ALTER TABLE `user_credential_otp` ENABLE KEYS */;


-- Dumping structure for table cooperative.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_name` varchar(50) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT NULL,
  `modify_user` varchar(50) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_name`,`role_name`),
  KEY `FK_user_role_role_master` (`role_name`),
  KEY `user_name` (`user_name`),
  CONSTRAINT `FK_user_role_role_master` FOREIGN KEY (`role_name`) REFERENCES `role_master` (`role_name`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_name`) REFERENCES `user` (`user_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table cooperative.user_role: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
REPLACE INTO `user_role` (`user_name`, `role_name`, `start_date`, `end_date`, `create_user`, `create_date`, `modify_user`, `modify_date`) VALUES
	('ashish', 'admin', NULL, NULL, NULL, NULL, NULL, NULL),
	('ashish', 'user', NULL, NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
