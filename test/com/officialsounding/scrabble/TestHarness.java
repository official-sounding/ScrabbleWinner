package com.officialsounding.scrabble;

import java.io.FileNotFoundException;

/**
 * TestHarness is used to run the Scrabble function without having to use command-line arguments, for testing within Eclipse
 * @author Peter
 *
 */
public class TestHarness {

	public static void main(String[] args) throws FileNotFoundException{
		Scrabble.main(new String[]{"i","g","h","l","p","a","a"});
	}
}
