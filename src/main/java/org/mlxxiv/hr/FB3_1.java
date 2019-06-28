import java.util.ArrayList;

public class FB3_1 {
    public static void main(String[] args) {
        System.out.println(solution("9", "13")); // 6
        System.out.println(solution("21", "29")); // 7
        System.out.println(solution("1", "2")); //1
        System.out.println(solution("4", "7")); //4
        System.out.println(solution("1", "1")); //0
        System.out.println(solution("11", "4")); //5
    }


    public static String solution(String x, String y) {
        int m1 = Integer.parseInt(x);
        int f1 = Integer.parseInt(y);

        ArrayList<Integer> init = new ArrayList<>();
        init.add(1);
        init.add(1);
        init.add(0);

        ArrayList<ArrayList<Integer>> stack = new ArrayList<>();
        stack.add(init);


        int solution = Integer.MAX_VALUE;

        while (stack.size() > 0) {
            int last = stack.size()-1;
            ArrayList<Integer> cur = stack.get(last);
            stack.remove(last);

            int m0 = cur.get(0);
            int f0 = cur.get(1);;
            int cycle = cur.get(2);

            if (m0 == m1 && f0 == f1) {
                if (cycle < solution) {
                    solution = cycle;
                }
            }

            if (m0 > m1 || f0 > f1) {
                continue;
            }

            ArrayList<Integer> case1 = new ArrayList<>();
            case1.add(m0);
            case1.add(m0+f0);
            case1.add(cycle+1);

            ArrayList<Integer> case2 = new ArrayList<>();
            case2.add(m0+f0);
            case2.add(f0);
            case2.add(cycle+1);

            stack.add(case1);
            stack.add(case2);
        }
        if (solution == Integer.MAX_VALUE) {
            return "impossible";
        } else {
            return  Integer.toString(solution);
        }
    }



    public static String solutionOld(String x, String y) {
        // initial number of M and F bombs
        int m0 = 1;
        int f0 = 1;


        // target number of M and F bombs
        int m1 = Integer.parseInt(x);
        int f1 = Integer.parseInt(y);

        // number of iterations (generations)
        int iteration = 0;

        while (m0 <= m1 && f0 <= f1) {

            if (m0 == m1 && f0 == f1) {
                return Integer.toString(iteration);
            }

            if (m0 + f0 == m1) {
                m0 += f0;
            } else if (m0 + f0 == f1) {
                f0 += m0;
            } else if (m1-m0 > f1-f0) {
                m0 += f0;
            } else {
                f0 += m0;
            }
            iteration++;
        }

        return "impossible";
    }

    public static int[] generate(int cycles) {
        int m = 1;
        int f = 1;
        int i = 0;

        while (i < cycles) {
            if (Math.random() > 0.5) {
                f += m;
            } else {
                m += f;
            }
            i++;

            System.out.println(m +", " + f);
        }
        return new int[] {m, f};
    }
}
