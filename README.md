# docker demo redis

## project directory structure

```text
/project
 |
 +--- docker-compose.yml
 +--- README.md
 |
 +--- /demo-redis-spring
 |
 +--- /app-server
 |      |
 |      +--- Dockerfile
 |
 +--- /mysql-server
 |      |
 |      +--- Dockerfile
 |
 +--- /redis-server
        |
        +--- Dockerfile
```

## docker image

### app-server

```text
FROM centos:7
```

Java Version

```bash
$ java -version
openjdk version "1.8.0_181"
OpenJDK Runtime Environment (build 1.8.0_181-b13)
OpenJDK 64-Bit Server VM (build 25.181-b13, mixed mode)
```

OS Version

```bash
$ cat /etc/os-release
NAME="CentOS Linux"
VERSION="7 (Core)"
ID="centos"
ID_LIKE="rhel fedora"
VERSION_ID="7"
PRETTY_NAME="CentOS Linux 7 (Core)"
ANSI_COLOR="0;31"
CPE_NAME="cpe:/o:centos:centos:7"
HOME_URL="https://www.centos.org/"
BUG_REPORT_URL="https://bugs.centos.org/"

CENTOS_MANTISBT_PROJECT="CentOS-7"
CENTOS_MANTISBT_PROJECT_VERSION="7"
REDHAT_SUPPORT_PRODUCT="centos"
REDHAT_SUPPORT_PRODUCT_VERSION="7"
```

### mysql-server

```text
FROM mysql:8.0.12
```

MySQL Version

```bash
# mysqld --version
/usr/sbin/mysqld  Ver 8.0.12 for Linux on x86_64 (MySQL Community Server - GPL)
```

OS Version

```bash
# cat /etc/os-release
PRETTY_NAME="Debian GNU/Linux 9 (stretch)"
NAME="Debian GNU/Linux"
VERSION_ID="9"
VERSION="9 (stretch)"
ID=debian
HOME_URL="https://www.debian.org/"
SUPPORT_URL="https://www.debian.org/support"
BUG_REPORT_URL="https://bugs.debian.org/"
```

### redis-server

```text
FROM redis:4.0.11
```

Redis Version

```bash
# redis-server --version
Redis server v=4.0.11 sha=00000000:0 malloc=jemalloc-4.0.3 bits=64 build=74253224a862200c
```

OS Version

```bash
# cat /etc/os-release
PRETTY_NAME="Debian GNU/Linux 9 (stretch)"
NAME="Debian GNU/Linux"
VERSION_ID="9"
VERSION="9 (stretch)"
ID=debian
HOME_URL="https://www.debian.org/"
SUPPORT_URL="https://www.debian.org/support"
BUG_REPORT_URL="https://bugs.debian.org/"
```

## docker-compose command

### build

```text
> docker-compose build
```

```text
> docker-compose build --no-cache
```

### up

```text
> docker-compose up -d
```

#### mysql-server

```text
> docker-compose up -d mysql-server
```

#### redis-server

```text
> docker-compose up -d redis-server
```

#### application

```text
> docker-compose up -d app-server
```

### ps

```text
> docker-compose ps
```

### top

```text
> docker-compose top
```

### down

```text
> docker-compose down
```

### exec

#### mysql-server

**bash**

```text
> docker-compose exec mysql-server bash --login
```

**mysql**

```text
> docker-compose exec mysql-server mysql -u root -p
```

**mysql**

```text
> docker-compose exec mysql-server mysql -u test_user -p --database=sample_db
```

#### redis-server

**bash**

```text
> docker-compose exec redis-server bash --login
```

**redis-cli**

```text
> docker-compose exec redis-server redis-cli
```

#### app-server

**bash**

```text
> docker-compose exec app-server bash --login
```

```text
$ ./start.sh
```


##### application

index

```text
http://localhost:9000/
```

# misc

## Docker hub

[centos](https://hub.docker.com/_/centos/)
[mysql](https://hub.docker.com/_/mysql/)
[redis](https://hub.docker.com/_/redis/)

## configuration

### mysql

install location

/var/lib/mysql

#### conf


### redis

#### conf

https://raw.githubusercontent.com/antirez/redis/4.0/redis.conf

before

```text
bind 127.0.0.1
logfile ""
# maxclients 10000
# requirepass foobared
appendonly no
```

after changed

```text
# bind 127.0.0.1
logfile /var/log/redis.log
maxclients 100
requirepass foobared
appendonly yes
```
