package com.officialsounding.scrabble;

class Node {
	private boolean terminal;
	private int outDegree;
	private Node[] children;

	// Node constructor, sets terminal to false, degree to 0, and creates an array of Nodes for the Node's children
	public Node() {
		terminal = false;
		outDegree = 0;
		children = new Node[26];
	}

	// get the out degree of the node
	public int getDegree() {
		return outDegree;
	}

	// return whether the node is terminal
	public boolean isTerminal() {
		return terminal;
	}

	// set the terminal state of the Node
	public void setTerminal(boolean t) {
		terminal = t;
	}
	
	// return whether the node has a child of a given value
	public boolean hasChild(int n) {
		if(children[n] == null)
			return false;
		else
			return true;
	}

	// get a child of the Node at the given value
	public Node getChild(int n) {
		
		return children[n];
	}

	// add a child to the Node at the given value, incrementing the outDegree
	public void addChild(int n) {
		children[n] = new Node();
		outDegree++;
	}

	// delete a child from the Node at the given value, decrementing the outDegree
	public void deleteChild(int n) {
		children[n] = null;
		outDegree--;
	}
}