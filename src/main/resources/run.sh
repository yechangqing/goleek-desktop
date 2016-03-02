#!/bin/bash 

classpath=.
classpath=$classpath:./goleek-desktop-2.0.jar
classpath=$classpath:./lib/aopalliance-1.0.jar
classpath=$classpath:./lib/aspectjweaver-1.8.4.jar
classpath=$classpath:./lib/baseframework-client-1.0.jar
classpath=$classpath:./lib/commons-codec-1.9.jar
classpath=$classpath:./lib/commons-logging-1.2.jar
classpath=$classpath:./lib/gson-2.2.4.jar
classpath=$classpath:./lib/httpclient-4.4.1.jar
classpath=$classpath:./lib/httpcore-4.4.1.jar
classpath=$classpath:./lib/log4j-1.2.17.jar
classpath=$classpath:./lib/spring-aop-4.0.9.RELEASE.jar
classpath=$classpath:./lib/spring-aspects-4.0.9.RELEASE.jar
classpath=$classpath:./lib/spring-beans-4.0.9.RELEASE.jar
classpath=$classpath:./lib/spring-context-4.0.9.RELEASE.jar
classpath=$classpath:./lib/spring-core-4.0.9.RELEASE.jar
classpath=$classpath:./lib/spring-expression-4.0.9.RELEASE.jar
classpath=$classpath:./lib/spring-web-4.0.9.RELEASE.jar

main=org.yecq.goleek.desktop.Main

java -cp $classpath $main
