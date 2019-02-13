package testpa.y2016_1B;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-2-goku/challenges/salupa-problema-usoara
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Salupa {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        MyScanner scanner = new MyScanner();
        int G = scanner.nextInt();
        int N = scanner.nextInt();
        int[] g = new int[N];

        for (int i = 0; i < N; i++) {
            g[i] = scanner.nextInt();
        }

        Arrays.sort(g);
        int number = 0; // numarul de cufere
        int cuffer = 0; // greutatea cuferelor
        for (int greu : g) {
            if (cuffer + greu <= G) {
                number++;
                cuffer += greu;
            } else
                break;
        }

        System.out.print(number + "\n" + cuffer + "\n");

    }
}


// Folositi clasa aceasta ca sa cititi inputul mai rapid
class MyScanner {
    BufferedReader br;
    StringTokenizer st;

    public MyScanner() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while (st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return st.nextToken();
    }

    int nextInt() {
        return Integer.parseInt(next());
    }

    long nextLong() {
        return Long.parseLong(next());
    }

    double nextDouble() {
        return Double.parseDouble(next());
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
