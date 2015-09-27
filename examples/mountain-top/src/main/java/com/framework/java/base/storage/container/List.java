package com.framework.java.base.storage.container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Vector;

public class List {
	private static final int SAMPES = 1000000;
	private static final int REPEAT = 10;
	public ArrayList<Integer> arrayList = new ArrayList<Integer>();
	public LinkedList<Integer> linkedList = new LinkedList<Integer>();
	public Vector<Integer> vector = new Vector<Integer>();
	public Stack<Integer> stack = new Stack<Integer>();

	public List() {
		for (int i = 0; i < SAMPES; i++) {
			arrayList.add(i);
			linkedList.add(i);
			vector.add(i);
			stack.add(i);
		}
	}

	public void getValue(int value) {
		System.out.println("get value ..");
		long start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			arrayList.indexOf(value);
		System.out.println("ArrayList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			linkedList.indexOf(value);
		System.out.println("LinkedList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			vector.indexOf(value);
		System.out.println("Vector, time: "
				+ (System.currentTimeMillis() - start));
	}
	
	public void getWithIndex(int index) {
		System.out.println("get value with index ..");
		long start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			arrayList.get(index);
		System.out.println("ArrayList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			linkedList.get(index);
		System.out.println("LinkedList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			vector.get(index);
		System.out.println("Vector, time: "
				+ (System.currentTimeMillis() - start));
	}

	public void removeByValue(Integer value) {
		System.out.println("remove by value ..");
		long start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			arrayList.remove(value);
		System.out.println("ArrayList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			linkedList.remove(value);
		System.out.println("LinkedList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			vector.remove(value);
		System.out.println("Vector, time: "
				+ (System.currentTimeMillis() - start));
	}
	
	public void removeByIndex(int index) {
		System.out.println("remove by index ..");
		long start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			arrayList.remove(index);
		System.out.println("ArrayList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			linkedList.remove(index);
		System.out.println("LinkedList, time: "
				+ (System.currentTimeMillis() - start));

		start = System.currentTimeMillis();
		for (int repeat = 0; repeat < REPEAT; repeat++)
			vector.remove(index);
		System.out.println("Vector, time: "
				+ (System.currentTimeMillis() - start));
	}

	public static void main(String[] args) {
		List list = new List();
		list.getValue(SAMPES / 2);
		list.getWithIndex(SAMPES / 2);
		list.removeByValue(SAMPES / 2);
		list.removeByIndex(SAMPES / 2);
	}

}
