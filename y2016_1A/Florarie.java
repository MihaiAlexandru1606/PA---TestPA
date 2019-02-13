package testpa.y2016_1A;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-1-sasuke/challenges/florarie-problema-usoara
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Florarie {
    private static class Elem implements Comparable<Elem> {
        private int x, y;
        private int v;

        public Elem(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }


        @Override
        public int compareTo(Elem o) {
            return this.v - o.v;
        }
    }

    private static int N;
    private static int M;
    private static Elem[] array;

    private static int find(int low, int hight, int v) {
        int mid = low + (hight - low) / 2;

        if (array[mid].v == v) {
            return mid;
        }

        if (mid == N - 2) {
            if (array[N - 1].v > v) {
                return N - 2;
            } else
                return N - 1;
        }

        if (mid == 0) {
            if (array[1].v > v) {
                return 0;
            } else
                return 1;
        }

        if (array[mid + 1].v == v) {
            return mid + 1;
        }

        if (array[mid - 1].v == v) {
            return mid - 1;
        }

        if (array[mid].v < v && v < array[mid + 1].v) {
            return mid;
        }

        if (array[mid - 1].v < v && v < array[mid].v) {
            return mid;
        }

        if (array[mid].v > v) {
            return find(low, mid - 1, v);
        } else
            return find(mid + 1, hight, v);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        array = new Elem[N * M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(stringTokenizer.nextToken());
                array[i * M + j] = new Elem(i, j, v);
                //System.out.println(i + " " + j + " " + v);
            }
        }
        Arrays.sort(array);

        int K = Integer.parseInt(bufferedReader.readLine().trim());
        for (int i = 0; i < K; i++) {
            int v = Integer.parseInt(bufferedReader.readLine().trim());
            int index = find(0, N * M - 1, v);
            if (array[index].v > v) {
                do {
                    index--;
                } while (array[index].v > v);
            }
            bufferedWriter.write(array[index].x + " " + array[index].y + "\n");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
