package org.mlxxiv.lc;

import java.util.Arrays;
import java.util.List;

public class Stream {
    public static void main(String[] args) {
        String[] in = new String[] {
          "N", "N", "F", "Y", "N", "y", "Y"
        };

        List<Boolean> values = Arrays.stream(in)
                .filter(k -> k.toUpperCase().equals("Y") || k.toUpperCase().equals("N") )
                .map(k -> k.toUpperCase().equals("Y")).toList();

        System.out.println(values);
    }
}
