import java.io.IOException;


public class Ghost {

	
	public static char readUserInput(){
		int input = 0;
		try {
			input = System.in.read();
			System.in.skip(1);
			return (char)input;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

	public static void main(String args[]){
		
		AlphaBetaPruning abPruner = new AlphaBetaPruning();
		StringBuilder builder = new StringBuilder();
		String currentLetters;
		int length=0;
		System.out.println("Enter the first letter: ");
		builder.append(readUserInput());
		currentLetters = builder.toString().toLowerCase();
		
		WordListReader reader=new WordListReader(currentLetters.toString().toLowerCase());
		
		do{
			String selectedWord = abPruner.alphaBetaSearch(currentLetters, reader);
			length = currentLetters.length();
			builder.delete(0, length);
			builder.append(selectedWord.substring(0, length+1));
			
			currentLetters = builder.toString().toLowerCase();
			
			System.out.println("Computer selected the letter '" + currentLetters.charAt(currentLetters.length()-1) + "'");
			System.out.println("The current set of letters are: " + currentLetters);
			
			if (reader.isWord(currentLetters)){
				System.out.println("Computer formed a word. You Win!!");
				break;
			}
			
			System.out.println("Please select next letter: ");
			builder.append(readUserInput());
			
			currentLetters = builder.toString().toLowerCase();
			
			if (reader.isWord(currentLetters)){
				System.out.println("You formed a word. You loose!!");
				break;
			}
			
			if (!reader.areLettersValid(currentLetters)){
				System.out.println("You selected invalid letter. The letters cannot be exteneded to a word. You loose!!");
				break;
			}
			
		}while(true);
		
	}
} 

