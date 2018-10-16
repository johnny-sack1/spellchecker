package com.codecool.spellchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */

public class WordChecker
{
	private WordList wordList;
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
	public WordChecker(WordList wordList)
	{
		this.wordList = wordList;
	}
	public WordChecker() {

	}
	

	/**
   * Returns true if the given word is in the WordList passed to the
   * constructor, false otherwise. 
   *
   * @param word Word to check against the internal word list
   * @return boolean indicating if the word was found or not.
   */
	public boolean wordExists(String word)
	{
		return wordList.lookup(word);
	}


	/**
   * Returns an ArrayList of Strings containing the suggestions for the
   * given word.  If there are no suggestions for the given word, an empty
   * ArrayList of Strings (not null!) should be returned.
   *
   * @param word String to check against
   * @return A list of plausible matches
   */
	public ArrayList<String> getSuggestions(String word)
	{
		ArrayList<String> suggestions = new ArrayList<>();
		suggestions.addAll(getSuggestionsSwappingAdjacentPairsOfLetters(word));
		suggestions.addAll(getSuggestionsInsertingLetters(word));
		return suggestions;
	}

	private ArrayList<String> getSuggestionsSwappingAdjacentPairsOfLetters(String word) {
		ArrayList<String> suggestions = new ArrayList<>();
		char[] wordCharArr = word.toCharArray();

		for (int i = 0; i < word.length() - 1; i++) {
			char[] resultingWordCharArr = new char[wordCharArr.length];
			System.arraycopy(wordCharArr, 0, resultingWordCharArr, 0, wordCharArr.length);
			char firstLetterInPair = wordCharArr[i];
			char secondLetterInPair = wordCharArr[i + 1];
			resultingWordCharArr[i] = secondLetterInPair;
			resultingWordCharArr[i + 1] = firstLetterInPair;

			String possibleSuggestion = new String(resultingWordCharArr);
			if (wordList.lookup(possibleSuggestion)) {
				suggestions.add(possibleSuggestion);
			}
		}
		return suggestions;
	}

	private ArrayList<String> getSuggestionsInsertingLetters(String word) {
		ArrayList<String> suggestions = new ArrayList<>();

		for (int i = 0; i < word.length() + 1; i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				String possibleSuggestion = word.substring(0, i) + c + word.substring(i, word.length());
				if (wordList.lookup(possibleSuggestion)) {
					suggestions.add(possibleSuggestion);
				}
			}
		}
		return suggestions;
	}
}
