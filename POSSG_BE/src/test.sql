select * from Product;

--ALTER TABLE Product ADD conv_seq int not null AFTER product_seq;
ALTER TABLE Product ADD foreign key(conv_seq) references Convenience(conv_seq);
-- ALTER TABLE Product ADD conv_seq int not null AFTER product_roman_name;
ALTER TABLE Product ADD product_translation_name VARCHAR(255) AFTER product_roman_name;

INSERT INTO Product (conv_seq, category_id, product_name, price, price_discount, stock_quantity, 
expiration_date, discount_rate, promotion_info, barcode, img_url) 
VALUES (1, 1, '농심)새우탕큰사발', 1400, 1400, 4, '2023-10-02 21:00:00', 0, 3, 0000000000000, null) ;

select * from Category;

INSERT INTO Category (category_name) VALUES ('행사없음'), ('세일'), ('덤증정'), ('1 + 1'), ('2 + 1'), ('1 + 2'), ('2 + 2');

select * from Convenience;