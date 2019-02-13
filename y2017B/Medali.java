package testpa.y2017B;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v2-meeseeks/challenges/test-2-arborele-filogenetic-medie
 */

import java.io.*;
import java.util.StringTokenizer;

public class Medali {

    private static double rez = 0;

    private static void generate(int k, int poz, int start, double[] P, int[] probe, boolean[] used) {
        if (k <= poz) {
            double part = 1;
            for (int i = 0; i < probe.length; i++) {
                part *= P[probe[i]];
            }

            rez += part;

        } else {
            for (int i = start; i < P.length; i++) {
                if (used[i])
                    continue;
                probe[poz] = i;
                used[i] = true;
                generate(k, poz + 1, i + 1, P, probe, used);
                used[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int k = Integer.parseInt(stringTokenizer.nextToken());
        double[] P = new double[n];
        for (int i = 0; i < n; i++) {
            P[i] = Double.parseDouble(bufferedReader.readLine().trim());
        }
        generate(k, 0, 0, P, new int[k], new boolean[n]);

        for (int i = 2; i <= k; i++) {
            rez *= i;
        }

        long fact = 1;
        for (int i = n - k + 1; i <= n; i++) {
            fact *= i;
        }
        rez /= fact;

        bufferedWriter.write(rez + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
