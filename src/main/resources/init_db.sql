CREATE DATABASE BlackJack;

CREATE TABLE users (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  cash int (6),
  PRIMARY KEY (id)
);
