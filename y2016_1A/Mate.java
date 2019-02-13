package testpa.y2016_1A;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-1-sasuke/challenges/mate-problema-medie/problem
 */

import java.io.*;
import java.util.Stack;

public class Mate {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] S = bufferedReader.readLine().trim().toCharArray();
        int tip = Integer.parseInt(bufferedReader.readLine().trim());
        int[][] dp = new int[2][S.length]; /** dp[0] -> mai mare; dp[1] -> mai mic */
        int[][] p = new int[2][S.length];

        dp[0][0] = 1;
        dp[1][0] = 1;
        p[0][0] = 0;
        p[1][0] = 0;

        int max = 1;
        int start = 0;
        int start_dp = 0;

        for (int i = 1; i < S.length; i++) {
            dp[0][i] = 1;
            dp[1][i] = 1;
            p[0][i] = i;
            p[1][i] = i;

            for (int j = 0; j < i; j++) {

                if (S[j] > S[i]) {
                    if (dp[1][i] < dp[0][j] + 1) {
                        dp[1][i] = dp[0][j] + 1;
                        p[1][i] = j;
                    }
                }

                if (S[j] < S[i]) {
                    if (dp[0][i] < dp[1][j] + 1) {
                        dp[0][i] = dp[1][j] + 1;
                        p[0][i] = j;
                    }
                }

            }

            if (max < dp[0][i]) {
                max = dp[0][i];
                start = i;
                start_dp = 0;
            }

            if (max < dp[1][i]) {
                max = dp[1][i];
                start = i;
                start_dp = 1;
            }
        }

        bufferedWriter.write(max + "\n");

        if (tip == 1) {
            Stack<Integer> stack = new Stack<>();
            stack.push(start);

            while (p[start_dp][start] != start) {
                stack.push(p[start_dp][start]);
                start = p[start_dp][start];
                start_dp = 1 - start_dp;
            }

            while (!stack.isEmpty()){
                bufferedWriter.write(S[stack.pop()] + "");
            }
            bufferedWriter.write("\n");
        }


        bufferedReader.close();
        bufferedWriter.close();
    }
}
