cd yiqun
gradle build -x test

scp ./yiqun-service/build/libs/yiqun-service.war root@114.215.94.219:/home/uumai/tools/apache-tomcat-7.0.68/webapps/


cd ..
mvn install 
scp ./target/LicenseServer.war root@114.215.94.219:/home/uumai/tools/apache-tomcat-7.0.68/webapps/