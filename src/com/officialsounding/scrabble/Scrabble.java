package com.officialsounding.scrabble;
import java.io.*;
import java.util.*;

public class Scrabble {

	
	private static String dictlocation = "D:\\media\\Downloads\\enable1.txt";
	public static void main(String[] args) throws FileNotFoundException{
		Trie dictionary = new Trie();
		List<Character> letters = new ArrayList<Character>();
		
		System.err.print("Building Dictionary...");
		Scanner input = new Scanner(new File(dictlocation));
		int i = 0;
		while(input.hasNext()){
			dictionary.insert(input.nextLine());
			i++;
		}
		System.err.println(" "+i+" words loaded");
		System.err.print("Letters supplied: ");
		for(String arg: args){
			letters.add(arg.charAt(0));
			System.err.print(arg+" ");
		}
		System.err.println();
		System.err.println("Generating Scrabble Words from Input...");
		Set<Word> wordlist = dictionary.findByLetters(letters);
		
		List<Word> sortedlist = new ArrayList<Word>(wordlist);
		Collections.sort(sortedlist);
		System.err.println("Found "+wordlist.size()+" words");
		
		for(Word w: sortedlist){
			System.out.println(w);
		}
	}
}
