package translator;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller{

    public ImageView switchButton;
    public TextArea textToBeTranslated;
    public TextArea translation;
    public Label leftLabel, rightLabel;

    Translator trans = new Translator();
    private boolean fromEnglish = true;

    /**
     * CURRENT PROBLEM:
     *
     * 'How are you?' doesn't translate properly back into english.
     *  I've checked. The translator has no problem finding all those words
     *  in the dictionary. It's only when they're together that it says
     *  there's no correct English translation.
     */
    public void switchMode()
    {
        /*If we were translating to English, but want
          switch modes*/
        if (fromEnglish)
        {
            //Let the user know we are now translating from Unga Bunga Tounga to English
            leftLabel.setText("Unga Bunga Tounga");
            rightLabel.setText("English");
        }else //We were translating to Unga Bunga Tounga, but want to switch modes
        {
            //Let the user know we are now translating from English to Unga Bunga Tounga
            leftLabel.setText("English");
            rightLabel.setText("Unga Bunga Tounga");
        }

        fromEnglish = !fromEnglish;

        //Do that cool thing google translate does
        String temp;
        temp = textToBeTranslated.getText();
        textToBeTranslated.setText(translation.getText());
        translation.setText(temp);
        translateText();
    }

    public void translateText()
    {
        String newText;

        newText = trans.translate(textToBeTranslated.getText(), fromEnglish);
        translation.setText(newText);
    }

}
