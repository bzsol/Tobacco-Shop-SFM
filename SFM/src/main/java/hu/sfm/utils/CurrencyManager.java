package hu.sfm.utils;

import javafx.scene.control.TextField;

public class CurrencyManager {
    public static String removeTextFieldPattern(String text) {
        StringBuilder removePattern = new StringBuilder();
        for (char element : text.toCharArray()) {
            if (element != 'F' && element != 't' && element != ' ') {
                removePattern.append(element);
            }
        }
        return removePattern.toString();
    }

    public static String createPattern(String text) {
        StringBuilder textPattern = new StringBuilder();
        int runTimeCount = 1;
        for (int i = text.length() - 1; i >= 0; i--) {
            textPattern.append(text.charAt(i));
            if (runTimeCount % 3 == 0) {
                textPattern.append(" ");
            }
            runTimeCount++;
        }
        textPattern.reverse();
        textPattern.append(" Ft");
        return textPattern.toString();
    }

    public static void setTextFieldPattern(TextField fullPrice, TextField unitPrice, TextField quantityTextField) {
        fullPrice.setText(createPattern(String.valueOf(Integer.parseInt(removeTextFieldPattern(unitPrice.getText())) * Integer.parseInt(quantityTextField.getText()))));
    }
}
