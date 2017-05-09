package org.hopto.gameserver.basicencryptioncracker.engine;

import java.nio.charset.Charset;

public class AlphabetRotationEngine
{
    public static String encryptString(String input, int rotation)
    {
        input = input.toUpperCase();
        byte[] chars = input.getBytes(Charset.forName("US-ASCII"));

        int i = 0;

        while(i < rotation)
        {
            for(int j = 0; j < chars.length; j++)
            {
                //Prüfen ob Byte Buchstabe ist
                if(chars[j] >= 65 && chars[j] <= 90)
                {
                    //Workarround because reasons
                    byte t = ((byte) (((int) chars[j]) +1));
                    chars[j] = t;

                    if(chars[j] > 90)
                    {
                        chars[j] = 65;
                    }
                }
            }

            i++;
        }

        return new String(chars, Charset.forName("US-ASCII"));
    }


    public static String decryptString(String input, int rotation)
    {
        input = input.toUpperCase();
        byte[] chars = input.getBytes(Charset.forName("US-ASCII"));

        int i = 0;

        while(i < rotation)
        {
            for(int j = 0; j < chars.length; j++)
            {
                //Prüfen ob Byte Buchstabe ist
                if(chars[j] >= 65 && chars[j] <= 90)
                {
                    //Workarround because reasons
                    byte t = ((byte) (((int) chars[j]) -1));
                    chars[j] = t;

                    if(chars[j] < 65)
                    {
                        chars[j] = 90;
                    }
                }
            }

            i++;
        }

        return new String(chars, Charset.forName("US-ASCII"));
    }
}
