#!/bin/sh
rm -f tpid
nohup java -jar mytest.jar --spring.config.location=application.yml > mylog.log 2>&1 &
echo $! > tpid
echo Start Success
