package com.ashish.java8.binary.tree;

public class Node {
	private int data;
	private Node left;
	private Node right;
	
	public Node() {
		
	}
	public Node(int value) {
        this.data = value;
        right = null;
        left = null;
    }
	
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}
	
}
