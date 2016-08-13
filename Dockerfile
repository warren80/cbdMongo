FROM centos

EXPOSE 8080
RUN mkdir /opt/backendApp
WORKDIR /opt/backendApp
RUN yum update -y
RUN yum install java-1.8.0-openjdk -y
RUN curl 192.168.0.15:80/target/cbd-app-0.1.0.jar > app.jar

ENTRYPOINT java -jar /opt/backendApp/app.jar


