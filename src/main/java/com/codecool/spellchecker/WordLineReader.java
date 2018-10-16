package com.codecool.spellchecker;

public class WordLineReader
{
    private final String line;
    private int currentChar;
    private String nextWord;

    public WordLineReader(String paramString)
    {
        line = paramString;

        if (paramString == null)
        {
            nextWord = null;
        }
        else
        {
            currentChar = 0;
            readNextWord();
        }
    }


    public boolean hasNextWord()
    {
        return nextWord != null;
    }


    public String nextWord()
    {
        String str = nextWord;

        if (nextWord != null)
        {
            readNextWord();
        }

        return str;
    }


    private void readNextWord()
    {
        nextWord = "";

        while ((currentChar < line.length()) && (!isWordStartingOrEndingLetter(line.charAt(currentChar))))
        {

            currentChar += 1;
        }

        while ((currentChar < line.length()) && (isWordLetter(line.charAt(currentChar))))
        {

            nextWord += line.charAt(currentChar);
            currentChar += 1;
        }

        if (nextWord.length() == 0)
        {
            nextWord = null;
            return;
        }

        if (!isWordStartingOrEndingLetter(nextWord.charAt(nextWord.length() - 1)))
        {
            nextWord = nextWord.substring(0, nextWord.length() - 1);
        }
    }


    private boolean isWordStartingOrEndingLetter(char paramChar)
    {
        return ((paramChar >= 'A') && (paramChar <= 'Z')) || ((paramChar >= 'a') && (paramChar <= 'z')) || ((paramChar >= '0') && (paramChar <= '9'));
    }



    private boolean isWordLetter(char paramChar)
    {
        return (isWordStartingOrEndingLetter(paramChar)) || (paramChar == '-') || (paramChar == '\'');
    }
}
