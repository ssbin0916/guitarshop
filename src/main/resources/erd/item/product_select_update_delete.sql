SELECT * FROM product;

SELECT * FROM product WHERE id = 1;

SELECT * FROM product WHERE name = 'testName';

UPDATE product SET
	name = 'updateName',
	price = 50000,
	image = 'updateImage',
	count = 2,
	category = 'ELECTRIC_GUITAR'
WHERE id = 1;

DELETE FROM product WHERE id = 1;