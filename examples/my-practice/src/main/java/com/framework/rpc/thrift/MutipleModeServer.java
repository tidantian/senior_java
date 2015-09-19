package com.framework.rpc.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.framework.rpc.thrift.Hello.Processor;

public class MutipleModeServer {

	public void startServer() {
		System.out.println("thrift server open port 1234");
		try {
			TServerTransport serverTransport = null;
			TProtocolFactory portFactory = null;
			Processor<HelloImpl> process = new Processor<HelloImpl>(
					new HelloImpl());

			serverTransport = createTransport(serverTransport);
			portFactory = createProtocolFactory(portFactory);

			Args args = new Args(serverTransport);
			args.processor(process);
			args.protocolFactory(portFactory);

			TServer server = null;
			server = createServer(serverTransport, portFactory, process, args);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (Exception e) {
			//System.out.print(e);
		}
	}

	private TServer createServer(TServerTransport serverTransport,
			TProtocolFactory portFactory, Processor<HelloImpl> process,
			Args args) {
		TServer server = null;
		switch (Configuration.getServerType().intValue) {
		case 0: // Simple
			server = new TSimpleServer(args);
			break;
		case 1: // Thread Pool
			server = new TThreadPoolServer(args);
			break;
		case 2: // Non Blocking
			org.apache.thrift.server.TNonblockingServer.Args nonBlockingArgs = new org.apache.thrift.server.TNonblockingServer.Args(
					(TNonblockingServerSocket) serverTransport);
			nonBlockingArgs.processor(process);
			nonBlockingArgs.protocolFactory(portFactory);
			nonBlockingArgs.transportFactory(new TFramedTransport.Factory());
			server = new TNonblockingServer(nonBlockingArgs);
			break;
		default:
		}
		return server;
	}

	private TProtocolFactory createProtocolFactory(TProtocolFactory portFactory) {
		switch (Configuration.getProtocolType().intValue) {
		case 0: // Bin
			portFactory = new TBinaryProtocol.Factory(true, true);
			break;
		case 1: // Compact
			portFactory = new TCompactProtocol.Factory();
			break;
		case 2: // Json
			break;
		case 3: // Simple Json
			break;
		default:

		}
		return portFactory;
	}

	private TServerTransport createTransport(TServerTransport serverTransport)
			throws TTransportException {
		switch (Configuration.getTransportType().intValue) {
		case 0: // socket
			serverTransport = new TServerSocket(Configuration.getPort());
			break;
		case 1:
		case 2: // non-blocking
			serverTransport = new TNonblockingServerSocket(
					Configuration.getPort());
			break;
		default:

		}
		return serverTransport;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("thrift server init");
		MutipleModeServer server = new MutipleModeServer();
		System.out.println("thrift server start");
		server.startServer();
		System.out.println("thrift server end");

	}

}
