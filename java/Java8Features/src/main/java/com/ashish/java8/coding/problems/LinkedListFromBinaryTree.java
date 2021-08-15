package com.ashish.java8.coding.problems;

import java.util.ArrayList;
import java.util.List;

//Question 3: Write a function which accepts the root of a tree, and returns a Linked List which contains the 
//leaf nodes of the tree from left to right order

//Assumptions:
//
//(*) Structure of the node of tree is as follows:
//struct TreeNode
//{
//    int data;
//    struct TreeNode* left;
//    struct TreeNode* right;
//};
//
//(*) Don't allocate extra memory for Linked List, just let the right pointer of a leaf
//node point to the next leaf node to form a linked list.
//
//Example:
//            10 
//         /      \               
//       20        100          
//      /  \       / \             
//    30    40    9   66
//
//Output: 30 -> 40 -> 9 -> 66 
public class LinkedListFromBinaryTree {
	public static List<TreeNode> list = new ArrayList<TreeNode>();
	
	public static TreeNode prev = null;
	
	public static void main(String[] args) {
		TreeNode root = null;
		root = insert(10, root);
		insert(8, root);
		insert(15, root);
		insert(12, root);
		insert(16, root);
		insert(6, root);
		insert(9, root);
		
		travarse(root);
		System.out.println(list);
		
		list.forEach(x-> System.out.print(x.getData() + " "));
		
		System.out.println(prev);
	}
	
	public static TreeNode insert(int data, TreeNode root) {
		if(root == null) {
			return new TreeNode(data);
		}
		
		if(root.getData() > data) {
			root.setLeft(insert(data, root.getLeft()));
		} else if(root.getData() < data) {
			root.setRight(insert(data, root.getRight()));
		}
		return root;
	}
	
	public static TreeNode travarse(TreeNode root) {
		if(root.getLeft() == null && root.getRight() == null) {
			if(prev == null) {
				prev = root;
			} else {
				TreeNode temp = prev;
				while(true) {
					if(temp.getRight() == null) {
						temp.setRight(root);
						break;
					} else {
						temp = temp.getRight();
					}
				}
			}
			list.add(root);
			return root;
		}
		TreeNode tn = null;
		travarse(root.getLeft());
		travarse(root.getRight());
		return root;
		
	}
}
