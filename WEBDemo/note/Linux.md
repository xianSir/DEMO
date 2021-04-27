#了解Linux
    https://www.runoob.com/wp-content/uploads/2014/06/003vPl7Rty6E8kZRlAEdc690.jpg
    /bin：
        bin是Binary的缩写, 这个目录存放着最经常使用的命令。
    /boot：
        这里存放的是启动Linux时使用的一些核心文件，包括一些连接文件以及镜像文件。
    /dev ：
        dev是Device(设备)的缩写, 该目录下存放的是Linux的外部设备，在Linux中访问设备的方式和访问文件的方式是相同的。
    /etc：
        这个目录用来存放所有的系统管理所需要的配置文件和子目录。
    /home：
        用户的主目录，在Linux中，每个用户都有一个自己的目录，一般该目录名是以用户的账号命名的。
    /lib：
        这个目录里存放着系统最基本的动态连接共享库，其作用类似于Windows里的DLL文件。几乎所有的应用程序都需要用到这些共享库。
    /lost+found：
        这个目录一般情况下是空的，当系统非法关机后，这里就存放了一些文件。
    /media：
        linux系统会自动识别一些设备，例如U盘、光驱等等，当识别后，linux会把识别的设备挂载到这个目录下。
    /mnt：
        系统提供该目录是为了让用户临时挂载别的文件系统的，我们可以将光驱挂载在/mnt/上，然后进入该目录就可以查看光驱里的内容了。
    /opt：
        这是给主机额外安装软件所摆放的目录。比如你安装一个ORACLE数据库则就可以放到这个目录下。默认是空的。
    /proc：
        这个目录是一个虚拟的目录，它是系统内存的映射，我们可以通过直接访问这个目录来获取系统信息。
        这个目录的内容不在硬盘上而是在内存里，我们也可以直接修改里面的某些文件，比如可以通过下面的命令来屏蔽主机的ping命令，使别人无法ping你的机器：
        echo 1 > /proc/sys/net/ipv4/icmp_echo_ignore_all
    /root：
        该目录为系统管理员，也称作超级权限者的用户主目录。
    /sbin：
        s就是Super User的意思，这里存放的是系统管理员使用的系统管理程序。
    /selinux：
        这个目录是Redhat/CentOS所特有的目录，Selinux是一个安全机制，类似于windows的防火墙，但是这套机制比较复杂，这个目录就是存放selinux相关的文件的。
    /srv：
        该目录存放一些服务启动之后需要提取的数据。
    /sys：
        这是linux2.6内核的一个很大的变化。该目录下安装了2.6内核中新出现的一个文件系统 sysfs 。
        sysfs文件系统集成了下面3种文件系统的信息：针对进程信息的proc文件系统、针对设备的devfs文件系统以及针对伪终端的devpts文件系统。
        该文件系统是内核设备树的一个直观反映。
        当一个内核对象被创建的时候，对应的文件和目录也在内核对象子系统中被创建。
    /tmp：
        这个目录是用来存放一些临时文件的。
    /usr：
        这是一个非常重要的目录，用户的很多应用程序和文件都放在这个目录下，类似于windows下的program files目录。
    /usr/bin：
        系统用户使用的应用程序。
    /usr/sbin：
        超级用户使用的比较高级的管理程序和系统守护程序。
    /usr/src：
        内核源代码默认的放置目录。
    /var：
        这个目录中存放着在不断扩充着的东西，我们习惯将那些经常被修改的目录放在这个目录下。包括各种日志文件。
    /run：
        是一个临时文件系统，存储系统启动以来的信息。当系统重启时，这个目录下的文件应该被删掉或清除。如果你的系统上有 /var/run 目录，应该让它指向 run。

#yum（ Yellow dog Updater, Modified）是一个在Fedora和RedHat以及SUSE中的Shell前端软件包管理器。
    基於RPM包管理，能够从指定的服务器自动下载RPM包并且安装，可以自动处理依赖性关系，并且一次安装所有依赖的软体包，无须繁琐地一次次下载、安装。
    yum提供了查找、安装、删除某一个、一组甚至全部软件包的命令，而且命令简洁而又好记。
    yum 语法
    yum [options] [command] [package ...]
    options：可选，选项包括-h（帮助），-y（当安装过程提示选择全部为"yes"），-q（不显示安装的过程）等等。
    command：要进行的操作。
    package操作的对象。

#Linux rpm 命令用于管理套件。
    rpm(redhat package manager) 原本是 Red Hat Linux 发行版专门用来管理 Linux 各项套件的程序，
    由于它遵循 GPL 规则且功能强大方便，因而广受欢迎。逐渐受到其他发行版的采用。RPM 套件管理方式的出现，让 Linux 易于安装，升级，
    间接提升了 Linux 的适用度。

#Linux命令
    #切换root用户
        sudo/su 
    #切换目录  (Linux/指的是根目录)
        cd /:
    #修改查看文件
        vim/vi:
    #基本可以判断出安装mysql目录在哪里
     find / -name mysql
    #创建文件
     touch filename
    #创建目录 
        mkdir dirName
                -m ：配置文件的权限喔！直接配置，不需要看默认权限 (umask) 的脸色～
                -p ：帮助你直接将所需要的目录(包含上一级目录)递归创建起来		
    #删除空的目录
        rmdir:
                -p ：连同上一级『空的』目录也一起删除		
    #列出目录 
        ls/ll:
                -a ：全部的文件，连同隐藏档( 开头为 . 的文件) 一起列出来(常用)
                -d ：仅列出目录本身，而不是列出目录内的文件数据(常用)
                -l ：长数据串列出，包含文件的属性与权限等等数据；(常用)		
    #显示目前所在的目录
        pwd:
                P ：显示出确实的路径，而非使用连结 (link) 路径。		
    #复制文件或目录
        cp:
                -a：相当於 -pdr 的意思，至於 pdr 请参考下列说明；(常用)
                -d：若来源档为连结档的属性(link file)，则复制连结档属性而非文件本身；
                -f：为强制(force)的意思，若目标文件已经存在且无法开启，则移除后再尝试一次；
                -i：若目标档(destination)已经存在时，在覆盖时会先询问动作的进行(常用)
                -l：进行硬式连结(hard link)的连结档创建，而非复制文件本身
                -p：连同文件的属性一起复制过去，而非使用默认属性(备份常用)；
                -r：递归持续复制，用於目录的复制行为；(常用)
                -s：复制成为符号连结档 (symbolic link)，亦即『捷径』文件；
                -u：若 destination 比 source 旧才升级 destination ！
    #移除文件或目录
        rm:
                -f ：就是 force 的意思，忽略不存在的文件，不会出现警告信息；
                -i ：互动模式，在删除前会询问使用者是否动作
                -r ：递归删除啊！最常用在目录的删除了！这是非常危险的选项！！！
    #移动文件或目录 或修改文件或目录的名称
        mv: 
                -f ：force 强制的意思，如果目标文件已经存在，不会询问而直接覆盖；
                -i ：若目标文件 (destination) 已经存在时，就会询问是否覆盖！
                -u ：若目标文件已经存在，且 source 比较新，才会升级 (update)
    #解包：
        tar zxvf FileName.tar
    #打包：
        tar czvf FileName.tar DirName
            -z或--gzip或--ungzip 通过gzip指令处理备份文件。
            -x或--extract或--get 从备份文件中还原文件
            -v或--verbose 显示指令执行过程。
            -f<备份文件>或--file=<备份文件> 指定备份文件
            -c或--create 建立新的备份文件。
    
    #编辑完之后，保存并退出，然后输入以下指令，刷新环境配置使其生效：
        source /etc/profile
    
    #修改文件权限
        chmod 777 fielName
        chmod rwxrwx---/rwx------
        
    #查看linux中某个端口（port）是否被占用
        1.使用lsof 
        lsof -i:端口号                     查看某个端口是否被占用
        2.使用netstat 
        使用netstat -anp|grep 80
    使用RPM安装:
    rmp –ivh vagrant_1.9.8_x86_64.rpm
        -i 安装软件包
        -nodeps   不验证软件包的依赖
        -v  可视化，提供更多的详细信息的输出
        -h  显示安装进度另外的常用的附带参数为：
        -force 强制安装，即使覆盖其他包的文件也要安装
        -a 查询所有已经安装的软件包
        -f 查询 文件所属于的软件包
        -q 查询软件包（通常用来看下还未安装的软件包）
        -l 显示软件包的文件列表
        -d 显示被标注为文档的文件列表
        -c 显示被标注为配置文件的文件列表 最后这两个用的很少了

#杀死某一进程
    kill -9 32683
#Linux下的复制粘贴	
    1、在终端下：
    复制命令：Ctrl + Shift + C  组合键。
    粘贴命令：Ctrl + Shift + V  组合键。
    2、在控制台下：
    
    复制命令：Ctrl + Insert  组合键　　或　　用鼠标选中即是复制。
    粘贴命令：Shift + Insert  组合键　　或　　单击鼠标滚轮即为粘贴。
     
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
#df命令参数功能：
    检查文件系统的磁盘空间占用情况。可以利用该命令来获取硬盘被占用了多少空间，目前还剩下多少空间等信息。
    语法：
    df [-ahikHTm] [目录或文件名]
    选项与参数：
        -a ：列出所有的文件系统，包括系统特有的 /proc 等文件系统；
        -k ：以 KBytes 的容量显示各文件系统；
        -m ：以 MBytes 的容量显示各文件系统；
        -h ：以人们较易阅读的 GBytes, MBytes, KBytes 等格式自行显示；
        -H ：以 M=1000K 取代 M=1024K 的进位方式；
        -T ：显示文件系统类型, 连同该 partition 的 filesystem 名称 (例如 ext3) 也列出；
        -i ：不用硬盘容量，而以 inode 的数量来显示		
		
		
#ps命令用于显示当前进程 (process) 的状态。
    ps [options] [--help]
            参数：
            ps 的参数非常多, 在此仅列出几个常用的参数并大略介绍含义
            -A 列出所有的行程
            -w 显示加宽可以显示较多的资讯
            -au 显示较详细的资讯
            -aux 显示所有包含其他使用者的行程
            au(x) 输出格式 :
            USER PID %CPU %MEM VSZ RSS TTY STAT START TIME COMMAND
            USER: 行程拥有者
            PID: pid
            %CPU: 占用的 CPU 使用率
            %MEM: 占用的记忆体使用率
            VSZ: 占用的虚拟记忆体大小
            RSS: 占用的记忆体大小
            TTY: 终端的次要装置号码 (minor device number of tty)
            STAT: 该行程的状态:
            D: 无法中断的休眠状态 (通常 IO 的进程)
            R: 正在执行中
            S: 静止状态
            T: 暂停执行
            Z: 不存在但暂时无法消除
            W: 没有足够的记忆体分页可分配
            <: 高优先序的行程
            N: 低优先序的行程
            L: 有记忆体分页分配并锁在记忆体内 (实时系统或捱A I/O)
            START: 行程开始时间
            TIME: 执行的时间
            COMMAND:所执行的指令		
#Linux netstat命令用于显示网络状态。
    利用netstat指令可让你得知整个Linux系统的网络情况。
    语法:netstat [-acCeFghilMnNoprstuvVwx][-A<网络类型>][--ip]
        参数说明：
        -a或--all 显示所有连线中的Socket。
        -A<网络类型>或--<网络类型> 列出该网络类型连线中的相关地址。
        -c或--continuous 持续列出网络状态。
        -C或--cache 显示路由器配置的快取信息。
        -e或--extend 显示网络其他相关信息。
        -F或--fib 显示FIB。
        -g或--groups 显示多重广播功能群组组员名单。
        -h或--help 在线帮助。
        -i或--interfaces 显示网络界面信息表单。
        -l或--listening 显示监控中的服务器的Socket。
        -M或--masquerade 显示伪装的网络连线。
        -n或--numeric 直接使用IP地址，而不通过域名服务器。
        -N或--netlink或--symbolic 显示网络硬件外围设备的符号连接名称。
        -o或--timers 显示计时器。
        -p或--programs 显示正在使用Socket的程序识别码和程序名称。
        -r或--route 显示Routing Table。
        -s或--statistice 显示网络工作信息统计表。
        -t或--tcp 显示TCP传输协议的连线状况。
        -u或--udp 显示UDP传输协议的连线状况。
        -v或--verbose 显示指令执行过程。
        -V或--version 显示版本信息。
        -w或--raw 显示RAW传输协议的连线状况。
        -x或--unix 此参数的效果和指定"-A unix"参数相同。
        --ip或--inet 此参数的效果和指定"-A inet"参数相同。		
    
#Linux 文件内容查看
    cat  由第一行开始显示文件内容
    tac  从最后一行开始显示，可以看出 tac 是 cat 的倒著写！
    nl   显示的时候，顺道输出行号！
    more 一页一页的显示文件内容
    less 与 more 类似，但是比 more 更好的是，他可以往前翻页！
    head 只看头几行
    tail 只看尾巴几行
    你可以使用 man [命令]来查看各个命令的使用文档，如 ：man cp。
    cat:由第一行开始显示文件内容
            语法：
            cat [-AbEnTv]
            选项与参数：
            -A ：相当於 -vET 的整合选项，可列出一些特殊字符而不是空白而已；
            -b ：列出行号，仅针对非空白行做行号显示，空白行不标行号！
            -E ：将结尾的断行字节 $ 显示出来；
            -n ：列印出行号，连同空白行也会有行号，与 -b 的选项不同；
            -T ：将 [tab] 按键以 ^I 显示出来；
            -v ：列出一些看不出来的特殊字符
    检看 /etc/issue 这个文件的内容：
    tac与cat命令刚好相反，文件内容从最后一行开始显示，可以看出 tac 是 cat 的倒着写！如：
    nl:显示行号
            nl [-bnw] 文件
            选项与参数：
            -b ：指定行号指定的方式，主要有两种：
            -b a ：表示不论是否为空行，也同样列出行号(类似 cat -n)；
            -b t ：如果有空行，空的那一行不要列出行号(默认值)；
            -n ：列出行号表示的方法，主要有三种：
            -n ln ：行号在荧幕的最左方显示；
            -n rn ：行号在自己栏位的最右方显示，且不加 0 ；
            -n rz ：行号在自己栏位的最右方显示，且加 0 ；
            -w ：行号栏位的占用的位数。
    more一页一页翻动
            列如:
            [root@www ~]# more /etc/man_db.config 
            #
            # Generated automatically from man.conf.in by the
            # configure script.
            #
            # man.conf from man-1.6d
            ....(中间省略)....
            --More--(28%)  <== 重点在这一行喔！你的光标也会在这里等待你的命令
            在 more 这个程序的运行过程中，你有几个按键可以按的：
            空白键 (space)：代表向下翻一页；
            Enter         ：代表向下翻『一行』；
            /字串         ：代表在这个显示的内容当中，向下搜寻『字串』这个关键字；
            :f            ：立刻显示出档名以及目前显示的行数；
            q             ：代表立刻离开 more ，不再显示该文件内容。
            b 或 [ctrl]-b ：代表往回翻页，不过这动作只对文件有用，对管线无用。
            less
            一页一页翻动，以下实例输出/etc/man.config文件的内容：
            [root@www ~]# less /etc/man.config
            #
            # Generated automatically from man.conf.in by the
            # configure script.
            #
            # man.conf from man-1.6d
            ....(中间省略)....
            :   <== 这里可以等待你输入命令！
    less运行时可以输入的命令有：
            空白键    ：向下翻动一页；
            [pagedown]：向下翻动一页；
            [pageup]  ：向上翻动一页；
            /字串     ：向下搜寻『字串』的功能；
            ?字串     ：向上搜寻『字串』的功能；
            n         ：重复前一个搜寻 (与 / 或 ? 有关！)
            N         ：反向的重复前一个搜寻 (与 / 或 ? 有关！)
            q         ：离开 less 这个程序；
            head
            取出文件前面几行
            语法：
    head [-n number] 文件 
            选项与参数：
            -n ：后面接数字，代表显示几行的意思
            [root@www ~]# head /etc/man.config
            默认的情况中，显示前面 10 行！若要显示前 20 行，就得要这样：
            [root@www ~]# head -n 20 /etc/man.config
    tail:取出文件后面几行
            tail [-n number] 文件 
            选项与参数：
            -n ：后面接数字，代表显示几行的意思
            -f ：表示持续侦测后面所接的档名，要等到按下[ctrl]-c才会结束tail的侦测
		
#wget常见命令参数
        GNU Wget 1.12，非交互式的网络文件下载工具。
        用法： wget [选项]... [URL]...
         
        长选项所必须的参数在使用短选项时也是必须的。
         
        开始:
          -V,  --version           显示 Wget 的版本信息并退出。
          -h,  --help              打印此帮助。
          -b,  --background        启动后转入后台。
          -e,  --execute=COMMAND   运行一个‘.wgetrc’风格的命令。
         
        登入并输入文件:
          -o,  --output-file=FILE    将信息写入 FILE。
          -a,  --append-output=FILE  将信息添加至 FILE。
          -d,  --debug               打印大量调试信息。
          -q,  --quiet               安静模式(无信息输出)。
          -v,  --verbose             详尽的输出(此为默认值)。
          -nv, --no-verbose          关闭详尽输出，但不进入安静模式。
          -i,  --input-file=FILE     下载本地或外部 FILE 中的 URLs。
          -F,  --force-html          把输入文件当成 HTML 文件。
          -B,  --base=URL            解析与 URL 相关的
                                     HTML 输入文件（由 -i -F 选项指定）。
         
        下载:
          -t,  --tries=NUMBER           设置重试次数为 NUMBER (0 代表无限制)。
                --retry-connrefused       即使拒绝连接也是重试。
          -O,  --output-document=FILE    将文档写入 FILE。
          -nc, --no-clobber              不要重复下载已存在的文件。
                                         
          -c,  --continue                继续下载部分下载的文件。
               --progress=TYPE           选择进度条类型。
          -N,  --timestamping            只获取比本地文件新的文件。
                                          
          -S,  --server-response         打印服务器响应。
               --spider                   不下载任何文件。
          -T,  --timeout=SECONDS         将所有超时设为 SECONDS 秒。
               --dns-timeout=SECS        设置 DNS 查寻超时为 SECS 秒。
               --connect-timeout=SECS    设置连接超时为 SECS 秒。
               --read-timeout=SECS       设置读取超时为 SECS 秒。
          -w,  --wait=SECONDS            等待间隔为 SECONDS 秒。
               --waitretry=SECONDS       在取回文件的重试期间等待 1..SECONDS 秒。
               --random-wait             取回时等待 0...2*WAIT 秒。
               --no-proxy                关闭代理。
          -Q,  --quota=NUMBER            设置取回配额为 NUMBER 字节。
               --bind-address=ADDRESS    绑定至本地主机上的 ADDRESS (主机名或是 IP)。
               --limit-rate=RATE         限制下载速率为 RATE。
               --no-dns-cache            关闭 DNS 查寻缓存。
               --restrict-file-names=OS  限定文件名中的字符为 OS 允许的字符。
               --ignore-case             匹配文件/目录时忽略大小写。
          -4,  --inet4-only              仅连接至 IPv4 地址。
          -6,  --inet6-only              仅连接至 IPv6 地址。
               --prefer-family=FAMILY    首先连接至指定协议的地址
                                         FAMILY 为 IPv6，IPv4 或是 none。
               --user=USER               将 ftp 和 http 的用户名均设置为 USER。
               --password=PASS           将 ftp 和 http 的密码均设置为 PASS。
               --ask-password           提示输入密码。
               --no-iri                关闭 IRI 支持。
               --local-encoding=ENC      IRI 使用 ENC 作为本地编码。
               --remote-encoding=ENC     使用 ENC 作为默认远程编码。
         
        目录:
          -nd, --no-directories           不创建目录。
          -x,  --force-directories        强制创建目录。
          -nH, --no-host-directories      不要创建主目录。
               --protocol-directories     在目录中使用协议名称。
          -P,  --directory-prefix=PREFIX  以 PREFIX/... 保存文件
               --cut-dirs=NUMBER          忽略 NUMBER 个远程目录路径。
         
        HTTP 选项:
               --http-user=USER        设置 http 用户名为 USER。
               --http-password=PASS    设置 http 密码为 PASS。
               --no-cache              不在服务器上缓存数据。
               --default-page=NAME     改变默认页
                                       （默认页通常是“index.html”）。
          -E,  --adjust-extension      以合适的扩展名保存 HTML/CSS 文档。
               --ignore-length         忽略头部的‘Content-Length’区域。
               --header=STRING         在头部插入 STRING。
               --max-redirect          每页所允许的最大重定向。
               --proxy-user=USER       使用 USER 作为代理用户名。
               --proxy-password=PASS   使用 PASS 作为代理密码。
               --referer=URL           在 HTTP 请求头包含‘Referer: URL’。
               --save-headers          将 HTTP 头保存至文件。
          -U,  --user-agent=AGENT      标识为 AGENT 而不是 Wget/VERSION。
               --no-http-keep-alive    禁用 HTTP keep-alive(永久连接)。
               --no-cookies            不使用 cookies。
               --load-cookies=FILE     会话开始前从 FILE 中载入 cookies。
               --save-cookies=FILE     会话结束后保存 cookies 至 FILE。
               --keep-session-cookies  载入并保存会话(非永久) cookies。
               --post-data=STRING      使用 POST 方式；把 STRING 作为数据发送。
               --post-file=FILE        使用 POST 方式；发送 FILE 内容。
               --content-disposition   当选中本地文件名时
                                       允许 Content-Disposition 头部(尚在实验)。
               --auth-no-challenge     send Basic HTTP authentication information
                                       without first waiting for the server's
                                       challenge.
         
        HTTPS (SSL/TLS) 选项:
               --secure-protocol=PR     选择安全协议，可以是 auto、SSLv2、
                                        SSLv3 或是 TLSv1 中的一个。
               --no-check-certificate   不要验证服务器的证书。
               --certificate=FILE       客户端证书文件。
               --certificate-type=TYPE  客户端证书类型， PEM 或 DER。
               --private-key=FILE       私钥文件。
               --private-key-type=TYPE  私钥文件类型， PEM 或 DER。
               --ca-certificate=FILE    带有一组 CA 认证的文件。
               --ca-directory=DIR       保存 CA 认证的哈希列表的目录。
               --random-file=FILE       带有生成 SSL PRNG 的随机数据的文件。
               --egd-file=FILE          用于命名带有随机数据的 EGD 套接字的文件。
         
        FTP 选项:
               --ftp-user=USER         设置 ftp 用户名为 USER。
               --ftp-password=PASS     设置 ftp 密码为 PASS。
               --no-remove-listing     不要删除‘.listing’文件。
               --no-glob               不在 FTP 文件名中使用通配符展开。
               --no-passive-ftp        禁用“passive”传输模式。
               --retr-symlinks         递归目录时，获取链接的文件(而非目录)。
         
        递归下载:
          -r,  --recursive          指定递归下载。
          -l,  --level=NUMBER       最大递归深度( inf 或 0 代表无限制，即全部下载)。
               --delete-after       下载完成后删除本地文件。
          -k,  --convert-links      让下载得到的 HTML 或 CSS 中的链接指向本地文件。
          -K,  --backup-converted   在转换文件 X 前先将它备份为 X.orig。
          -m,  --mirror             -N -r -l inf --no-remove-listing 的缩写形式。
          -p,  --page-requisites    下载所有用于显示 HTML 页面的图片之类的元素。
               --strict-comments    开启 HTML 注释的精确处理(SGML)。
         
        递归接受/拒绝:
          -A,  --accept=LIST               逗号分隔的可接受的扩展名列表。
          -R,  --reject=LIST               逗号分隔的要拒绝的扩展名列表。
          -D,  --domains=LIST              逗号分隔的可接受的域列表。
               --exclude-domains=LIST      逗号分隔的要拒绝的域列表。
               --follow-ftp                跟踪 HTML 文档中的 FTP 链接。
               --follow-tags=LIST          逗号分隔的跟踪的 HTML 标识列表。
               --ignore-tags=LIST          逗号分隔的忽略的 HTML 标识列表。
          -H,  --span-hosts                递归时转向外部主机。
          -L,  --relative                  只跟踪有关系的链接。
          -I,  --include-directories=LIST  允许目录的列表。
          -X,  --exclude-directories=LIST  排除目录的列表。
          -np, --no-parent                 不追溯至父目录。
#解决Ubuntu桌面版root登录
    1安装ssh
        apt-get install openssh-server
    2.修改配置文件
        执行：sudo_passwd_root   修改root账户的密码（红色的下划线表示空格，这个命令很简单不会用请自行百度）
        打开文件"/etc/pam.d/gdm-autologin"将这个文件中的"auth    required    pam_succeed_if.so user != root quiet_success"注释掉后保存。
        打开文件"/etc/pam.d/gdm-password"将这个文件中的"auth    required    pam_succeed_if.so user != root quiet_success"注释掉后保存。
        打开文件"/root/.profile"将这个文件的最后一行修改为"tty -s && mesg n || true"
        修改 /usr/share/lightdm/lightdm.conf.d/50-ubuntu.conf  添加:
                        greeter-show-manual-login=ture
                        all-guest=false
    3.修改 vim /etc/ssh/sshd_config 
        在authentication 添加或修改 permitRootLogin yes
    4重启服务
        service ssh restart
	
#vim使用
    i:进入插入模式
    q:退出
    wq:保存并退出
    /+"": 快速查询 匹配按下n(小写n)
     Ubuntu:安装: apt-get install vim
     centos:yum -y install vim*

