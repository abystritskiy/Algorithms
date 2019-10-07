package org.mlxxiv.kickstart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws FileNotFoundException {
//        System.setIn(new FileInputStream("02.in"));
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        long t = in.nextLong();
        for (int i = 0; i < t; i++) {
            Map<Integer, List<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> beauties = new HashMap<>();
            int V = in.nextInt();
            for(int j = 1; j <= V; j++){
                beauties.put(j, in.nextInt());
            }
            for(int j =0; j < V - 1;j ++){
                int v1 = in.nextInt();
                int v2 = in.nextInt();
                List<Integer> l1 = graph.getOrDefault(v1, new ArrayList<>());
                l1.add(v2);
                graph.put(v1, l1);
                List<Integer> l2 = graph.getOrDefault(v2, new ArrayList<>());
                l2.add(v1);
                graph.put(v2, l2);
            }

            System.out.println("Case #" + (i + 1) + ": " + find(V, graph, beauties,0, 1, new HashSet<>()));
        }
    }

    private static int find(int n, Map<Integer, List<Integer>> graph,
                            Map<Integer, Integer> beauties,
                            int sum,
                            int idx,
                            Set<Integer> used) {
        if(idx > n) return sum;
        List<Integer> vert = graph.get(idx);
        Set<Integer> toRemove = new HashSet<>();
        int count = 0;
        for(Integer v: vert){
            count += used.contains(v) ? 0 : beauties.get(v);
            if(used.add(v)){
                toRemove.add(v);
            }
        }
        count += used.contains(idx) ? 0 :  beauties.get(idx);
        if(used.add(idx)){
            toRemove.add(idx);
        }
//        used.add(idx);
        int res0 = find(n, graph, beauties, sum + count, idx + 1, used);
        for(Integer v: toRemove){
            used.remove(v);
        }
//        used.remove(idx);
        int res1 = find(n, graph, beauties, sum, idx+1, used);
        return Math.max(res0, res1);
    }
}

