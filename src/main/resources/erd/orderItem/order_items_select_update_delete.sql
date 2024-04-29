SELECT * FROM order_items WHERE order_id = 1;

-- 수량 변경
UPDATE order_items SET
	quantity = 2
WHERE id = 1;

DELETE FROM order_items WHERE id = 1;