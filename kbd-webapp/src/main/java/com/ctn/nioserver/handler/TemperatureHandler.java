package com.ctn.nioserver.handler;

import com.ctn.tcp.FrameManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.net.SocketAddress;

/**
 * Created by barry on 2015/3/12.
 */
public class TemperatureHandler extends ChannelInboundHandlerAdapter{

    public TemperatureHandler(){

    }

    public TemperatureHandler(FrameManager manager){
        this.manager = manager;
    }

    private FrameManager manager;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        SocketAddress socketAddress = channel.remoteAddress();
        String saString = socketAddress.toString();
        String[] ary = saString.split(":");
        String ip =ary[0].replace("/", "");//192.168.1.13
        System.out.println(ip);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] binary = ((ByteBuf)msg).array();

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
