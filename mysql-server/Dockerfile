FROM mysql:8.0.12

COPY ./conf/mysqld.cnf /etc/mysql/conf.d

COPY ./sql/1_schema.sql /docker-entrypoint-initdb.d
COPY ./sql/2_init_memo_data.sql /docker-entrypoint-initdb.d

#ENTRYPOINT ["docker-entrypoint.sh"]

#EXPOSE 3306
#CMD ["mysqld"]