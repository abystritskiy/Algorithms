package org.mlxxiv.lc;

import java.util.LinkedList;
import java.util.List;

public class StackWIthQueues {

    List<Integer> queue1;
    List<Integer> queue2;


    public StackWIthQueues() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        queue1.add(x);
    }

    public int pop() {
        while (queue1.size() > 1) {
            queue2.add(queue1.remove(0));
        }
        int result = queue1.remove(0);
        while(!queue2.isEmpty()) {
            queue1.add(queue2.remove(0));
        }
        return result;
    }

    public int top() {
        while (queue1.size() > 1) {
            queue2.add(queue1.remove(0));
        }
        int result = queue1.remove(0);
        queue2.add(result);
        while(!queue2.isEmpty()) {
            queue1.add(queue2.remove(0));
        }
        return result;
    }

    public boolean empty() {
        return queue1.isEmpty();
    }


}
