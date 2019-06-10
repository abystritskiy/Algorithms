import java.util.Arrays;

public class FB2_1
{
    public static void main(String[] args) {
        int[] l1 = new int[] {1, 2, 3, 4};
        int t1 = 15;
        System.out.println(Arrays.toString(solution(l1, t1))); // [-1, -1]


        int[] l2 = new int[] {4, 3, 10, 2, 8};
        int t2 = 12;
        System.out.println(Arrays.toString(solution(l2, t2))); //[2,3]
    }

    public static int[] solution(int[] l, int t) {
        // Your code here
        int i = 0;
        while (i < l.length) {
            int sum = 0;
            int j = i;
            while (j<l.length && sum < t) {
                sum += l[j];
                if (sum == t) {
                    return new int[] {i, j};
                }
                j++;
            }
            i++;
        }

        return new int[] {-1,-1};
    }
}
