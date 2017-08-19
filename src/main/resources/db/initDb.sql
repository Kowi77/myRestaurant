DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS restaurants;

CREATE TABLE restaurants
(
  restId INTEGER PRIMARY KEY AUTO_INCREMENT,
  name   TEXT NOT NULL
);

CREATE TABLE dishes
(
  dishId       INTEGER PRIMARY KEY AUTO_INCREMENT,
  description TEXT NOT NULL,
  price        INTEGER NOT NULL,
  restId       INTEGER,
  FOREIGN KEY (restId) REFERENCES restaurants (restId) ON DELETE CASCADE
);

CREATE TABLE users (
  userId       INTEGER PRIMARY KEY AUTO_INCREMENT,
  name         TEXT NOT NULL,
  restId       INTEGER,
  FOREIGN KEY (restId) REFERENCES restaurants (restId) ON DELETE CASCADE
);