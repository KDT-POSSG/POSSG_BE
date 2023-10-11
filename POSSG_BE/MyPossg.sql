use mydb;

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

-- 본사 확인 인증 키
CREATE TABLE Account_num(
	account_num_seq INT auto_increment primary key,	-- 고유번호
	account_code VARCHAR(255) not null,				-- 본사 지급 번호
	code_status INT not null						-- 코드 사용 상태 번호 0:사용전 1:사용중
);

-- 점주 인증 토큰 
create table Token(
	seq int auto_increment primary key,	-- 토큰 고유번호
	refresh varchar(500) not null,		-- 로그인 시 저장 할 refresh 토큰
	user_id varchar(500) not null		-- 유저 아이디
);

drop table Token;


-- 고객 인증 토큰
create table CustomerToken(
	seq int auto_increment primary key,	-- 토큰 고유번호
	refresh varchar(255) not null,		-- 로그인 시 저장 할 refresh 토큰
	customer_id varchar(255) not null	-- 유저 아이디
);

-- 문자 인증 확인 db
create table Sms(
	seq int auto_increment primary key,		-- 문자 고유번호
    sms_num int not null					-- 문자 번호
);


select * from Payment;

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

select * from items;

SELECT
    P.receipt_id,
    P.del AS del,
    P.purchased_at AS payment_time,
    GROUP_CONCAT(I.item_name SEPARATOR ', ') AS purchased_items,
    P.price AS price,
    P.method AS method,
    P.card_num,
    P.receipt_url
FROM
    Payment AS P
JOIN
    Items AS I ON P.receipt_id = I.receipt_id
WHERE
    P.receipt_id = '651c36b500be04001f8f6b48';
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

select * from employee;

select count(*)
		FROM Employee 
		where conv_seq = 1;

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

-- 홈 즐겨찾기 페이지 --
CREATE table favorite_page(
	seq INT auto_increment primary key,		-- 즐겨찾기 고유번호
    conv_seq int,							-- 즐겨찾기 점포 
    page_name varchar(100),					-- 즐겨찾기 페이지 이름
    favorite_enable varchar(50),				-- 즐겨찾기 여부 (enable, disable)
    foreign key (conv_seq) references convenience(conv_seq) 
);	

select * from favorite_page;
RENAME TABLE favorite_page TO Favorite_page;

-- 시재 테이블 -- 
CREATE TABLE Settlement(
	seq INT auto_increment primary key,		-- 시재 고유번호
    conv_seq INT, 							-- 시재 기록 점포 고유번호
	rdate TIMESTAMP not null, 				-- 시재 기록시간
	cash int not null,						-- 시재 금액
    memo varchar(255), 						-- 시재 메모사항 (필수 x)
    foreign key(conv_seq) references Convenience(conv_seq)
);

-- 포인트 테이블 --
CREATE TABLE PT(
	pt_seq INT auto_increment primary key,							-- 포인트 고유번호 
	customer_seq INT, 												-- customer 테이블에서 참조 
	total_pt INT not null,											-- 총 포인트
	variation INT,													-- 포인트 변화
    foreign key(customer_seq) references Customer(customer_seq)		-- customer 테이블에서 참조 
);