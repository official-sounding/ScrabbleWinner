package com.officialsounding.scrabble;

import java.util.*;

/**
 * the Word class stores a word found in the dictionary, along with its value if played (without letter/word modifiers) on a standard English Scrabble board
 * 
 * @author Peter
 *
 */
public class Word implements Comparable<Word>{

	private List<Character> letters;
	private int value;
	
	/**
	 * this is the default constructor for an empty word
	 */
	public Word(){
		letters = new ArrayList<Character>();
		value = 0;
	}
	
	/**
	 * Use this Constructor to append a character to an existing Word object
	 * @param wordsofar the existing word object to append to
	 * @param c the character to append to the existing word
	 */
	public Word(Word wordsofar, char c) {
		letters = new ArrayList<Character>();
		for(char x: wordsofar.getLetters()){
			letters.add(x);
		}
		letters.add(c);
		value = wordsofar.getValue() + getScrabbleValue(c);
	}

	/**
	 * get the English Scrabble value for the given character
	 * @param c the character to find the value of
	 * @return the value of the specified character
	 */
	private int getScrabbleValue(char c) {
		// TODO Auto-generated method stub
		switch(c){
		case 'e':
		case 'a':
		case 'i':
		case 'o':
		case 'u':
		case 'l':
		case 'n':
		case 'r':
		case 't':
		case 's':
			return 1;
		case 'd':
		case 'g':
			return 2;
		case 'b':
		case 'c':
		case 'm':
		case 'p':
			return 3;
		case 'f':
		case 'h':
		case 'v':
		case 'w':
		case 'y':
			return 4;
		case 'k':
			return 5;
		case 'j':
		case 'x':
			return 8;
		case 'q':
		case 'z':
			return 10;
		}
		return 0;
	}

	/**
	 * get the Scrabble value of the Word
	 * @return
	 */
	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	/**
	 * get the list of letters in the Word
	 * @return
	 */
	private List<Character> getLetters(){
		return letters;
	}

	/**
	 * get a String representing the Word
	 * @return
	 */
	public String getWord(){
		StringBuffer sb = new StringBuffer();
		for(char c: letters){
			sb.append(c);
		}
		return sb.toString();
	}
	
	/**
	 * get the String and the Scrabble value of the Word
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(getWord());
		sb.append('\t');
		sb.append(value);

		
		return sb.toString();
	}
	
	/**
	 * get the length of the Word
	 * @return
	 */
	public int getLength(){
		return letters.size();
	}
	
	
	/**
	 * sort by the word's scrabble value, decreasing
	 */
	@Override
	public int compareTo(Word arg0) {
		return -Integer.compare(getValue(), arg0.getValue());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return getWord().hashCode();
	}
	@Override
	public boolean equals(Object obj){
		return getWord().compareTo(((Word)obj).getWord()) == 0;
	}
	
}
