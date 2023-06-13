package org.mlxxiv.lc;

import java.util.*;

public class DiameterOfBinaryTree543 {
    public static final int MAX = 15;
    public static int maxDiameter = 0;
    public static Set<Integer> used = new HashSet<>();
    public static Map<Integer, List<Integer>> levels = new HashMap<>();
    public static List<TreeNode> queue = new ArrayList<>();
    public static final Random random = new Random();

    public static void main(String[] args) {
        //generate random tree
        TreeNode head = new TreeNode();
        queue.add(head);
        generateTree();

        // get levels map
        collectTreeLevels(head, 0);

        //print tree
        levels.forEach((level, nodes) -> {
            System.out.println(level + ": "+nodes.toString());
        });
        System.out.println("Binary Tree Diameter = " + diameterOfBinaryTree(head));
        boolean stop = true;
    }

    public static int diameterOfBinaryTree(TreeNode node) {
        helper(node);
        return maxDiameter;
    }
    public static void helper(TreeNode node) {
        TreeNode left = node.left;
        TreeNode right = node.right;

        int leftHeight = 0;
        int rightHeight = 0;

        int diameter = 0;
        if (left != null) {
            leftHeight = height(left) + 1;
            diameterOfBinaryTree(left);
        }
        if (right != null) {
            rightHeight = height(right) + 1;
            diameterOfBinaryTree(right);
        }

        diameter = leftHeight + rightHeight;
        if (diameter > maxDiameter) {
            maxDiameter = diameter;
        }
    }

    public static int height(TreeNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));

    }

    public static void collectTreeLevels(TreeNode node, int level) {
        levels.computeIfAbsent(level, k -> new ArrayList<>()).add(node.val);
        if (node.left != null) collectTreeLevels(node.left, level+1);
        if (node.right != null) collectTreeLevels(node.right, level+1);
    }

    public static void generateTree() {
        if (queue.isEmpty()) {
            return;
        }
        TreeNode node = queue.get(0);
        queue.remove(0);

        int val = random.nextInt(2 * MAX) + 1;
        while (used.contains(val)) {
            val = random.nextInt(2 * MAX) + 1;
        }

        node.val = val;
        used.add(val);
        if (used.size() >= MAX) {
            return;
        }

        if (random.nextInt(10) < 8) {
            TreeNode left = new TreeNode();
            node.left = left;
            queue.add(left);
        }

        if (random.nextInt(10) < 8) {
            TreeNode right = new TreeNode();
            node.right = right;
            queue.add(right);
        }
        generateTree();
    }

    static class TreeNode {
        Integer val;
        TreeNode left;
        TreeNode right;

        TreeNode() { }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
