package com.example.galaxy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class InputTranslateService {

    private GalacticNumeralService galacticNumeralService;

    @Autowired
    public InputTranslateService(GalacticNumeralService galacticNumeralService) {

        this.galacticNumeralService = galacticNumeralService;
    }

    public String translateInquiry(MultipartFile inquiryRequest) throws IOException {

        Map<String, Numerals> numeralsMap = new HashMap();
        Map<String, Double> commodityMap = new HashMap();
        List<String> questions = new ArrayList();
        List<String> statements = new ArrayList();

        BufferedReader br = new BufferedReader(new InputStreamReader(inquiryRequest.getInputStream()));

        //Break out individual lines
        br.lines().forEach(line -> {

            //Determine the questions from the statements
            if (line.contains("?")) {
                questions.add(line);
            } else {
                statements.add(line);
            }
        });

        //Get numerals from statements
        numeralsMap.putAll(findNumerals(statements));

        //Get prices for the commodities
        commodityMap.putAll(pricePerCommodity(statements, numeralsMap));

        //Return the answered questions
        return answerQuestion(questions, numeralsMap, commodityMap);
    }

    private Map<String, Numerals> findNumerals(List<String> statements) {

        Map<String, Numerals> numeralsMap = new HashMap();

        statements.forEach(statement -> {
            //Get the individual words
            String[] words = statement.split(" ");

            //Associate values for each custom name of a unit
            Arrays.stream(words)
                    .filter(w -> galacticNumeralService.isNumeralsValid(w))
                    .forEach(word -> numeralsMap.put(words[0], Numerals.valueOf(word)));
        });

        return numeralsMap;

    }

    private Map<String, Double> pricePerCommodity(List<String> statements, Map<String, Numerals> numeralsMap) {

        Map<String, Double> commodityMap = new HashMap();

        statements.forEach(statement -> {
            StringBuilder sb = new StringBuilder();
            String[] words = statement.split(" ");

            //Find a price from a String
            OptionalDouble price = Arrays.stream(words)
                    .filter(word -> word.matches("[0-9]+"))
                    .mapToDouble(x -> Integer.parseInt(x)).findFirst();

            //If a price exists, it must be a commodity
            if (price.isPresent()) {
                Arrays.stream(words).forEach(word -> {
                    if (numeralsMap.containsKey(word)) {
                        sb.append(numeralsMap.get(word).getId());
                    }
                });

                //Find the commodity
                Optional<String> commodity = Arrays.stream(words).filter(word -> word.chars().mapToObj(c -> (char) c)
                                .anyMatch(c -> Character.isUpperCase(c))
                                && !numeralsMap.containsKey(word)
                                && !word.matches("Credits"))
                        .findFirst();

                int commodityQty = galacticNumeralService.fromGalacticNumerals(sb.toString());

                if (commodity.isPresent()) {
                    commodityMap.put(commodity.get(), price.getAsDouble() / commodityQty);
                }
            }
        });

        return commodityMap;
    }

    private String answerQuestion(List<String> questions, Map<String, Numerals> numeralsMap,
                                  Map<String, Double> commodityMap) {

        StringBuilder sb = new StringBuilder();

        questions.forEach(question -> {
            String[] words = question.split(" ");
            StringBuilder numeralSB = new StringBuilder();
            List<String> nameList = new ArrayList();

            //Get the numerals in the question
            Arrays.stream(words)
                    .filter(word -> numeralsMap.containsKey(word))
                    .forEach(num -> {
                        numeralSB.append(numeralsMap.get(num).getId());
                        nameList.add(num);
                    });

            if(!nameList.isEmpty()) {
                nameList.forEach(name -> sb.append(name + " "));
            }

            //Get the quantity based on the numerals
            int qty = galacticNumeralService.fromGalacticNumerals(numeralSB.toString());

            Optional<String> commodity = Arrays.stream(words).filter(word -> commodityMap.containsKey(word)).findFirst();

            if(commodity.isPresent()) {
                DecimalFormat df = new DecimalFormat("###.#");
                sb.append(commodity.get() + " is " +
                        df.format(commodityMap.get(commodity.get()).doubleValue() * qty) + " Credits");
            } else if(qty != 0) {
                sb.append("is " + qty);
            } else {
                sb.append("I have no idea what you are talking about");
            }

            sb.append("\n");
        });

        return sb.toString();
    }
}