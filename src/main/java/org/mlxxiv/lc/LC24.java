package org.mlxxiv.lc;

/**
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * Difficulty: medium
 */
public class LC24 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    public ListNode swapPairs(ListNode head) {
        ListNode firstParent = null; // why ???
        ListNode first = null;
        ListNode second;
        ListNode secondParent;

        ListNode node = head;
        int pointer = 0;

        while (node != null) {
            ListNode next = node.next;
            if (pointer % 2 == 0) {
                first = node;
            } else {
                second = node;
                secondParent = second.next;
                if (firstParent == null) {
                    head = second;
                    head.next = first;
                    head.next.next = secondParent;
                } else {
                    firstParent.next = second;
                    second.next = first;
                    second.next.next = secondParent;
                }
                firstParent = first;
            }

            node = next;
            pointer++;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4};

        ListNode prevNode = new ListNode(data[0]);
        ListNode head = prevNode;
        ListNode oldHead = head;


        for (int i = 1; i < data.length; i++) {
            ListNode node = new ListNode(data[i]);
            prevNode.next = node;
            prevNode = node;
        }

        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }

        System.out.println("");
        LC24 obj = new LC24();
        head = obj.swapPairs(oldHead);

        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }


    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
