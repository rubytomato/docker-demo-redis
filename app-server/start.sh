#!/bin/bash

set -ex

java -Xmx512m -Xms512m -XX:MaxMetaspaceSize=256m -verbose:gc -Xloggc:app-gc.log -jar /var/target/demo.jar
