package testpa.y2017A;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v1-plumbus/challenges/test-1-extraterestrii
 */

import java.io.*;
import java.util.StringTokenizer;

public class Extraterestri {

    private static final int MOD = 666013;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int N, M, K;
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        int[][] rest = new int[M][M];
        long[][] dp = new long[N][M];

        for (int i = 0; i < K; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            int j = Integer.parseInt(stringTokenizer.nextToken());
            int u = Integer.parseInt(stringTokenizer.nextToken());
            rest[j - 1][u - 1] = 1;
        }

        for (int i = 0; i < M; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int l = 0; l < M; l++) {
                    if(rest[l][j] == 0)
                        dp[i][j] = ((dp[i][j] % MOD) + (dp[i - 1][l] % MOD)) % MOD;
                }
            }
        }

        long rez = 0;

        for(int j = 0; j < M; j++){
            rez = ((rez % MOD) + (dp[N - 1][j] % MOD)) % MOD;
        }

        bufferedWriter.write(rez + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
