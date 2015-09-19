package com.framework.rpc.thrift;

import org.apache.thrift.TException;

public class HelloImpl implements Hello.Iface {

	private static int counter;

	@Override
	public String helloString(String word) throws TException {
		counter++;
		StringBuffer rsp= new StringBuffer();
		rsp.append("Hello Baby, you access times: " + counter + "; you say "+word);
		//System.out.println(rsp.toString());
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rsp.toString();
	}

}
