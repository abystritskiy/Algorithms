package org.mlxxiv.sdevc.dec11_1;

public class Solution {
    public LNode mergeLists(LNode head1, LNode head2) {
        if (head1 == null && head2 == null) return null;
        if (head1 == null || head2 == null) {
            if (head1 != null) {
                LNode l = new LNode(head1.data);
                l.next = mergeLists(head1.next, head2);
                return l;
            } else {
                LNode l = new LNode(head2.data);
                l.next = mergeLists(head1, head2.next);
                return l;
            }
        }
        if (head1.data < head2.data) {
            LNode l = new LNode(head1.data);
            l.next = mergeLists(head1.next, head2);
            return l;
        } else {
            LNode l = new LNode(head2.data);
            l.next = mergeLists(head1, head2.next);
            return l;
        }
    }

    public class LNode
    {
        int data;
        LNode next;
        LNode()
        {
            this.next = null;
        }
        LNode(int value)
        {
            this.data = value;
            this.next = null;
        }
    }
}