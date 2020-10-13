#### Markov Chain Text Generator

###### Implemented from scratch with a Chaining Hash Map

---

To learn more about Markov Chains, follow this [link](https://setosa.io/ev/markov-chains/) 
To learn more about Hash Maps, follow this [link](https://www.interviewcake.com/concept/java/hash-map)

The **Markov Chain Text Generator** works as follows: 

1. Given a text file, for each word, count how many times other words appear after that word 
2. Store the frequencies using a Chaining Hash Map 
3. Pick a random word from the text (to start generating with)
4. Use the probabilities of what words come after that word to randomly choose the next word
5. Repeat for next word 
6. Repeat until a text of desired length has been generated (numWords)



> ###### How to use: 
>
> 1. Download source files into a directory 
> 2. Run: _Java TextGenerator_
> 3. Per instructions in the terminal, paste/type the absolute path to the text file for training 
> 4. Per instructions in the terminal, specify the number of words (int) to be generated 
> 5. Output is printed in the terminal 