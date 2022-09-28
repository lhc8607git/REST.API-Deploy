# openjdk의 기반 이미지로 만들겠다, 버전은 8-jdk-alpine
FROM openjdk:8-jdk-alpine

# Docker 에서 컨테이너를 삭제할 때 컨테이너 내부의 데이터가 삭제되는데, 
#VOLUME 명령어를 사용할 경우 컨테이너 외부와 컨테이너 내부를 연결시켜 저장된 데이터의 수명과 데이터를 생성한 컨테이너의 수명을 분리할 수 있습니다.
# -------------------------
# 디렉토리 하나를 사용할 경우 VOLUME <컨테이너 디렉토리> , 여러 디렉토리를 설정할 경우 VOLUME ["컨테이너 디렉토리1", "컨테이너 디렉토리2"]와 같이 사용할 수 있습니다.
# VOLUME /tmp 로 설정할 경우 컨테이너 내부의 /tmp 디렉토리가 호스트의 /var/lib/docker/volumes/${volume_name}/_data에 마운트 됩니다. (볼륨 이름은 임의의 해쉬값으로 생성)
# (참고) https://joont92.github.io/docker/volume-container-%EC%B6%94%EA%B0%80%ED%95%98%EA%B8%B0/
VOLUME /tmp

EXPOSE 8088

# ARG 명령어는 build 시에 사용되는 변수
# 'target/*.jar 이라는 값을 가진 JAR_FILE 변수를 사용하겠다'라는 것
# ARG JAR_FILE=restful-web-service-0.0.1-SNAPSHOT.jar   <-- 연습용
ARG JAR_FILE=target/*.jar

# 첫번째에 있는 걸, 두번째 있는 곳으로 
# 압축파일이면 압축을 해제한 상태로 파일을 복사합니다
ADD ${JAR_FILE} app.jar

# ENTRYPOINT 는 해당 컨테이너가 시작되었을 때 수행할 실행 명령을 정의하는 명령어
# Dockerfile 파일 내에서 1번만 정의가 가능합니다.
# ENTRYPOINT <실행할 명령어> 혹은 ENTRYPOINT ["실행 파일", "매개 변수 1", "매개 변수 2", ...] 형식으로 사용할 수 있습니다.
# 설명 : 실행하면 '컨테이너가 시작되면 java -jar /app.jar 쉘 명령어를 실행' 합니다.
ENTRYPOINT ["java", "-jar", "/app.jar"]


# (참고) https://velog.io/@skygl/Docker-%EC%8A%A4%ED%94%84%EB%A7%81-%EB%B6%80%ED%8A%B8-%EC%82%AC%EC%9D%B4%ED%8A%B8-%EA%B0%80%EC%9D%B4%EB%93%9C%EC%9D%98-Docker-%EC%97%B0%EB%8F%99%ED%95%98%EB%8A%94-%EB%AA%85%EB%A0%B9%EC%96%B4-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B8%B0