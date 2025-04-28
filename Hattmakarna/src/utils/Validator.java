package utils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.IdentityHashMap;
import java.util.regex.Pattern;

public class Validator {

    private static final IdentityHashMap<JTextField, ValidationData> validationMap = new IdentityHashMap<>();

    private enum ValidationType {
        EMAIL,
        NUMBER,
        LENGTH,
        DATE,
        PHONE
    }

    private static class ValidationData {
        ValidationType type;
        int minLength;
        int maxLength;
    }

    public static void setEmail(JTextField textField) {
        setValidationType(textField, ValidationType.EMAIL);
    }

    public static void setNumber(JTextField textField) {
        setValidationType(textField, ValidationType.NUMBER);
    }

    public static void setLength(JTextField textField, int minLength, int maxLength) {
        ValidationData data = new ValidationData();
        data.type = ValidationType.LENGTH;
        data.minLength = minLength;
        data.maxLength = maxLength;
        validationMap.put(textField, data);
    }

    public static void setDate(JTextField textField) {
        setValidationType(textField, ValidationType.DATE);
    }

    public static void setPhone(JTextField textField) {
        setValidationType(textField, ValidationType.PHONE);
    }

    private static void setValidationType(JTextField textField, ValidationType type) {
        ValidationData data = new ValidationData();
        data.type = type;
        validationMap.put(textField, data);
    }

    public static boolean isValid(JTextField textField) {
        ValidationData data = validationMap.get(textField);
        if (data == null) {
            return false;
        }
        String text = textField.getText();
        boolean valid = false;
        switch (data.type) {
            case EMAIL:
                valid = Pattern.matches("^[\\w\\-\\.+]*[\\w\\-\\.]@[\\w]+\\.[a-z]{2,4}$", text);
                break;
            case NUMBER:
                valid = Pattern.matches("\\d+", text);
                break;
            case LENGTH:
                valid = text.length() >= data.minLength && text.length() <= data.maxLength;
                break;
            case DATE:
                valid = Pattern.matches("\\d{4}-\\d{2}-\\d{2}", text);
                break;
            case PHONE:
                valid = Pattern.matches("\\+?[0-9\\-\\s]{7,15}", text);
                break;
        }

        updateBorder(textField, valid);
        return valid;
    }

    private static void updateBorder(JTextField textField, boolean isValid) {
    if (isValid) {
        textField.setBorder(UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
    } else {
        textField.setBorder(new LineBorder(Color.RED, 1));
    }
    }
}
