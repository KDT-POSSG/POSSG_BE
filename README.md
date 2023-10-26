# POSSG_BE
웹 기반 POS 시스템

## 🖥️ 프로젝트 소개
편의점을 비롯한 상품 판매점용 웹 기반 POS 시스템을 지원하는 사이트입니다. 
<br>
해당 repository는 backend 부분을 구성하고 있습니다.

## 실행 방법

### db띄우는 방법

- 컨테이너 꺼졌다가 다시 켜지면 데이터 다 날아가지 않게 db디렉터리 마운트 할것
- 명령어 실행 전 명령어 실행하는 디렉터리에 db폴더가 있어야 함

```bash
docker run -d \
    -p 3306:3306 \
    -e MARIADB_ROOT_PASSWORD="root 암호" \
    -v ${PWD}/db:/var/lib/mysql \
    --name mariadb \
    mariadb:11.1.2
```

- db 처음 띄운거면, 연결한 db 따로 만들어 줘야함!

### 이미지 빌드

```bash
docker build -t [빌드할 이미지 명] .
```

### 백엔드 띄우는 법(컨테이너)

```bash
docker run -d \
    -p 3000:3000 \
    -e DB_HOST=db \
    -e DB_USER=[db 유저이름] \
    -e DB_PASSWORD=[db 암호] \
    -e NCS_ACCESSKEY=[..] \
    -e NCS_SECRETKEY=[..] \
    -e NCS_SERVICEID=[..] \
    -e NCS_PHONE_NUMBER=[..] \
    -e JWT_TOKEN=[..] \
    -- link mariadb:db \
    [빌드한 이미지 명]
```

## 🕰️ 개발 기간
* 23.08.25일 - 23.10.24일

### 🧑‍🤝‍🧑 멤버 구성
- 팀장  : 강창수
- 팀원1 : 최민규
- 팀원2 : 변경태

## ⚙️ 개발환경 
- `java 17`
- `jwt 0.11.2`
- `spring security 3.1.3`
- **IDE** : STS 4.0
- **Framwork** : spring boot 3.1.3
- **Database** : mysql 8.0
- **ORM** : mybatis 3.0.1
- **CI/CD** : dockerHub, GitHub Actions
- **API** : naver cloud platform, bootpay, kakaomap

## 📌 주요 기능
#### 메인 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 즐겨찾기 수정
- 즐겨찾기 목록 조회
#### 회원 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 로그인
- 로그아웃
- 회원가입
- 회원 정보 수정
- 계정 찾기
#### 발주 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 발주 대기 목록
- 발주 내역
- 발주 배송
#### 고객 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 고객 가입
- 고객 정보 관리
- 배달 점포 저장
#### 결제 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 결제 및 취소 기능
- 포인트 관리 및 조회
- 결제 내역
#### 배달 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 배달 장바구니
- 배달 주문 및 취소
- 점주 배달
#### 재고 관리 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 재고 목록
- 재고 관리
- 번역
#### 상품 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 상품 목록
- 상품 목록 관리
- 상품 영양정보 확인
- 번역
#### 직원 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 직원 목록
- 직원 관리
- 직원 상세조회
- 출/퇴근
#### 시재 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 시재 조회
- 시재 관리
#### 분석 페이지 - <a href=>상세보기 - WIKI 이동</a>
- 월세 및 관리비 입력
- 관리비 조회
- 매출 보고서
- 판매 통계
#### 알림 - <a href=>상세보기 - WIKI 이동</a>
- 유통기한 임박/만료 알림
