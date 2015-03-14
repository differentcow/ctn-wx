package com.ctn.nioserver.client;

import com.ctn.nioserver.client.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Created by hp on 2015/3/12.
 */
public class SendClient {

    public void send(String host,int port){
        EventLoopGroup work = new NioEventLoopGroup();

        try{
            Bootstrap client = new Bootstrap();
            client.group(work);
            client.channel(NioSocketChannel.class);
            client.option(ChannelOption.SO_KEEPALIVE, true);
            client.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ClientHandler());
                }
            });
            // Start the client.
            ChannelFuture f = client.connect(host, port).sync();
            // Wait until the connection is closed.
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();;
        }finally {
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        SendClient c = new SendClient();
        c.send("192.168.1.83",9056);
    }

}
