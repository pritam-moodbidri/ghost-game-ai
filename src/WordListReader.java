import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class WordListReader {
	
	private FileInputStream fstream;
	private DataInputStream in;
	private BufferedReader br;
	private List<String> possibleWords = new ArrayList<String>();
	
	
	/*
	 *  Read all the words with the first selected character by the user into a list, 
	 *  to avoid reading from the file again, and improve speed.
	 */
	public WordListReader(String firstCharacter){
		try{
			
			fstream = new FileInputStream("WORD.LST.lst");
			in = new DataInputStream(fstream);
			br = new BufferedReader(new InputStreamReader(in));
			
			String strLine;
			boolean reachedMatch = false;
			while ((strLine = br.readLine()) != null)   {
				if (strLine.startsWith(firstCharacter)){
					possibleWords.add(strLine);
					reachedMatch = true;
				}else if (reachedMatch==true){
					break;
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 *  Returns all the possible word combinations with the starting letters provided.
	 *  @param letters The starting set of letters for a word to be searched in the the list.
	 *  @return List<String> A list of all the words that starts with the letters provided as input.
	 */
	
	public List<String> findSuccessor(String letters){
		List<String> successors = new ArrayList<String>();	
		boolean reachedMatch = false;
		for (String word: possibleWords){
			if (word.startsWith(letters)){
				successors.add(word);
				reachedMatch = true;
			}else if (reachedMatch==true){
				break;
			}
			
		}

		return successors;
	}
	
	/**
	 *  Checks whether a given set of letters forms a word.
	 *  @param letters The set of letters to be checked.
	 *  @return Returns true if the letters form a word. Returns false if the letters don't form a word.
	 */
	public boolean isWord(String letters){	
		for (String word : possibleWords){
			if (word.equals(letters))
				return true;
		}
		return false;
	}
	
	/**
	 *  Checks whether the current set of letters in the game can be extended to form a valid word.
	 *  @param letters The set of letters to be checked.
	 *  @return Returns true if the letters can be extended to a word. Returns false if the letters can't be extended to a word.
	 */
	public boolean areLettersValid(String letters){
		for (String word : possibleWords){
			if (word.startsWith(letters))
				return true;
		}
		return false;
	}
}
