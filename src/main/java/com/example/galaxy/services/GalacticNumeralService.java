package com.example.galaxy.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GalacticNumeralService {

    static final String INVALID_MESSAGE = "I don't know what you are talking about";

    public String toGalacticNumerals(int number) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(calculateThousandsToNumeral(number / 1000));
        number = number % 1000;
        stringBuilder.append(calculateHundredsToNumeral(number / 100));
        number = number % 100;
        stringBuilder.append(calculateTensToNumeral(number / 10));
        number = number % 10;
        stringBuilder.append(calculateOnesToNumeral(number));

        String numerals = stringBuilder.toString();

        if(!isNumeralsValid(numerals)) return "I don't know what you are talking about";

        return stringBuilder.toString();
    }

    public String fromGalacticNumerals(String numerals) {

        List<Integer> numbers = new ArrayList();

        if (isNumeralsValid(numerals) == false) return INVALID_MESSAGE;

        numerals.chars().mapToObj(c -> (char) c).forEach(character -> {
            numbers.add(Numerals.valueOf(character.toString()).getValue());
        });

        return calculateToNumber(numbers).stream().reduce(0, Integer::sum).toString();
    }

    private boolean isNumeralsValid(String numeral) {

        if (numeral.contains("DD") || numeral.contains("LL") || numeral.contains("VV") || numeral.contains("XXXX") ||
                numeral.contains("IIII") || numeral.contains("CCCC") || numeral.contains("MMMM") ||
                numeral.contains("IL") || numeral.contains("IC") || numeral.contains("ID") || numeral.contains("IM")
        ) {
            return false;
        }

        return true;
    }

    private List<Integer> calculateToNumber(List integers) {

        List<Integer> calculatedList = new ArrayList();


        for (int i = 0; i < integers.size(); i++) {

            int currentNumber = (int) integers.get(i);

            if (integers.size() == 1) {
                calculatedList.add((int) integers.get(0));
            } else {
                int nextNum = (i + 1 <= integers.size() - 1) ? (int) integers.get(i + 1) : 0;
                int previousNumber = (i - 1 >= 0) ? (int) integers.get(i - 1) : 0;

                if (previousNumber < currentNumber && previousNumber != 0) {
                    calculatedList.add(currentNumber - previousNumber);
                } else if (currentNumber >= nextNum) {
                    calculatedList.add(currentNumber);
                }
            }

        }

        return calculatedList;
    }

    private String calculateThousandsToNumeral(int thousands) {
        StringBuilder stringBuilder = new StringBuilder();

        for(int x = 0; x < thousands; x++) {
            if(stringBuilder.length() >= 3 &&
                    stringBuilder.substring(stringBuilder.length() - 3, stringBuilder.length()).contains("MMM")) {
                stringBuilder.append("DMDM");
            } else {
                stringBuilder.append("M");
            }
        }

        return stringBuilder.toString();
    }

    private String calculateHundredsToNumeral(int hundreds) {
        StringBuilder stringBuilder = new StringBuilder();

        if(hundreds == 4) {
            stringBuilder.append("CD");
            hundreds = hundreds - 4;
        } else if(hundreds >= 5 && hundreds < 9) {
            stringBuilder.append("D");
            hundreds = hundreds - 5;
        } else if(hundreds == 9){
            stringBuilder.append("CM");
            hundreds = hundreds - 9;
        }

        for(int x = 0; x < hundreds; x++) {
            stringBuilder.append("C");
        }

        return stringBuilder.toString();
    }

    private String calculateTensToNumeral(int tens) {

        StringBuilder stringBuilder = new StringBuilder();

        if(tens == 4) {
            stringBuilder.append("XL");
            tens = tens - 4;
        } else if(tens >= 5 && tens < 9) {
            stringBuilder.append("L");
            tens = tens - 5;
        } else if(tens == 9){
            stringBuilder.append("XC");
            tens = tens - 9;
        }

        for(int x = 0; x < tens; x++) {
            stringBuilder.append("X");
        }

        return stringBuilder.toString();
    }

    private String calculateOnesToNumeral(int ones) {

        StringBuilder stringBuilder = new StringBuilder();

        if(ones == 4) {
            stringBuilder.append("IV");
            ones = ones - 4;
        } else if(ones >= 5 && ones < 9) {
            stringBuilder.append("V");
            ones = ones - 5;
        } else if(ones == 9){
            stringBuilder.append("IX");
            ones = ones - 9;
        }

        for(int x = 0; x < ones; x++) {
            stringBuilder.append("I");
        }

        return stringBuilder.toString();
    }
}
