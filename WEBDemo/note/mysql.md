#修改权限
grant all privileges on *.* to 'root'@'%' identified by '123456' with grant option
#查询数据存储目录
show global variables like "%datadir%"