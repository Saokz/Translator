## 4/20/2018
- Created base for V3 of the translator
- Made major design changes to the language. The frequency of vowels has been reduced, and the cipher that determines the
  words of the language has been simplified. As a result, the translator has been really super simplified. The plan is
  to give this language a complete grammatical structure unique from English to really legitimize it as a language.
- Took out the "culture" aspect. This was fine when the language was still mostly a joke, but I'm taking this project a little 
  more seriously than I was before, so now it just seems dumb. 
## 3/25/2018
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

## 3/24/2018
- Added a GUI for the translator
- Removed incFlag, changed it to a global flag to be better integrated into the GUI
- Changed getPossNumberOfStrings, or whatever it was called before, to getPossEngTrans
  to add clarity.
- Fixed a bug regarding the letter 'A' when translating to English. 'A's in this language
  always translate into 'Z's when going in reverse. So as far as the translator is concerned,
  when translating to English, 'A' isn't a vowel. This change is reflected in the changed method
  getPossEngTrans.
