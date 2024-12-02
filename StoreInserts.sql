Use Store
go
Insert Into Products (ID, Product_Name, Product_Type, Quantity, Price)
Values (1, 'Apple', 'Fruit', 100, 0.50),
(2, 'Banana', 'Fruit', 120, 0.30),
(3, 'Orange', 'Fruit', 80, 0.60),
(4, 'Milk', 'Dairy', 50, 1.20),
(5, 'Cheddar Cheese', 'Dairy', 40, 3.50),
(6, 'Bread', 'Bakery', 70, 1.00),
(7, 'Chicken Breast', 'Meat', 60, 5.00),
(8, 'Rice', 'Grains', 200, 1.10),
(9, 'Tomato', 'Vegetable', 90, 0.80),
(10, 'Pasta', 'Grains', 150, 1.30);
go
Insert Into Subsidiary(ID, Subsidiary_Name, Subsidiary_Address, Subsidiary_Phone)
Values (1, 'Store1', 'st.Street1', 20000000),
(2, 'Store2', 'st.Street2', 20000000);
go
Insert Into Products_and_Subsidiary
Values (1, 1, 1),
(2, 3, 1),
(3, 5, 1),
(4, 7, 1),
(5, 9, 1),
(6, 2, 2),
(7, 4, 2),
(8, 6, 2),
(9, 8, 2),
(10, 10, 2);
go
Insert Into Sale (ID, Product_ID, New_Price)
Select ID, ID as Product_ID, Price From Products
go
Update Sale	
	Set Sale_percent = 0
go
Select * from Products
go
Select * from Sold_Products
go
Select * from Subsidiary
go
Select * from Products_and_Subsidiary
go
Select * from Sale
go
Delete from Sale
go
Delete from Products
go
Delete from Subsidiary
go
Delete from Sold_Products
go
Drop View productsView
go
Create View productsView as
	Select p.ID as ProductID, p.Product_Name, p.Product_Type, p.Quantity, sale.New_Price as Price, sale.Sale_percent, s.ID as StoreID, s.Subsidiary_Name from Products p
		Join Products_and_Subsidiary ps on p.ID = ps.Product_ID
		Join Subsidiary s on ps.Subsidiary_ID = s.ID
		Left Outer Join Sale sale on p.ID = sale.Product_ID
go
go
Create Function maxProducts (@store_id int)
Returns table as
	Return
		Select ProductID, Product_Name, Product_Type, Quantity, Price, Sale_percent, Subsidiary_Name from productsView
			Where StoreID = @store_id and Quantity = (Select Max(Quantity) from productsView Where StoreID = @store_id)

go
Create Function storeProducts (@store_id int)
Returns table as
	Return
		Select ProductID, Product_Name, Product_Type, Quantity, Price, Sale_percent, Subsidiary_Name from productsView
			Where StoreID = @store_id

go
Drop View saleView
go
Create View saleView as
	Select ProductID, Product_Name, Product_Type, Quantity, Price, Sale_percent, Subsidiary_Name from productsView
		Where Sale_percent < 1
go
Drop View soldView
go
Create View soldView as
	Select s.ID as ProductID, s.Product_Name, s.Product_Type, s.Quantity, s.Price, sub.ID as StoreID, sub.Subsidiary_Name from Sold_Products s
		Join Products_and_Subsidiary ps on s.ID = ps.Product_ID
		Join Subsidiary sub on ps.Subsidiary_ID = sub.ID
		Where s.Quantity = (Select Max(Quantity) from Sold_Products)
go
Select * from productsView
go
Select * from soldView
go
Drop Function maxProducts
go
Select * from maxProducts(1)
go
Create User Lilian
For Login Lilian
go
EXEC sp_addrolemember 'db_owner', 'Lilian';
go
Drop Proc insertProducts
go
Create Proc insertProducts(
@product_id int,
@product_name varchar(100),
@product_type varchar(100),
@product_quantity int,
@product_price float,
@sub_id int,
@prod_sub_id int,
@sale_id int,
@sale float
)
as
Begin
	Insert Into Products
		Values (@product_id, @product_name, @product_type, @product_quantity, @product_price);
	Insert Into Products_and_Subsidiary
		Values (@prod_sub_id, @product_id, @sub_id)
	Insert Into Sale
		Values (@sale_id, @product_id, @sale, @product_price)
End
go
Drop Proc insertSold
go
Create Proc insertSold(
@product_id int,
@product_name varchar(100),
@product_type varchar(100),
@product_quantity int,
@product_price float
)
as
Begin
	If Exists (Select 1 From Sold_Products Where ID = @product_id)
		Begin
			Update Sold_Products
				Set Quantity = Quantity + @product_quantity, Price = Price + @product_price
					Where ID = @product_id
		End;
	Else
		Begin
			Insert Into Sold_Products
				Values (@product_id, @product_name, @product_type, @product_quantity, @product_price);
		End;
End
go
Drop Proc updateSale
go
Create Proc updateSale(
@product_id int,
@price float,
@sale_percentage float
)
as
Begin
	Update Sale
		Set Sale_percent = @sale_percentage, New_Price = @price - @price * @sale_percentage / 100
			Where Product_ID = @product_id
End
go
Drop Proc updateQuantity
go
Create Proc updateQuantity(
@product_id int,
@product_quantity int
)
as
Begin
	Update Products
		Set Quantity = @product_quantity
			Where ID = @product_id
End
go
Select * from Sale
go
Exec updateSale 1, 0.5, 30
go
Select * from Sale

Update Sale
	Set New_Price = 0.5
		Where Product_ID = 11
go
Insert Into Accounts
	Values (1, 'Lilian', 'lilianpass')
	go
Select * from Accounts

