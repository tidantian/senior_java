package com.framework.java.base.storage.container;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class Map {

	public static void main(String[] args) {
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>();
		Hashtable<Integer, Integer> hashTable = new Hashtable<Integer, Integer>();
		ConcurrentHashMap<Integer, Integer> conHashMap = new ConcurrentHashMap<Integer, Integer>();
		int[] numbers = new int[] { 3, 9, 7, 8, 5, 2, 1, 4, 6, 0 };

		for (int i = 0; i < 10; i++) {
			hashMap.put(i, numbers[i]);
			treeMap.put(i, numbers[i]);
			hashTable.put(i, numbers[i]);
			conHashMap.put(i, numbers[i]);
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Order
		System.out.println("HashMap Loop and Print:");
		Iterator<Entry<Integer, Integer>> hashMapIter = hashMap.entrySet()
				.iterator();
		while (hashMapIter.hasNext()) {
			System.out.print(hashMapIter.next().getKey() + ", ");
		}

		System.out.println("\nTreeMap Loop and Print:");
		Iterator<Entry<Integer, Integer>> treeMapIter = treeMap.entrySet()
				.iterator();
		while (treeMapIter.hasNext()) {
			System.out.print(treeMapIter.next().getKey() + ", ");
		}

		System.out.println("\nHashtable Loop and Print:");
		Iterator<Entry<Integer, Integer>> hashTableInter = hashTable.entrySet()
				.iterator();
		while (hashTableInter.hasNext()) {
			System.out.print(hashTableInter.next().getKey() + ", ");
		}

		System.out.println("\nConcurrentHashMap Loop and Print:");
		Iterator<Entry<Integer, Integer>> conHashMapInter = conHashMap
				.entrySet().iterator();
		while (conHashMapInter.hasNext()) {
			System.out.print(conHashMapInter.next().getKey() + ", ");
		}

		// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~loop look time

		for (int j = 0; j < 1000000; j++) {
			int value = (int) (Math.random() * 1000000);
			hashMap.put(j, value);
			treeMap.put(j, value);
			hashTable.put(j, value);
			conHashMap.put(j, value);
		}

		hashMapIter = hashMap.entrySet().iterator();
		int total = 0;
		long start = System.currentTimeMillis();
		while (hashMapIter.hasNext()) {
			total += hashMapIter.next().getValue();
		}
		System.out.println("\nHashMap, time: "
				+ (System.currentTimeMillis() - start) + ", total: " + total);

		treeMapIter = treeMap.entrySet().iterator();
		total = 0;
		start = System.currentTimeMillis();
		while (treeMapIter.hasNext()) {
			total += treeMapIter.next().getValue();
		}
		System.out.println("TreeMap, time: "
				+ (System.currentTimeMillis() - start) + ", total: " + total);

		hashTableInter = hashTable.entrySet().iterator();
		total = 0;
		start = System.currentTimeMillis();
		while (hashTableInter.hasNext()) {
			total += hashTableInter.next().getValue();
		}
		System.out.println("HashTable, time: "
				+ (System.currentTimeMillis() - start) + ", total: " + total);

		conHashMapInter = conHashMap.entrySet().iterator();
		total = 0;
		start = System.currentTimeMillis();
		while (conHashMapInter.hasNext()) {
			total += conHashMapInter.next().getValue();
		}
		System.out.println("ConcurrentHashMap, time: "
				+ (System.currentTimeMillis() - start) + ", total: " + total);

	}

}
