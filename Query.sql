use JavaWebDB2;

UPDATE [User] SET fullname ='Le Van Luyen', password='123' WHERE username='thailv'

SELECT ProID, ProName, Price
FROM Products
Delete from Products where Products.proid='prod1';
--Product
SELECT * FROM [Products]
SELECT ProID, ProName, Price, [image] FROM [Products] WHERE [status] = 1
SELECT ProID, ProName, Price, [image], [status] FROM Products WHERE ProName like 
UPDATE [Products] SET [ProName]='Birthday', [Price]=14, [image]='prod4.jpg', [status]=0 WHERE [ProID]='prod4'
SELECT COUNT (*) AS total FROM [Products]

--Staff
SELECT * FROM [Staffs]
DELETE FROM [Staffs]


--Customers
SELECT * FROM Customers
SELECT COUNT(*) FROM Customers
INSERT INTO [Customers] VALUES ('CUS0396162108', 'Cao Dinh Day', 0396162108, 'Quan Trung')
SELECT cusid FROM Customers WHERE cusSDT = '0396162108'
SELECT COUNT (cusID) FROM [Customers]
SELECT * FROM [Customers] Where [Customers].cusid = 'CUS0396162108';
DELETE FROM Customers

--Order
SELECT * FROM [Orders]
SELECT GETDATE();
SELECT COUNT (*) AS total FROM [Orders]
INSERT INTO [Orders] VALUES('1', GETDATE(), sqlTime(), 1, (SELECT cusid FROM Customers WHERE cusSDT = '0396162108'))
SELECT orderid, orderDate, [timestamp] FROM [Orders] WHERE [Orders].status = 0
SELECT * FROM [Orders] WHERE [Orders].orderid = '20190620003109'
UPDATE [Orders] SET [status] = 'false' WHERE [orderid] =  '20190620003109'
SELECT orderid FROM [Orders] WHERE orderid = '20190630131825'

DELETE FROM [Orders]	


--OderDetail
SELECT	* FROM [OrderDetails]
SELECT COUNT (*) AS total FROM [OrderDetails]
INSERT INTO [OrderDetails] VALUES('1', (SELECT orderid FROM [Orders] WHERE orderid = '1'), 
	(SELECT proid FROM [Products] WHERE proid = 'C0001'), '4')

SELECT [OrderDetails].proid, [Products].ProName, [Products].Price, [Products].[image], [quantity] 
FROM OrderDetails INNER JOIN [Products] 
ON [Products].proid = [OrderDetails].proid
WHERE orderid = '20190620003109'


DELETE FROM [OrderDetails]


--user
SELECT * FROM [Users]
select role from [Users]
where [username]='abcd' and password='admin'
INSERT INTO [Users] VALUES (10,'trungnt','6969',2)
SELECT id FROM [Users]
UPDATE [Users] SET [password] = '123123' WHERE [Users].id = '6'
SELECT [Users].password FROM [Users] WHERE [Users].id = 6
SELECT [Users].password FROM [Users] WHERE [Users].username = 'vanttn'
SELECT [Members].status FROM [Members] INNER JOIN [Users]
ON [Members].userid = [Users].id
WHERE [Users].username = 'admin'


--Members
select * from [Members] 

where [fullname] like '%Day%'

SELECT DISTINCT [Users].username AS tmp FROM [Users]
INNER JOIN Members
ON [Users].id = 5;

DELETE [Members]
FROM [Members] INNER JOIN [Users] 
ON [Users].id = [Members].userid
WHERE [Members].userid = 8;

INSERT INTO [Members] VALUES (5, '', '', '', 1,
	(SELECT [Users].id FROM [Users] WHERE [Users].id = '9'))

UPDATE [Members] SET [fullname] ='Bui Le Hieu', [phone]='0396587423', [address]='TPHCM', [status]=1  WHERE [userid]='9'

SELECT [memberid], [fullname], [phone], [address], [status], [userid] FROM [Members]
INNER JOIN [Users]
ON [Users].username = 'daycd' AND
[Users].id = [Members].userid

SELECT DISTINCT fullname, phone, address FROM [Members]
INNER JOIN [Users] 
ON [Members].userid = 5;



select * 
from [Staffs] 
where [fullname] like '%Day%'

UPDATE [Members] SET [fullname] , [password], [phone], [address], [status]=? WHERE [memberid]=?"


