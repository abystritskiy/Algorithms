import java.math.BigInteger;
import java.util.ArrayList;

public class FB3_1 {
    public static void main(String[] args) {
        System.out.println(solution("13", "9")); // 6
        System.out.println(solution("21", "29")); // 7
        System.out.println(solution("1", "2")); //1
        System.out.println(solution("4", "31")); //10
        System.out.println(solution("1", "1")); //0
        System.out.println(solution("11", "4")); //5
        System.out.println(solution("425", "277")); //16
        System.out.println(solution("2", "4")); //impossible
        System.out.println(solution("1", "3")); //2
        System.out.println(solution("1", "10000000000000000001")); //2
    }


    public static String solution(String x, String y) {
        BigInteger m = new BigInteger(x);
        BigInteger f = new BigInteger(y);
        BigInteger i = new BigInteger("0");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");

        while (!(m.equals(one) && f.equals(one))) {
            if (f.compareTo(m) > 0) {
                BigInteger tmp = new BigInteger(f.toString());
                f = new BigInteger(m.toString());
                m = new BigInteger(tmp.toString());
            }

            if (f.equals(zero) || f.compareTo(zero) < 0 || m.equals(zero) || m.compareTo(zero) < 0) {
                return  "impossible";
            } else if (f.equals(one)) {
                i = i.add(m.subtract(one));
                return i.toString();
            } else {
                i = i.add(m.divide(f));
                m = m.mod(f);
            }
        }
        return i.toString();
    }


    public static String solutionReverse(String x, String y) {
        BigInteger m = new BigInteger(x);
        BigInteger f = new BigInteger(y);
        BigInteger i = new BigInteger("0");

        BigInteger two = new BigInteger("2");
        BigInteger one = new BigInteger("1");
        BigInteger zero = new BigInteger("0");

        if (m.equals(one) && f.equals(one)) return "0";

        if (m.equals(f)) return "impossible";

        if (f.mod(two).equals(zero) &&
                m.mod(two).equals(zero)) {
            return "impossible";
        }

        while ((m.compareTo(one) > 0 || m.compareTo(one) == 0)
                && (f.compareTo(one) > 0 || f.compareTo(one) == 0)
        ) {
            if (m.equals(one) && f.equals(one)) {
                return i.toString();
            } else if ((f.equals(one) && m.equals(two)) ||
                    (m.equals(one) && f.equals(two))) {
                i = i.add(one);
                return i.toString();
            } else {
                if (f.compareTo(m) > 0) {
                    BigInteger cycles = f.mod(m).equals(zero)  ?
                            f.divide(m).subtract(one) : f.divide(m);
                    i = i.add(cycles);
                    f = f.subtract(m.multiply(cycles));
                } else {
                    BigInteger cycles = m.mod(f).equals(zero) ?
                            m.divide(f).subtract(one) : m.divide(f);
                    i = i.add(cycles);
                    m = m.subtract(f.multiply(cycles));
                }

            }
        }

        return "impossible";
    }

    public static String solutionStack(String x, String y) {
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
            int last = stack.size() - 1;
            ArrayList<Integer> cur = stack.get(last);
            stack.remove(last);

            int m0 = cur.get(0);
            int f0 = cur.get(1);
            ;
            int cycle = cur.get(2);

            if (m0 == m1 && f0 == f1) {
                if (cycle < solution) {
                    solution = cycle;
                }
                continue;
            }

            if (m0 > m1 || f0 > f1) {
                continue;
            }

            ArrayList<Integer> case1 = new ArrayList<>();
            case1.add(m0);
            case1.add(m0 + f0);
            case1.add(cycle + 1);

            ArrayList<Integer> case2 = new ArrayList<>();
            case2.add(m0 + f0);
            case2.add(f0);
            case2.add(cycle + 1);

            stack.add(case1);
            stack.add(case2);
        }
        if (solution == Integer.MAX_VALUE) {
            return "impossible";
        } else {
            return Integer.toString(solution);
        }
    }


    public static String solutionGreedy(String x, String y) {
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
            } else if (m1 - m0 > f1 - f0) {
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

            System.out.println(m + ", " + f);
        }
        return new int[]{m, f};
    }
}
