package com.ashish.java.misc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.SortedMap;
import java.util.TreeMap;

public class LongestPalindrom {
	public static void main(String[] args) {
		String word = "abcbabbab";
		List<String> list = new ArrayList<>();
		Map<Integer, String> map = new TreeMap<Integer, String>();
		
		for(int i = 0; i < word.length(); i++) {
			for(int j = word.length() - 1; j > i+1; j--) {
				String subStr = word.substring(i, j);
				StringBuilder sb = new StringBuilder(subStr).reverse();
				if(sb.toString().equals(subStr)) {
					map.put(subStr.length(), subStr);
				}
			}
		}
		
		Iterator<Integer> it = map.keySet().iterator();
		
		int length = 0;
		while(it.hasNext()) {
			length = it.next();
		}
		
		System.out.print(length);
	}
}
