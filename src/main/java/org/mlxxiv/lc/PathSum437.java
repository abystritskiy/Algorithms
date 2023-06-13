package org.mlxxiv.lc;

import java.util.Random;

public class PathSum437 {
    public static final Random random = new Random();
    public static int total = 0;
    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(-3);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(11);
        TreeNode n6 = new TreeNode(3);
        TreeNode n7 = new TreeNode(-2);
        TreeNode n8 = new TreeNode(1);

        root.left = n1;
        root.right = n2 ;

        n1.left = n3 ;
        n1.right = n4 ;

        n2.left = null;
        n2.right = n5;

        n3.left = n6;
        n3.right = n7;

        n4.left = null;
        n4.right = n8;


        TreeNode root2 = new TreeNode(-2, null, new TreeNode(-3));
        System.out.println(pathSum(root2, -5));
    }

    public static int pathSum(TreeNode root, int targetSum) {
        checkSum(root, 0, targetSum);
        return total;
    }

    public static void checkSum(TreeNode node, int sumBefore, int targetSum) {
        if (node == null) {
            return;
        }

        if (sumBefore + node.val == targetSum) {
            total++;
        } else  {
//            if (sumBefore + node.val < targetSum) {
                checkSum(node.left, sumBefore + node.val, targetSum);
                checkSum(node.right, sumBefore + node.val, targetSum);
//            }
            checkSum(node.left, 0, targetSum);
            checkSum(node.right, 0, targetSum);
        }
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
