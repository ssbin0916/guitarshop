SELECT * FROM orders;

SELECT * FROM orders WHERE id = 1;

SELECT * FROM orders WHERE name = 'orderName';

UPDATE orders SET
	price = 50000,
	name = 'updateName',
	phone = '222-2222-2222',
	email = 'update@email.com',
	image = 'updateimage',
	address = 'updateAddress'
WHERE id = 1;

DELETE FROM orders WHERE id = 1;