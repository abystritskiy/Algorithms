
public class LC24 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public ListNode swapPairs(ListNode head) {
        ListNode node = head;
        ListNode prevNode = null;
        ListNode prevNodeParent = null;

        int pointer = 0;
        while (node != null) {
            if (pointer % 2 == 1) {
                ListNode next = node.next;

                if (prevNodeParent == null) {
                    head = node;
                } else {
                    prevNodeParent.next = node;
                }
                node.next = prevNode;
                prevNode.next = next;
                prevNodeParent = prevNode;
            } else {
                prevNode = node;
            }
            pointer++;
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] data = new int[] {1,2,3,4};

        ListNode prevNode = new ListNode(data[0]);
        ListNode head = prevNode;


        for (int i=1; i<data.length; i++) {
            ListNode node = new ListNode(data[i]);
            prevNode.next = node;
            prevNode = node;
        }

        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
    }


    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
