package org.hopto.gameserver.basicencryptioncracker.engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PasswordRotationEngine
{
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";
    private int rotation;
    private String key;
    private String keyAlphabet;

    public PasswordRotationEngine(int rotation, String key)
    {
        this.rotation = rotation;
        this.key = key;
        String alpha = alphabet;

        char[] keyChars = key.toCharArray();

        for (char c: keyChars)
        {
            alpha = alpha.replace(c, Character.MIN_VALUE);
        }

        //Nach dem ersetzen ist alpha "verunreinigt", also müssen wir ihn reinigen -,-
        char[] alphaChars = alpha.toCharArray();

        StringBuilder sb = new StringBuilder();
        for(char c : alphaChars)
        {
            if(c != Character.MIN_VALUE)
            {
                sb.append(c);
            }
        }
        alpha = sb.toString();

        //Der SplitChar wird beim Splitten entfernt, also müssen wir ihn manuell wieder einfügen...
        char splitChar = alpha.charAt( alpha.length() - rotation);
        String[] alphaParts = alpha.split(Character.toString(splitChar));
        this.keyAlphabet = Character.toString(splitChar) + alphaParts[1] + key + alphaParts[0];
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

    public int getRotation() {
        return rotation;
    }

    public String getKey() {
        return key;
    }

    public String getKeyAlphabet() {
        return keyAlphabet;
    }
}
