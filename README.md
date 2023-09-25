# POSSG_BE

## 개발환경 실행 방법

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
