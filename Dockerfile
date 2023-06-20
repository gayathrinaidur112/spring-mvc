# Getting Tomcat Image for java-11
FROM tomcat:8-alpine
# Copying war file from current directory to tomcat dir
ADD target/FirstJavaProgramInSpringMVC.war /usr/local/tomcat/webapps/

EXPOSE 8080

CMD ["catalina.sh", "run"]