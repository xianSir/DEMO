package com.demo.Netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/**
 * @author xks
 * @date 2019-10-21
 */
public class NettyClient {

    public Channel init() {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<Channel>() {
            /**
             * MessageToMessageCodec
             *
             * @param ch
             * @throws Exception
             */
                              @Override
                              protected void initChannel(Channel ch) throws Exception {
                                 // ch.pipeline().addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
                                  ch.pipeline().addLast(new StringDecoder()); //需注意顺序否则服务端 客户端不起作用
                                  ch.pipeline().addLast(new StringEncoder());
                                  ch.pipeline().addLast(new ClientHander());
                              }
                          }
        );


        Channel channel = bootstrap.connect(new InetSocketAddress("192.168.0.18", 8080)).channel();
        boolean open = channel.isOpen();
        System.out.println(open);
        return channel;
    }

    public void sendMessage(String msg) throws Exception {
        Channel channel = this.init();
        Thread.sleep(2000L);
        channel.writeAndFlush(msg);
        //channel.closeFuture();
    }

    public static void main(String[] args) throws Exception, InterruptedException {
        NettyClient client = new NettyClient();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        client.sendMessage(in.readLine()/*"hello"+"\r\n"*/);
        System.out.println("消息发送成功");
    }

}
