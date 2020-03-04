package org.mlxxiv.sdevc.dec4_1;

import java.util.HashMap;
import java.util.List;

class Solution {
    static int countPairs(int numCount, List<Integer> ratingValues, int target) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        int count = 0;

        for (int i = 0; i < ratingValues.size(); i++) {
            for (int j = i + 1; j < ratingValues.size(); j++) {
                if (ratingValues.get(i) + ratingValues.get(j) == target) {
                    boolean duplicate = false;
                    if (hashMap.containsKey(ratingValues.get(i))) {
                        int value = hashMap.get(ratingValues.get(i));
                        if (value == ratingValues.get(j)) {
                            duplicate = true;
                        }
                    }

                    if (hashMap.containsKey(ratingValues.get(j))) {
                        int value = hashMap.get(ratingValues.get(j));
                        if (value == ratingValues.get(i)) {
                            duplicate = true;
                        }
                    }

                    if (!duplicate) {
                        hashMap.put(i, j);
                        count++;
                    }
                }
            }
        }
        return count;
    }
    // METHOD SIGNATURE ENDS
}