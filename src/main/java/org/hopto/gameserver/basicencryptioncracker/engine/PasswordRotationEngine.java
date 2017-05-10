package org.hopto.gameserver.basicencryptioncracker.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PasswordRotationEngine
{
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private String key;
    private String keyAlphabet;

    public PasswordRotationEngine(String key)
    {
        this.key = key;
        String alpha = alphabet;

        //Doppelte Buchstaben aus key filtern
        List<Character> keyChars = new ArrayList<Character>();
        for(char c : key.toCharArray())
        {
            if(!keyChars.contains(c))
            {
                keyChars.add(c);
            }
        }

        //Buchstaben des Keys aus dem Alphabet filtern
        for(char c : keyChars)
        {
            alpha = alpha.replace(c, Character.MIN_VALUE);
        }
        StringBuilder sb = new StringBuilder();
        for(char c : alpha.toCharArray())
        {
            if(c != Character.MIN_VALUE)
            {
                sb.append(c);
            }
        }
        alpha = sb.toString();

        int offset = 0;
        char lastKeyChar = keyChars.get(keyChars.toArray().length - 1);
        char nextChar = alphabet.charAt( alphabet.indexOf(lastKeyChar)+1 );

        while(keyChars.contains(nextChar))
        {
            offset++;
            nextChar = alphabet.charAt( alphabet.indexOf(lastKeyChar)+1+offset );
        }

        //Alles zusammenbauen
        String[] alphaParts = alpha.split(Character.toString(nextChar));
        StringBuilder alphaBuilder = new StringBuilder();

        //KeyChars hinzufügen
        for(char c : keyChars)
        {
            alphaBuilder.append(c);
        }

        alphaBuilder.append(nextChar + alphaParts[1] + alphaParts[0]);
        this.keyAlphabet = alphaBuilder.toString();
    }


    public String encrypt(String input)
    {
        input = input.toLowerCase();
        StringBuilder output = new StringBuilder();
        char[] chars = input.toCharArray();

        for (char c: chars)
        {
            int pos = alphabet.indexOf(c);

            if(pos != -1)
            {
                output.append(this.keyAlphabet.charAt(pos));
            }
            else
            {
                output.append(c);
            }
        }

        return output.toString();
    }


    public String decrypt(String input)
    {
        input = input.toLowerCase();
        StringBuilder output = new StringBuilder();
        char[] chars = input.toCharArray();

        for (char c: chars)
        {
            int pos = keyAlphabet.indexOf(c);

            if(pos != -1)
            {
                output.append(this.alphabet.charAt(pos));
            }
            else
            {
                output.append(c);
            }
        }

        return output.toString();
    }



    public String getAlphabet() {
        return alphabet;
    }

    public String getKey() {
        return key;
    }

    public String getKeyAlphabet() {
        return keyAlphabet;
    }
}
