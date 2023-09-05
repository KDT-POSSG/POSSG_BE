-- SET foreign_key_checks = 0;
-- SET foreign_key_checks = 1;
/*
drop table call_product_conv;
drop table call_product_customer;
drop table Payment;
drop table Delivery;
drop table Product;
drop table Category;

select * from Category;
-- 1: 행사X, 2: 세일, 3: 덤증정, 4: 1+1, 5: 2+1, 6: 1+2, 7: 2+2
INSERT INTO Category (category_name) VALUES ('행사없음'), ('세일'), ('덤증정'), ('1 + 1'), ('2 + 1'), ('1 + 2'), ('2 + 2');


select * from Product;

INSERT INTO Product (category_id, product_name, price, price_discount, stock_quantity, expiration_date, discount_rate, promotion_info, barcode, img_url) VALUES (1, '농심)새우탕큰사발', 1400, 1400, 4, '2023-10-02 21:00:00', 0, 3, 0000000000000, null) ;

select img_url
	from Product

DELETE FROM Product;
	
ALTER TABLE Product
ADD img_url varchar(255);

ALTER TABLE call_product_Conv ADD call_ref varchar(255) unique;
*/