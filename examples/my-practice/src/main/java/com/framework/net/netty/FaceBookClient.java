package com.framework.net.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class FaceBookClient {

	private int port;

	FaceBookClient(int port) {
		this.port = port;
	}

	public void run() {
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {

			Bootstrap client = new Bootstrap();
			client.group(workGroup);
			client.channel(NioSocketChannel.class);
			client.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new MessageEncoder(),
							new MessageDecoder(), new FaceBookClientHandler());
				}
			});
			client.option(ChannelOption.SO_KEEPALIVE, true);

			ChannelFuture channelFuture = client.connect("localhost", port)
					.sync();
			channelFuture.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FaceBookClient server = new FaceBookClient(1234);
		server.run();

	}

}
