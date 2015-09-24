package com.framework.net.netty;

import java.util.Date;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;

;

public class FaceBookClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("client read");
		if (msg != null) {
			Message recvMsg = (Message) msg;
			if (recvMsg.getType() == Message.TYPE.SHOW.value) {
				System.out.println((String) (recvMsg.getBody()));
			}
		}
		Message showMsg = new Message();
		showMsg.setType(Message.TYPE.SHOW.value);
		showMsg.setBody("show info to server");

		ctx.write(showMsg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable e) {
		e.printStackTrace();
		ctx.close();
	}

}
