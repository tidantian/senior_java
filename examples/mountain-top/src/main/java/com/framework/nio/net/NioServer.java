package com.framework.nio.net;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.framework.util.ObjectSerialTool;

public class NioServer {

	public static void main(String[] args) throws IOException,
			ClassNotFoundException {
		start();

	}

	private static void start() throws IOException, ClosedChannelException,
			ClassNotFoundException {
		Selector selector = Selector.open();
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		serverChannel.configureBlocking(false);
		serverChannel.socket().bind(new InetSocketAddress(4321));
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		try {
			while (true) {
				int keyCount = selector.select();
				if (keyCount >= 1) {
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> iterator = keys.iterator();
					while (iterator.hasNext()) {
						SelectionKey key = iterator.next();

						if (key.isAcceptable()) {
							ServerSocketChannel serverSocketChannel = ((ServerSocketChannel) key
									.channel());
							SocketChannel clientChannel = serverSocketChannel
									.accept();
							if (clientChannel != null) {
								System.out.println("accept");
								clientChannel.configureBlocking(false);
								clientChannel.register(selector,
										SelectionKey.OP_READ
												| SelectionKey.OP_WRITE);
							}

						} else if (key.isConnectable()) {

						} else if (key.isReadable()) {
							System.out.println("read");
							if (key.channel() != null) {
								readAndPrint(key);
								//writeAndPrint(key);
							}
						} else if (key.isWritable()) {
							if (key.channel() != null) {
								System.out.println("write");
								writeAndPrint(key);
							}

						}
					}
				} else {
					continue;
				}
			}
		} finally {
			serverChannel.close();
			selector.close();
		}
	}

	private static void writeAndPrint(SelectionKey key) throws IOException {
		Person person = new Person();
		person.setName("Tidan");
		person.setAge(34);
		Message msg = new Message();
		msg.setType(1);
		msg.setContent(ObjectSerialTool.writeObject(person));

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
