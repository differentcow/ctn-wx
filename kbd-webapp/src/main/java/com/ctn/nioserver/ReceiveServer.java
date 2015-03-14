package com.ctn.nioserver;

import com.ctn.nioserver.handler.TemperatureHandler;
import com.ctn.tcp.FrameManager;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by barry on 2015/3/12.
 */
public class ReceiveServer {

    private FrameManager manager;

    public void initFrameStructure(){
        manager = new FrameManager("frame/frame_up.xml");
    }

    public void startup(int port){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss,work).channel(NioServerSocketChannel.class)
                  .childHandler(new ChannelInitializer<SocketChannel>() {
                      @Override
                      protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new TemperatureHandler(manager));
                      }
                  })
                  .option(ChannelOption.SO_BACKLOG,128)
                  .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = server.bind(port).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args){
        ReceiveServer server = new ReceiveServer();
        server.startup(9056);
    }

}
