package com.framework.rpc.thrift;

public class Configuration {
	private static ServerType serverType = ServerType.NonBlocking;
	private static ProtocolType protocolType = ProtocolType.Compact;
	private static TransportType transportType = TransportType.NonBlocking;
	private static String host = "localhost";
	private static int port = 1234;
	private static boolean isDebugMode = false;
	
	public static ServerType getServerType() {
		return serverType;
	}
	
	public static ProtocolType getProtocolType() {
		return protocolType;
	}
	
	public static TransportType getTransportType() {
		return transportType;
	}
	
	public static String getHost() {
		return host;
	}
	
	public static int getPort() {
		return port;
	}
	
	public static boolean isDbugMode() {
		return isDebugMode;
	}
	
	public static enum ServerType {
		Simple(0), ThreadPool(1), NonBlocking(2);
		int intValue;
		
		ServerType(int intValue) {
			this.intValue = intValue;
		}
	}
	
	public static enum ProtocolType {
		Binary(0), Compact(1), Json(2), SimpleJason(3);
		int intValue;

		ProtocolType(int intValue) {
			this.intValue = intValue;
		}
	}
	
	public static enum TransportType {
		Socket(0), Frame(1), NonBlocking(2);
		int intValue;

		TransportType(int intValue) {
			this.intValue = intValue;
		}
	}

}
