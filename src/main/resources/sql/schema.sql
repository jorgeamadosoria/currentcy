CREATE TABLE email (
  email VARCHAR(100) NOT NULL,
  active INT DEFAULT  '0' NOT NULL,
  token VARCHAR(255) NOT NULL,
  PRIMARY KEY (email));

CREATE TABLE snapshot (
  code VARCHAR(6) NOT NULL,
  buy DOUBLE DEFAULT '0' NOT NULL,
  sell DOUBLE DEFAULT '0' NOT NULL,
  date DATETIME NOT NULL,
  prev_date DATETIME NOT NULL,
  prev_buy DOUBLE DEFAULT '0' NOT NULL,
  prev_sell DOUBLE DEFAULT '0' NOT NULL,
  currency varchar(4) NOT NULL,
  PRIMARY KEY (code,currency));
  
  
  
CREATE TABLE samples (
  id INTEGER IDENTITY,
  code VARCHAR(6) NOT NULL,
  currency varchar(4) default 'USD' NOT NULL,
  buy DOUBLE DEFAULT '0' NOT NULL,
  sell DOUBLE DEFAULT '0' NOT NULL,
  date DATETIME NOT NULL
);
