package com.codecool.spellchecker;

import java.io.PrintStream;

public class SpellCheck
{
    public SpellCheck() {}

    public static void main(String[] paramArrayOfString)
    {
        if (paramArrayOfString.length == 0)
        {
            showUsageMessage();
            return;
        }

        String str1 = paramArrayOfString[(paramArrayOfString.length - 1)];
        String str2 = "wordlist.txt";
        Object localObject = new LousyStringHasher();
        PrintStream localPrintStream = System.out;
        int i = 0;

        int j = 0;

        while (j < paramArrayOfString.length - 1)
        {
            if (paramArrayOfString[j].equals("-degenerate"))
            {
                localObject = new DegenerateStringHasher();
            }
            else if (paramArrayOfString[j].equals("-lousy"))
            {
                localObject = new LousyStringHasher();
            }
            else if (paramArrayOfString[j].equals("-better"))
            {
                localObject = new BetterStringHasher();
            }
            else if (paramArrayOfString[j].equals("-quiet"))
            {
                localPrintStream = new PrintStream(new NullOutputStream());
                i = 1;
            }
            else if (paramArrayOfString[j].equals("-wordlist"))
            {
                j++;

                if (j >= paramArrayOfString.length - 1)
                {
                    showUsageMessage();
                    return;
                }

                str2 = paramArrayOfString[j];
            }

            j++;
        }

        if (paramArrayOfString[(paramArrayOfString.length - 1)].charAt(0) == '-')
        {
            showUsageMessage();
            return;
        }

        try
        {
            long l1 = System.currentTimeMillis();

            new Checker().check(str1, str2, (StringHasher)localObject, localPrintStream);

            long l2 = System.currentTimeMillis();

            if (i != 0)
            {
                System.out.println("Checker ran in " + (l2 - l1) + "ms");
            }
        }
        catch (java.io.IOException localIOException)
        {
            localIOException.printStackTrace();
        }
    }


    private static void showUsageMessage()
    {
        System.out.println("Usage: java SpellCheck [options] inputFilename");
        System.out.println();
        System.out.println("    options");
        System.out.println("    -------");
        System.out.println("    -degenerate");
        System.out.println("        runs the spell checker with the degenerate word hashing algorithm");
        System.out.println();
        System.out.println("    -lousy");
        System.out.println("        runs the spell checker with a lousy word hashing algorithm (default)");
        System.out.println();
        System.out.println("    -better");
        System.out.println("        runs the spell checker with a better word hashing algorithm");
        System.out.println();
        System.out.println("    -quiet");
        System.out.println("        runs the spell checker without any output, reporting the total time");
        System.out.println("        taken to load the dictionary and perform the spell check");
        System.out.println();
        System.out.println("    -wordlist wordlistFilename");
        System.out.println("        runs the spell checker using the wordlist specified, rather than");
        System.out.println("        the default (wordlist.txt)");
        System.out.println();
        System.out.println("    example");
        System.out.println("    -------");
        System.out.println("    java SpellCheck -wordlist biglist.txt -better -quiet big-input.txt");
        System.out.println("        executes the spell checker using the wordlist 'biglist.txt', the");
        System.out.println("        better word hashing algorithm, in quiet mode (i.e. no output),");
        System.out.println("        on the input file 'big-input.txt'");
    }
}
