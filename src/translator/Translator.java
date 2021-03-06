/**
 * @author William FitzGerald
 * Purpose: Version 3 of the Translator for a made-up language
 */
package translator;

import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class Translator {

	private HashMap<Character, Character> engTrans = new HashMap<>();
	private HashMap<Character, Character> revTrans = new HashMap<>();
	
	public Translator()
	{
		loadCipher();
	}
	
	private void loadCipher()
	{
		File f = new File("rules.txt");
		try
		{
			if (!f.exists())
			{
				f.createNewFile();
			}
			
			BufferedReader fr = new BufferedReader(new FileReader(f));
			String currentLine = fr.readLine();
			
			while(currentLine != null)
			{
				/* Establish a mapping for the cipher, translating to and
				 * from English
				 */
				engTrans.put(currentLine.charAt(0), currentLine.charAt(2));
				revTrans.put(currentLine.charAt(2), currentLine.charAt(0));
				currentLine = fr.readLine();
			}
			
			fr.close();
			
		}catch (IOException e)
		{
			e.printStackTrace();
		}
	}

    /**
     * Translates a string to the language and back
     * @param par1Str String to be translating
     * @param English flag for determining which direction we translate in. True means from English to the language
     * @return Translated string
     */
	public String translate(String par1Str, boolean English)
	{
		StringBuilder newText = new StringBuilder();
		char[] letters = par1Str.toCharArray();
		
		for (char c: letters)
		{
			char translatedLetter;
			if (!engTrans.containsKey(c) && !revTrans.containsKey(c))
			{
				translatedLetter = c;
			}else
			{
				if(English)
				{
					translatedLetter = engTrans.get(c);
				}else
				{
					translatedLetter = revTrans.get(c);
				}
			}
			
			newText.append(translatedLetter);
		}
		
		return newText.toString();
	}
	
}
