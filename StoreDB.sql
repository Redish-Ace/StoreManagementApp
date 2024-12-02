use master
go
if exists(select 'true' from sys.databases where name = 'Store')
begin
	drop database Store
end
go
create database Store
go
alter authorization on database:: Store to sa
go
use Store
go
set dateformat dmy
go
create table Products(
	ID int primary key,
	Product_Name varchar(100),
	Product_Type varchar(100),s
	Quantity int,
	Price float
)
go
create table Subsidiary(
	ID int primary key,
	Subsidiary_Name varchar(100),
	Subsidiary_Address varchar(100),
	Subsidiary_Phone bigint
)
go
create table Products_and_Subsidiary(
	ID int primary key,
	Product_ID int foreign key references Products(ID) on delete cascade,
	Subsidiary_ID int foreign key references Subsidiary(ID) on delete cascade
)
go
create table Sale(
	ID int primary key,
	Product_ID int foreign key references Products(ID) on delete cascade,
	Sale_percent float,
	New_Price float
)
go
create table Sold_Products(
	ID int primary key,
	Product_Name varchar(100),
	Product_Type varchar(100),
	Quantity int,
	Price float
)
go
create table Accounts(
	ID int primary key,
	Username varchar(100),
	Account_Password varchar(100) unique,
)
