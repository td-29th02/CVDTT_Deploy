# Sử dụng hình ảnh OpenJDK 24
FROM eclipse-temurin:24-jdk

# Đặt thư mục làm việc
WORKDIR /app

# Sao chép JAR file từ thư mục target vào container
COPY /target/cafe-0.0.1-SNAPSHOT.jar app.jar

# Chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]

# Mở port cho ứng dụng
EXPOSE 8080