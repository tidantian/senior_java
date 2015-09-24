package com.framework.net.netty;

import com.framework.util.ObjectSerialTool;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Message> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Message msg,
			ByteBuf bytebuf) throws Exception {
		bytebuf.writeInt(msg.getType());
		if (msg.getType() != 0) {
			byte[] person = ObjectSerialTool.writeObject(msg.getBody());
			bytebuf.writeInt(person.length + 2);
			bytebuf.writeBytes(person);
		} else {
			bytebuf.writeInt(((String) msg.getBody()).length() + 2);
			bytebuf.writeBytes(((String) msg.getBody()).getBytes());
		}

	}

}
