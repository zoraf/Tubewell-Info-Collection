package com.example.tubewellinfocollection.util;

public class ConvertToBengali {

    public static String convertToBengali(int number) {
        String[] bengaliNumbers = {
                "০", "১", "২", "৩", "৪", "৫", "৬", "৭", "৮", "৯"
        };
        String englishNumber = String.valueOf(number);
        StringBuilder bengaliNumber = new StringBuilder();
        for (int i = 0; i < englishNumber.length(); i++) {
            char digit = englishNumber.charAt(i);
            if (Character.isDigit(digit)) {
                int index = Character.getNumericValue(digit);
                bengaliNumber.append(bengaliNumbers[index]);
            } else {
                bengaliNumber.append(digit);
            }
        }
        return bengaliNumber.toString();
    }
}
