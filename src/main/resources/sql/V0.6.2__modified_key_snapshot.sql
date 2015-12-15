ALTER TABLE `currentcy`.`snapshot` 
CHANGE COLUMN `currency` `currency` VARCHAR(4) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`code`, `currency`);

ALTER TABLE `currentcy`.`snapshot` 
DROP INDEX `code_UNIQUE` ;