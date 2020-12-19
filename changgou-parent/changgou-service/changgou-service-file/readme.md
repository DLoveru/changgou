### 5.2 FastDFS搭建
我们使用Docker搭建FastDFS的开发环境

拉取镜像

```properties
docker pull morunchang/fastdfs
```

运行tracker

```properties
docker run -d --name tracker --net=host morunchang/fastdfs sh tracker.sh
```

运行storage

```properties
docker run -d --name storage --net=host -e TRACKER_IP=<your tracker server address>:22122 -e GROUP_NAME=<group name> morunchang/fastdfs sh storage.sh
```

- 使用的网络模式是–net=host, <your tracker server address> 替换为你机器的Ip即可  
- <group name> 是组名，即storage的组
- 如果想要增加新的storage服务器，再次运行该命令，注意更换 新组名

（4）修改nginx的配置  

进入storage的容器内部，修改nginx.conf

```
docker exec -it storage  /bin/bash
```

进入后

vi /etc/nginx/conf/nginx.conf 

添加以下内容

```
location ~/M00 {
    root data/fastdfs_data/data;
    ngx_fastdfs_module;
 }
```
注意：storage.conf 的http.port要与nginx的配置 listen 保持一致。
server {
        listen      8888;
        server_name  localhost;
        location / {
            root   html;
            index  index.html index.htm;
        }
        location ~ /M00 {
                    root /data/fast_data/data;
                    ngx_fastdfs_module;
        }
}


（5）退出容器

```
exit
```

（6）重启storage容器

```
docker restart storage
```

