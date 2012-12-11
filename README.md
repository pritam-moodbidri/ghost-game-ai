The folder 'Jar File' contains the executable jar file, and the word list file. It can be executed on the command line, and the input can be given 
on the command line.

The folder 'Ghost' contains the source code. (Its an eclipse project)

I used Eclipse IDE and JDK 1.6.

This was the first version of my implementation and testing. I am sure this could be further improved, and implemented with some other algorithm 
to better suit the gameplay and make better use of the pattern of the input set of letters. 


============================================================

- There are three java files: AlphaBetaPruning.java, WordListReader.java and Ghost.java. The Ghost class in Ghost.java contains the main function
for the game to run.

- The Word List file should be named 'WORD.LST.lst', which is same as provided. This should be in the project folder while running in dev, and in the
same folder as the jar, when running it from the executable jar.

- Since, UI wasn't the focus I am using command line for user input and displaying output.


============================================================

- I am using Alpha-beta pruning to select the letters based on the words provided in the words list file. I am examining a tree upto depth of 3.

- I am using the following letter selection consideration for the heuristics:
	1. The letter selected should not add upto a word. Which means if a player selects a letter for some word, the current set of letters should
	not form a word.
	2. Since, the user will be playing first. The computer can only win when the number of letters in the word are odd, and it will not finish the 
	word last.
	3. If the number of letters for a word is high, there are more chances of having choices for letter selection, and extending the gameplay.
	

- The #1 consideration above can be implemented in two different way. Either it could be a heuristic value or it can be considered to prune the trees
while selecting the next possible child nodes of the tree, eliminating the growth of the tree. 

I tried out both the methods, and found out that having the heuristic results in checking the words at at the leaf nodes. But it reduces the number of
nodes to be pruned.

The second method where to check whether the possible words that starts with the current set of letters can form the word was much more expensive than
the first method. The reason is that if the number of words having the common first letters are less, it will result in all the words to be checked.
Checking first few letters, of all the possible resulting words is expensive. 

Therefore I selected the first approach, which results in the first computer move to take a couple of seconds.



