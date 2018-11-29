import java.util.ArrayList;
/**
 *
 * https://leetcode.com/problems/add-two-numbers/submissions/
 * Difficulty: medium
 *
 */

public class LC2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ArrayList<Integer> digits = new ArrayList<>();
        while (true) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
            }
            if (l2 != null) {
                sum += l2.val;
            }
            if (l1 == null && l2 == null) {
                break;
            }

            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
            carry = sum > 9 ? 1 : 0;
            int digit = sum % 10;
            digits.add(digit);
        }
        if (carry > 0) {
            digits.add(carry);
        }

        ListNode l3 = new ListNode(digits.get(digits.size()-1));
        for (int j = digits.size() -2; j>=0; j--) {
            ListNode newNode = new ListNode(digits.get(j));
            newNode.next = l3;
            l3 = newNode;
        }
        return l3;
    }

    public static void main(String[] args) {
        int[] arrL1 = new int[] {5};
        int[] arrL2 = new int[] {5};

        ListNode l1 = new ListNode(arrL1[0]);
        for (int i=1; i<arrL1.length; i++) {
            ListNode newNode = new ListNode(arrL1[i]);
            newNode.next = l1;
            l1 = newNode;
        }
        ListNode l2 = new ListNode(arrL2[0]);
        for (int i=1; i<arrL2.length; i++) {
            ListNode newNode = new ListNode(arrL2[i]);
            newNode.next = l2;
            l2 = newNode;
        }

        LC2 ref = new LC2();
        ListNode l3 = ref.addTwoNumbers(l1, l2);
        while (l3 != null) {
            System.out.print(l3.val);
            l3 = l3.next;
        }
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
}
