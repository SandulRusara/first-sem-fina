drop database if exists BlendBuddy;
create database if not exists BlendBuddy;

use BlendBuddy;

create table user(
     userId int(20) not null auto_increment primary key ,
     userName varchar(50),
     password varchar(30),
     email varchar(60)

);

create table employee(
    employeeId int(20) not null auto_increment primary key ,
    employeeName varchar(25) ,
    employeeAddress text ,
    employeeContact varchar(14) not null ,
    userId int(20),
    foreign key (userId) references user (userId) on DELETE cascade on UPDATE cascade

);

create table customer(
    customerId int(20) not null auto_increment primary key ,
    customerName varchar(50),
    customerContact varchar(12),
    userId int(20),
    foreign key (userId) references user (userId) on DELETE cascade on UPDATE cascade
);

create table orders(
    orderId int(20) not null auto_increment primary key,
    orderPayment varchar(50),
    orderType varchar(30),
    orderDate date,
    customerId int(20),
    foreign key (customerId) references customer (customerId) on DELETE cascade on UPDATE cascade
);

create table item(
    itemId int(20) not null auto_increment primary key ,
    itemCategory varchar(40),
    itemName varchar(35),
    itemPrice varchar(40)
);

create table orderItemDetails(
    orderId int(20),
    foreign key (orderId) references orders (orderId) on DELETE cascade on UPDATE cascade ,
    itemId int(20),
    foreign key (itemId) references item (itemId) on DELETE cascade on UPDATE cascade
);

use BlendBuddy;

create table ingredient(
    ingredientId int(20) not null auto_increment primary key ,
    ingredientName varchar(30),
    qtyOnHand int(50)

);

create table itemIngredientDetails(
    itemId int(20),
    foreign key (itemId) references item (itemId) on DELETE cascade on UPDATE cascade ,
    ingredientId int(20),
    foreign key (ingredientId) references ingredient (ingredientId) on DELETE cascade on UPDATE cascade
);

create table supplier(
    supplierId int(20) not null auto_increment primary key ,
    supplierName varchar(30),
    supplierAddress text,
    supplierContact varchar(12)
);

create table supply(
    supplierId int(20),
    foreign key (supplierId) references supplier (supplierId) on DELETE cascade on UPDATE cascade ,
    ingredientId int(20),
    foreign key (ingredientId) references ingredient (ingredientId) on DELETE cascade on UPDATE cascade ,
    date date,
    qty int(100)
);

insert into user values (0,'a','a','a');

