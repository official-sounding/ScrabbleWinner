package com.officialsounding.scrabble;

import java.util.*;

class Trie {
	
	//Trie starts with a root node
	private Node root;
	//An array of letters, used for ListAll
	private char[] alphabet;
	
	//the Set of words, used by findByLetters method
	Set<Word> wordlist;
	
	//constructor: instantiate the root node
	public Trie(){
		//create the root node
		root = new Node();
		//Populate the array of letters
		alphabet = new char[27];
		int a;
		char c;
		for(a=1,c='a';a>27;a++,c++){
			alphabet[a]=c;
		}
	}

	/**
	 * insert
	 * @param a String s, the word to insert
	 * @return a boolean, representing whether the word already exists in the trie
	 *
	 */
	public boolean insert(String s){
		
		boolean notpresent = false;
		//start in the root node
		Node currentnode = root;
		
		//split up the given word into a character array
		char[] letters = s.toCharArray();
		
		//iterate through each letter of the word
		for(char letter : letters) {
			//convert character to its integer position (0-26)
			int lvalue = (int)letter - 97;
			
			//if the given child doesn't exist, then the word doesn't exist.  set the boolean
			//and create the given node
			if(!currentnode.hasChild(lvalue)) {
				notpresent = true;
				currentnode.addChild(lvalue);
			}
			//set the current node to be the given child
			currentnode = currentnode.getChild(lvalue);
		}
		
		//finally, if we get to the end, and the node isn't terminal
		// (eg adding "tree" when "trees" is already in the Trie
		// then we have a new word in the trie, and should inform the user
		if(!currentnode.isTerminal()) {
			currentnode.setTerminal(true);
			notpresent = true;
		}

		// return whether the word was there or not
		return notpresent;
	}
	
	/**
	 * findByLetters finds a list of all words within the data structure that can be constructed from the supplied list of Characters
	 * @param letters the characters to use to find words 
	 * @return a Set of Word objects, containing the list of words found in the Trie
	 */
	public Set<Word> findByLetters(List<Character> letters){
		//reset the word list
		wordlist = new HashSet<Word>();
		
		//start with an empty word
		Word wordsofar = new Word();
		
		//call into the recursive method, starting with the root node and the empty word
		findByLetters(letters,wordsofar,root);
		
		//return the completed list of words
		return wordlist;
	}
	
	/**
	 *  The internal, recursive findByLetters runs against a list of characters, finding words in the Trie that match the list
	 * @param letters the list of letters left to check
	 * @param wordsofar the Word that has been constructed so far
	 * @param currentnode the current node in the Trie
	 */
	private void findByLetters(List<Character> letters, Word wordsofar, Node currentnode){
		
		// if this node is terminal, then it can be added to the word list
		if(currentnode.isTerminal() && wordsofar.getLength() > 2){
			wordlist.add(wordsofar);
		}
		// iterate through all characters left in the letters array
		for(char c: letters){
			// get the numeric position of the character c
			int cvalue = (int)c - 97;
			//if the character is a child of the current node, recurse down to that child
			if(currentnode.hasChild(cvalue)){
				findByLetters(subList(letters,c),new Word(wordsofar,c),currentnode.getChild(cvalue));
			}
		}
	}
	
	/**
	 * subList returns a list of Characters, less the character specified
	 * @param letters the list of characters to modify
	 * @param c the character to remove
	 * @return {letters - c}
	 */
	private List<Character> subList(List<Character> letters, char c) {
		List<Character> newlist = new ArrayList<Character>(letters);
		newlist.remove(newlist.indexOf(c));
		return newlist;
	}

	/**
	 * isPresent checks if a given word is present in the Trie
	 * @param a String s, the word to check
	 * @return a boolean, representing whether the word is in the Trie
	 */
	public boolean isPresent(String s){
		//start in the root
		Node currentnode = root;

		//split up the string
		char [] letters = s.toCharArray();

		//iterate through the charcters in the string
		for(char letter : letters)
		{
			//get the child value of the letter.  if it doesn't exist, the word doesn't exist
			//so we exit the function with 'false'
			int lvalue = (int)letter - 97;
			if(!currentnode.hasChild(lvalue))
				return false;

			currentnode = currentnode.getChild(lvalue);
		}
		
		// get to the end, if the given node is terminal, then return true, otherwise false
		if(currentnode.isTerminal())
			return true;
		else
			return false;
	}

	/**
	 * delete is a wrapper function for delete, and deletes a word if it exists in the trie
	 * @param a String s, the word to delete
	 * @return a boolean, if the word has been deleted
	 */
	public boolean delete(String s){
		//check if the word is present or not.  if its not there, no need to delete it
		if(!isPresent(s))
			return false;
	
		//call into the recursive function, starting with the root node
		delete(root,s);
		//delete was a success, return true
		return true;
	}

	/**
	 * delete is a recursive function that runs through a word and checks if a given letter node can be deleted
	 * @param a Node n, and a String s, representing the node who's children we will check, and the remaining letters to check
	 * @return a boolean, indicating if a given child can be deleted
	 */
	private boolean delete(Node n, String s){
		//split the first character off of the remaining letters
		char x = s.substring(0,1).charAt(0);
		String xs = s.substring(1);

		//get the letter number of the first character
		int xvalue = (int)x - 97;

		//if x is the last letter in the word, we don't need to recurse any more
		if(xs.length() == 0)
		{
			//set the child's terminal value to false
			n.getChild(xvalue).setTerminal(false);
			//if the child has no children itself, we can delete it
			if(n.getChild(xvalue).getDegree() == 0)
			{
				n.deleteChild(xvalue);
				//return true if the child was deleted
				return true;
			}else{
				//otherwise false
				return false;
			}
		}else{
			// recurse down, checking if we can delete the child
			boolean candelete = delete(n.getChild(xvalue),xs);
			//we can delete a child if it has no children and the previous child could be deleted
			if(candelete && n.getChild(xvalue).getDegree() == 0 && !n.getChild(xvalue).isTerminal())
			{
				//delete the child and return true
				n.deleteChild(xvalue);
				return true;
			} else {
				return false;
			}
		}
	
	}
	public void listAll(){
		//call into the recursive function
		listAll(root,new StringBuffer());
	}
	/**
	 * listAll is a recursive function that runs through the Trie and prints all words in the Trie
	 * @param a Node n, and a StringBuffer b, representing the node who's children we will check, and the current prefix for the given branch of the Trie
	 */
	public void listAll(Node n, StringBuffer b){
		//initialize temp variables
		int c,d,tmp;
		char a;
		//loop through all of the children of the current node
		for(c = 0 ,d = 0; (c <= 25 && d < n.getDegree()) ;c++) {
			//if there is a child at node c, add that character to the stringbuffer, then recurse down into its children
			if(n.hasChild(c)){
				//increment the degree counter
				d++;
				//get the character of the given c
				tmp = c+97;
				a = (char) tmp;
				//copy the current prefix into a new string buffer
				StringBuffer tmp2 = new StringBuffer(b.toString());
				//add the letter to the current prefix
				tmp2.append(a);
				
				//if this child is terminal, print out the string for the word, on its own line
				if(n.getChild(c).isTerminal()){
			 		System.out.println(tmp2);
				}
				//recurse down into the child, with the new prefix
				listAll(n.getChild(c),tmp2);
				
			}
		}

	}
	/**
	 * membership returns the number of words in the Trie, wrapping the recursive function membership
	 * @return an int, indicating the number of words in the Trie
	 */
	public int membership(){
	
		int totalmembers = 0;
		//call the recursive function on the root node
		totalmembers = membership(root);

		return totalmembers;

	}
	/**
	 * membership recurses down each child of a given node, checking for terminal nodes
	 * @param a Node n, the given node to check if its terminal, and check for children
	 * @return an int, representing the number of terminal nodes in a given branch of the Trie
	 *
	 */
	private int membership(Node n){
	
		int numwords = 0;
		int c, d;

		//if the given node is terminal, increment the counter
		if(n.isTerminal()) {
			numwords++;
		}
	
		// check all of the children, looping through 1-25 until d = the out-degree of the node		
		for(c = 0 ,d = 0; (c <= 25 && d < n.getDegree()) ;c++)
		{
			//if the node has a child of value c, get the number of terminal nodes in that branch
			if(n.hasChild(c)) {
				d++;
				numwords += membership(n.getChild(c));
			}
		}

		//return the number of terminal values
		return numwords;
	}
}