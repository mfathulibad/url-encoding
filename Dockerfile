# Menggunakan image base yang memiliki Java 17
FROM adoptopenjdk/openjdk17:alpine-slim

# Menambahkan metadata untuk Docker image
LABEL maintainer="Your Name <your.email@example.com>"

# Menyalin JAR yang dihasilkan oleh Maven ke dalam container
COPY url-encoding.jar /app/url-encoding.jar

# Port yang akan digunakan oleh aplikasi Spring Boot
EXPOSE 8081

# Perintah yang akan dijalankan ketika container berjalan
CMD ["java", "-jar", "/app/url-encoding.jar"]