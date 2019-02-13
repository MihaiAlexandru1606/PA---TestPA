package testpa.y2016_2A;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2-illidan/challenges/topsort-problema-usoara
 */

import java.io.*;
import java.util.*;

public class TopSort {

    private static class Vertex {
        private int node;
        private int v;

        public Vertex(int node, int v) {
            this.node = node;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int numberVertex = Integer.parseInt(stringTokenizer.nextToken());
        int numberEdge = Integer.parseInt(stringTokenizer.nextToken());
        Vertex[] vertices = new Vertex[numberVertex + 1];

        for (int i = 1; i <= numberVertex; i++) {
            vertices[i] = new Vertex(i , Integer.parseInt(bufferedReader.readLine().trim()));
        }

        int[] in_grad = new int[numberVertex + 1];
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[numberVertex + 1];
        for (int i = 1; i <= numberVertex; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < numberEdge; i++) {
            int u, v;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            u = Integer.parseInt(stringTokenizer.nextToken());
            v = Integer.parseInt(stringTokenizer.nextToken());

            graph[u].add(v);
            in_grad[v]++;
        }

        TreeSet<Vertex> queue = new TreeSet<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                if (o1.v == o2.v) {
                    return o1.node - o2.node;
                }

                return o1.v - o2.v;
            }
        });

        for (int i = 1; i <= numberVertex; i++) {
            if (in_grad[i] == 0) {
                queue.add(vertices[i]);
            }
        }

        int[] top_list = new int[numberVertex];
        int index = 0;

        while (!queue.isEmpty()) {
            Vertex c = queue.pollFirst();
            int current = c.node;
            top_list[index] = current;
            index++;

            for (int i : graph[current]) {
                in_grad[i]--;

                if (in_grad[i] == 0) {
                    queue.add(vertices[i]);
                }
            }
        }

        for (int i = 0; i < numberVertex; i++) {
            bufferedWriter.write(top_list[i] + " ");
        }
        bufferedWriter.newLine();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
