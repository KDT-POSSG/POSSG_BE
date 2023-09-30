select * from Call_product_conv;
drop table Call_product_Conv;
-- 점주 발주 테이블 --
CREATE TABLE Call_product_conv (
	call_seq INT auto_increment primary key,					-- 편의점 발주 고유번호
	conv_seq INT not null,										-- 편의점 고유번호 (주문자)
	product_seq INT,											-- 상품 고유번호	
	amount INT not null,										-- 주문 양
	rp_name VARCHAR(255) not null,								-- 대표자명
	b_name VARCHAR(255) not null,								-- 점포명	
	price INT not null,											-- 발주 가격	
  call_date Timestamp not null,								-- 발주 날짜	
	product_name VARCHAR(255) not null,							-- 상품 이름
	call_ref varchar(255) not null,								-- 발주 목록 묶음
	call_status INT not null,									-- 발주 상태 (0: 발주 대기/ 1: 발주 접수중/ 2: 접수완료/ 3: 배송중/ 4: 배송완료)
	img_url VARCHAR(255),										-- 상품 이미지
    foreign key(conv_seq) references Convenience(conv_seq),		-- 편의점 테이블에서 참조
    foreign key(product_seq) references Product(product_seq),	-- customer 테이블에서 참조
    foreign key(call_ref) references Call_product_conv_order_list(call_ref)
);
select * from Call_product_conv_order_list;
CREATE TABLE Call_product_conv_order_list(
	seq INT auto_increment primary key,							-- seq
	conv_seq INT,										-- 편의점 점주 아이디 (주문자)
	call_ref VARCHAR(255) unique not null,						-- 발주 목록 묶음
	call_date Timestamp not null,								-- 발주 날짜
	call_status INT not null,									-- 발주 상태 (0: 발주 대기/ 1: 발주 접수중/ 2: 접수완료/ 3: 배송중/ 4: 배송완료)
	call_total_number INT not null,								-- 발주 상품 수량
	call_total_price INT not null,								-- 발주 총 가격		
	call_remark VARCHAR(255)									-- 비고
);
select * from Convenience;
INSERT INTO Call_product_conv_order_list (seq,conv_seq, call_ref, call_date, call_status, call_total_number
								, call_total_price, call_remark) 
VALUES (0,0, '0', '2000-01-01', 0, 0, 0, 'temp');
update Call_product_conv_order_list set conv_seq = 0 where seq = 1;

select * from Employee;
INSERT INTO Employee (emp_name, conv_seq, birth_date, gender, phone_number, hire_date, salary) 
VALUES ('temp',1 ,'dlfwlao', '10101010', '일지매', '센텀시티 이마트', '01011112222', '20230805', 1);

select * from Product;