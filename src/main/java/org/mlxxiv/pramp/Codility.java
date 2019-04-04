package org.mlxxiv.pramp;

import java.util.Stack;

public class Codility {
    public static void main(String[] args) {
        String s = "13 DUP 4 POP 5 DUP + DUP + -";
        System.out.println(solution(s));
    }

    public static int solution(String str) {
        int error = -1;
        String[] commands = str.split(" ");
        Stack<Integer> stack = new Stack<>();
        for (String com : commands) {
            if (com.equals("DUP")) {
                if (stack.empty()) {
                    return error;
                }
                int val = stack.pop();

                stack.push(val);
                stack.push(val);

            } else if (com.equals("POP")) {
                if (stack.empty()) {
                    return error;
                }
                stack.pop();

            } else if (com.equals("-")) {
                if (stack.empty()) {
                    return error;
                }
                int val1 = stack.pop();
                if (stack.empty()) {
                    return error;
                }
                int val2 = stack.pop();
                int total = val1 - val2;

                if (total < 0 ) {
                    return error;
                } else {
                    stack.push(total);
                }

            } else if (com.equals("+")) {
                if (stack.empty()) {
                    return error;
                }
                int val1 = stack.pop();
                if (stack.empty()) {
                    return error;
                }
                int val2 = stack.pop();

                if (Integer.MAX_VALUE - val1 < val2) {
                    return error;
                }
                int total = val2 + val1;
                stack.push(total);

            } else {
                int val = Integer.parseInt(com);
                stack.push(val);
            }
        }

        if (!stack.empty()) {
            return stack.pop();
        }

        return error;
    }
}
