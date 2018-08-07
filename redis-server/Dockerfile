FROM redis:4.0.11

COPY ./conf/redis.conf /usr/local/etc/redis/redis.conf

RUN set -x && \
    touch /var/log/redis.log && \
    chown redis:root /var/log/redis.log && \
    chmod 664 /var/log/redis.log

# ENTRYPOINT ["docker-entrypoint.sh"]
# EXPOSE 6379

CMD [ "redis-server", "/usr/local/etc/redis/redis.conf" ]
