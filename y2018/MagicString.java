package testpa.y2018;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2018-v1-deadbeef/challenges/test-1-1-2-sir-magic-usoara
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MagicString {

    public static void main(String args[]) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] input = bufferedReader.readLine().trim().toCharArray();

        for (int i = 0; i < input.length; i++) {
            if (i == 0 && input[i] == '_') {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j != input[1]) {
                        input[i] = j;
                        break;
                    }
                }
                continue;
            }

            if (input[i] == '_' && i != input.length - 1) {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j != input[i - 1] && j != input[i + 1]) {
                        input[i] = j;
                        break;
                    }
                }
                continue;
            }

            if (input[i] == '_' && i == input.length - 1) {
                for (char j = 'a'; j <= 'z'; j++) {
                    if (j != input[i - 1]) {
                        input[i] = j;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < input.length; i++) {
            bufferedWriter.write(input[i] + "");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}