package com.ctn.nioserver.decode;

import com.ctn.tcp.FrameManager;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by barry on 2015/3/14.
 */
public class TCPFrameDecoder extends ByteToMessageDecoder{

    private FrameManager manager;

    public TCPFrameDecoder(FrameManager manager){
        this.manager = manager;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> objects) throws Exception {
        if (byteBuf.readableBytes() < 12){
            return;
        }
        int size = byteBuf.readableBytes();
        byte[] bs = new byte[size];
        while (byteBuf.isReadable()){
            bs[byteBuf.readerIndex()] = byteBuf.readByte();
        }

        manager.convert(bs);

//        objects.add()
    }
}
