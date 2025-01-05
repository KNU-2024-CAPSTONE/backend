#!/bin/bash

# 자주 사용하는 값 변수에 저장
REPOSITORY=/home/ubuntu/repository
PROJECT_NAME=backend
JAR_NAME=crm-0.0.1-SNAPSHOT.jar
JAR_REPOSITORY=/home/ubuntu/repository/jars

# git clone 받은 위치로 이동
cd $REPOSITORY/$PROJECT_NAME/

# backend_deploy 실행
echo "> backend deploy 시작"

# master 브랜치의 최신 내용 받기
echo "> Git Pull"
git pull origin master

# build 수행
echo "> project build start"
echo "> 30초 ~ 2분 사이의 시간이 소요됩니다."
./gradlew build -x test

echo "> directory로 이동"
cd $REPOSITORY

# build의 결과물 (jar 파일) 특정 위치로 복사
echo "> build 파일 복사"
cp $REPOSITORY/$PROJECT_NAME/build/libs/$JAR_NAME $JAR_REPOSITORY/

# 기존에 실행중이던 파일 종료
kill -9 $(pgrep -f $JAR_NAME)

echo "> 새 애플리케이션 배포"
# nohup 실행을 통해 백그라운드에서 실행되도록 설정
nohup java -Duser.timezone=Asia/Seoul -jar $JAR_REPOSITORY/$JAR_NAME > $REPOSITORY/$PROJECT_NAME.log 2>&1 &

# 안정적 배포를 위해 5초간 대기
sleep 5
echo "> 5초 남았습니다..."

# SSH 세션 종료
exit 0
