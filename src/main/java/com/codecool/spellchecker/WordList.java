package com.codecool.spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WordList
{
    private final HashTable hashTable;

    public WordList(String paramString, StringHasher paramStringHasher)
            throws IOException
    {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));

        int i = Integer.parseInt(localBufferedReader.readLine());

        hashTable = new HashTable((int)(i * 1.2D), paramStringHasher);

        for (int j = 0; j < i; j++)
        {
            hashTable.add(localBufferedReader.readLine().trim().toUpperCase());
        }

        localBufferedReader.close();
    }


    public boolean lookup(String paramString)
    {
        return hashTable.lookup(paramString.toUpperCase());
    }
}
