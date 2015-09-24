package com.framework.net.netty;

import java.io.Serializable;

public class Message implements Serializable {
	private static final long serialVersionUID = 2222779734241765490L;
	private int type;
	private int len;
	private Object body;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLen() {
		return len;
	}

	public void setLen(int len) {
		this.len = len;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public enum TYPE {
		SHOW(0), QUERY(1), ADD(2), UPDATE(3), DELETE(4);
		int value;

		TYPE(int value) {
			this.value = value;
		}
	}

}
