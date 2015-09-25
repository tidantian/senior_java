package com.framework.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.framework.util.ObjectSerialTool;

public class NioClient {

	private static final int SERVER_PORT = 4321;
	private static final String SERVER_IP = "localhost";

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		start();

	}

	private static void start() throws IOException, ClosedChannelException,
			ClassNotFoundException {
		SocketChannel clientChannel = SocketChannel.open();
		clientChannel.configureBlocking(false);
		clientChannel.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
		Selector selector = Selector.open();
		clientChannel.register(selector, SelectionKey.OP_CONNECT);

		try {
			while (true) {
				int keyCount = selector.select();
				if (keyCount >= 1) {
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = keys.iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();
						iterator.remove();

						if (key.isConnectable()) {
							if (((SocketChannel) key.channel())
									.isConnectionPending()) {
								if (((SocketChannel) key.channel())
										.finishConnect()) {
									System.out.println("connect");
									// 只有当连接成功后才能注册OP_READ事件
									key.interestOps(SelectionKey.OP_READ);
								} else {
									key.cancel();
								}
							}

						} else if (key.isReadable()) {
							if (key.channel() != null) {
								readAndPrint(key);
								writeAndPrint(key);
							}
						} else if (key.isWritable()) {
							if (key.channel() != null) {
								System.out.println("write");
							}

						}
					}
				} else {
					continue;
				}
			}
		} finally {
			clientChannel.close();
			selector.close();
		}
	}

	private static void writeAndPrint(SelectionKey key) throws IOException {
		Message msg = new Message();
		msg.setType(0);
		msg.setContent("bengbeng say hi to you.".getBytes());

		((SocketChannel) key.channel()).write(ByteBuffer.wrap(ObjectSerialTool
				.writeObject(msg)));
	}

	private static void readAndPrint(SelectionKey key) throws IOException,
			ClassNotFoundException {
		ByteBuffer inByteBuf = ByteBuffer.allocate(1024);
		((SocketChannel) key.channel()).read(inByteBuf);
		inByteBuf.flip();
		Message msg = (Message) ObjectSerialTool.readObject(inByteBuf.array());
		if (msg.getType() == 1) {
			// Object
			Person person = (Person) ObjectSerialTool.readObject(msg
					.getContent());
			System.out.println("Object, Name:" + person.getName() + ", age: "
					+ person.getAge());
		} else {
			// String
			System.out.println("String, " + new String(msg.getContent()));
		}
	}
}
