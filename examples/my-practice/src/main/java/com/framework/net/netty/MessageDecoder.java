package com.framework.net.netty;

import java.util.List;

import com.framework.util.ObjectSerialTool;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class MessageDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() < 8) {
			return;
		}

		in.markReaderIndex();
		int msgType = in.readInt();
		int msgLen = in.readInt();

		if (in.readableBytes() < msgLen) {
			in.resetReaderIndex();
			return;
		}

		byte[] body = new byte[msgLen - 2];
		in.readBytes(body);
		Message msg = new Message();
		msg.setType(msgType);
		msg.setLen(msgLen);
		if (msgType != Message.TYPE.SHOW.value) {
			msg.setBody((Person) ObjectSerialTool.readObject(body));
		} else {
			msg.setBody(new String(body));
		}
		out.add(msg);
	}

}
