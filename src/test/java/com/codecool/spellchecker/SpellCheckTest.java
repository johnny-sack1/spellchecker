package com.codecool.spellchecker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class SpellCheckTest {
    private Random generator = new Random();
    private WordList wordList;
    private WordChecker checker;

    @BeforeEach
    void setup() {
        try {
            String wordListPath = new File("wordlist.txt").getAbsolutePath();
            Object localObject = new BetterStringHasher();
            wordList = new WordList(wordListPath, (StringHasher) localObject);
            checker = new WordChecker(wordList);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testSwappingPairOfLetters() {
        String word = this.wordList.getRandomWord();
        int firstLetterIndex = generator.nextInt(word.length() - 1);
        int secondLetterIndex = firstLetterIndex + 1;
        char[] scrambledWordArr = word.toCharArray();
        scrambledWordArr[firstLetterIndex] = word.charAt(secondLetterIndex);
        scrambledWordArr[secondLetterIndex] = word.charAt(firstLetterIndex);
        String scrambledWord = new String(scrambledWordArr);

        assertTrue(checker.getSuggestions(scrambledWord).contains(word));
    }

    @Test
    void testLetterInsertion() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String word = this.wordList.getRandomWord();
        int insertionIndex = generator.nextInt(word.length() + 1);
        char letterToInsert = alphabet.charAt(generator.nextInt(alphabet.length()));
        String scrambledWord = word.substring(0, insertionIndex) + letterToInsert + word.substring(insertionIndex, word.length());

        assertTrue(checker.getSuggestions(scrambledWord).contains(word));
    }

    @Test
    void testLetterRemoval() {
        String word = this.wordList.getRandomWord();
        int removalIndex = generator.nextInt(word.length());
        StringBuilder sb = new StringBuilder(word);
        sb.deleteCharAt(removalIndex);
        String scrambledWord = sb.toString();

        assertTrue(checker.getSuggestions(scrambledWord).contains(word));
    }

    @Test
    void testLetterReplacement() {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String word = this.wordList.getRandomWord();
        int replacementIndex = generator.nextInt(word.length());
        char letterToReplaceWith = alphabet.charAt(generator.nextInt(alphabet.length()));
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(replacementIndex, letterToReplaceWith);
        String scrambledWord = sb.toString();

        assertTrue(checker.getSuggestions(scrambledWord).contains(word));
    }

    @Test
    void testWordSplitting() {
        String firstWord = "";
        String secondWord = "";
        while (firstWord.equals(secondWord)) {
            firstWord = this.wordList.getRandomWord();
            secondWord = this.wordList.getRandomWord();
        }
        String joinedWord = firstWord + secondWord;
        String expectedSuggestion = firstWord + " " + secondWord;

        assertTrue(checker.getSuggestions(joinedWord).contains(expectedSuggestion));
    }
}
