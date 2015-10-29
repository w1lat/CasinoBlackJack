CREATE DATABASE BlackJack;

CREATE SEQUENCE auto_id_players;

CREATE TABLE players (
  id int NOT NULL DEFAULT nextval('auto_id_players'),
  cash int,
  PRIMARY KEY (id)
);

CREATE SEQUENCE auto_id_transactions;

CREATE TABLE transactions (
  id int NOT NULL DEFAULT nextval('auto_id_transactions'),
  player_id int NOT NULL references players(id),
  trans_type varchar(10),
  amount real,
  PRIMARY KEY (id)
);
