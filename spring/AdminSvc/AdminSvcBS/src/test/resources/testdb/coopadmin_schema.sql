USE `coopadmin_unittest`;
DELIMITER $$
DROP PROCEDURE IF EXISTS truncateTables $$
CREATE PROCEDURE truncateTables()
BEGIN

  DROP TEMPORARY TABLE IF EXISTS tempTbl;
  CREATE TEMPORARY TABLE IF NOT EXISTS tempTbl  (
    `query` VARCHAR(50)
  );
  
	INSERT INTO tempTbl SELECT CONCAT('truncate table ',table_name,';')
	FROM INFORMATION_SCHEMA.TABLES
	WHERE TABLE_SCHEMA = 'coopadmin_unittest'
	AND TABLE_TYPE = 'BASE TABLE';
	
	select * from tempTbl;
END $$
DELIMITER ;

--CALL truncateTables();
