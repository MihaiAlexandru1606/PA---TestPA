package testpa.y2016_1A;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-1-sasuke/challenges/super-glue-chess-problema-medie/problem
 */

import java.io.*;
import java.util.StringTokenizer;

public class Superglue_Chess {

    private static boolean check(int[] queens, int index, int x, int y) {
        for (int i = 0; i < index; i++) {

            if (i == x)
                continue;

            if (queens[i] == queens[index]) {
                System.out.println();
                return false;
            }


            if ((index > x && x > i) &&
                    (Math.abs(index - x) == Math.abs(queens[index] - y)) &&
                    (Math.abs(i - x) == Math.abs(queens[i] - queens[x]))) {

                continue;
            }

            if (Math.abs(i - index) == Math.abs(queens[i] - queens[index])){

                return false;
            }
        }

        return true;
    }

    private static void solution(int[] queens, int index, int[] number, int x, int y) {
        if (index == x) {
            queens[index] = y;
            solution(queens, index + 1, number, x, y);

        } else {

            if (index == queens.length) {
                number[0]++;
            } else {

                for (int i = 0; i < queens.length; i++) {
                    if (i == y) {
                        continue;
                    }

                    queens[index] = i;

                    if (check(queens, index, x, y)){
                        solution(queens, index + 1, number, x, y);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine().trim());
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int x = Integer.parseInt(stringTokenizer.nextToken());
        int y = Integer.parseInt(stringTokenizer.nextToken());

        int[] queens = new int[N];
        int[] number = new int[1];
        solution(queens, 0, number, x, y);
        bufferedWriter.write(number[0] + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
