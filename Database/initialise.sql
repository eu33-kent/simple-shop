DROP DATABASE IF EXISTS simpleShop;

CREATE DATABASE simpleShop;
USE simpleShop;

CREATE TABLE users (
    uid INT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(20) UNIQUE NOT NULL CHECK (LENGTH(login) > 2),
    passHash VARCHAR(50) NOT NULL,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    balance DECIMAL(15,2) DEFAULT 0.00 CHECK (balance >= 0)
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
    name VARCHAR(50) NOT NULL CHECK (LENGTH(TRIM(name)) > 2),
    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
    imgPath VARCHAR(255)
);
CREATE TABLE orders (
	oid INT PRIMARY KEY AUTO_INCREMENT,
    uid INT NOT NULL,
    pid INT NOT NULL,
    timestamp DATETIME NOT NULL,
    FOREIGN KEY (uid) REFERENCES users(uid),
    FOREIGN KEY (pid) REFERENCES products(pid)
);

INSERT INTO users (login, passHash, firstName, lastName)
VALUES ('tricksy.', 'fba14b5274133422ebbe7c5c8496f47a', 'Elliot', 'Ursell');
INSERT INTO users (login, passHash, firstName, lastName)
VALUES ('test', 'fba14b5274133422ebbe7c5c8496f47a', 'test', 'test');

INSERT INTO products (name, price, imgPath)
VALUES ('NoVidIdea Graphics Card', 9999.98, 'https://assets.nvidia.partners/images/png/nvidia-geforce-rtx-3090.png');
INSERT INTO products (name, price, imgPath)
VALUES ('Pear aPhone 23XSI', 1452.71, 'https://ih1.redbubble.net/image.1027498293.4407/flat,750x1000,075,f.jpg');
INSERT INTO products (name, price, imgPath)
VALUES ('Freeddo', 0.05, 'https://upload.wikimedia.org/wikipedia/en/a/a1/Freddoaus.jpg');
INSERT INTO products (name, price, imgPath)
VALUES ('Nokeya Brick', 4.5, 'https://i.redd.it/3xzvk9eg7aj11.jpg');

INSERT INTO orders (uid, pid, timestamp)
VALUES (1, 1, NOW());