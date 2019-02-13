package testpa.y2018;

/**
 * Problama PA
 * https://www.hackerrank.com/contests/test-practic-pa-2018-v1-deadbeef/challenges/test-1-1-1-semnal-usoara
 */

import java.io.*;
import java.util.StringTokenizer;

public class Semnale {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine().trim());
        int[] configuration = new int[N];
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());

        for(int i = 0; i < N; i++){
            configuration[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        int max = 0;

        for(int i = 0; i < N; i++){
            if(configuration[i] == 1)
                max++;
        }

        int min = 0;
        for(int i = 0; i < N; ){
            if(configuration[i] == 1){
                min++;
                i += 3;
            }else
                i++;
        }

        bufferedWriter.write(min + " " + max + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }
}
