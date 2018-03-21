# Translator
A translator for a made-up language: Unga Bunga Tounga

The language is basically a cipher, but with some exceptions. These exceptions are idioms/non-literal
translations that add culture, and serve to legitimize the cipher as a language.

## Culture
A language cannot be separated from the culture of those who speak it. But, not many people speak this
language, so we have to inject the culture artificially by creating our own idioms and non-literal translations.
The culture of this language is primarily based around stones and Counter-Strike.

## Change Log
- Added a GUI for the translator
- Removed incFlag, changed it to a global flag to be better integrated into the GUI
- Changed getPossNumberOfStrings, or whatever it was called before, to getPossEngTrans.
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
