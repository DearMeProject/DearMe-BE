# ==============================
# 1단계: Gradle로 빌드 (Builder Stage)
# ==============================
FROM gradle:8.4-jdk21 AS builder
WORKDIR /app

# Gradle 설정 파일 복사
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# 의존성 미리 다운로드 (캐시 최적화)
RUN ./gradlew dependencies --no-daemon || return 0

# 전체 프로젝트 복사
COPY . .

# 빌드 (테스트 제외)
RUN ./gradlew clean build -x test --no-daemon

# ==============================
# 2단계: 애플리케이션 실행 이미지 (Runtime Stage)
# ==============================
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app

# 빌드 결과물 복사
COPY --from=builder /app/build/libs/*.jar app.jar

# 포트 오픈
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
