package com.framework.java.base;

public class StringUsage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		String c = "c";

		String string = a;
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			string += b;
			string += c;
			string.equals("abc");
		}
		System.out.println("string+ cost time:"
				+ (System.currentTimeMillis() - start) + "ms");

		start = System.currentTimeMillis();

		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 10000; i++) {
			stringBuffer.append(a);
			stringBuffer.append(b);
			stringBuffer.append(c);
			if (stringBuffer.toString().equals("abc")) {
			}
		}

		System.out.println("[Thread safe]stringbuffer cost time:"
				+ (System.currentTimeMillis() - start) + "ms");

		start = System.currentTimeMillis();

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			stringBuilder.append(a);
			stringBuilder.append(b);
			stringBuilder.append(c);
			if (stringBuilder.toString().equals("abc")) {
			}
		}

		System.out.println("[Thread unsafe]stringbuilder cost time:"
				+ (System.currentTimeMillis() - start) + "ms");

	}

}
