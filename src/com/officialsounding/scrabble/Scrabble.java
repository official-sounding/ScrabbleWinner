package com.officialsounding.scrabble;
import java.io.*;
import java.util.*;

/**
 * Scrabble defines a command-line program that takes a list of arguments comprised of characters to search a dictionary for all words present that contain the specified letters
 * 
 * @author Peter Elliott
 *
 */
public class Scrabble {

	private static String dictlocation = "resources/enable1.txt";
	public static void main(String[] args) throws FileNotFoundException{
		
		Trie dictionary = new Trie();
		List<Character> letters = new ArrayList<Character>();
		
		//Build the Dictionary from the Dictionary file
		System.err.print("Building Dictionary...");
		Scanner input = new Scanner(new File(dictlocation));
		int i = 0;
		while(input.hasNext()){
			dictionary.insert(input.nextLine());
			i++;
		}
		System.err.println(" "+i+" words loaded");
		
		//Read in the letters from standard input
		System.err.print("Letters supplied: ");
		for(String arg: args){
			letters.add(arg.charAt(0));
			System.err.print(arg+" ");
		}
		System.err.println();
		
		//Find the words that contain the letters supplied
		System.err.println("Generating Scrabble Words from Input...");
		Set<Word> wordlist = dictionary.findByLetters(letters);
		
		//sort the list by the word's scrabble value, decreasing
		List<Word> sortedlist = new ArrayList<Word>(wordlist);
		Collections.sort(sortedlist);
		
		
		System.err.println("Found "+wordlist.size()+" words");
		
		//print the words to standard output
		for(Word w: sortedlist){
			System.out.println(w);
		}
	}
}
