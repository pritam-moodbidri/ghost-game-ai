import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AlphaBetaPruning {

	private final static int allowedDepth = 2; // Examining the tree with a depth of 3
	private final static int pInfinity = 99999;
	private final static int nInfinity = -99999;
	private WordListReader reader;
	private List<String> selectedWord;
	private int currentLettersLength;
	
	/**
	 * Perform Alpha-beta pruning to determine the optimal selection of a word for the Ghost game.
	 * @param currentLetters Current set of letters in the ghost game
	 * @param reader WordListReader to read the list of words
	 * @return Returns the word that proves to be optimal to win according to certain heuristics.
	 */
	
	public String alphaBetaSearch(String currentLetters, WordListReader reader){
		this.reader = reader;
		selectedWord = new ArrayList<String>();
		currentLettersLength = currentLetters.length();
		int value = maxValue(nInfinity, pInfinity, currentLetters, allowedDepth, currentLetters.length());
		
		// Randomly selecting from the words that can prove a possible win.
		return selectedWord.get(new Random().nextInt(selectedWord.size()));
	}
	
	public int maxValue(int alpha, int beta, String letters, int depth, int length){
		if (terminalTest(letters.substring(0,length)) || depth==0)
			return heuristic(letters);
				
		int value = nInfinity;
		int prevValue = value;
		List<String> successors = reader.findSuccessor(letters.substring(0,length));
		for (String s:successors){
			int v = minValue(alpha, beta, s, depth-1, length+1);
			
			if (v>value)
				value = v;
				
			
			if (value>=beta)
				return value;
			
			if (alpha < value)
				alpha = value;
			
			if (depth==allowedDepth && v==value){
				if (prevValue<value){
					selectedWord.clear();
					prevValue=value;
				}
				selectedWord.add(s);
			}	
		}
		return value;
	}
	
	public int minValue(int alpha, int beta, String letters, int depth, int length){
		if (terminalTest(letters.substring(0,length)) || depth==0)
			return heuristic(letters);
		
		int value = pInfinity;
		
		List<String> successors = reader.findSuccessor(letters.substring(0,length));
		
		for (String s:successors){
			int v = maxValue(alpha, beta, s, depth-1, length+1);
			
			if (v<value)
				value = v;
			
			if (value<=alpha)
				return value;
			
			if (beta > value)
				beta = value;
		}
		
		return value;
	}
	
	public boolean terminalTest(String letters){
		return reader.isWord(letters);
	}
	
	public int heuristic(String word){
		int value = 0;
		
		// Checking whether the computer selected character would form a word.
		if (reader.isWord(word.substring(0,currentLettersLength+1)))
			return -20;
		
		/* Since the user plays first, the computer will win when the number of letters in the word
		 * are odd for the user to finish last. 
		*/
		if (word.length()%2 != 0)  
			value+=20;
		
		else 
			value-=10;
		
		// The chances of extending the game and choosing more words increases when words are lengthy.
		if (word.length() > 4)
			value+=(word.length()-4);
		
		return value;
	}
	
}


