FROM amazoncorretto:17
WORKDIR /app
COPY target/URL-Shortener-0.0.1-SNAPSHOT.jar /app/url_shortener.jar
EXPOSE 8080
CMD ["java", "-jar", "url_shortener.jar"]