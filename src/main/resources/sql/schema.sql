CREATE TABLE IF NOT EXISTS `currentcy`.`email` (
  `email` VARCHAR(100) NOT NULL,
  `active` INT(11) NOT NULL DEFAULT '0',
  `token` VARCHAR(255) NOT NULL,
  `code` VARCHAR(6) NOT NULL;
  PRIMARY KEY (`email`),
  UNIQUE INDEX `token_UNIQUE` (`token` ASC))
ENGINE = MEMORY
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `currentcy`.`samples` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(6) NOT NULL,
  `buy` DOUBLE NOT NULL DEFAULT '0',
  `sell` DOUBLE NULL DEFAULT '0',
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = MEMORY
AUTO_INCREMENT = 846
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `currentcy`.`snapshot` (
  `code` VARCHAR(6) NOT NULL,
  `buy` DOUBLE NOT NULL DEFAULT '0',
  `sell` DOUBLE NOT NULL DEFAULT '0',
  `date` DATETIME NOT NULL,
  `prev_date` DATETIME NOT NULL,
  `prev_buy` DOUBLE NOT NULL DEFAULT '0',
  `prev_sell` DOUBLE NOT NULL DEFAULT '0',
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC))
ENGINE = MEMORY
DEFAULT CHARACTER SET = utf8