package org.mlxxiv.hr;

import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeTopView {
    static HashMap<Integer, int[]> levels = new HashMap<>();

    public static void topView(Node root) {
        levels.put(0, new int[] {root.data, 0} );
        setLevels(root, 0, 0);

        SortedSet<Integer> keys = new TreeSet<>(levels.keySet());
        for (Integer key : keys) {
            System.out.print(levels.get(key)[0] + " ");
        }
    }

    public static void setLevels(Node node, int levelWidth, int levelDepth)
    {
        if (node == null) {
            return;
        }
        if (!levels.containsKey(levelWidth) || levels.get(levelWidth)[1] > levelDepth) {
            levels.put(levelWidth, new int[] {node.data, levelDepth});
        }

        setLevels(node.left, levelWidth-1, levelDepth+1);
        setLevels(node.right, levelWidth+1, levelDepth+1);

    }


    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Node root = null;
        int[] in = new int[] {1,14,3,7,4,5,15,6,13,10,11,2,12,8,9};
        for (int data: in) {
            root = insert(root, data);
        }

        topView(root);
    }

    static class Node {
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }
}
