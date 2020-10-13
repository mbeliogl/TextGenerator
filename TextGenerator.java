package com.maxim;
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A class for training a Markov chain from a body of text
 * and then generating new text using the estimated frequencies.
 */

class TextGenerator {

    protected ChainingHashMap countTable;
    protected ChainingHashMap totalTable;
    protected int totalWords;
    protected Random rand;
    
    public TextGenerator() {
		rand = new Random();
		countTable = new ChainingHashMap();
		totalTable = new ChainingHashMap();
		totalWords = 0;
    }
    
    /**
     * Trains the Markov chain on the given body of text
     * @param text a String containing the text to train on
     */
    public void train(String text) {
		String [] words = text.split("\\s+"); //Split the string on whitespace
	
		for(int i = 0; i < words.length - 1; i++) {
			totalWords++;
			if(totalTable.get(words[i]) == null) {
				totalTable.put(words[i], 1); // if the word is new, initialize value to 1
		    }
			else {
				int more = (Integer) totalTable.get(words[i]) + 1; // if not, update value to + 1 from previous
				totalTable.put(words[i], more);
		    }
		
			if(countTable.get(words[i]) == null) {
				ChainingHashMap inner = new ChainingHashMap(); //the map value for the word
			
				inner.put(words[i+1], 1);
				countTable.put(words[i], inner); //inner becomes value for word
		    }     
		
			else {
				ChainingHashMap current = (ChainingHashMap)countTable.get(words[i]);
			
				if(current.get(words[i+1]) == null) {
					current.put(words[i+1], 1);  //  checking if inner is empty or has values
			    }
				else {
					int more1 = (Integer) current.get(words[i+1]) + 1; //update the count for the inner map
					current.put(words[i+1], more1);
			    }
		    }
	    }
    }
    
    /**
     * Picks a random word from the given frequencies
     * @param counts a hash map that maps words (String) to the frequency of that word (Integer)
     * @param totalCount the sum of all the frequencies in counts
     * @return the randomly chosen word
     */
    public String sampleWord(ChainingHashMap counts, int totalCount) {

		double randNum = rand.nextDouble(); //random in range 0-1
		double probSum = 0.0;

		for (Entry e : counts) {
		//Add this word's frequency to the cumulative probability so far
			int num = (Integer) e.value;
			probSum += ((double)num / (double)totalCount);

		//If the sum crosses the random number, this is the randomly selected word
		if (randNum < probSum)
		    return (String) e.key;
	    }
	
		// Should never reach this line!
		return "";
    }
    
    /**
     * Uses the Markov chain to generate a sequence of words of the given length
     * (Randomly picks a word, then based on that word picks a next word, and so on)
     * @param numWords the number of words to generate
     * @return a String containing the generated text
     */
    public String generateText(int numWords) {
		String [] text = new String[numWords];
		String first = sampleWord(totalTable, totalWords); //the first word
		text[0] = first;
	
		for(int i = 1; i < numWords; i++) {
			ChainingHashMap freq = (ChainingHashMap) countTable.get(text[i-1]); // frequencies of each word
			int lastWord = (Integer) totalTable.get(text[i-1]);
			String nWord = sampleWord(freq, lastWord); // next word generator
			text[i] = nWord;
	    }
	
		String t  = "";

		for(String s: text) {
			t = t+s;
			t = t + " "; // back to text form
	    }
		return "\"..." + t + "...\"";
    }


    /**
     * Takes a file, reads its contents and passes them to train()
	 * Asks user for the number of words to be generated [numWords]
	 * Uses generateText(numWords) to generate and print the text
     */
    public static void main(String [] args) {

    	// get file to train from
		System.out.println("Please provide the absolute path to the file I can train from: ");
		Scanner fileName = new Scanner(System.in);
		String inputFileName = fileName.nextLine().trim();

		// get numWords from user
		System.out.println("How many words should I generate? ");
		int numWords = fileName.nextInt();

    	//Open the file
		File file = new File(inputFileName);
		Scanner scan = null;

		try {
			scan = new Scanner(file);
	    }
		catch (FileNotFoundException fnf) {
			System.err.println("Input file not found");
			System.exit(1);
	    }
	
		// read the entire file into a String
		scan.useDelimiter("\\Z");
		String text = scan.next();

		// generate Markov text
		TextGenerator t = new TextGenerator();
		t.train(text);
		System.out.println(t.generateText(numWords));
    }
}
