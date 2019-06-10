public class FB2_2 {
    public static void main(String[] args) {
        System.out.println(solution(143));
    }

    public static int solution(int total_lambs) {
        return greedy(total_lambs) - generous(total_lambs);
    }

    public static int greedy(int total) {
        int l1 = 1;
        total -= 1;
        int count = 1;

        if (total <= 0) {
            return count;
        }

        int l2 = 1;
        total -= 1;
        count = 2;

        if (total < 1) {
            return count;
        }

        int next = l1 + l2;
        while (total >= next){
            count++;
            total -= next;
            l1 = l2;
            l2 = next;
            next = l1 + l2;
        }

        return count;
    }

    public static int generous(int total) {

        int count = 1;
        total -= 1;

        if (total < 2) {
            return count;
        }

        int l2 = 2;
        total -= 2;
        count = 2;

        int next = 2 * l2;

        while (total >= next){
            count++;
            total -= next;

            l2 = next;
            next = 2 * l2;
        }

        return count;
    }
}
