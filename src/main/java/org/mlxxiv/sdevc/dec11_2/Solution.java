package org.mlxxiv.sdevc.dec11_2;

public class Solution {
    public ALNode deepCopy(ALNode head) {
        ALNode head2 = null;
        ALNode curr = null;
        ALNode temp = null;

        if (head == null)
            return head2;
        curr = head;
        while (curr != null) {
            temp = curr.next;
            curr.next = new ALNode(curr.value);
            curr.next.next = temp;
            curr = temp;
        }
        curr = head;
        while (curr != null) {
            if (curr.arbitrary != null)
                curr.next.arbitrary = curr.arbitrary.next;
            curr = curr.next.next;
        }
        curr = head2 = head.next;
        temp = head;

        while (curr != null) {
            temp.next = curr.next;
            temp = curr.next;
            if (curr.next != null) {
                curr.next = curr.next.next;
            }
            if (temp != null)
                curr = temp.next;
            else
                curr = null;
        }
        return head2;

    }

    class ALNode
    {
        public int value;
        public ALNode next;
        public ALNode arbitrary;

        public ALNode(int val)
        {
            this.value = val;
            this.next = null;
            this.arbitrary = null;
        }

        public ALNode()
        {
            this.value = -1;
            this.next = null;
            this.arbitrary = null;
        }
    }
}
