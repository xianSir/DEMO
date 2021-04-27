
# 小白笔记
# 初始化本地仓库
git init
# 配置用户
git config global user.name "xks"
git config global email "...@qq.com"



------------------------------------------------------------------git 基本命令-------------------------------------------------

git add -A/-all/fileName/*  #添加到暂缓区
git commit -m""
git push origin master #master 主干或分支
git status  查看文件状态
git different 查看文件详情(及文件增加 或 删除那些内容)

	尚未缓存的改动：git diff
	查看已缓存的改动： git diff --cached
	查看已缓存的与未缓存的所有改动：git diff HEAD
	显示摘要而非整个 diff：git diff --stat
git commit -a  文件提价到缓存并将缓存中的文件提交
git reset HEAD 命令用于取消已缓存的内容。
git log 查询日志
	--oneline 选项来查看历史记录的简洁的版本。
	--graph 选项，查看历史中什么时候出现了分支、合并。\
	--reverse 参数来逆向显示所有日志。
	git log --author 找指定用户的提交日志可以使用命令：
	如果你要指定日期，可以执行几个选项：--since 和 --before，但是你也可以用 --until 和 --after。
	
git tag -a v1.0  打标签
git log --decorate 查看标签
--------------------------------------------------------------------------END---------------------------------------------------




---------------------------------------------------------------------分支管理---------------------------------------------------


创建分支命令：
	git branch (branchname)

切换分支命令:
	git checkout (branchname)

删除分支命令：
	git branch -d (branchname)
	git push origin :branch(删除github上的分支)

合并分支命令:
	git merge 
----------------------------------------------------------------------------END---------------------------------------------------



---------------------------------------------------------------------------使用github---------------------------------------------
#1.本地电脑创建秘钥
ssh-keygen -t rsa -C "github注册邮箱"

#2.配置github账户与本地秘钥关联
#进入Account Settings（账户配置），左边选择SSH Keys，Add SSH Key,title随便填，粘贴在你电脑上生成的key。

#3.添加github远程仓库 git remote add [shortname] [url]
git remote add origin git@github.com:yourName/yourRepo.git #yourName和yourRepo表示你再github的用户名和刚才新建的仓库

#4执行如下命令以创建一个本地仓库的克隆版本：
git clone /path/to/repository 
git clone 远程地址

#从远程仓库下载新分支与数据：
git fetch
	
#推送你的新分支与数据到某个远端仓库命令:
git push [alias] [branch]
	
#删除远程仓库你可以使用命令：
git remote rm [别名]
#克隆仓库
git clone git@192.168.45.4:/home/gitrepo/runoob.git

#下面命令表示，当前分支自动与唯一一个追踪分支进行合并。   git pull = git fetch + git merge
git pull  

#如果没有克隆现有仓库，并欲将你的仓库连接到某个远程服务器，你可以使用如下命令添加：
git remote add origin <server>
如此你就能够将你的改动推送到所添加的服务器上去了
git提交文件步骤  add-> commit -> push
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
