FROM centos:7

RUN set -x && \
    yum update -y && \
    yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel && \
    yum reinstall -y glibc-common && \
    yum -y clean all

ENV JAVA_HOME /usr/lib/jvm/java-1.8.0-openjdk

# locale settings
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja

RUN set -x && \
    localedef -i ja_JP -c -f UTF-8 ja_JP.UTF-8 && \
    unlink /etc/localtime && \
    ln -s /usr/share/zoneinfo/Japan /etc/localtime

# application user settings
ENV HOME /home/app

RUN set -x && \
    groupadd app && \
    useradd -g app -d /home/app -s /bin/bash app

WORKDIR $HOME
USER app

COPY start.sh .

EXPOSE 9000

# ENTRYPOINT [ "/bin/bash" ]
# CMD ["/bin/bash"]
# CMD [ "./start.sh" ]