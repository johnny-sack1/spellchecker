package com.codecool.spellchecker;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Checker
{
    public Checker() {}

    public void check(String paramString1, String paramString2, StringHasher paramStringHasher, PrintStream paramPrintStream) throws java.io.IOException
    {
        WordList localWordList = new WordList(paramString2, paramStringHasher);

        BufferedReader localBufferedReader = new BufferedReader(new java.io.FileReader(paramString1));
        String str1 = localBufferedReader.readLine();
        WordLineReader localWordLineReader = new WordLineReader(str1);

        WordChecker localWordChecker = new WordChecker(localWordList);

        while (str1 != null) {
            Iterator localIterator;
            while (localWordLineReader.hasNextWord())
            {
                String str2 = localWordLineReader.nextWord().toLowerCase();

                if (!localWordChecker.wordExists(str2))
                {
                    ArrayList localArrayList = localWordChecker.getSuggestions(str2);

                    paramPrintStream.println();
                    paramPrintStream.println(str1);
                    paramPrintStream.println("     word not found: " + str2);

                    if (localArrayList.size() > 0)
                    {
                        java.util.Collections.sort(localArrayList);

                        paramPrintStream.println("  perhaps you meant: ");

                        for (localIterator = localArrayList.iterator(); localIterator.hasNext();)
                        {
                            String str3 = (String)localIterator.next();
                            paramPrintStream.println("          " + str3 + " ");
                        }
                    }
                }
            }

            str1 = localBufferedReader.readLine();
            localWordLineReader = new WordLineReader(str1);
        }

        localBufferedReader.close();
    }
}
