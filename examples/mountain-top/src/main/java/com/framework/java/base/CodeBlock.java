package com.framework.java.base;

public class CodeBlock {

	static {
		System.out.println("init static Code Block ...");
	}

	{
		System.out.println("init Code Block ...");
	}

	public CodeBlock() {
		System.out.println("init Constructure ...");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CodeBlock codeBlock = new CodeBlock();
		codeBlock.toString();

	}

}
