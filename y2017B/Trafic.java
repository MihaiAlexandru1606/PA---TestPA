package testpa.y2017B;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v2-meeseeks/challenges/test-2-3-2-trafic-grea/problem
 */

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Trafic {

    private static class Edge {
        private int source;
        private int dest;
        private int cost;

        public Edge(int source, int dest, int cost) {
            this.source = source;
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int numberVertex, numberEdge;
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        numberVertex = Integer.parseInt(stringTokenizer.nextToken());
        numberEdge = Integer.parseInt(stringTokenizer.nextToken());
        Edge[] edges = new Edge[numberEdge];

        for (int i = 0; i < numberEdge; i++) {
            int u, v, cost;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            u = Integer.parseInt(stringTokenizer.nextToken());
            v = Integer.parseInt(stringTokenizer.nextToken());
            cost = Integer.parseInt(stringTokenizer.nextToken());

            edges[i] = new Edge(u, v, cost);
        }

        long[] dist = new long[numberVertex + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        for (int i = 0; i <= numberVertex; i++) {
            for (int j = 0; j < numberEdge; j++) {
                int u = edges[j].source;
                int v = edges[j].dest;
                int cost = edges[j].cost;

                if (dist[v] > dist[u] + cost) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        long[] d = new long[numberVertex + 1];
        for (int i = 1; i <= numberVertex; i++) {
            d[i] = dist[i];
        }


        for (int j = 0; j < numberEdge; j++) {
            int u = edges[j].source;
            int v = edges[j].dest;
            int cost = edges[j].cost;

            if (d[v] > d[u] + cost) {
                d[v] = d[u] + cost;
            }
        }


        for (int i = 2; i <= numberVertex; i++) {
            if (dist[i] == d[i]) {
                bufferedWriter.write(d[i] + "\n");
                continue;
            }

            bufferedWriter.write("-INF\n");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
