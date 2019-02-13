package testpa.y2017A;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v1-plumbus/challenges/1-2-usoare/problem
 */

import java.io.*;
import java.util.StringTokenizer;

public class Caraus {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int D = Integer.parseInt(stringTokenizer.nextToken());
        int[] array = new int[N];

        long sum = 0;
        long min = Integer.MIN_VALUE;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
            sum += array[i];
            min = Math.max(min, array[i]);
        }

        long P = find(array, sum / D, min, D);
        bufferedWriter.write(P + "\n");
        int j = check(array, 13740, D);
        System.out.println(j);
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static long find(int[] array, long start,long min2,  int D){
        long min = Math.max(min2, start);
        long max = 2 * start;
        long P;
        int i;
        int j;
        int k;

        do{
            P = min + (max - min) / 2;
            i = check(array, P - 1, D);
            j = check(array, P, D);
            k = check(array, P, D);

            if(i > 0 && j == 0 && k == 0){
                return P;
            }

            if(i > 0 && j > 0 && k == 0){
                return P + 1;
            }

            if(i > 0 && j == 0 && k < 0){
                return P;
            }

            if(i == 0 && j == 0 && k == 0){
                max = P;
            }

            if(i > 0 && j > 0 && k > 0){
                min = P;
            }else{
                max = P;
            }

            if(min == max){
                return P;
            }

        }while (true);

    }

    private static int check(int[] array, long P, int D) {
        long left = P;
        int number = 0;

        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                left -= array[i];
                continue;
            }

            if (left >= array[i]) {
                left -= array[i];
                continue;
            }

            number++; /** se incrementeza numarul de drumuri */
            left = P - array[i]; /** inceputul unui drum */
        }
        number++;

        return number - D;
    }

    private static long P2(int[] array, int number){
        long P = Long.MIN_VALUE;
        for(int i = 0; i < (array.length - number + 1); i++ ){
            long partial = 0;
            for(int j = 0; j < number; j++){
                partial += array[i + j];
            }

            P = Math.max(P, partial);
        }

        return P;
    }
}
