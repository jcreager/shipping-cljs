FROM openjdk:8-alpine

COPY target/uberjar/shipping-cljs.jar /shipping-cljs/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/shipping-cljs/app.jar"]
