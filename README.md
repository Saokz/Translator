# Translator
A translator for a made-up language: Unga Bunga Tounga

The language is basically a cipher, but with some exceptions. These exceptions are idioms/non-literal
translations that add culture, and serve to legitimize the cipher as a language.

## Culture
A language cannot be separated from the culture of those who speak it. But, not many people speak this
language, so we have to inject the culture artificially by creating our own idioms and non-literal translations.
The culture of this language is primarily based around stones and Counter-Strike.

## Dictionary
Sometimes certain words don't exist in the dictionary file. Sometimes super weird words are in the dictionary file, and they share translations with common words that you're most likely going to be using more. If you're not getting the translation
you want, check to make sure that the word you're using is in the dictionary file, and if not, you can add it. You can also
remove words from the dictionary file; words that you think you'll never use. Doesn't matter. You downloaded it, it's yours.

## Change Log 3/25/2018
- Fixed the punctuation issue. The translator can now interpret sentences with full punctuation with no problem...for the most
  part. Something like "words words words (parentheses)." might not work, but I've decided that this is fine for now.
  I'll deal with that issue later. For about, 99% of the time, it will work just fine.
- Updated the dictionary file. Deleted the word "eave" because it has the same translation as "have". Added the word "to".
  I'll also probably delete the dictionary file from the repository, because that's not really needed. You can just go to the
  releases to find it.
- Updated the release to include the fixed version of the translator, the new dictionary file, and a new exceptions.txt
  that contain some examples of culture.
- Updated the documentation to be a little less intrusive. I just felt like the previous documentation made the code
  a little harder to read. 

## Change Log 3/24/2018
- Added a GUI for the translator
- Removed incFlag, changed it to a global flag to be better integrated into the GUI
- Changed getPossNumberOfStrings, or whatever it was called before, to getPossEngTrans
  to add clarity.
- Fixed a bug regarding the letter 'A' when translating to English. 'A's in this language
  always translate into 'Z's when going in reverse. So as far as the translator is concerned,
  when translating to English, 'A' isn't a vowel. This change is reflected in the changed method
  getPossEngTrans.
  
## Bugs
- Some words have duplicate translations. For example, "and" and "did" both translate to "eoe". "have" and "eave"
  both translate to "iewi". This is a flaw by design, and there's really not much I can do about it for now. Just know 
  that if you translate full sentences, sometimes they won't make sense when translated into English.
  
  Example in a sentence: "I have lost all motivation" -> "O iewi mutu emm nuuoweuouo"
                         "O iewi mutu emm nuuoweuouo" -> "I eave lost all motivation"
  I'll figure something out later.
  
- The GUI is not resizable. You just have to deal with it being 640x400. I'll also figure something out for that later.
