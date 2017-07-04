CREATE TABLE users (
    id INT UNIQUE NOT NULL AUTO_INCREMENT,
    userName VARCHAR(255) PRIMARY KEY NOT NULL,
    password VARCHAR(255) NOT NULL);

insert into users(userName, password) values ('picimaci', MD5('almaalma'));

CREATE TABLE usersInformation (
    userId INT PRIMARY KEY NOT NULL,
    firstName VARCHAR(255) NOT NULL,
    lastName VARCHAR(255) NOT NULL,
    regDate DATE NOT NULL,
    emailAddress VARCHAR(255) NOT NULL);

insert into usersInformation(userId, firstName, lastName, regDate, emailAddress) values ((SELECT MAX(id) FROM users), 'Péter', 'Mackó', CURDATE(), 'picimaci@freemail.hu');

CREATE TABLE usersSkill (
    userId INT PRIMARY KEY NOT NULL,
    phone VARCHAR(12) NOT NULL,
    education VARCHAR(20) NOT NULL,
    englishLevel VARCHAR(20) NOT NULL,
    age VARCHAR(10) NOT NULL,
    newsLetter VARCHAR(3) NOT NULL);

insert into usersSkill(userId, phone, education, englishLevel, age, newsLetter) values ((SELECT MAX(id) FROM users), '+36701231123', 'Egyéb', 'Alapfok', '26-35', 'on');

CREATE TABLE emailAddresses (
    id INT UNIQUE NOT NULL AUTO_INCREMENT,
    emailAddress VARCHAR(255) PRIMARY KEY NOT NULL);

insert into emailAddresses(emailAddress) values ('dankopeti12@freemail.hu');