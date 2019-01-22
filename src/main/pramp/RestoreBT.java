import java.util.*;

public class RestoreBT {

     public static void restore(int[][] input) {
         HashMap<Integer, Node> map = new HashMap<>();

         Node parent = null;

         for (int i=0; i<input.length; i++) {
             Node child;
             if (map.containsKey(input[i][0])) {
                  parent = map.get(input[i][0]);
             } else {
                 parent = new Node(input[i][0]);
             }
             if (map.containsKey(input[i][1])) {
                 child = map.get(input[i][1]);
             } else {
                 child = new Node(input[i][1]);
             }

             if (parent.left == null ) {
                 parent.left = child;
             } else {
                 parent.right = child;
             }
             child.parent = parent;

             map.put(input[i][1], child);
             map.put(input[i][0], parent);
         }

         Node root = null;
         while (parent != null) {
             root = parent;
             parent = parent.parent;
         }

         System.out.println(root.val);

     }

     public static void main(String[] args){
         int[][] input = new int[][] {
             {2,4},
             {1,2},
             {3,6},
             {1,3},
             {2,5},
         };
         restore(input);

     }

     private static class Node {
         final int val;
         public Node left, right, parent;
         public Node(int val) {
             this.val = val;
         }
    }
}
