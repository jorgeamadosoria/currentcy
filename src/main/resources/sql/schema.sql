CREATE TABLE currentcy.email (
  email VARCHAR(100) NOT NULL,
  active INT(11) NOT NULL DEFAULT '0',
  token VARCHAR(255) NOT NULL,
  PRIMARY KEY (email),
  UNIQUE INDEX token_UNIQUE (token ASC));
