FROM openjdk:11
EXPOSE 9090
ADD build/libs/ibp-data.war ibp-data.war
ENV TZ=Asia/Kolkata
ENTRYPOINT ["java", "-jar", "/ibp-data.war"]