SET MODE MySQL;
SET IGNORECASE TRUE;
create SCHEMA currentcy;

CREATE TABLE `currentcy`.email (
  email VARCHAR(100) NOT NULL,
  active INT(11) NOT NULL DEFAULT '0',
  token VARCHAR(255) NOT NULL,
  code VARCHAR(6) NOT NULL,
  PRIMARY KEY (email,code),
  UNIQUE INDEX token_UNIQUE (token ASC)
 );
 
 CREATE TABLE `currentcy`.samples (
  id INT(11) NOT NULL AUTO_INCREMENT,
  code VARCHAR(6) NOT NULL,
  buy DOUBLE NOT NULL DEFAULT '0',
  sell DOUBLE NULL DEFAULT '0',
  date DATETIME NOT NULL,
  currency varchar(4) NOT NULL default 'USD',
  PRIMARY KEY (id),
  UNIQUE INDEX id_UNIQUE (id ASC));

  
CREATE TABLE `currentcy`.snapshot (
  code VARCHAR(6) NOT NULL,
  buy DOUBLE NOT NULL DEFAULT '0',
  sell DOUBLE NOT NULL DEFAULT '0',
  date DATETIME NOT NULL,
  prev_date DATETIME NOT NULL,
  prev_buy DOUBLE NOT NULL DEFAULT '0',
  prev_sell DOUBLE NOT NULL DEFAULT '0',
  currency varchar(4) NOT NULL DEFAULT 'USD',
  PRIMARY KEY (code,currency)
)


