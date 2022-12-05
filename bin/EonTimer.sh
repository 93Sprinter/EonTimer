#!/bin/sh
java -jar -XX:+UseSerialGC -Xss512k -XX:MaxRAM=72m eon-timer.jar
