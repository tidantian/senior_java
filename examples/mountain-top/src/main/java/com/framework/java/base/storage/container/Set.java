package com.framework.java.base.storage.container;

import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;

public class Set {

	public static void main(String[] args) {
		HashSet<Integer> hashSet = new HashSet<Integer>();
		TreeSet<Integer> treeSet = new TreeSet<Integer>();
		int[] numbers = new int[] { 3, 9, 7, 8, 5, 2, 1, 4, 6, 0 };

		for (int i = 0; i < 10; i++) {
			hashSet.add(numbers[i]);
			treeSet.add(numbers[i]);
		}

		System.out.println("HashSet Loop and Print:");
		Iterator<Integer> hashMapIter = hashSet.iterator();
		while (hashMapIter.hasNext()) {
			System.out.print(hashMapIter.next() + ", ");
		}

		System.out.println("\nTreeSet Loop and Print:");
		Iterator<Integer> treeMapIter = treeSet.iterator();
		while (treeMapIter.hasNext()) {
			System.out.print(treeMapIter.next() + ", ");
		}

		for (int j = 0; j < 1000000; j++) {
			int value = (int) (Math.random() * 1000000);
			hashSet.add(value);
			treeSet.add(value);
		}

		hashMapIter = hashSet.iterator();
		int total = 0;
		long start = System.currentTimeMillis();
		while (hashMapIter.hasNext()) {
			total += hashMapIter.next();
		}
		System.out.println("\nHashSet, time: "
				+ (System.currentTimeMillis() - start) + ", total: " + total);

		treeMapIter = treeSet.iterator();
		total = 0;
		start = System.currentTimeMillis();
		while (treeMapIter.hasNext()) {
			total += treeMapIter.next();
		}
		System.out.println("\nTreeSet, time: "
				+ (System.currentTimeMillis() - start) + ", total: " + total);

	}

}
