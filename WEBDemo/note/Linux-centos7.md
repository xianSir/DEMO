#一安装:
   1.解决yum命令无法连接镜像资源:

    (1) cd /etc/sysconfig/network-scripts  进入该文件夹后，然后再ls一下，查看是否有ifcfg-ens33，有时是ifcfg-eth0
    (2) vi ifcfg-ens33 进入VI的一般模式，移动光标到ONBOOT=no
    (3) 按下"i"进入VI的编辑模式，把no删除改为yes

   2.配置网络
   ![avator](/note/img/固定ip.png)

    编辑 vim /etc/sysconfig/network-scripts/ifcfg-enp0s3

    (1).可以为BOOTPRTO（表示网卡配置静态还是动态IP地址）设置以下4种选项

    bootp：表示使用BOOTP协议。

    dhcp：表示使用DHCP协议动态获取IP地址。

    static：表示手工设置静态IP地址。

    （2）ONBOOT=yes

    表示启动系统时是否激活该网卡，yes表示激活，no表示不激活。

    systemctl restart network  (重启网络服务)

    配置固定ip
    bootproto=static
    onboot=yes
    在最后加上几行，IP地址、子网掩码、网关、dns服务器
    IPADDR=192.168.1.160
    NETMASK=255.255.255.0
    GATEWAY=192.168.1.1
    DNS1=119.29.29.29
    DNS2=8.8.8.8
   3.解决ifconfig不能使用

    yum search ifconfig
    yum -y install net-tools


#普通用户切换root
    su root


#二 防火墙
    1、查看firewall服务状态
        systemctl status firewalld
    2、查看firewall的状态
        firewall-cmd --state
    3、开启、重启、关闭、firewalld.service服务
         开启: service firewalld start
         重启: service firewalld restart
         关闭:service firewalld stop
    4、查看防火墙规则
        firewall-cmd --list-all
    5、查询、开放、关闭端口
    # 查询端口是否开放
        firewall-cmd --query-port=8080/tcp
    # 开放80端口
        firewall-cmd --permanent --add-port=80/tcp
    # 移除端口
        firewall-cmd --permanent --remove-port=8080/tcp
    #重启防火墙(修改配置后要重启防火墙)
        firewall-cmd --reload
    # 参数解释
        1、firwall-cmd：是Linux提供的操作firewall的一个工具；
        2、--permanent：表示设置为持久；
        3、--add-port：标识添加的端口；
   CentOS 7.0默认使用的是firewall作为防火墙，这里改为iptables防火墙。

    1、关闭firewall：
    systemctl stop firewalld.service
    systemctl disable firewalld.service
    systemctl mask firewalld.service

    2、安装iptables防火墙
    yum install iptables-services -y

    3.启动设置防火墙

    # systemctl enable iptables
    # systemctl start iptables

    4.查看防火墙状态

    systemctl status iptables

    5编辑防火墙，增加端口
    vi /etc/sysconfig/iptables #编辑防火墙配置文件
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 22 -j ACCEPT
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 80 -j ACCEPT
    -A INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT
    :wq! #保存退出

    3.重启配置，重启系统
    systemctl restart iptables.service #重启防火墙使配置生效
    systemctl enable iptables.service #设置防火墙开机启动

#SSH
    安装ssh程序包
    打开系统命令行，输入以下命令下载包。

    yum install -y openssh-server
    等待几分钟就下载好了。

    启动ssh服务
    接下来我们运行以下命令使ssh服务可以长时间在线运行。

    启动ssh服务程序
    systemctl start sshd
    开机自启动
    systemctl enable sshd
    开启防火墙服务
    firewall-cmd --add-service=ftp/tcp --permanent
    firewall-cmd --add-service=http/tcp --permanent
    firewall-cmd --reload
#三 安装 配置jdk
   1.安装

        tar zxvf FileName.tar

   2.配置

        vim /etc/profile
        export JAVA_HOME=/opt/jdk1.8
        export CLASSPATH=.:${JAVA_HOME}/jre/lib/rt.jar:${JAVA_HOME}/lib/dt.jar:${JAVA_HOME}/lib/tools.jar
        export PATH=$PATH:${JAVA_HOME}/bin

    通过 source /etc/profile 命令使更改的配置立即生效
#安装mysql  注安装 yum -y install autoconf
   一、安装YUM Repo

    1、由于CentOS 的yum源中没有mysql，需要到mysql的官网下载yum repo配置文件。
    下载命令：
    wget https://dev.mysql.com/get/mysql57-community-release-el7-9.noarch.rpm
    2、然后进行repo的安装：
    rpm -ivh mysql57-community-release-el7-9.noarch.rpm
    执行完成后会在/etc/yum.repos.d/目录下生成两个repo文件mysql-community.repo mysql-community-source.repo
   二、使用yum命令即可完成安装

    注意：必须进入到 /etc/yum.repos.d/目录后再执行以下脚本
    1、安装命令：
        yum install mysql-server
    2、启动msyql：
        systemctl start mysqld #启动MySQL
    3、获取安装时的临时密码（在第一次登录时就是用这个密码）：
        grep 'temporary password' /var/log/mysqld.log
    4、倘若没有获取临时密码，则
    4.1、删除原来安装过的mysql残留的数据
        rm -rf /var/lib/mysql
    4.2.再启动mysql
        systemctl start mysqld #启动MySQL
#使用本地文件安装mysql
    1.下载mysql Linux版  解压缩
    2.安装自动配置  yum -y install autoconf
    2.删除冲突依赖  rpm -qa | grep postfix   rpm -qa | grep mariadb
    3.rpm -ivh MySQL-client-5.6.46-1.el7.x86_64.rpm
                MySQL-devel-5.6.46-1.el7.x86_64.rpm
                MySQL-shared-5.6.46-1.el7.x86_64.rpm
                MySQL-server-5.6.46-1.el7.x86_64.rpm
                ...
    4.配置my.cnf文件  加载顺序:/etc/my.cnf /etc/mysql/my.cnf /usr/etc/my.cnf ~/.my.cnf
    5.service mysql start/stop
    6.systemctl enable mysqld
    配置环境变量
    export MYSQL_HOME=/usr/local/mysql
    export PATH=$PATH:${MYSQL_HOME}/bin

    设置密码 :UPDATE user SET password=PASSWORD('123456') WHERE user='root';
       权限: FLUSH PRIVILEGES;

#开启端口
   mysql3306

    firewall-cmd --zone=public --add-port=3306/tcp --permanent
    firewall-cmd --reload




