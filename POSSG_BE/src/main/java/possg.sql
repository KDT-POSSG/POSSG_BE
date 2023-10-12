use mydb;

-- SET foreign_key_checks = 0;
-- SET foreign_key_checks = 1;

-- 편의점 (점주) 테이블 --
CREATE TABLE Convenience (
	conv_seq INT auto_increment primary key,	-- 편의점 고유 번호
	user_id	VARCHAR(255) unique,				-- 편의점 점주 아이디
	pwd	VARCHAR(255) not null,					-- 편의점 점주 비밀번호	
	representative_name	VARCHAR(255) not null,	-- 편의점 점주명	
	branch_name	VARCHAR(255) not null,			-- 편의점 점포명
	phone_number VARCHAR(255) not null,			-- 편의점 점주 휴대폰 번호
	registration_date TIMESTAMP not null,		-- 편의점 설립일	
	conv_status int not null,	 				-- 0: 폐점, 1: 운영중(activate)
	conv_key VARCHAR(100) unique,				-- 본사 인증 번호
    conv_location VARCHAR(255),					-- 매장 주소
    latitude double,							-- 위도
    longtitude double,							-- 경도
    FOREIGN KEY (conv_key) REFERENCES account_num(account_code)
);

-- 고객 테이블 --
CREATE TABLE Customer (
	customer_seq INT auto_increment	primary key,				-- 고객 고유 번호
	customer_id	VARCHAR(255) default 'user0000' not null,		-- 고객 아이디 ( 비회원은 default로 ex) user0000) 나중에 삽입해주삼.
	pin_number INT,												-- 결제 비밀 번호. 6자리?
	customer_name VARCHAR(255) default 'anonymous' not null,	-- 고객 이름
	phone_number VARCHAR(255) not null,							-- 고객 휴대폰 번호
	registration_date TIMESTAMP,								-- 고객 가입일 
    customer_status int default 1 not null,						-- 고객 탈퇴 여부 (0: 탈퇴 1: 가입됨)
    conv_seq int,												-- 간편 가입 시 가입 한 편의점 
    location VARCHAR(255),										-- 배달 받을 본인 주소
    pwd VARCHAR(255),										 	-- 계정 비밀번호
    branch_name VARCHAR(255),									-- 배달 시킬 지점
    foreign key(conv_seq) references Convenience(conv_seq)
);

select * from Customer;

ALTER TABLE Customer MODIFY phone_number VARCHAR(255) NOT NULL;

insert into Customer(pin_number, phone_number, registration_date, customer_status)
values (1234, 01012345123, 20230830, 1);

-- 점주 인증 토큰 
create table token(
	seq int auto_increment primary key,	-- 토큰 고유번호
	refresh varchar(255) not null,		-- 로그인 시 저장 할 refresh 토큰
	user_id varchar(255) not null		-- 유저 아이디
);

-- 고객 인증 토큰
create table customerToken(
	seq int auto_increment primary key,	-- 토큰 고유번호
	refresh varchar(255) not null,		-- 로그인 시 저장 할 refresh 토큰
	customer_id varchar(255) not null	-- 유저 아이디
);

-- 문자 인증 확인 db
create table sms(
	seq int auto_increment primary key,		-- 문자 고유번호
    sms_num int not null					-- 문자 번호
);

INSERT INTO Customer (customer_id, pin_number, customer_name, phone_number, registration_date, customer_status)
VALUES ('user0001', 111111, '홍길동', '01012345678', NOW(), 1);

-- 배달 테이블 --
CREATE TABLE Delivery (
	order_seq int auto_increment primary key,					-- 배달 고유 번호
	user_id	INT, 												-- 배달 시킨 사람
	product_seq INT,											-- 배달 상품 고유번호
	order_status int not null, 									-- 배송상태 (1: 접수대기, 2: 픽업대기, 3: 배송중, 4: 배송완료)
	quantity int not null,										-- 배달 상품 하나의 갯수
	product_name VARCHAR(255),									-- 배달 상품명
	order_date TIMESTAMP not null,								-- 배달 주문 시각
	ref	INT not null,											-- 배달 묶음 
	location VARCHAR(255) not null, 							-- 배송지
	price INT not null,											-- 상품 최종 가격
	branch_name VARCHAR(255) not null,							-- 배달 접수받은 지점
    foreign key(user_id) references Customer(customer_seq),		-- customer 테이블에서 참조
    foreign key(product_seq) references Product(product_seq) 	-- product 테이블에서 참조

);

-- 상품 테이블 --
CREATE TABLE Product (
	product_seq	INT auto_increment primary key, 						-- 상품 고유번호	
	conv_seq INT not null,
	category_id	int, 													-- product_category 테이블에서 참조	
	product_name VARCHAR(255) not null,									-- 상품명
	product_roman_name VARCHAR(255),
	product_translation_name VARCHAR(255),
	price INT not null,													-- 상품 가격
	price_discount INT,													-- 할인 후 상품 가격
	stock_quantity INT not null,										-- 상품 재고
	expiration_date	Timestamp,											-- 유통기한
	discount_rate DECIMAL(5, 2),										-- 할인율
	promotion_info INT,													-- 할인 정보 (1: 1+1, 2: 2+1, 3: 세일...)
	barcode	VARCHAR(255) not null,										-- 바코드 번호
	img_url VARCHAR(255),												-- 이미지 주소
    foreign key(category_id) references Category(category_id)			-- Category 테이블에서 참조
);

-- 상품 카테고리 테이블 --
CREATE TABLE Category (
	category_id	INT auto_increment primary key, 	-- 상품 카테고리 고유번호
	category_name VARCHAR(255) not null				-- 상품 카테고리명				
);

-- 배달 테이블 --
CREATE TABLE Delivery (
	order_seq int auto_increment primary key,					-- 배달 고유 번호
	user_id	INT, 												-- 배달 시킨 사람
	product_seq INT,											-- 배달 상품 고유번호
	order_status int not null, 									-- 배송상태 (1: 접수대기, 2: 픽업대기, 3: 배송중, 4: 배송완료)
	quantity int not null,										-- 배달 상품 하나의 갯수
	product_name VARCHAR(255),									-- 배달 상품명
	order_date Timestamp not null,								-- 배달 주문 시각
	ref	INT not null,											-- 배달 묶음 
	location VARCHAR(255) not null, 							-- 배송지
    foreign key(user_id) references Customer(customer_seq),		-- customer 테이블에서 참조
    foreign key(product_seq) references Product(product_seq) 	-- product 테이블에서 참조
);

CREATE TABLE Delivery_list (
	seq INT auto_increment primary key,							-- seq
	del_ref VARCHAR(255) unique not null,						-- 발주 목록 묶음
	del_date Timestamp not null,								-- 발주 날짜
	del_status INT not null,									-- 발주 상태 (0: 발주 대기/ 1: 발주 접수중/ 2: 접수완료/ 3: 배송중/ 4: 배송완료)
	del_total_number INT not null,								-- 발주 상품 수량
	del_total_price INT not null,								-- 발주 총 가격		
	del_remark VARCHAR(255)									    -- 비고
);

-- 결제 정보 테이블 --
CREATE TABLE Payment (
	receipt_id VARCHAR(255) primary key,						-- 결제 고유번호 
	user_seq INT, 												-- customer 테이블에서 참조 
	product_seq INT,											-- product 테이블에서 참조
    conv_seq INT, 												-- convenience 테이블에서 참조
	pg VARCHAR(100), 											-- 결제사
    method VARCHAR(255) not null,								-- 결제 방법 (카카오페이, 네이버페이, 실물카드, 깊티 ...)
	discount_info TEXT,											-- 할인 정보
	price INT not null,											-- 총 가격 
	purchased_at TIMESTAMP not null,							-- 결제시간
    receipt_url VARCHAR(500),									-- 결제 영수증 url
	card_num varchar(255),										-- 카드 번호 
    card_company varchar(100),									-- 카드 회사 
    del varchar(100) not null,									-- 결제 취소 여부 (결제완료, 결제취소)
    foreign key(user_seq) references Customer(customer_seq),	-- Customer 테이블에서 참조
    foreign key(conv_seq) references convenience(conv_seq)		
);

-- 결제 아이템 목록들(ref)--
CREATE TABLE Items(
	receipt_id VARCHAR(255),				-- 영수증 아이디 
	item_id int not null,					-- 상품 아이디
    item_name varchar(255) not null,		-- 상품 이름 
    qty int not null,						-- 상품 수량
    price int not null,						-- 상품 가격 
    foreign key(receipt_id) references Payment(receipt_id),
    foreign key(item_id) references Product(product_seq)
);



-- 직원 테이블 --
CREATE TABLE Employee (
	employee_seq INT auto_increment primary key,				-- 직원 고유번호
    conv_seq INT,												-- 직원 일하는 점포고유번호
	emp_name VARCHAR(255) not null,								-- 직원 이름
	birth_date Date not null,									-- 직원 생년월일
	gender VARCHAR(20) not null,								-- 직원 성별
	phone_number VARCHAR(255) not null,							-- 직원 휴대폰 번호		
	hire_date Date not null,									-- 직원 고용일	
	termination_date Date,										-- 직원 해고일
	salary INT not null,										-- 직원 월급
    foreign key(conv_seq) references Convenience(conv_seq)		-- 편의점 테이블 참조
);

-- 지출(분석) 테이블 -- 
CREATE TABLE Cost (
	cost_seq INT auto_increment	primary key, 				-- 지출 고유 번호
	rent INT,												-- 월세
	water_bill INT,											-- 수도세
	electricity_bill INT,									-- 전기세
	gas_bill INT,											-- 가스비 
	total_labor_cost INT,									-- 총 인건비
	security_maintenance_fee INT,							-- 보안비
	conv_seq INT,											-- 점포 고유번호 
	cost_year INT,											-- 분석 년
	cost_month INT,											-- 분석 월		
    foreign key(conv_seq) references Convenience(conv_seq)	-- Convenience 테이블에서 참조
);

-- 근태 테이블 --
CREATE TABLE Attendance (
	att_seq	INT auto_increment primary key,							-- 근태 고유번호
	employee_seq INT,												-- 직원 고유번호
	attendance TIMESTAMP not null,									-- 출근시간	
	leave_work TIMESTAMP,											-- 퇴근시간
	work_hours TIME,												-- 근무시간(TIME은 시간만 저장해줌)	
	remark VARCHAR(20),												-- 비고(출근, 퇴근, 지각, 휴가 등)
	matter TEXT,													-- 특이사항
    foreign key (employee_seq) references Employee(employee_seq)	-- Employee 테이블에서 참조
);

select * from Attendance;

-- 홈 즐겨찾기 페이지 --
CREATE table Favorite_page(
	seq INT auto_increment primary key,		-- 즐겨찾기 고유번호
    conv_seq int,							-- 즐겨찾기 점포 
    page_name varchar(100),					-- 즐겨찾기 페이지 이름
    favorite_enable varchar(50),				-- 즐겨찾기 여부 (enable, disable)
    foreign key (conv_seq) references convenience(conv_seq) 
);	

-- 시재 테이블 -- 
CREATE TABLE Settlement(
	seq INT auto_increment primary key,		-- 시재 고유번호
    conv_seq INT, 							-- 시재 기록 점포 고유번호
	rdate TIMESTAMP not null, 				-- 시재 기록시간
	cash int not null,						-- 시재 금액
    memo varchar(255), 						-- 시재 메모사항 (필수 x)
    foreign key(conv_seq) references Convenience(conv_seq)
);

-- 즐겨찾기 테이블 --
CREATE TABLE ConvenienceBookmark (
	bookmark_seq INT auto_increment	primary key,			-- 즐겨찾기 고유번호
	bookmark_name VARCHAR(255),								-- 즐겨찾기 이름
	conv_seq INT,											-- 편의점 테이블에서 참조
    foreign key (conv_seq) references Convenience(conv_seq)	-- Employee 테이블에서 참조
);

-- 포인트 테이블 --
CREATE TABLE PT(
	pt_seq INT auto_increment primary key,							-- 포인트 고유번호 
	phone_num varchar(255) not null, 								-- customer 테이블에서 참조 
	total_pt INT default 0 not null									-- 총 포인트
);

-- 점주 발주 테이블 --
CREATE TABLE call_product_Conv (
	call_seq INT auto_increment primary key,					-- 편의점 발주 고유번호
	conv_seq INT not null,										-- 편의점 고유번호 (주문자)
	product_seq INT,											-- 상품 고유번호	
	amount INT not null,										-- 주문 양
	rp_name VARCHAR(255) not null,								-- 대표자명
	b_name VARCHAR(255) not null,								-- 점포명	
	price INT not null,											-- 상품 가격	
	price_origin INT,											-- 발주 원가
  	call_date Timestamp not null,								-- 발주 날짜	
	product_name VARCHAR(255) not null,							-- 상품 이름
	call_ref varchar(255) not null,								-- 발주 목록 묶음
	call_status INT not null,									-- 발주 상태 (0: 발주 대기/ 1: 발주 접수중/ 2: 접수완료/ 3: 배송중/ 4: 배송완료)
	img_url VARCHAR(255),										-- 상품 이미지
    foreign key(conv_seq) references Convenience(conv_seq),		-- 편의점 테이블에서 참조
    foreign key(product_seq) references Product(product_seq),	-- 상품 테이블에서 참조
    foreign key(call_ref) references call_product_conv_order_list(call_ref)
);

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

select * from call_product_conv_order_list;

-- 고객 발주 테이블 --
CREATE TABLE Call_product_customer (
	call_seq INT auto_increment primary key,						-- 고객 발주 고유번호
	customer_seq INT,												-- 고객 고유번호
	product_seq INT,												-- 상품 고유번호	
	amount INT not null,											-- 주문 양								
	c_name VARCHAR(255) not null,									-- 고객 이름
	b_name VARCHAR(255) not null,									-- 점포 이름
	price INT not null,												-- 발주 주문 가격
	call_date TIMESTAMP not null,									-- 발주 날짜
	product_name VARCHAR(255) not null,								-- 상품 이름
    foreign key(customer_seq) references Customer(customer_seq),	-- customer 테이블에서 참조 
	foreign key(product_seq) references Product(product_seq)		-- 상품 테이블에서 참조
);

