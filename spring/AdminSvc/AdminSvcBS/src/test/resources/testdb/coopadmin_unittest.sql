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

-- Dumping database structure for coopadmin
DROP DATABASE IF EXISTS `coopadmin_unittest`;
CREATE DATABASE IF NOT EXISTS `coopadmin_unittest` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `coopadmin_unittest`;


-- Dumping structure for table coopadmin.address
DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `address_name` varchar(50) DEFAULT NULL,
  `address_type` varchar(10) DEFAULT NULL COMMENT 'HOME or OFFICE',
  `address_line1` varchar(255) DEFAULT NULL,
  `address_line2` varchar(255) DEFAULT NULL,
  `address_line3` varchar(255) DEFAULT NULL,
  `pin` varchar(10) NOT NULL,
  `dist_id` int(11) NOT NULL,
  `email_id1` varchar(50) DEFAULT NULL,
  `email_id2` varchar(50) DEFAULT NULL,
  `phone_no1` varchar(15) NOT NULL,
  `phone_no2` varchar(15) DEFAULT NULL,
  `website` varchar(50) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`address_id`),
  KEY `FK_address_district_master` (`dist_id`),
  CONSTRAINT `FK_address_district_master` FOREIGN KEY (`dist_id`) REFERENCES `district_master` (`dist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.branch_address
DROP TABLE IF EXISTS `branch_address`;
CREATE TABLE IF NOT EXISTS `branch_address` (
  `company_addr_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) NOT NULL,
  `address_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`company_addr_id`),
  KEY `FK_company_address_company_master` (`branch_id`),
  KEY `FK_company_address_address` (`address_id`),
  CONSTRAINT `FK_company_address_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FK_company_address_company_master` FOREIGN KEY (`branch_id`) REFERENCES `branch_master` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.branch_license_dtl
DROP TABLE IF EXISTS `branch_license_dtl`;
CREATE TABLE IF NOT EXISTS `branch_license_dtl` (
  `branch_license_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `grace_day` int(11) DEFAULT '0',
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`branch_license_id`),
  KEY `FK_branch_license_dtl_branch_master` (`branch_id`),
  CONSTRAINT `FK_branch_license_dtl_branch_master` FOREIGN KEY (`branch_id`) REFERENCES `branch_master` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.branch_master
DROP TABLE IF EXISTS `branch_master`;
CREATE TABLE IF NOT EXISTS `branch_master` (
  `branch_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `bank_name` varchar(50) NOT NULL,
  `branch_name` varchar(50) NOT NULL,
  `ifsc_code` varchar(50) DEFAULT NULL,
  `micr_code` varchar(50) DEFAULT NULL,
  `db_name` varchar(50) NOT NULL,
  `context_root` varchar(50) NOT NULL,
  `email1` varchar(50) DEFAULT NULL,
  `email2` varchar(50) DEFAULT NULL,
  `phone1` varchar(15) DEFAULT NULL,
  `phone2` varchar(15) DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.branch_rule
DROP TABLE IF EXISTS `branch_rule`;
CREATE TABLE IF NOT EXISTS `branch_rule` (
  `branch_rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) NOT NULL DEFAULT '0',
  `rule_id` int(11) NOT NULL DEFAULT '0',
  `rule_value` varchar(50) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`branch_rule_id`),
  KEY `FK_company_rule_company_master` (`branch_id`),
  KEY `FK_company_rule_rule_master` (`rule_id`),
  CONSTRAINT `FK_company_rule_company_master` FOREIGN KEY (`branch_id`) REFERENCES `branch_master` (`branch_id`),
  CONSTRAINT `FK_company_rule_rule_master` FOREIGN KEY (`rule_id`) REFERENCES `rule_master` (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.country_master
DROP TABLE IF EXISTS `country_master`;
CREATE TABLE IF NOT EXISTS `country_master` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_code` varchar(10) NOT NULL,
  `country_name` varchar(100) NOT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`country_id`),
  UNIQUE KEY `country_code` (`country_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.district_master
DROP TABLE IF EXISTS `district_master`;
CREATE TABLE IF NOT EXISTS `district_master` (
  `dist_id` int(11) NOT NULL AUTO_INCREMENT,
  `state_id` int(11) NOT NULL,
  `district_code` varchar(6) DEFAULT NULL,
  `district_name` varchar(100) NOT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dist_id`),
  UNIQUE KEY `state_id_district_code` (`state_id`,`district_code`),
  KEY `FK_district_master_state_master` (`state_id`),
  CONSTRAINT `FK_district_master_state_master` FOREIGN KEY (`state_id`) REFERENCES `state_master` (`state_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.module_master
DROP TABLE IF EXISTS `module_master`;
CREATE TABLE IF NOT EXISTS `module_master` (
  `module_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(50) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`module_id`),
  UNIQUE KEY `module_name` (`module_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.permission_master
DROP TABLE IF EXISTS `permission_master`;
CREATE TABLE IF NOT EXISTS `permission_master` (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `permission` varchar(50) NOT NULL COMMENT 'READ,WRITE,DELETE',
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `module_id_permission` (`module_id`,`permission`),
  KEY `FK_permission_master_module_master` (`module_id`),
  CONSTRAINT `FK_permission_master_module_master` FOREIGN KEY (`module_id`) REFERENCES `module_master` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.role_master
DROP TABLE IF EXISTS `role_master`;
CREATE TABLE IF NOT EXISTS `role_master` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `role_description` varchar(100) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modify_user` varchar(20) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`),
  KEY `FK_role_master_company_master` (`branch_id`),
  CONSTRAINT `FK_role_master_company_master` FOREIGN KEY (`branch_id`) REFERENCES `branch_master` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.role_permission
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_perm_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_perm_id`),
  KEY `FK_role_permission_role_master` (`role_id`),
  KEY `FK_role_permission_permission_master` (`permission_id`),
  CONSTRAINT `FK_role_permission_permission_master` FOREIGN KEY (`permission_id`) REFERENCES `permission_master` (`permission_id`),
  CONSTRAINT `FK_role_permission_role_master` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.rule_master
DROP TABLE IF EXISTS `rule_master`;
CREATE TABLE IF NOT EXISTS `rule_master` (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) NOT NULL,
  `rule_name` varchar(50) NOT NULL,
  `rule_description` varchar(100) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rule_id`),
  UNIQUE KEY `module_id_rule_name` (`module_id`,`rule_name`),
  KEY `FK_rule_master_module_master` (`module_id`),
  CONSTRAINT `FK_rule_master_module_master` FOREIGN KEY (`module_id`) REFERENCES `module_master` (`module_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.rule_master_values
DROP TABLE IF EXISTS `rule_master_values`;
CREATE TABLE IF NOT EXISTS `rule_master_values` (
  `rule_value_id` int(11) NOT NULL AUTO_INCREMENT,
  `rule_id` int(11) NOT NULL,
  `rule_value` varchar(50) DEFAULT NULL,
  `rule_value_description` varchar(50) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rule_value_id`),
  UNIQUE KEY `rule_id_rule_value` (`rule_id`,`rule_value`),
  KEY `FK_rule_master_values_rule_master` (`rule_id`),
  CONSTRAINT `FK_rule_master_values_rule_master` FOREIGN KEY (`rule_id`) REFERENCES `rule_master` (`rule_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.security_questions
DROP TABLE IF EXISTS `security_questions`;
CREATE TABLE IF NOT EXISTS `security_questions` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(100) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.state_master
DROP TABLE IF EXISTS `state_master`;
CREATE TABLE IF NOT EXISTS `state_master` (
  `state_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) NOT NULL,
  `state_code` varchar(5) DEFAULT NULL,
  `state_name` varchar(100) NOT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`state_id`),
  UNIQUE KEY `country_id_state_code` (`country_id`,`state_code`),
  KEY `FK_state_master_country_master` (`country_id`),
  CONSTRAINT `FK_state_master_country_master` FOREIGN KEY (`country_id`) REFERENCES `country_master` (`country_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `branch_id` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email1` varchar(50) DEFAULT NULL,
  `email2` varchar(50) DEFAULT NULL,
  `phone1` varchar(50) NOT NULL,
  `phone2` varchar(50) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `end_date` timestamp NULL DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_user` varchar(20) DEFAULT NULL,
  `modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  KEY `FK_user_company_master` (`branch_id`),
  CONSTRAINT `FK_user_company_master` FOREIGN KEY (`branch_id`) REFERENCES `branch_master` (`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.user_credential
DROP TABLE IF EXISTS `user_credential`;
CREATE TABLE IF NOT EXISTS `user_credential` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(100) NOT NULL,
  `transaction_pwd` varchar(100) DEFAULT NULL,
  `last_login` timestamp NULL DEFAULT NULL,
  `last_unsuccessful_login` timestamp NULL DEFAULT NULL,
  `unsuccessful_login_count` int(11) DEFAULT '0',
  `successful_login` int(11) DEFAULT '0',
  `delete_ind` varchar(4) DEFAULT '0',
  `create_user` varchar(20) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(20) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_user_credential_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.user_credential_otp
DROP TABLE IF EXISTS `user_credential_otp`;
CREATE TABLE IF NOT EXISTS `user_credential_otp` (
  `user_id` int(11) NOT NULL,
  `otp` varchar(10) DEFAULT NULL,
  `otp_resend_counter` int(11) NOT NULL DEFAULT '0',
  `start_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `FK_user_credential_otp_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.user_role
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_role_id`),
  KEY `FK_user_role_user` (`user_id`),
  KEY `FK_user_role_role_master` (`role_id`),
  CONSTRAINT `FK_user_role_role_master` FOREIGN KEY (`role_id`) REFERENCES `role_master` (`role_id`),
  CONSTRAINT `FK_user_role_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.


-- Dumping structure for table coopadmin.user_sequrity_question
DROP TABLE IF EXISTS `user_sequrity_question`;
CREATE TABLE IF NOT EXISTS `user_sequrity_question` (
  `user_seq_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  `answer` varchar(50) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date DEFAULT NULL,
  `delete_ind` varchar(4) DEFAULT NULL,
  `create_user` varchar(50) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_user` varchar(50) DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_seq_id`),
  KEY `FK_user_sequrity_question_user` (`user_id`),
  KEY `FK_user_sequrity_question_security_questions` (`question_id`),
  CONSTRAINT `FK_user_sequrity_question_security_questions` FOREIGN KEY (`question_id`) REFERENCES `security_questions` (`question_id`),
  CONSTRAINT `FK_user_sequrity_question_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
