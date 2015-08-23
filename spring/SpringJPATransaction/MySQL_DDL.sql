In creditard database following are the DDL for user_details and transactions table
===================================================================================

CREATE TABLE `user_details` (
	`USER_ID` INT(11) NOT NULL AUTO_INCREMENT,
	`USER_NAME` VARCHAR(50) NOT NULL,
	`FIRST_NAME` VARCHAR(50) NOT NULL,
	`LAST_NAME` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`USER_ID`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=42
;


CREATE TABLE `transactions` (
	`TX_ID` INT(11) NOT NULL AUTO_INCREMENT,
	`USER_ID` INT(11) NOT NULL,
	`OUTSTANDING` DOUBLE NULL DEFAULT NULL,
	`PAID` DOUBLE NULL DEFAULT NULL,
	`BALANCE` DOUBLE NULL DEFAULT NULL,
	PRIMARY KEY (`TX_ID`),
	INDEX `FK__user_details` (`USER_ID`),
	CONSTRAINT `FK__user_details` FOREIGN KEY (`USER_ID`) REFERENCES `user_details` (`USER_ID`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=24
;