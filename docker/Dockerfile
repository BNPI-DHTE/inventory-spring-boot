FROM eclipse-temurin:17-jdk-alpine as builder
WORKDIR application
COPY ./target/*.jar inventory.jar
RUN java -Djarmode=layertools -jar inventory.jar extract

FROM eclipse-temurin:17-jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
CMD ["java", \
  "org.springframework.boot.loader.JarLauncher"]
EXPOSE 8080