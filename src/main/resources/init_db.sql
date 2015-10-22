CREATE DATABASE BlackJack;

CREATE TABLE users (
  id int NOT NULL UNIQUE AUTO_INCREMENT,
  cash int (6),
  PRIMARY KEY (id)
);


CREATE TABLE players (
  id int NOT NULL IDENTITY,
  cash int
);

CREATE TABLE transactions {
  id int NOT NULL IDENTITY,
  player_id int NOT NULL,
  trans_type text(10),
  amount real,
  FOREIGN KEY(player_id)
  };