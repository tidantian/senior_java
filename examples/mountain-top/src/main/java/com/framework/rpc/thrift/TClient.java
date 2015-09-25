package com.framework.rpc.thrift;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class TClient implements Runnable {
	private static AtomicInteger counter = new AtomicInteger();

	public void startClient() {
		TTransport transport = null;
		try {
			transport = createTransport(transport);

			TProtocol protocol = null;
			TProtocolFactory protocolFactory = null;

			switch (Configuration.getProtocolType().intValue) {
			case 0: // Bin
				protocol = new TBinaryProtocol(transport);
				protocolFactory = new TBinaryProtocol.Factory();
				break;
			case 1: // Compact
				protocol = new TCompactProtocol(transport);
				protocolFactory = new TCompactProtocol.Factory();
				break;
			case 2: // Json
				break;
			case 3: // Simple Json
				break;
			default:

			}
			
			if (Configuration.getServerType() == Configuration.ServerType.NonBlocking) {
				TAsyncClientManager clientManager = new TAsyncClientManager();
				Hello.AsyncClient client = new Hello.AsyncClient(
						protocolFactory, clientManager,
						(TNonblockingSocket) transport);
				MethodCallback callBack = new MethodCallback();

				client.helloString("non-blocking", callBack);
				counter.incrementAndGet();
				// System.out.println("thrift client close connextion");
				//if (transport.isOpen())
					//transport.close();
			} else {
				Hello.Client client = new Hello.Client(protocol);
				transport.open();
				// System.out.println(client.helloString("panguso"));
				for (int i = 0; i < 10; i++) {
					client.helloString("panguso");
				}
				counter.incrementAndGet();
				transport.close();
				// System.out.println("thrift client close connextion");
			}
			
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private TTransport createTransport(TTransport transport) throws IOException {
		// System.out.println("thrift client connext server at 1234 port ");
		switch (Configuration.getTransportType().intValue) {
		case 0: // socket
			transport = new TSocket(Configuration.getHost(),
					Configuration.getPort(), 10000);
			break;
		case 1: // Frame
			transport = new TFramedTransport(new TSocket(
					Configuration.getHost(), Configuration.getPort()));
			break;
		case 2: // non-blocking
			transport = new TNonblockingSocket(Configuration.getHost(),
					Configuration.getPort());
			break;
		default:

		}
		return transport;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// System.out.println("thrift client init ");
		// TClient client = new TClient();
		// System.out.println("thrift client start ");
		// client.startClient();
		long startTime = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(200);
		TClient [] client = new TClient[10];
		for (int i = 0; i < 1; i++) {
			client[i]= new TClient();
			executor.execute(client[i]);
		}
		while (true) {
			if (counter.get() == 1) {
				break;
			}
		}
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
		long duringTime = System.currentTimeMillis() - startTime;
		System.out.print(duringTime);
		// System.out.println("thrift client end ");

	}

	public class MethodCallback implements AsyncMethodCallback<Object> {
		Object response = null;

		public Object getResult() {
			return this.response;
		}

		@Override
		public void onComplete(Object response) {
			this.response = response;
			System.out.println("Function Call Finished.");
			System.out.println(response);
		}

		@Override
		public void onError(Exception arg0) {
		}
	}

	@Override
	public void run() {
		startClient();
	}

}
