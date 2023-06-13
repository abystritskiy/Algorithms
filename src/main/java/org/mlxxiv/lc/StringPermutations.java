package org.mlxxiv.lc;

import java.util.ArrayList;
import java.util.List;

public class StringPermutations {
    public static void main(String[] args) {
        String input = "abc";
        List<String> permutations = generatePermutations(input);
        System.out.println("Permutations: " + permutations);
    }

    public static List<String> generatePermutations(String input) {
        List<String> permutations = new ArrayList<>();
        backtrack(permutations, input, "", new boolean[input.length()]);
        return permutations;
    }

    private static void backtrack(List<String> permutations, String input, String currentPermutation, boolean[] used) {
        if (currentPermutation.length() == input.length()) {
            permutations.add(currentPermutation);
            return;
        }

        for (int i = 0; i < input.length(); i++) {
            if (used[i]) {
                continue;  // Skip already used characters
            }

            used[i] = true;
            backtrack(permutations, input, currentPermutation + input.charAt(i), used);
            used[i] = false;
        }
    }
}
