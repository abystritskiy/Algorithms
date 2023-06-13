package org.mlxxiv.lc;

import java.util.*;
import java.util.regex.Pattern;

public class StrComparison {
    public static void main(String[] args) {
        List<String> identifiers = Arrays.asList("USA", "CAN", "MEX", "MEX");
        List<String> countryIdentifiers = Arrays.asList("CAN", "MEX", "AUS", "IND");

        List<String> result = compareIdentifiers(identifiers, countryIdentifiers);
        System.out.println(result);
    }

    public static List<String> compareIdentifiers(List<String> identifiers, List<String> countryIdentifiers) {
        List<String> matchingIdentifiers = new ArrayList<>();
        for (String identifier : identifiers) {
            if (countryIdentifiers.contains(identifier)) {
                matchingIdentifiers.add(identifier);
            }
        }
        return matchingIdentifiers.stream().distinct().toList();
//        return matchingIdentifiers;
    }

    public static List<String> compareWithWildcard(List<String> identifiers, List<String> countryIdentifiers, String wildcard) {
        List<String> matchingIdentifiers = new ArrayList<>();
        String wildcardPattern = wildcard.replace("*", ".*");
        for (String identifier : identifiers) {
            if (Pattern.matches(wildcardPattern, identifier)) {
                matchingIdentifiers.add(identifier);
            }
        }
//        return matchingIdentifiers.stream().distinct().toList();
        return matchingIdentifiers;
    }
    public static Map<String, Integer> compareWithPriority(List<String> identifiers, List<String> countryIdentifiers) {
        Map<String, Integer> matchingIdentifiers = new HashMap<>();
        for (String pair : identifiers) {
            String[] parts = pair.split(":");
            String identifier = parts[0];
            int priority = Integer.parseInt(parts[1]);
            if (countryIdentifiers.contains(identifier) && !matchingIdentifiers.containsKey(identifier)) {
                matchingIdentifiers.put(identifier, priority);
            }
        }
        return matchingIdentifiers;
    }
}
