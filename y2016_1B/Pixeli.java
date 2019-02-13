package testpa.y2016_1B;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-2-goku/challenges/pixeli-problema-usoara
 */

import java.io.*;
import java.util.StringTokenizer;

public class Pixeli {
    private static int[][] matrix;

    private static int cod2(int i, int j, int n, int[] check){
        if (n == 1) {
            if (matrix[i][j] == 1) {
                check[0] = 1;
                return 2;
            } else {
                check[0] = 0;
                return 2;
            }
        }
        int newN = n / 2;
        int[][] newCheck = new int[4][1];
        int[] rez = new int[4];
        rez[0] = cod2(i, j, newN, newCheck[0]);
        rez[1] = cod2(i, j + newN, newN, newCheck[1]);
        rez[2] = cod2(i + newN, j, newN, newCheck[2]);
        rez[3] = cod2(i + newN, j + newN, newN, newCheck[3]);

        if(newCheck[0][0] == newCheck[1][0] && newCheck[2][0] == newCheck[0][0] && newCheck[0][0] == newCheck[3][0]
                && newCheck[0][0] != 2){
            check[0] = newCheck[0][0];
            return 2;
        }

        check[0] = 2;
        return 1 + rez[0] + rez[1] + rez[2] + rez[3];
    }

    private static String cod(int i, int j, int n) {
        if (n == 1) {
            if (matrix[i][j] == 1) {
                return "01";
            } else {
                return "00";
            }
        }
        int newN = n / 2;
        String[] ret = new String[4];
        ret[0] = cod(i, j, newN);
        ret[1] = cod(i, j + newN, newN);
        ret[2] = cod(i + newN, j, newN);
        ret[3] = cod(i + newN, j + newN, newN);

        if (ret[0].equals("00") && ret[1].equals("00") && ret[2].equals("00") && ret[3].equals("00")) {
            return "00";
        }

        if (ret[0].equals("01") && ret[1].equals("01") && ret[2].equals("01") && ret[3].equals("01")) {
            return "01";
        }

        return "1" + ret[0] + ret[1] + ret[2] + ret[3];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine().trim());
        matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        bufferedWriter.write(cod(0, 0, N).length() + "\n");
        bufferedWriter.write(cod2(0, 0, N, new int[1]) + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
