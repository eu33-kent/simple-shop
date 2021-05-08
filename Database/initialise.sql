DROP DATABASE IF EXISTS simpleShop;

CREATE DATABASE simpleShop;
USE simpleShop;

DROP TABLE IF EXISTS logs;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(20) UNIQUE NOT NULL,
    passHash VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00
);
CREATE TABLE logs (
    action VARCHAR(255) NOT NULL, 
    timestamp DATETIME NOT NULL,
    uid INT NOT NULL,
    PRIMARY KEY (action, timestamp, uid),
    FOREIGN KEY (uid) REFERENCES users(uid)
);
CREATE TABLE products (
	pid INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(10,2) NOT NULL
);
CREATE TABLE orders (
	oid INT PRIMARY KEY AUTO_INCREMENT,
    uid INT,
    pid INT,
    FOREIGN KEY (uid) REFERENCES users(uid),
    FOREIGN KEY (pid) REFERENCES products(pid)
);

INSERT INTO users (login, passHash, firstName, lastName)
VALUES ('tricksy.', 'fba14b5274133422ebbe7c5c8496f47a', 'Elliot', 'Ursell');