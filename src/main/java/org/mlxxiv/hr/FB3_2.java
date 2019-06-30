import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FB3_2 {
    public static void main(String[] args) {

        int[] l1 = new int[] {1,1,1};
        System.out.println(solution(l1)); //1

        int[] l2 = new int[] {1, 2, 3, 4, 5, 6};
        System.out.println(solution(l2)); //3

        int[] l3 = new int[] {1, 2, 3, 4, 5, 6, 8, 48};
        System.out.println(solution(l3)); //5
    }

    public static int solution(int[] l) {
        Arrays.sort(l);
        int level;
        if (l[0] != 1) {
            level = 0;
        } else {
            level = 1;
        }

        Node head = new Node(l[0], level);
        int count = 0;

        for (int i = 1; i<l.length; i++) {
            head.insert(l[i]);
        }

        ArrayList<Node> elements = new ArrayList<>();
        elements.add(head);

        while (elements.size()  > 0) {
            int last = elements.size()-1;
            Node node = elements.get(last);
            elements.remove(last);

            if (node.level == 3) {
                count++;
            } else {
                for (Map.Entry<Integer, Node> child: node.children().entrySet()) {
                    elements.add(child.getValue());
                }
            }
        }
        return count;
    }

    static class Node
    {
        final public int value;
        final public int level;
        private HashMap<Integer, Node> children = new HashMap<>();


        public Node(int value, int level) {
            this.value = value;
            this.level = level;
        }

        public HashMap<Integer, Node> children()
        {
            return this.children;
        }

        public boolean isValid(int candidate)
        {
            return candidate >= value &&
                    candidate % value == 0;
        }

        public void insert(int value)
        {
            if (this.level == 2 && this.isValid(value) && !this.children.containsKey(value)) {
                this.children.put(value, new Node(value, level+1));
            } else if (this.level < 2) {
                if (this.level == 0 || this.isValid(value)) {
                    for (Map.Entry<Integer, Node> child: children.entrySet()) {
                        child.getValue().insert(value);
                    }
                    if (!this.children.containsKey(value)) {
                        this.children.put(value, new Node(value, level+1));
                    }
                }
            }

        }
    }
}
