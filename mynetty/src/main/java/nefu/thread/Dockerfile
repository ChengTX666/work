FROM centos:7
LABEL authors="ctx"
# 添加文件到镜像中
COPY jdk-8u202-linux-x64.tar.gz /usr/local/
COPY TongWeb7.0.4.9_M5_Enterprise_Linux.tar.gz /usr/local/
COPY license.dat /usr/local/

# 创建目标目录并解压文件
RUN tar -zxvf /usr/local/jdk-8u202-linux-x64.tar.gz

RUN tar -zxvf /usr/local/TongWeb7.0.4.9_M5_Enterprise_Linux.tar.gz

RUN cp /usr/local/license.dat /usr/local/TongWeb7.0.4.9_M5_Enterprise_Linux

# 设置环境变量
ENV JAVA_HOME /usr/local/jdk1.8.0_202
ENV CLASSPATH $JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
ENV PATH $JAVA_HOME/bin:$PATH


RUN chmod +x /usr/local/TongWeb7.0.4.9_M5_Enterprise_Linux/bin/startservernohup.sh

# 暴露端口
EXPOSE 9060

# 启动脚本
CMD ["/usr/local/TongWeb7.0.4.9_M5_Enterprise_Linux/bin/startservernohup.sh"]