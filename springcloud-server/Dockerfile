#指定基础镜像，本地没有会从 dockerHub pull 下来
FROM java:8
#作者
MAINTAINER 1941456312@qq.com
WORKDIR /opt/springcloud
#移动可执行jar到基础镜像目录种
ADD ./target/springcloud-server-1.0-SNAPSHOT.jar ./server.jar
#镜像要暴露的端口，如要使用端口，在执行docker run 命令时 -p生效
EXPOSE 4002
#对外提供可挂载的目录Docker的数据持久化即使数据不随着container的结束而结束，数据存在于host机器上——要么存在于host的某个指定目录中（使用bind mount），要么使用docker自己管理的volume（/var/lib/docker/volumes下）。
VOLUME /opt/springcloud/log
#镜像启动指令
ENTRYPOINT ["nohup","java","-Djava.security.egd=file:/dev/./urandom","-jar","./server.jar",">","./server.log","2>&1","&"]
#可以被替代的命令 多个CMD 最后一个生效
CMD ["--spring.profiles.active=dev"]