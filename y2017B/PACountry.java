package testpa.y2017B;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v2-meeseeks/challenges/test-2-pa-country-medie
 */

import java.io.*;
import java.util.StringTokenizer;

public class PACountry {
    private static final int MOD = 666013;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int N, M, P;
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        P = Integer.parseInt(stringTokenizer.nextToken());
        long[][][] dp = new long[N + 2][M + 2][P + 1];
        int[][] game = new int[N + 2][M + 2];
        int Sx, Sy, Fx, Fy;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        Sx = Integer.parseInt(stringTokenizer.nextToken());
        Sy = Integer.parseInt(stringTokenizer.nextToken());
        Fx = Integer.parseInt(stringTokenizer.nextToken());
        Fy = Integer.parseInt(stringTokenizer.nextToken());

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            for (int j = 1; j <= M; j++) {
                game[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        dp[Sx][Sy][0] = 1;
        for (int p = 1; p <= P; p++) {

            for (int n = 1; n <= N; n++) {

                for (int m = 1; m <= M; m++) {
                    //dp[n][m][p] = dp[n - 1][m][p - 1] + dp[n + 1][m][p - 1] + dp[n][m - 1][p - 1] + dp[n][m + 1][p - 1];
                    if(game[n - 1][m] != 1)
                        dp[n][m][p] += dp[n - 1][m][p - 1];
                    if(game[n + 1][m] != 1)
                        dp[n][m][p] += dp[n + 1][m][p - 1];
                    if(game[n][m - 1] != 1)
                        dp[n][m][p] += dp[n][m - 1][p - 1];
                    if(game[n][m + 1] != 1)
                        dp[n][m][p] += dp[n][m + 1][p - 1];

                    dp[n][m][p] %= MOD;
                }
            }
        }

        long rez = 0;
        for(int p = 1; p <= P; p++){
            rez += dp[Fx][Fy][p];
            rez %= MOD;
        }

        bufferedWriter.write(rez + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
