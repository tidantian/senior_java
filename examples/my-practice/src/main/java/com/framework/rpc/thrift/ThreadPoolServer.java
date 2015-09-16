package com.framework.rpc.thrift;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TCompactProtocol.Factory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

import com.framework.rpc.thrift.Hello.Processor;

public class ThreadPoolServer {

	public enum ProtocolType {
		Binary(0), Compact(1), Json(2), SimpleJason(3);
		int type;

		ProtocolType(int type) {
			this.type = type;
		}
	}

	public void startServerWithBinary() {
		try {
			System.out.println("thrift server open port 1234");
			TServerTransport serverTransport = new TServerSocket(1234);
			Processor<HelloImpl> process = new Processor<HelloImpl>(
					new HelloImpl());
			org.apache.thrift.protocol.TBinaryProtocol.Factory portFactory = new TBinaryProtocol.Factory(
					true, true);
			Args args = new Args(serverTransport);
			args.processor(process);
			args.protocolFactory(portFactory);
			TServer server = new TThreadPoolServer(args);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public void startServerWithCompact() {
		try {
			System.out.println("thrift server open port 1234");
			TServerTransport serverTransport = new TServerSocket(1234);
			Processor<HelloImpl> process = new Processor<HelloImpl>(
					new HelloImpl());
			Factory portFactory = new TCompactProtocol.Factory();
			Args args = new Args(serverTransport);
			args.processor(process);
			args.protocolFactory(portFactory);
			TServer server = new TThreadPoolServer(args);
			server.serve();
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}

	public void startServer(int protocolType) {
		switch (protocolType) {
		case 0:
			startServerWithBinary();
			break;
		case 1:
			startServerWithCompact();
			break;
		case 2:
			break;
		case 3:
			break;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("thrift server init");
		ThreadPoolServer server = new ThreadPoolServer();
		System.out.println("thrift server start");
		server.startServer(ProtocolType.Binary.type);
		System.out.println("thrift server end");

	}

}
