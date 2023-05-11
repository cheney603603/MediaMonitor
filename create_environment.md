

# 环境搭建

## 实验环境

云服务器名称：华为云HECS

硬件配置：2核 4GB内存 2Mb带宽

操作系统：CentOS 7.9 64bit

## Docker环境搭建

### 安装Docker依赖

```shell
[root@hecs-161794 ~]# yum install -y yum-utils device-mapper-persistent-data lvm2
```

安装结果：

```shell
Installed:
  device-mapper-persistent-data.x86_64 0:0.8.5-3.el7_9.2           lvm2.x86_64 7:2.02.187-6.el7_9.5          
  yum-utils.noarch 0:1.1.31-54.el7_8                              

Dependency Installed:
  device-mapper-event.x86_64 7:1.02.170-6.el7_9.5    device-mapper-event-libs.x86_64 7:1.02.170-6.el7_9.5   
  libaio.x86_64 0:0.3.109-13.el7                     lvm2-libs.x86_64 7:2.02.187-6.el7_9.5                  
  python-chardet.noarch 0:2.2.1-3.el7                python-kitchen.noarch 0:1.1.1-5.el7                    

Complete!
```

### 添加阿里云yum源

```shell
[root@hecs-161794 ~]# yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

添加结果：

```shell
Loaded plugins: fastestmirror
adding repo from: http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
grabbing file http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo to /etc/yum.repos.d/docker-ce.repo
repo saved to /etc/yum.repos.d/docker-ce.repo
```

重建yum缓存：

```shell
[root@hecs-161794 ~]# yum clean all
```

```shell
Loaded plugins: fastestmirror
Cleaning repos: base docker-ce-stable epel extras updates
Cleaning up list of fastest mirrors
```

```shell
[root@hecs-161794 ~]# yum makecache
```

```shell
Loaded plugins: fastestmirror
Determining fastest mirrors
base                                                                                  | 3.6 kB  00:00:00     
docker-ce-stable                                                                      | 3.5 kB  00:00:00     
epel                                                                                  | 4.7 kB  00:00:00     
extras                                                                                | 2.9 kB  00:00:00     
updates                                                                               | 2.9 kB  00:00:00     
(1/20): base/7/x86_64/group_gz                                                        | 153 kB  00:00:00     
(2/20): base/7/x86_64/primary_db                                                      | 6.1 MB  00:00:00     
(3/20): base/7/x86_64/filelists_db                                                    | 7.2 MB  00:00:00     
(4/20): base/7/x86_64/other_db                                                        | 2.6 MB  00:00:00     
(5/20): docker-ce-stable/7/x86_64/updateinfo                                          |   55 B  00:00:00     
(6/20): docker-ce-stable/7/x86_64/filelists_db                                        |  44 kB  00:00:00     
(7/20): epel/x86_64/group_gz                                                          |  99 kB  00:00:00     
(8/20): docker-ce-stable/7/x86_64/primary_db                                          | 107 kB  00:00:00     
(9/20): epel/x86_64/filelists_db                                                      |  12 MB  00:00:00     
(10/20): epel/x86_64/prestodelta                                                      | 2.2 kB  00:00:00     
(11/20): docker-ce-stable/7/x86_64/other_db                                           | 132 kB  00:00:00     
(12/20): epel/x86_64/primary_db                                                       | 7.0 MB  00:00:00     
(13/20): epel/x86_64/other_db                                                         | 3.4 MB  00:00:00     
(14/20): extras/7/x86_64/filelists_db                                                 | 276 kB  00:00:00     
(15/20): extras/7/x86_64/other_db                                                     | 149 kB  00:00:00     
(16/20): updates/7/x86_64/primary_db                                                  |  21 MB  00:00:00     
(17/20): extras/7/x86_64/primary_db                                                   | 249 kB  00:00:02     
(18/20): updates/7/x86_64/filelists_db                                                |  11 MB  00:00:09     
(19/20): updates/7/x86_64/other_db                                                    | 1.3 MB  00:00:09     
(20/20): epel/x86_64/updateinfo                                                       | 1.0 MB  00:00:11 
```

### 安装Docker

```shell
[root@hecs-161794 ~]# yum install docker-ce
```

```shell
=============================================================================================================
 Package                         Arch         Version                           Repository              Size
=============================================================================================================
Installing:
 docker-ce                       x86_64       3:23.0.6-1.el7                    docker-ce-stable        23 M
Installing for dependencies:
 audit-libs-python               x86_64       2.8.5-4.el7                       base                    76 k
 checkpolicy                     x86_64       2.5-8.el7                         base                   295 k
 container-selinux               noarch       2:2.119.2-1.911c772.el7_8         extras                  40 k
 containerd.io                   x86_64       1.6.21-3.1.el7                    docker-ce-stable        34 M
 docker-buildx-plugin            x86_64       0.10.4-1.el7                      docker-ce-stable        12 M
 docker-ce-cli                   x86_64       1:23.0.6-1.el7                    docker-ce-stable        13 M
 docker-ce-rootless-extras       x86_64       23.0.6-1.el7                      docker-ce-stable       8.8 M
 docker-compose-plugin           x86_64       2.17.3-1.el7                      docker-ce-stable        12 M
 fuse-overlayfs                  x86_64       0.7.2-6.el7_8                     extras                  54 k
 fuse3-libs                      x86_64       3.6.1-4.el7                       extras                  82 k
 libcgroup                       x86_64       0.41-21.el7                       base                    66 k
 libsemanage-python              x86_64       2.5-14.el7                        base                   113 k
 policycoreutils-python          x86_64       2.5-34.el7                        base                   457 k
 python-IPy                      noarch       0.75-6.el7                        base                    32 k
 setools-libs                    x86_64       3.3.8-4.el7                       base                   620 k
 slirp4netns                     x86_64       0.4.3-4.el7_8                     extras                  81 k

Transaction Summary
=============================================================================================================
Install  1 Package (+16 Dependent packages)

Total download size: 105 M
Installed size: 372 M
Is this ok [y/d/N]: y
```

安装结果：

```shell
Installed:
  docker-ce.x86_64 3:23.0.6-1.el7                                                                            

Dependency Installed:
  audit-libs-python.x86_64 0:2.8.5-4.el7                   checkpolicy.x86_64 0:2.5-8.el7                   
  container-selinux.noarch 2:2.119.2-1.911c772.el7_8       containerd.io.x86_64 0:1.6.21-3.1.el7            
  docker-buildx-plugin.x86_64 0:0.10.4-1.el7               docker-ce-cli.x86_64 1:23.0.6-1.el7              
  docker-ce-rootless-extras.x86_64 0:23.0.6-1.el7          docker-compose-plugin.x86_64 0:2.17.3-1.el7      
  fuse-overlayfs.x86_64 0:0.7.2-6.el7_8                    fuse3-libs.x86_64 0:3.6.1-4.el7                  
  libcgroup.x86_64 0:0.41-21.el7                           libsemanage-python.x86_64 0:2.5-14.el7           
  policycoreutils-python.x86_64 0:2.5-34.el7               python-IPy.noarch 0:0.75-6.el7                   
  setools-libs.x86_64 0:3.3.8-4.el7                        slirp4netns.x86_64 0:0.4.3-4.el7_8               

Complete!
```

### 设置启动项

```shell
[root@hecs-161794 ~]# systemctl start docker
```

```shell
[root@hecs-161794 ~]# systemctl enable docker
```

```shell
Created symlink from /etc/systemd/system/multi-user.target.wants/docker.service to /usr/lib/systemd/system/docker.service.
```

### 检查Docker运行状态

```shell
[root@hecs-161794 ~]# docker ps
```

```shell
CONTAINER ID   IMAGE     COMMAND   CREATED   STATUS    PORTS     NAMES
```

## 更新python

CentOS自带的是python2，更新为python3.7

### 安装新版openssl

安装依赖

```shell
[root@hecs-161794 ~]# yum install -y zlib zlib-dev openssl-devel sqlite-devel bzip2-devel libffi libffi-devel gcc gcc-c++
```

安装

```shell
[root@hecs-161794 ~]# wget http://www.openssl.org/source/openssl-1.1.1.tar.gz
[root@hecs-161794 ~]# tar -zxvf openssl-1.1.1.tar.gz
[root@hecs-161794 openssl-1.1.1]# ./config --prefix=$HOME/openssl shared zlib
[root@hecs-161794 openssl-1.1.1]# make && make install
[root@hecs-161794 openssl-1.1.1]# echo "export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$HOME/openssl/lib" >> $HOME/.bash_profile
[root@hecs-161794 openssl-1.1.1]# source $HOME/.bash_profile
```

### python安装

安装依赖

```shell
[root@hecs-161794 ~]# yum install openssl-devel bzip2-devel expat-devel gdbm-devel readline-devel sqlite-devel psmisc libffi-devel
```

将`https://www.python.org/ftp/python/3.7.9/Python-3.7.9.tgz`上传到服务器

解压python

```shell
[root@hecs-161794 ~]# tar -xzf Python-3.7.9.tgz 
```

切换到解压目录

```	shell
[root@hecs-161794 ~]# cd Python-3.7.9
```

创建python安装目录

```shell
[root@hecs-161794 Python-3.7.9]# mkdir  /usr/local/python3/
```

生成安装配置文件

```shell
[root@hecs-161794 Python-3.7.9]# ./configure --prefix=/usr/local/python3 --with-openssl=$HOME/openssl
```

执行编译和安装

```shell
[root@hecs-161794 Python-3.7.9]# make && make install
```

修改系统软链接

```shell
[root@hecs-161794 bin]# ln -s /usr/local/python3/bin/python3.7 /usr/bin/python3.7
[root@hecs-161794 bin]# ln -s /usr/local/python3/bin/pip3.7 /usr/bin/pip3.7
```

## Docker-Compose安装

### Docker-Compose介绍

Docker Compose是一个用来定义和运行复杂应用的Docker工具。一个使用Docker容器的应用，通常由多个容器组成。使用Docker Compose不再需要使用shell脚本来启动容器。 
Compose 通过一个配置文件来管理多个Docker容器，在配置文件中，所有的容器通过services来定义，然后使用docker-compose脚本来启动，停止和重启应用，和应用中的服务以及所有依赖服务的容器，非常适合组合使用多个容器进行开发的场景。

### 通过pip安装Docker-Compose

```shell
[root@hecs-161794 ~]# pip3.7 install docker-compose
Successfully installed PyYAML-5.4.1 attrs-23.1.0 bcrypt-4.0.1 cached-property-1.5.2 certifi-2023.5.7 cffi-1.15.1 charset-normalizer-3.1.0 cryptography-40.0.2 distro-1.8.0 docker-6.1.1 docker-compose-1.29.2 dockerpty-0.4.1 docopt-0.6.2 idna-3.4 importlib-metadata-6.6.0 jsonschema-3.2.0 packaging-23.1 paramiko-3.1.0 pycparser-2.21 pynacl-1.5.0 pyrsistent-0.19.3 python-dotenv-0.21.1 requests-2.30.0 six-1.16.0 texttable-1.6.7 typing-extensions-4.5.0 urllib3-2.0.2 websocket-client-0.59.0 zipp-3.15.0
```

### 测试安装结果

```shell
[root@hecs-161794 ~]# docker-compose --version
docker-compose version 1.29.2, build unknown
```

# Hadoop环境搭建

使用docker-compose快速部署hadoop集群

### 安装git

```shell
[root@hecs-161794 ~]# yum install git
Installed:
  git.x86_64 0:1.8.3.1-24.el7_9                                                                                                       
Dependency Installed:
  perl-Error.noarch 1:0.17020-2.el7         perl-Git.noarch 0:1.8.3.1-24.el7_9         perl-TermReadKey.x86_64 0:2.30-20.el7        
  rsync.x86_64 0:3.1.2-12.el7_9            

Complete!
```

从`https://github.com/big-data-europe/docker-hadoop.git`获得docker-compose.yml资源清单，来完成hadoop部署

### 克隆docker-hadoop脚本

```shell
[root@hecs-161794 ~]# git clone https://github.com/big-data-europe/docker-hadoop.git
```

### 查看docker-hadoop配置

```shell
[root@hecs-161794 docker-hadoop]# vim docker-compose.yml

version: "3"

services:
  namenode:
    image: bde2020/hadoop-namenode:2.0.0-hadoop3.2.1-java8
    container_name: namenode
    restart: always
    ports:
      - 9870:9870
      - 9000:9000
    volumes:
      - hadoop_namenode:/hadoop/dfs/name
    environment:
      - CLUSTER_NAME=test
    env_file:
      - ./hadoop.env

  datanode1:
    image: bde2020/hadoop-datanode:2.0.0-hadoop3.2.1-java8
    container_name: datanode1
    restart: always
    volumes:
      - hadoop_datanode1:/hadoop/dfs/data
    environment:
      SERVICE_PRECONDITION: "namenode:9870"
    env_file:
      - ./hadoop.env
  
  datanode2:
    image: bde2020/hadoop-datanode:2.0.0-hadoop3.2.1-java8
    container_name: datanode2
    restart: always
    volumes:
      - hadoop_datanode2:/hadoop/dfs/data
    environment:
      SERVICE_PRECONDITION: "namenode:9870"
    env_file:
      - ./hadoop.env
  
  resourcemanager:
    image: bde2020/hadoop-resourcemanager:2.0.0-hadoop3.2.1-java8
    container_name: resourcemanager
    restart: always
    environment:
      SERVICE_PRECONDITION: "namenode:9000 namenode:9870 datanode:9864"
    env_file:
      - ./hadoop.env

  nodemanager1:
    image: bde2020/hadoop-nodemanager:2.0.0-hadoop3.2.1-java8
    container_name: nodemanager
    restart: always
    environment:
      SERVICE_PRECONDITION: "namenode:9000 namenode:9870 datanode:9864 resourcemanager:8088"
    env_file:
      - ./hadoop.env

  historyserver:
    image: bde2020/hadoop-historyserver:2.0.0-hadoop3.2.1-java8
    container_name: historyserver
    restart: always
    environment:
      SERVICE_PRECONDITION: "namenode:9000 namenode:9870 datanode:9864 resourcemanager:8088"
    volumes:
      - hadoop_historyserver:/hadoop/yarn/timeline
    env_file:
      - ./hadoop.env


volumes:
  hadoop_namenode:
  hadoop_datanode1:
  hadoop_datanode2:
  hadoop_historyserver:

```

Hadoop集群共分配了5个节点，包括：namenode（管理命名空间）、datanode（提供读写请求）、nodemanager（运行程序并监控节点）、resourcemanager（全局资源监控）、historyserver（记录作业信息）节点各一个。

上面是docker-compose.yml的相关配置，其中通过`volumes`，可以将hdfs的文件映射到本地，这样一来即使我们删除了容器，只要compose目录下的文件都在，那么我们在创建了新的集群后，原有的hdfs文件就会恢复，不需要重新上传了。

### 启动Hadoop集群

```shell
[root@hecs-161794 docker-hadoop]# docker-compose up -d
```

### 查看Docker状态

```shell
[root@hecs-161794 docker-hadoop]# docker ps
CONTAINER ID   IMAGE                                                    COMMAND                  CREATED          STATUS                             PORTS                                                                                  NAMES
97d1950ea488   bde2020/hadoop-datanode:2.0.0-hadoop3.2.1-java8          "/entrypoint.sh /run…"   26 seconds ago   Up 24 seconds (health: starting)   9864/tcp                                                                               datanode2
31557084e7b8   bde2020/hadoop-datanode:2.0.0-hadoop3.2.1-java8          "/entrypoint.sh /run…"   26 seconds ago   Up 24 seconds (health: starting)   9864/tcp                                                                               datanode1
ae4877d6412b   bde2020/hadoop-namenode:2.0.0-hadoop3.2.1-java8          "/entrypoint.sh /run…"   33 minutes ago   Up 24 seconds (health: starting)   0.0.0.0:9000->9000/tcp, :::9000->9000/tcp, 0.0.0.0:9870->9870/tcp, :::9870->9870/tcp   namenode
3f8e5128e368   bde2020/hadoop-nodemanager:2.0.0-hadoop3.2.1-java8       "/entrypoint.sh /run…"   33 minutes ago   Up 24 seconds (health: starting)   8042/tcp                                                                               nodemanager
e5f1a004c106   bde2020/hadoop-resourcemanager:2.0.0-hadoop3.2.1-java8   "/entrypoint.sh /run…"   33 minutes ago   Up 24 seconds (health: starting)   8088/tcp                                                                               resourcemanager
e3fccbb83c75   bde2020/hadoop-historyserver:2.0.0-hadoop3.2.1-java8     "/entrypoint.sh /run…"   33 minutes ago   Up 24 seconds (health: starting)   8188/tcp                 
```

### 访问Hadoop-NameNode

浏览器输入：http://http://123.249.6.46:9870/

Hadoop集群主界面

![1](.\env_imgs\1.png)

点击进入DataNodes，可以看到有两个DataNode正在运行

![2](.\env_imgs\2.png)

查看HDFS界面：Utillities->Browse the file system

![3](.\env_imgs\3.png)

至此，基于docker容器的Hadoop集群搭建完成。
