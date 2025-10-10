FROM openjdk:21
EXPOSE 8080
ADD backend/target/Stock_App.jar stock_app.jar
ENTRYPOINT ["java", "-jar", "stock_app.jar"]