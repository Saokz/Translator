/**
 * @author William FitzGerald
 * Purpose: This is a translator for a fake language I made up
 * 			with a couple friends. If you want to learn how to speak
 * 			a made-up language so you can communicate secretly for whatever
 * 			reason, this will help you do that.
 */
package translator;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;


public class Translator {
	
	private HashMap<String, String> culture = new HashMap<>();
	private ArrayList<String> keys;
	private boolean dictExists;

	private final int ALPHABET_START_UPPER_CASE = 65;
	private final int ALPHABET_END_UPPER_CASE = 90;
	private final int ALPHABET_START_LOWER_CASE = 97;
	private final int ALPHABET_END_LOWER_CASE = 122;
	private final int F_UPPER_CASE = 70;
	private final int F_LOWER_CASE = 102;
	private final int J_UPPER_CASE = 74;
	private final int J_LOWER_CASE = 106;
	private final int V_UPPER_CASE = 86;
	private final int N_LOWER_CASE = 110;
	private final int V_LOWER_CASE = 118;

	public Translator()
	{
		File f = new File("exceptions.txt");
		File dict = new File("dictionary.txt");
		try
		{
			if (!f.exists())
			{
				f.createNewFile();
				System.out.println(f.getAbsolutePath());
			}
			
			if (dict.exists())
			{
				dictExists = true;
			}
			
			loadCulture();
		
		}catch (IOException E)
		{
			E.printStackTrace();
		}
	}

    /**
     * Method that checks if a word exists in the dictionary.
     *
     * @param word The word that is to be checked.
     * @return True if it exists in the dictionary, false otherwise.
     */
	public boolean wordExistsInDict(String word)
	{
		boolean wordExists = false;
		if (!dictExists)
		{
			System.out.println("Unable to perform dictionary check.");
			return false;
		}
		try
		{
			BufferedReader fr = new BufferedReader(new FileReader("dictionary.txt"));
			String currentLine = fr.readLine();
			while (currentLine != null)
			{
				if (currentLine.equalsIgnoreCase(word))
				{
					fr.close();
					wordExists = true;
					break;
				}else
				{
					currentLine = fr.readLine();
				}
			}
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		return wordExists;
	}

	private void loadCulture()
	{
		try
		{
			BufferedReader fr = new BufferedReader(new FileReader("exceptions.txt"));
			String currentLine = fr.readLine();
			while (currentLine != null)
			{
				String[] translations = currentLine.split(" : ");
				culture.put(translations[0], translations[1]);
				currentLine = fr.readLine();
			}
			
			fr.close();
		}catch (IOException E)
		{
			E.printStackTrace();
		}

		/*
		These need to be sorted by largest to smallest, because we need to check
		for the largest exceptions/non-literal translations first, in case we
		have any non-literal translations that also contain other smaller
		non-literal translations.
		 */
		keys = new ArrayList<String>(culture.keySet());
		Collections.sort(keys, new CustomComparator());
	}

    /**
     * Method that checks if a string has a non-literal/idiomatic
     * translation in UngaBungaTounga.
     *
     * e.g. peace be with you - > may your stones be big
     *
     * @param par1Str String to be checked
     * @return Non-literal translation of the string
     */
	public String checkForCulture(String par1Str)
	{
		StringBuilder cultureBuilder = new StringBuilder(par1Str);
		
		for (String s: keys)
		{
			if (cultureBuilder.toString().equalsIgnoreCase(s))
			{
				par1Str = culture.get(s);
			}
			
			if (cultureBuilder.indexOf(s) != -1)
			{
				int startIndex = cultureBuilder.indexOf(s);
				int endIndex = startIndex + s.length();
				String replace = cultureBuilder.substring(startIndex, endIndex);
				cultureBuilder.replace(startIndex, endIndex, culture.get(replace));
			}
		}
		
		return cultureBuilder.length() > 0 ? cultureBuilder.toString() : par1Str.toString();
	}

    /**
     * Translate an english string into UngaBungaTounga, following the
     * rules of the cipher.
     *
     * @param str String to be translated
     * @return UngaBungian translation
     */
	public String translate(String str)
	{
		str = checkForCulture(str); //check for any non-literal translations
		StringBuilder newText = new StringBuilder();
		char[] letters = str.toCharArray();
		
		for (char c: letters)
		{
		    //if the character is not in the alphabet
			if (c < ALPHABET_START_UPPER_CASE || (c < ALPHABET_START_LOWER_CASE &&
                c > ALPHABET_END_UPPER_CASE) || c > ALPHABET_END_LOWER_CASE) {
				newText.append(c); //It's the same thing in English as it is in our language
			}else
			{
			    /*Start shifting the vowels to the next vowel over
			     *'U's go to 'Y's
			     */
				if (isVowel(c))
				{
					if (c == 'u')
					{
						newText.append('y');
					}else if (c == 'U')
					{
						newText.append('Y');

					//Vowels in the first half of the alphabet
					}else if (c < F_UPPER_CASE || (c < F_LOWER_CASE && c >= ALPHABET_START_LOWER_CASE) )
					{
						newText.append( (char) (c + 4) ); //we can shift the position by 4 to get the next vowel
					}else //Vowels in the latter half of the alphabet
					{
						newText.append( (char) (c + 6) ); //Shift by 6 to get the next vowel
					}
				}else
				{
					if (c == 'z')
					{
						newText.append('a');
					}else if (c == 'Z')
					{
						newText.append('A');
					}else
					{
						newText.append( (char) (c + 1) ); //Translate to the next letter
					}
				}
			}
			
		}
		
		return newText.toString();
	}

    /**
     * Translate an UngaBungaTounga string into its possible English form.
     *
     * @param str String to be translated
     * @return Possible translation
     */
	public String reverseTranslate(String str)
	{
		StringBuilder wordBuilder = new StringBuilder(str);
		
		for (int i = 0; i < str.length(); i++)
		{
		    //If character is not in the alphabet
			if (str.charAt(i) < ALPHABET_START_UPPER_CASE || (str.charAt(i) < ALPHABET_START_LOWER_CASE &&
                str.charAt(i) > ALPHABET_END_UPPER_CASE) || str.charAt(i) > ALPHABET_END_LOWER_CASE) {
				wordBuilder.setCharAt(i, str.charAt(i)); //Same in English as it is in UngaBungian
			}else if (str.charAt(i) == 'a' || str.charAt(i) == 'A')
			{
				wordBuilder.setCharAt(i, (char) (str.charAt(i) + 25)); //'A's will go back to 'Z's
			}else if (isVowel(str.charAt(i)) || str.charAt(i) == 'y' || str.charAt(i) == 'Y')
			{
				int rand = (int) (Math.random() * 2); //randomly decide how we're going to translate this vowel
				if (rand == 0) 
				{
					wordBuilder.setCharAt(i, (char) (str.charAt(i) - 1)); //translate to the previous letter
				}else if (rand == 1)
				{
				    //Vowels on the first half of the alphabet or a 'Y'
					if ( str.charAt(i) < J_UPPER_CASE || (str.charAt(i) >= ALPHABET_START_LOWER_CASE &&
                         str.charAt(i) < J_LOWER_CASE) || str.charAt(i) == 'y' || str.charAt(i) == 'Y') {

					    //We can shift the letter 4 letters back to get the previous vowel
						wordBuilder.setCharAt(i, (char) (str.charAt(i) - 4) );
					}else if (str.charAt(i) < V_UPPER_CASE || (str.charAt(i) > N_LOWER_CASE & str.charAt(i) < V_LOWER_CASE) )
					{

					    //We can shift the letter 6 letters back to get the previous vowel
						wordBuilder.setCharAt(i, (char) (str.charAt(i) - 6) );
					}
				}
			}else
			{
			    //Translate to the previous letter
				wordBuilder.setCharAt(i, (char) (str.charAt(i) - 1));
			}
		}
		
		return wordBuilder.toString();
	}

    /**
     * Translate UngaBungian text into English
     *
     * @param str Text to be translated
     * @return English form of text
     */
	public String translateToEnglish(String str)
	{
		String englishText = "";
		String[] words = str.split(" "); //Go one word at a time
		int count = 0; //How we break the loop
		int countMax = words.length;
		ArrayList<String> incCombs = new ArrayList<>(); //Also how we break the loop
		boolean incFlag = false; //Flag for if we can't find the word in the dictionary
	
		while(count < countMax)
		{
			for (String s : words)
            {
				int possStrCombs = getNumofPossibleStrings(s); //How many different translations are possible?
				String trans = reverseTranslate(s); //Start us off

                //While our guess translation is incorrect, and we haven't maxed out the possible combinations
				while (!wordExistsInDict(trans) && (incCombs.size() < possStrCombs) )
				{
					if (!incCombs.contains(trans)) //If we haven't already tried this guess
					{
						incCombs.add(trans); //add this guess to our list of incorrect guesses
					}
					trans = reverseTranslate(s); //Try another guess
				}

				/*If the loop broke because translation does not have an English counterpart
				  that exists in the dictionary
				 */
				if (incCombs.size() >= possStrCombs)
				{
					incFlag = true; //Let the user know that we couldn't find it
				}

				/*We're done with this word, so we clear the combinations
				  for the next word, add the current word to the englishText,
				  and increase the count to keep track of where we're at
				 */
				incCombs.clear();
				englishText += " " + trans; //adding the word, we'll remove the trailing spaces later
				count++;
				
			}
			
		}

		//If we couldn't find one of the words in the dictionary
		if (incFlag)
		{
		    //Return our best guess
			return "[POSSIBLE TRANSLATION]: " + englishText.trim();
		}

		//Return the translation
		return englishText.trim();
		
	}

    /**
     * This returns the number of possible translations for any given word
     * in the language.
     *
     * The number of possible translations is determined solely by how many vowels
     * there are in the string.
     *
     * e.g. tuuoit
     *      Has 4 vowels.
     *      2^4 = 16 possible translations
     * One of which, is the correct English translation: Stones
     *
     * @param str String to be translated
     * @return The number of possible translations
     */
	public int getNumofPossibleStrings(String str)
	{
		int numOfVowels = 0;
		
		for (char c : str.toCharArray())
		{
			if (isVowel(c) || c == 'y' || c == 'Y')
			{
				numOfVowels++;
			}
		}
		
		return (int) Math.pow(2, numOfVowels);
	}

    /**
     * Method to determine if a letter is a vowel
     *
     * @param c letter
     * @return true if it is a vowel, false otherwise
     */
	public boolean isVowel(char c)
	{
		boolean t;
		
		switch (c)
		{
			case 'a': t = true;
				break;
			case 'e': t = true;
				break;
			case 'i': t = true;
				break;
			case 'o': t = true;
				break;
			case 'u': t = true;
				break;
			case 'A': t = true;
				break;
			case 'E': t = true;
				break;
			case 'I': t = true;
				break;
			case 'O': t = true;
				break;
			case 'U': t = true;
				break;
			default: t = false;
		}
		
		return t;
	}
	
}
