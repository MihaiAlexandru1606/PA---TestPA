package testpa.y2017A;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v1-plumbus/challenges/test-1-latveria/problem
 */

import java.io.*;
import java.util.StringTokenizer;

public class Latveria {
    public static void main(String[] args) throws IOException {
        int N, K;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());

            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        long sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                sum += dist[i][j];
            }
        }

        for (int k = 0; k < K; k++) {
            int u, v, cost;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            u = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            v = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            cost = Integer.parseInt(stringTokenizer.nextToken());

            if (dist[u][v] > cost) {
                sum = sum - dist[u][v] + cost;
                dist[u][v] = cost;
                dist[v][u] = cost;

                for (int i = 0; i < N; i++) {
                    for (int j = i + 1; j < N; j++) {

                        if (dist[i][j] > dist[u][v] + dist[i][u] + dist[j][v]) {

                            sum = sum - dist[i][j] + dist[u][v] + dist[i][u] + dist[j][v];
                            dist[i][j] = dist[u][v] + dist[i][u] + dist[j][v];
                            dist[j][i] = dist[u][v] + dist[i][u] + dist[j][v];

                        }

                        if (dist[i][j] > dist[u][v] + dist[j][u] + dist[i][v]){
                            sum = sum - dist[i][j] + dist[u][v] +  dist[j][u] + dist[i][v];
                            dist[i][j] = dist[u][v] + dist[j][u] + dist[i][v];
                            dist[j][i] = dist[u][v] + dist[j][u] + dist[i][v];
                        }
                    }
                }

            }

            bufferedWriter.write(sum + " ");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
