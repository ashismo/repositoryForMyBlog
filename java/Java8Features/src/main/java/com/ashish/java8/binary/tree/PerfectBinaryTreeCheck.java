package com.ashish.java8.binary.tree;

public class PerfectBinaryTreeCheck {
	
	static int count=0;
	
//	A binary tree is perfect when all levels are complete.
//	Write a method that checks if a binary tree is perfect.
//	TreeNode API methods: node.left() and node.right().
	public static void main(String[] args) {
		PerfectBinaryTreeCheck obj = new PerfectBinaryTreeCheck();
		Node root = null;
		root = obj.insert(root, 4);
		obj.insert(root, 10);
		obj.insert(root, 2);
		obj.insert(root, 1);
		obj.insert(root, 3);
		obj.insert(root, 9);
		obj.insert(root, 12);
		
		int size = obj.count(root);
		System.out.println("Size of the binary tree: " + size);
	}
	
	public Node insert(Node current, int data) {
		if(current == null) {
			return new Node(data);
		} 
		if(data > current.getData()) {
			if(current.getRight() == null) {
				System.out.println("Value " + data + " is inserted to the right of " + current.getData());
			}
			current.setRight(insert(current.getRight(), data));
		} else if(data < current.getData()) {
			if(current.getLeft() == null) {
				System.out.println("Value " + data + " is inserted to the left of " + current.getData());
			}
			current.setLeft(insert(current.getLeft(), data));
		}
		return current;
	}
	
	public int count(Node current) {
		if(current == null) return count;
		count++;
		count(current.getLeft());
		count(current.getRight());
		return count;
	}
}
