DROP DATABASE IF EXISTS simpleShop;

CREATE DATABASE simpleShop;
USE simpleShop;

DROP TABLE IF EXISTS logs;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(20) UNIQUE NOT NULL,
    passHash VARCHAR(50),
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);
CREATE TABLE logs (
    action VARCHAR(255) NOT NULL, 
    timestamp DATETIME NOT NULL,
    uid INT NOT NULL,
    PRIMARY KEY (action, timestamp, uid),
    FOREIGN KEY (uid) REFERENCES users(uid)
);

INSERT INTO users
VALUES (1, 'tricksy.', 'fba14b5274133422ebbe7c5c8496f47a', 'Elliot', 'Ursell');