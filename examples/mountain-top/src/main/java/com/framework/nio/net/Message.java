package com.framework.nio.net;

import java.io.Serializable;

public class Message implements Serializable  {
    private static final long serialVersionUID = 4842567358256189675L;
    private byte[] content;
    private int type;

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
