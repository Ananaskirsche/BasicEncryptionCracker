package org.hopto.gameserver.basicencryptioncracker.engine;

import java.util.HashMap;

public class VigenereFrequencyAnalysisEngine
{
    private static HashMap<Character, Integer>[] rootList;

    private static void updateCharacterFrequency(char c, int hashMapIndex)
    {
        if (rootList == null) {
            return;
        }
        int i = (Integer) rootList[hashMapIndex].get(c);
        rootList[hashMapIndex].put(c, i + 1);
    }

    public static HashMap<Character, Integer>[] getFrequency(String text, int keyLenght)
    {
        rootList = new HashMap[keyLenght];
        for (int i = 0; i < keyLenght; i++)
        {
            HashMap<Character, Integer> map = new HashMap(26, 1.0F);
            map.put('a', 0);
            map.put('b', 0);
            map.put('c', 0);
            map.put('d', 0);
            map.put('e', 0);
            map.put('f', 0);
            map.put('g', 0);
            map.put('h', 0);
            map.put('i', 0);
            map.put('j', 0);
            map.put('k', 0);
            map.put('l', 0);
            map.put('m', 0);
            map.put('n', 0);
            map.put('o', 0);
            map.put('p', 0);
            map.put('q', 0);
            map.put('r', 0);
            map.put('s', 0);
            map.put('t', 0);
            map.put('u', 0);
            map.put('v', 0);
            map.put('w', 0);
            map.put('x', 0);
            map.put('y', 0);
            map.put('z', 0);
            rootList[i] = map;
        }
        text = text.toLowerCase();
        char[] inChars = text.toCharArray();
        for (int i = 0; i < inChars.length; i++)
        {
            char c = inChars[i];
            int mod = (i + 1) % keyLenght;
            if (mod > 0) {
                updateCharacterFrequency(c, mod - 1);
            }
            if (mod == 0) {
                updateCharacterFrequency(c, rootList.length - 1);
            }
        }
        return rootList;
    }
}
