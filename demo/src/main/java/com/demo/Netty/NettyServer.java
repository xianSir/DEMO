package com.demo.Netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author xks
 * @date 2019-10-21
 */
public class NettyServer {
    private int port;

    public NettyServer(int port) {
        this.port = port;
    }

    /***
     1.   NioEventLoopGroup是处理I/O操作的多线程事件循环。 Netty为不同类型的传输提供了各种EventLoopGroup实现。
         在此示例中，实现的是服务器端应用程序，因此将使用两个NioEventLoopGroup。
         第一个通常称为“boss”，接受传入连接。 第二个通常称为“worker”，当“boss”接受连接并且向“worker”注册接受连接，
         则“worker”处理所接受连接的流量。 使用多少个线程以及如何将它们映射到创建的通道取决于EventLoopGroup实现，
         甚至可以通过构造函数进行配置。
     2. ServerBootstrap是一个用于设置服务器的助手类。 您可以直接使用通道设置服务器。 但是，请注意，这是一个冗长的过程，在大多数情况下不需要这样做。
     3. 在这里，我们指定使用NioServerSocketChannel类，该类用于实例化新的通道以接受传入连接。
     4  此处指定的处理程序将始终由新接受的通道计算。
        ChannelInitializer是一个特殊的处理程序，用于帮助用户配置新的通道。
        很可能要通过添加一些处理程序(例如DiscardServerHandler)来配置新通道的ChannelPipeline来实现您的网络应用程序。
        随着应用程序变得复杂，可能会向管道中添加更多处理程序，并最终将此匿名类提取到顶级类中。
     5  还可以设置指定Channel实现的参数。这里编写的是一个TCP/IP服务器，所以我们允许设置套接字选项，
        如tcpNoDelay和keepAlive。 请参阅ChannelOption的apidocs和指定的ChannelConfig实现，以了解关于ChannelOptions。
     6  你注意到option()和childOption()没有？ option()用于接受传入连接的NioServerSocketChannel。
        childOption()用于由父ServerChannel接受的通道，在这个示例中为NioServerSocketChannel。
     7  现在准备好了。剩下的是绑定到端口和启动服务器。 这里，我们绑定到机器中所有NIC(网络接口卡)的端口：8080。
        现在可以根据需要多次调用bind()方法(使用不同的绑定地址)。恭喜！这就完成了一个基于 Netty 的第一个服务器。
     */

    public void run() throws Exception {
        // 处理I/O操作的多线程事件循环
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //配置server
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class) // (3)//初始化服务端通道
                    .childHandler(new ChannelInitializer<SocketChannel>() { // (4)配置新的通道。
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ServerHander());
                        }
                    })
                    //.option(ChannelOption.SO_BACKLOG, 128)          // (5)
                    .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
            System.out.println("Netty 服务端启动.........");
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new NettyServer(port).run();
    }

}
