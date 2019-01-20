import java.util.HashSet;

public class LinkedListCycle {
    static boolean hasCycle(Node head) {
        Node sr = head;
        Node fr = sr.next;

        while (sr != null && fr != null) {
            if (sr == fr) return  true;

            sr = sr.next;
            if (fr.next == null) {
                return false;
            }
            fr = fr.next.next;
        }
        return false;
    }
    static boolean hasCycle2(Node head) {
        HashSet<Node> visited = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            if (visited.contains(cur)) {
                System.out.println(cur.data);
                return true;
            }
            visited.add(cur);
            cur = cur.next;
        }
        return false;
    }
    static void detectCycleStart(Node head) {
        Node sr = head;
        Node fr = head;

        while (sr != null && fr != null) {
            sr = sr.next;
            fr = fr.next.next;

            if (sr == fr) {
                break;
            }
        }
        sr = head;
        while (sr != fr) {
            sr = sr.next;
            fr = fr.next;
        }

        System.out.println(sr.data);
    }

    public static void main(String[] args) {
        Node head = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);

        head.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;
        n8.next = n3;

//        System.out.println();
        detectCycleStart(head);
    }

    static class Node{
        Node next;
        int data;
        Node(int data) {
            this.data = data;
        }
    }
}
