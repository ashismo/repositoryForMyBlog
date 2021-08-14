package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import com.ashish.java8.comparable.comparator.Laptop2;

// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
//A string s is called good if there are no two different characters in s that have the same frequency.
//
//Given a string s, return the minimum number of characters you need to delete to make s good.
//
//The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

//Example 1:
//
//Input: s = "aab"
//Output: 0
//Explanation: s is already good.
//Example 2:
//
//Input: s = "aaabbbcc"
//Output: 2
//Explanation: You can delete two 'b's resulting in the good string "aaabcc".
//Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
//Example 3:
//
//Input: s = "ceabaacb"
//Output: 2
//Explanation: You can delete both 'c's resulting in the good string "eabaab".
//Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).

public class MinimumCharactersDeletion {
	
	static Integer minDeletion = 0;
	
	public static void main(String[] args) {
		minDeletion = minDeletion("aab");
		
		System.out.println("Min Deletion = " + minDeletion);
	}
	
	public static int minDeletion(String s) {
		
		// This map holds character count and list of characters.
		Map<Integer, List<String>> map = new TreeMap<>();
		
		for(int i = 0; i < s.length(); i++) {
			int count = 0;
			for(int j = 0; j < s.length(); j++) {
				if(s.charAt(i) == s.charAt(j)) {
					count++;
				}
			}
			if(count > 0) {
				if(map.get(count) == null) {
					map.put(count, new ArrayList<String>());
				}
				List<String> holderList = map.get(count);
				if(!holderList.contains(String.valueOf(s.charAt(i)))) {
					holderList.add(String.valueOf(s.charAt(i)));
				}
			}
		}
		
		System.out.println(map);
		Map<Integer, Integer> occuranceMap = new TreeMap<Integer, Integer>(Collections.reverseOrder());
		
		map.keySet().iterator().forEachRemaining(x->{
			occuranceMap.put(map.get(x).size(), x);
		});
		
		Iterator<Integer> it = occuranceMap.keySet().iterator();
		Integer firstKey = 0;
		while(it.hasNext()) {
			firstKey = occuranceMap.get(it.next());
			break;
		}
		
		System.out.println(occuranceMap);
		
		List<String> list = map.get(firstKey);
		if(list != null && list.size() > 1) {
			minDeletion++;
			minDeletion(s.replaceFirst(list.get(0), ""));
		}
		return minDeletion;
		
		
	}
}
