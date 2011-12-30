ScrabbleSolver is a simple java program that finds the best (highest value) words to play from a given set of letters

It uses the public domain list of words from Enhanced North American Benchmark Lexicon (ENABLE), a public domain list of words used for games like Words with Friends

the word list is available from the [dotnetperls-controls](https://code.google.com/p/dotnetperls-controls/downloads/detail?name=enable1.txt&can=2&q=) project

The program uses a Trie to store the words from the dictionary.  

I wrote the Trie class for my Algorithms class in University, and then modified it to add functionality to search by a list of letters
My implementation is probably quite inefficient, but the program is performant with the small (~180k) dictionary and # of characters to search

# Usage

java scrabble a b c d e

where 'a b c d e' is any list of space-separated characters that you wish to find words for

output is a tab separated list in the form: word	value
output is sorted by the word's scrabble value in decreasing order
