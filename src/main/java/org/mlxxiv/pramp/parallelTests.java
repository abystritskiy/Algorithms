package org.mlxxiv.pramp;

public class parallelTests {
    public static void main(String[] args) {

        int chunkSize = 1000;

        int[] arr = new int[10000000];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }
        sequential(arr);
        parallel(arr, chunkSize);

    }

    public static void sequential(int[] arr) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            fact(arr[i]);
        }
        System.out.printf("sequential: %d ms \n", System.currentTimeMillis() - start);
    }

    public static void parallel(int[] arr, int chunkSize) {
        long start = System.currentTimeMillis();
        int cycles = (arr.length + chunkSize - 1) / chunkSize;

        Thread[] threads = new Thread[cycles];
        for (int i = 0; i < cycles; i++) {

            int j = i;

            threads[i] = new Thread(() -> {
                for (int k = j * chunkSize; k < arr.length && k < (j+1) * chunkSize; k++) {
                    fact(arr[k]);
                }
            });
            threads[i].start();
        }
        for (int j = 0; j < cycles; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("parallel: %d ms \n", System.currentTimeMillis() - start);

    }

    private static int fact(int i) {
        if (i == 0 || i == 1) {
            return i;
        }
        return i * fact(i -1 );
    }
}
