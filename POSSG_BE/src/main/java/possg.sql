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
	conv_status int not null	 				-- 0: 폐점, 1: 운영중(activate)
	-- day_night int 							-- 주야간 (삭제 예정)
);

-- 고객 테이블 --
CREATE TABLE Customer (
	customer_seq INT auto_increment	primary key,				-- 고객 고유 번호
	customer_id	VARCHAR(255) default 'user0000' not null,		-- 고객 아이디 ( 비회원은 default로 ex) user0000) 나중에 삽입해주삼.
	pin_number INT,												-- 결제 비밀 번호. 6자리?
	customer_name VARCHAR(255) default 'anonymous' not null,	-- 고객 이름
	phone_number VARCHAR(255),									-- 고객 휴대폰 번호
	registration_date TIMESTAMP,								-- 고객 가입일 
    customer_status int default 1 not null						-- 고객 탈퇴 여부 (0: 탈퇴 1: 가입됨)
);

drop table Delivery;

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
    foreign key(user_id) references Customer(customer_seq),		-- customer 테이블에서 참조
    foreign key(product_seq) references Product(product_seq) 	-- product 테이블에서 참조
);

-- 상품 테이블 --
CREATE TABLE Product (
	product_seq	INT auto_increment primary key, 						-- 상품 고유번호	
	category_id	int, 													-- product_category 테이블에서 참조	
	product_name VARCHAR(255) not null,									-- 상품명
	price INT not null,													-- 상품 가격
	price_discount INT,													-- 할인 후 상품 가격
	stock_quantity INT not null,										-- 상품 재고
	expiration_date	TIMESTAMP,											-- 유통기한
	discount_rate DECIMAL(5, 2),										-- 할인율
	promotion_info INT,													-- 할인 정보 (1: 1+1, 2: 2+1, 3: 세일...)
	barcode	VARCHAR(255) not null,										-- 바코드 번호
    foreign key(category_id) references Category(category_id)			-- Category 테이블에서 참조
);

-- 상품 카테고리 테이블 --
CREATE TABLE Category (
	category_id	INT auto_increment primary key, 	-- 상품 카테고리 고유번호
	category_name VARCHAR(255) not null				-- 상품 카테고리명				
);

-- 결제 정보 테이블 --
CREATE TABLE Payment (
	payment_seq	INT auto_increment primary key,					-- 결제 고유번호 
	user_seq INT, 												-- customer 테이블에서 참조 
	product_seq INT,											-- product 테이블에서 참조
	payment_method VARCHAR(255) not null,						-- 결제 방법 (카카오페이, 네이버페이, 실물카드, 깊티 ...)
	discount_info TEXT,											-- 할인 정보
	price INT not null,											-- 가격 
	count INT not null,											-- 수량
	payment_date TIMESTAMP not null,							-- 결제일
	ref	INT not null,											-- 결제 묶음
	card_num varchar(255),										-- 카드 번호 
    foreign key(user_seq) references Customer(customer_seq),	-- Customer 테이블에서 참조
    foreign key(product_seq) references Product(product_seq)	-- Product 테이블에서 참조
);

-- 직원 테이블 --
CREATE TABLE Employee (
	employee_seq INT auto_increment primary key,				-- 직원 고유번호
	emp_name VARCHAR(255) not null,								-- 직원 이름
	birth_date TIMESTAMP not null,								-- 직원 생년월일
	gender VARCHAR(20) not null,								-- 직원 성별
	phone_number VARCHAR(255) not null,							-- 직원 휴대폰 번호		
	hire_date TIMESTAMP not null,								-- 직원 고용일	
	termination_date TIMESTAMP,									-- 직원 해고일
	salary INT not null											-- 직원 월급
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
	work_hours TIMESTAMP,											-- 근무시간	
	remark VARCHAR(20),												-- 비고
	matter TEXT,													-- 특이사항
    foreign key (employee_seq) references Employee(employee_seq)	-- Employee 테이블에서 참조
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
	customer_seq INT, 												-- customer 테이블에서 참조 
	total_pt INT not null,											-- 총 포인트
	variation INT,													-- 포인트 변화
    foreign key(customer_seq) references Customer(customer_seq)		-- customer 테이블에서 참조 
);

-- 점주 발주 테이블 --
CREATE TABLE call_product_Conv (
	call_seq INT auto_increment primary key,					-- 편의점 발주 고유번호
	user_id	VARCHAR(255),										-- 편의점 점주 아이디 (주문자)
	product_seq INT,											-- 상품 고유번호	
	amount INT not null,										-- 주문 양
	rp_name VARCHAR(255) not null,								-- 대표자명
	b_name VARCHAR(255) not null,								-- 점포명	
	price INT not null,											-- 발주 가격	
	call_date TIMESTAMP not null,								-- 발주 날짜	
	product_name VARCHAR(255) not null,							-- 상품 이름
    foreign key(user_id) references Convenience(user_id),		-- 편의점 테이블에서 참조
    foreign key(product_seq) references Product(product_seq)	-- customer 테이블에서 참조
);

-- 고객 발주 테이블 --
CREATE TABLE call_product_customer (
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