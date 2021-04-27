#安装JDK步骤
    1、先从网上下载jdk(jdk-7u1-linux-i586.rpm)，下载地址:http://www.oracle.com/technetwork/java/javase/downloads/jdk-7u1-download-513651.html，下载后放在/home目录中，当然其它地方也行。
    2、进入安装目录 #cd /home #cp jdk-7u1-linux-i586.rpm /usr/local #cd /usr/local 给所有用户添加可执行的权限
    #rpm -ivh jdk-7u1-linux-i586.rpm
    或
    1. 解压文件 tar zxvf fileName
    3、设置环境变量
    #vi /etc/profile
    打开后，在文档最下方加上以下环境变量配置代码：
    export JAVA_HOME=/usr/java/javajdk1.7.0_01
    export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
    export PATH=$JAVA_HOME/bin:$PATH
    注意：export PATH=$JAVA_HOME/bin:$PATH，注意将$PATH放到最后。以免造成新旧版本问题。
    编辑profile文档立即生效  source /etc/profile
    4、检查JDK是否安装成功。 #java -
    version 如果看到JVM版本及相关信息，即安装成功！
#安装mysql
傻瓜式教程:

    #安装mysql
    apt  install mysql-server
    #检查没mysql是否安装成功
    netstat -tap | grep mysql
    #登录mysql
    mysql -u root -p
    了确保数据库的安全性和正常运转，对数据库进行初始化操作。这个初始化操作涉及下面5个步骤。
    （1）安装验证密码插件。
    （2）设置root管理员在数据库中的专有密码。
    （3）随后删除匿名账户，并使用root管理员从远程登录数据库，以确保数据库上运行的业务的安全性。
    （4）删除默认的测试数据库，取消测试数据库的一系列访问权限。
    （5）刷新授权列表，让初始化的设定立即生效。
    对于上述数据库初始化的操作步骤，在下面的输出信息旁边我做了简单注释。
    root@ubuntu-virtual-machine:~# mysql_secure_installation

    Securing the MySQL server deployment.
    Connecting to MySQL using a blank password.
    VALIDATE PASSWORD PLUGIN can be used to test.test passwords
    and improve security. It checks the strength of password
    and allows the users to set only those passwords which are
    secure enough. Would you like to setup VALIDATE PASSWORD plugin?    # 要安装验证密码插件吗?
    Press y|Y for Yes, any other key for No: N    # 这里我选择N
    Please set the password for root here.
    New password:   # 输入要为root管理员设置的数据库密码
    Re-enter new password:   # 再次输入密码
    By default, a MySQL installation has an anonymous user,
    allowing anyone to log into MySQL without having to have
    a user account created for them. This is intended only for
    testing, and to make the installation go a bit smoother.
    You should remove them before moving into a production
    environment.
    Remove anonymous users? (Press y|Y for Yes, any other key for No) : y     # 删除匿名账户
    Success.
    Normally, root should only be allowed to connect from
    'localhost'. This ensures that someone cannot guess at
    the root password from the network.
    Disallow root login remotely? (Press y|Y for Yes, any other key for No) : N    # 禁止root管理员从远程登录，这里我没有禁止
    ... skipping.
    By default, MySQL comes with a database named 'test.test' that
    anyone can access. This is also intended only for testing,
    and should be removed before moving into a production
    environment.
    Remove test.test database and access to it? (Press y|Y for Yes, any other key for No) : y   # 删除test数据库并取消对它的访问权限
    - Dropping test.test database...
    Success.
    - Removing privileges on test.test database...
    Success.
    Reloading the privilege tables will ensure that all changes
    made so far will take effect immediately.
    Reload privilege tables now? (Press y|Y for Yes, any other key for No) : y   # 刷新授权表，让初始化后的设定立即生效
    Success.
    All done!
    #检查mysql服务状态：
    systemctl status mysql

    #再次用mysql -u root -p命令，Enter password:处输入刚设置的密码，回车，就能够进入mysql数据库。
    #使用 use mysql; 命令打开mysql命名的数据库，显示当前数据库的表：show tables;
    #查询user表里的数据：select * from user;（user表里是mysql数据库的所有账户信息）
    
    #配置mysql允许远程访问，首先编辑 /etc/mysql/mysql.conf.d/mysqld.cnf 配置文件：
    
    vim /etc/mysql/mysql.conf.d/mysqld.cnf
    
    注释掉bind-address          = 127.0.0.1
    
    保存退出，然后进入mysql数据库，执行授权命令：
    
    mysql -u root -p
    mysql> grant all on *.* to root@'%' identified by '你的密码' with grant option;
    mysql> flush privileges;    # 刷新权限
    mysql> exit
    #重启mysql：
    systemctl restart mysql

#tomcat
    1.下载Linux tomcat
    2.解压文件
        tar zxvf fieName
    3.修改Linux环境变量文件
        vim /etc/profile
        尾部添加:
            export TOMCAT_HOME=/usr/software/apache-tomcat-9.0.8
    4.完成  source /etc/profile 使修改的环境变量生效
#zookeeper
    1.下载Linux zookeeper
    2.解压文件
        tar zxvf fieName
    3.修改Linux环境变量文件
        vim /etc/profile
        尾部添加:
            export ZOOKEEPER_HOME=/usr/software/zookeeper
            export PATH=$ZOOKEEPER_HOME/bin:$PATH
    4.完成  source /etc/profile 使修改的环境变量生效
    5.启动  在bin目录下 zkServer.sh start
#redis
    1.下载
    wget http://download.redis.io/releases/redis-4.0.8.tar.gz
    2.  进行编译  可能需要安装 make gcc
        {
        安装gcc yum/apt install gcc
        安装make  apt install make
        }
        make
        cd src
        make install PREFIX=/usr/local/redis(安装目录)

    #配置redis为后台启动
    　　vi /usr/local/redis/etc/redis.conf //将daemonize no 改成daemonize yes
    #解决不能连接  进入redis.conf 主调 bind  修改protect-mode no
    #添加密码
    去掉注释 requirepass ****(密码)