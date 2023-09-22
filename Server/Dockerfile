# FROM: 이미지 지정
FROM openjdk:17

# ARG: docker build 커맨드를 사용할 때 입력받을 수 있는 인자를 선언
ARG JAR_FILE=build/libs/*.jar

# COPY: 이미지에 파일이나 폴더를 추가
COPY ${JAR_FILE} app.jar

# RUN: 이미지 내부에서 명령어를 실행
RUN zip -d app.jar 'BOOT-INF/classes/data.sql'

# ENTRYPOINT: 컨테이너를 실행할 때 실행할 명령어 강제 지정
ENTRYPOINT ["java", "-jar", "app.jar"]