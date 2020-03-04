package org.mlxxiv.kickstart.G2019;


import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Math.min;
import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class G2Correct {

    // Discuss this round on Codeforces: https://codeforces.com/blog/entry/70689

    static void solve() throws Exception {
        int n = scanInt();
        long m = scanLong();
        int cnts0[] = new int[60], cnts1[] = new int[60];
        for (int i = 0; i < n; i++) {
            long a = scanLong();
            for (int j = 0; j < 60; j++) {
                if ((a & (1L << j)) != 0) {
                    ++cnts1[j];
                } else {
                    ++cnts0[j];
                }
            }
        }
        for (int i = 0; i < 60; i++) {
            m -= (long) min(cnts0[i], cnts1[i]) << i;
        }
        if (m < 0) {
            printCase();
            out.println(-1);
            return;
        }
        long k = 0;
        for (int i = 59; i >= 0; i--) {
            if (cnts1[i] >= cnts0[i]) {
                k |= 1L << i;
            } else if (cnts0[i] - cnts1[i] <= Long.MAX_VALUE >> i &&
                    m >= (long) (cnts0[i] - cnts1[i]) << i) {
                m -= (long) (cnts0[i] - cnts1[i]) << i;
                k |= 1L << i;
            }
        }
        printCase();
        out.println(k);
    }

    static int scanInt() throws IOException {
        return parseInt(scanString());
    }

    static long scanLong() throws IOException {
        return parseLong(scanString());
    }

    static String scanString() throws IOException {
        while (tok == null || !tok.hasMoreTokens()) {
            tok = new StringTokenizer(in.readLine());
        }
        return tok.nextToken();
    }

    static void printCase() {
        out.print("Case #" + test + ": ");
    }

    static void printlnCase() {
        out.println("Case #" + test + ":");
    }

    static BufferedReader in;
    static PrintWriter out;
    static StringTokenizer tok;
    static int test;

    public static void main(String[] args) {
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            int tests = scanInt();
            for (test = 1; test <= tests; test++) {
                solve();
            }
            in.close();
            out.close();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
        }
    }
}