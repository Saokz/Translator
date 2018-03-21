package translator;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller{

    public ImageView switchButton;
    public TextArea textToBeTranslated;
    public TextArea translation;
    public Label errorLabel, leftLabel, rightLabel;

    Translator trans = new Translator();
    private boolean toEnglish = false;

    /**
     * CURRENT PROBLEM:
     *
     * Translator crashes when given a sequence of Unga Bunga words that do have English Translations,
     * followed by one that does not.
     */
    public void switchMode()
    {
        /*If we were translating to English, but want
          switch modes*/
        if (toEnglish)
        {
            //Let the user know we are now translating from English to Unga Bunga Tounga
            leftLabel.setText("English");
            rightLabel.setText("Unga Bunga Tounga");
        }else //We were translating to Unga Bunga Tounga, but want to switch modes
        {
            //Let the user know we are now translating from Unga Bunga Tounga to English
            leftLabel.setText("Unga Bunga Tounga");
            rightLabel.setText("English");
        }

        toEnglish = !toEnglish;

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
        errorLabel.setVisible(false);

        if (toEnglish)
        {
            newText = trans.translateToEnglish(textToBeTranslated.getText());
        }else
        {
           newText = trans.translate(textToBeTranslated.getText());
        }

        if (trans.getNoTrans())
        {
            errorLabel.setVisible(true);
        }

        translation.setText(newText);
    }

}
