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

all service start up.

```text
> docker-compose up -d
```

#### mysql-server

just mysql-server start up.

```text
> docker-compose up -d mysql-server
```

#### redis-server

just redis-server start up.

```text
> docker-compose up -d redis-server
```

#### app-server

just app-server start up.

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

### execute any commands

#### mysql-server

**bash**

```text
> docker-compose exec mysql-server bash --login
```

**mysql cli**

```text
> docker-compose exec mysql-server mysql -u root -p
```

**mysql cli**

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

application start

```text
$ ./start.sh
```


##### access web page

index

```text
http://localhost:9000/
```

### app-server scale

modified docker-compose.yml

before

```text
  app-server:
    ports:
      - 9000:9000
```

after changed

```text
  app-server:
    ports:
      - 9000-9001:9000
```

launch 2 app-servers

```text
> docker-compose up -d --scale app-server=2
Creating network "docker-demo-redis_redis-network" with driver "bridge"
Creating mysql-server ... done
Creating redis-server ... done
WARNING: The "app-server" service specifies a port on the host. If multiple containers for this service are created on a single host, the port will clash.
Creating docker-demo-redis_app-server_1 ... done
Creating docker-demo-redis_app-server_2 ... done
```

access to #1 app-server

```text
> docker-compose exec --index=1 app-server bash --login
```

check the port used by #1 app-server

```text
> docker-compose port --index=1 app-server 9000
0.0.0.0:9000
```

access to #2 app-server

```text
> docker-compose exec --index=2 app-server bash --login
```

check the port used by #2 app-server

```text
> docker-compose port --index=2 app-server 9000
0.0.0.0:9001
```

#### MySQL

* Process ids #8,#9 connected from #1 app-server
* Process ids #10,#11 connected from #2 app-server

```text
root@localhost [(none)] > show processlist;
+----+-----------------+------------------+-----------+---------+------+------------------------+------------------+
| Id | User            | Host             | db        | Command | Time | State                  | Info             |
+----+-----------------+------------------+-----------+---------+------+------------------------+------------------+
|  4 | event_scheduler | localhost        | NULL      | Daemon  |  843 | Waiting on empty queue | NULL             |
|  8 | test_user       | 172.22.0.4:48354 | sample_db | Sleep   |  674 |                        | NULL             |
|  9 | test_user       | 172.22.0.4:48356 | sample_db | Sleep   |  676 |                        | NULL             |
| 10 | test_user       | 172.22.0.5:40842 | sample_db | Sleep   |  263 |                        | NULL             |
| 11 | test_user       | 172.22.0.5:40844 | sample_db | Sleep   |  265 |                        | NULL             |
| 13 | root            | localhost        | NULL      | Query   |    0 | starting               | show processlist |
+----+-----------------+------------------+-----------+---------+------+------------------------+------------------+
6 rows in set (0.00 sec)
```

#### Redis

* client id #3 connected from #2 app-server
* client id #4 connected from #2 app-server

```text
127.0.0.1:6379> client list
id=3 addr=172.22.0.4:37174 fd=9 name= age=1621 idle=1621 flags=N db=0 sub=0 psub=0 multi=-1 qbuf=0 qbuf-free=0 obl=0 oll=0 omem=0 events=r cmd=get
id=4 addr=172.22.0.5:44918 fd=10 name= age=1051 idle=1051 flags=N db=0 sub=0 psub=0 multi=-1 qbuf=0 qbuf-free=0 obl=0 oll=0 omem=0 events=r cmd=get
id=5 addr=127.0.0.1:41110 fd=11 name= age=79 idle=0 flags=N db=0 sub=0 psub=0 multi=-1 qbuf=0 qbuf-free=32768 obl=0 oll=0 omem=0 events=r cmd=client
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
