package com.ashish.java8.binary.tree;

public class BinaryTree {
	public static void main(String[] args) {
		
		Node root = null;
		root = add(root,1);
		add(root,2);
		add(root,3);
		add(root,4);
	}
	
	public static Node add(Node root, int value) {
	    root = addRecursive(root, value);
	    return root;
	}
	
	// if the new node's value is lower than the current node's, we go to the left child
	//	if the new node's value is greater than the current node's, we go to the right child
	//	when the current node is null, we've reached a leaf node and we can insert the new node in that position
	private static Node addRecursive(Node current, int value) {
		if(current == null) {
			return new Node(value);
		}
		
		if(value > current.getData()) {
			current.setRight(addRecursive(current, current.getData()));
		} else if (value < current.getData()) {
			current.setLeft(addRecursive(current, current.getData()));
		} else {
			return current;
		}
		return current;
	}
}
