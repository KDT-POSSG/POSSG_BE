## MYSQL 도커파일 생성방법
```TXT
FROM mysql:8.0

ENV MYSQL_USER=POSSG
ENV MYSQL_PASSWORD=1234
ENV MYSQL_ROOT_PASSWORD=1234
ENV MYSQL_DATABASE=POSSGDB

EXPOSE 3305

CMD ["--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]

##명령어
-- 빌드
docker build -t mysql-image .
-- 시작
docker run -d -p 3305:3306 --name possg-mysql mysql-image

docker run --name ms -e MYSQL_ROOT_PASSWORD=(비밀번호) -d --rm  mysql -- 비밀번호 설정하기

docker images -- 도커이미지확인
docker ps -a --전체보기(실행중이지 않은 것 까지)
docker ps(실행중인 것만)

docker stop (imageID) -- 도커 실행 중지
docker rm (imageID) -- 도커 삭제

docker inspect (imageID) -- 정보 볼수 있음

docker exec -it (imageID) bash -- db접속

## 볼륨 옵션으로 실행하기

docker run -d -v D:\final\POSSG_BE\POSSG_BE\src\main\java:/var/lib/mysql -p 3305:3306 --name possg-mysql mysql-image


## db연결해서 데이터 열어보기
mysql -u root -p --host 127.0.0.1 --port 3306

use mydb;

show  tables;

select .... sql명령어

## UTF 8 설정확인

```SH
SHOW variables LIKE 'CHARACTER-SET_%';