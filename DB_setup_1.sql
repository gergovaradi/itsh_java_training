-- Created by Geri on 2017.08.15, use this to basic setup DB for BGG shop, exercise Projet

-- Create Database
CREATE DATABASE testdb;
USE testdb;

-- Create table User
CREATE TABLE user_account(
        id INTEGER not null AUTO_INCREMENT,
        name VARCHAR(30) not null,
        password VARCHAR(280) not null,
        salt VARCHAR(280) not null,
        primary key (id)
);

-- Insert some test data to user table

INSERT INTO user_account (name, password, salt)
VALUES ('tom','tom001', '1234');
 
insert into user_account (name, password, salt)
VALUES ('jerry', 'jerry001', '4321');

-- Create table product

CREATE TABLE products(
        id INTEGER not null,
        name VARCHAR(128) not null,
        price double precision not null,
        quantity INTEGER not null,
        primary key (id)
) ;

-- Insert some test data to table

INSERT INTO products (id, name, price, quantity)
values (1, 'TEST_Product1', 100, 230);
 
INSERT INTO products (id, name, price, quantity)
VALUES (2, 'TEST_Product2', 90, 400);

-- Create tables for Orders


CREATE TABLE Orders (
        id INTEGER not null,
        Date TIMESTAMP not null,
        product_id INTEGER not null,
        user_id INTEGER not null,
        quantity INTEGER not null
    );
    
CREATE TABLE User_information (
        id INTEGER not null AUTO_INCREMENT,
        Address varchar(255) not null,
        Email varchar(128) not null,
        Full_Name varchar(60) not null,
        Phone varchar(128) not null,
        primary key (id)
    );

-- Insert some test data to table    

INSERT INTO User_information (Address, Email, Full_Name, Phone)
VALUES ('testString', 'tom.01@gmail.com', 'Thomas', '0695382385' );

INSERT INTO User_information (Address, Email, Full_Name, Phone)
VALUES('TestString', 'jerry.01@gmail.com', 'Jerry', '0695382385' );

/*Select for userdata
SELECT user_account.name,password,salt,
userinformation.full_name,phone,address,email
FROM user_account JOIN user_information 
ON user_account.id = user_information.id;
*/
     
-- Create table for Billing

/* Minden számlán az alábbi adatokat kötelezõ feltüntetni:
- számla kibocsátás kelte
- számla sorszáma, amely a számlát egyértelmûen azonosítja
 - számlakiállító neve, címe, adószáma
 - vevõ neve, címe 
- értékesített termék/szolgáltatás megnevezése, mennyisége, nettó egységára (adó nélküli értéke)
 - számla nettó értéke (adó nélküli értéke)
 - az ÁFA százaléka és értéke */
 
/* CREATE TABLE BGG_Company_data(
        BGG_CompanyName varchar(128) not null,
        BGG_Address varchar(255) not null,
        BGG_tax_number INTEGER not null,
  );
 */
 CREATE TABLE Bill(
        Bill_Num INTEGER not null,
        Order_id INTEGER not null,
        Billing_Date datetime not null,
        Amount double precision not null,
        primary key (Bill_Num)
    );
 
  CREATE TABLE Seller(
      Name varchar(30) not null,
      id INTEGER not null,
      primary key (id)
    );   





    


