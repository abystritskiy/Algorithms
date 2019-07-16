public class FB3_3 {
    public static void main(String[] args) {
        System.out.println(solution(3)); //1
        System.out.println(solution(4)); //1
        System.out.println(solution(5)); //2
        System.out.println(solution(200)); //487067745
    }

    public static int solution(int n) {
        int results = 0;
        Integer[][] cache = new Integer[n][n];

        for (int i = 1; i < n; i++) {
            results += countStairs(n - i, i, cache);
        }

        return results;
    }

    public static int countStairs(int left, int prev, Integer[][] cache) {
        if (left == 0 || prev == 1) return 0;
        if (cache[left][prev] != null) return cache[left][prev];

        int results = 0;
        if (prev > left) results++;
        for (int i = 1; i <= left - 1; i++) {
            if (i >= prev) break;
            results += countStairs(left - i, i, cache);
        }
        cache[left][prev] = results;
        return results;
    }
}
