@echo off

set classpath=.
set classpath=%classpath%;.\goleek-desktop-1.1.jar
set classpath=%classpath%;.\lib\aopalliance-1.0.jar
set classpath=%classpath%;.\lib\aspectjweaver-1.8.4.jar
set classpath=%classpath%;.\lib\baseframework-plain-1.0.jar
set classpath=%classpath%;.\lib\commons-codec-1.9.jar
set classpath=%classpath%;.\lib\commons-dbcp2-2.1.jar
set classpath=%classpath%;.\lib\commons-logging-1.2.jar
set classpath=%classpath%;.\lib\commons-pool2-2.3.jar
set classpath=%classpath%;.\lib\gson-2.2.4.jar
set classpath=%classpath%;.\lib\httpclient-4.4.1.jar
set classpath=%classpath%;.\lib\httpcore-4.4.1.jar
set classpath=%classpath%;.\lib\log4j-1.2.17.jar
set classpath=%classpath%;.\lib\mysql-connector-java-5.1.30.jar
set classpath=%classpath%;.\lib\record-1.0.jar
set classpath=%classpath%;.\lib\spring-aop-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-aspects-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-beans-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-context-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-core-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-expression-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-jdbc-4.0.9.RELEASE.jar
set classpath=%classpath%;.\lib\spring-tx-4.0.9.RELEASE.jar

set main=org.yecq.goleek.desktop.Main

java -cp %classpath% %main%