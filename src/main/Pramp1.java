package main;

import java.util.Arrays;
import java.util.Random;

public class Pramp1 {

    static int getDifferentNumber(int[] arr) {
        Arrays.sort(arr);
        return  0;
    }


    public static void main(String[] args) {
        int n = 100;
        int[] sample = new int[n];

        Random random = new Random();
        int missedNum = random.nextInt(n);

        int i = 0;
        int num = 0;

        while (i < n)
        {
            if (num == missedNum) {
                num++;
                continue;
            }
            sample[i] = num;
            i++;
            num++;
        }

//        shuffleArray(sample);
        int res = getDifferentNumber(sample);
        System.out.println(Arrays.toString(sample));
        System.out.println(missedNum);
        System.out.println(res);
    }

    public static void shuffleArray(int[] a) {
        int n = a.length;
        Random random = new Random();
        random.nextInt();
        for (int i = 0; i < n; i++) {
            int change = i + random.nextInt(n - i);
            int tmp = a[i];
            a[i] = a[change];
            a[change] = tmp;
        }
    }


}
