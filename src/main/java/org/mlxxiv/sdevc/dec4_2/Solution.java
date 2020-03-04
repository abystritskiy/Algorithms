package org.mlxxiv.sdevc.dec4_2;

public class Solution {

    public ALNode deepCopy(ALNode head) {
        ALNode current = head;
        ALNode temp = null;
        if (current == null) {
            return null;
        }
        while (current != null) {
            temp = current.next;
            current.next = new ALNode(current.value);
            current.next.next = temp;
            current = temp;
        }
        current = head;
        while (current != null) {
            if (current.next != null) {
                if (current.arbitrary == null) {
                    current.next.arbitrary = current.arbitrary;
                } else {
                    current.next.arbitrary = current.arbitrary.next;
                }
            }
            if (current.next == null) {
                current = current.next;
            } else {
                current = current.next.next;
            }
        }
        ALNode og = head;
        ALNode clone = head.next;
        temp = clone;
        while (og != null && clone != null) {
            if (og.next != null) {
                og.next = og.next.next;
            } else {
                og.next = og.next;
            }
            og = og.next;
            if (clone.next == null) {
                clone.next = clone.next;
            } else {
                clone.next = clone.next.next;
            }
            clone = clone.next;
        }
        return temp;
    }

    class ALNode {
        public int value;
        public ALNode next;
        public ALNode arbitrary;

        public ALNode(int val) {
            this.value = val;
            this.next = null;
            this.arbitrary = null;
        }

        public ALNode() {
            this.value = -1;
            this.next = null;
            this.arbitrary = null;
        }
    }
}
