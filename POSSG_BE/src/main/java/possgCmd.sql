-- SET foreign_key_checks = 0;
-- SET foreign_key_checks = 1;

/*
drop table call_product_conv;
drop table call_product_customer;
drop table call_product_conv_order_list;
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

delete from call_product_conv where call_seq=6;

INSERT INTO call_product_conv (call_seq, user_id, product_seq, amount, rp_name
								, b_name, price, call_date, product_name, call_ref, call_status) 
VALUES (0, 'ghfrlfehd', 1, 10, '홍길동', '수영구 이마트', 14000, '2023-09-05', '농심)새우탕큰사발', '202309051811', 1);

INSERT INTO call_product_conv (call_seq, user_id, product_seq, amount, rp_name
								, b_name, price, call_date, product_name, call_ref, call_status) 
VALUES (1, 'ghfrlfehd', 1, 5, '홍길동', '수영구 이마트', 34500, '2023-09-05', '삼립)로스트치킨하바네로210g' , '202309051811', 1);

INSERT INTO call_product_conv_order_list (seq, call_ref, call_date, call_status, call_total_number
								, call_total_price, call_remark) 
VALUES (0, '-1', '2000-01-01', 0, 0, 0, 'temp');

select * from call_product_conv;

select * from call_product_conv_order_list;

delete from call_product_conv_order_list where call_ref=-1;

*/