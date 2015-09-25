package com.framework.net.netty;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;

;

public class FaceBookServerHandler extends ChannelHandlerAdapter {
    public FaceBookServerHandler() {

    }

    private LoadingCache<Integer, Person> personCache = CacheBuilder.newBuilder()
            .expireAfterAccess(10, TimeUnit.SECONDS).maximumSize(100).build(new CacheLoader<Integer, Person>() {
                @Override
                public Person load(Integer id) {
                    return new Person(id);
                }
            });

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msgObject) throws ExecutionException {
        System.out.println("server read");

        if (msgObject != null) {
            Message msg = (Message) msgObject;
            switch (msg.getType()) {
            case 0:
                System.out.println((String) (msg.getBody()));
                Message showFeedback = new Message();
                showFeedback.setType(Message.TYPE.SHOW.value);
                showFeedback.setBody("Show Feedback");
                ctx.write(showFeedback);
                ctx.flush();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:

            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
        e.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("channel active");
        Message showFeedback = new Message();
        showFeedback.setType(Message.TYPE.SHOW.value);
        showFeedback.setBody("Say Hello To Client");
        ctx.pipeline().channel().write(showFeedback).channel().flush();
    }

}
