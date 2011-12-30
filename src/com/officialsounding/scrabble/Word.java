package com.officialsounding.scrabble;

import java.util.*;

public class Word implements Comparable<Word>{

	private List<Character> letters;
	private int value;
	
	public Word(){
		letters = new ArrayList<Character>();
		value = 0;
	}
	public Word(Word wordsofar, char c) {
		letters = new ArrayList<Character>();
		for(char x: wordsofar.getLetters()){
			letters.add(x);
		}
		letters.add(c);
		value = wordsofar.getValue() + getScrabbleValue(c);
	}

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

	public int getValue() {
		// TODO Auto-generated method stub
		return value;
	}

	public List<Character> getLetters(){
		return letters;
	}

	public String getWord(){
		StringBuffer sb = new StringBuffer();
		for(char c: letters){
			sb.append(c);
		}
		return sb.toString();
	}
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(getWord());
		sb.append('\t');
		sb.append(value);

		
		return sb.toString();
	}
	
	public int getLength(){
		return letters.size();
	}
	
	@Override
	/**
	 * sort by the word's scrabble value, decreasing
	 */
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
