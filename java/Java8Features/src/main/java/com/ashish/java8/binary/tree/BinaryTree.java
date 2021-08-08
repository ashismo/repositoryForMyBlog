package com.ashish.java8.binary.tree;

public class BinaryTree {
	public static void main(String[] args) {
		BinaryTree binaryTree = new BinaryTree();
		Node root = null;
		
		// ADD node into binary tree
		root = binaryTree.insert(root, 3);
		binaryTree.insert(root, 2);
		binaryTree.insert(root, 1);
		binaryTree.insert(root, 10);
		binaryTree.insert(root, 9);
		binaryTree.insert(root, 12);
		
		
		
		// Find node from a binary tree
		binaryTree.find(root, 10);
		binaryTree.find(root, 100);
		binaryTree.find(root, 2);
	}
	
	
	// if the new node's value is lower than the current node's, we go to the left child
	//	if the new node's value is greater than the current node's, we go to the right child
	//	when the current node is null, we've reached a leaf node and we can insert the new node in that position
	public Node insert(Node current, int value) {
	    if(current == null) {
	    	return new Node(value);
	    } else {
	    	if(value > current.getData()) {
	    		if(current.getRight() == null) System.out.println("Adding " + value + " to the right of " + current.getData());
	    		current.setRight(insert(current.getRight(), value));
	    	} else if(value < current.getData()) {
	    		if(current.getLeft() == null) System.out.println("Adding " + value + " to the left of " + current.getData());
	    		current.setLeft(insert(current.getLeft(), value));
	    	}
	    }
	    return current;
	}
	
	
	public Node find(Node current, int value) {
		if(current.getData() == value) {
			System.out.println("value = " + value + " found which last left node " + current.getLeft() + " right node: " + current.getRight());
			return current;
		}
		if(current.getLeft() == null && current.getRight() == null) {
			System.out.println("value = " + value + " not found");
			return null;
		}
		if(value > current.getData()) {
			find(current.getRight(), value);
		} else if(value < current.getData()) {
			find(current.getLeft(), value);
		}
		return current;
	}
}
