package testpa.y2018;
/**
 * https://www.hackerrank.com/contests/test-practic-pa-2018-v1-deadbeef/challenges/test-1-3-2-teroristi-grea
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Teroristi {

    private static int[] timeDiscover;
    private static int[] low;
    private static boolean[] visited;
    private static int[] parent;
    private static int time = 0;
    private static boolean[] cutVertex;
    @SuppressWarnings("unchecked")
    private static ArrayList<Integer>[] graph;


    private static void DFS(int current) {
        timeDiscover[current] = time;
        low[current] = time;
        time++;

        visited[current] = true;
        int numberChild = 0;

        for (int i : graph[current]) {
            if (visited[i] == false) {
                numberChild++;
                parent[i] = current;
                DFS(i);

                low[current] = Math.min(low[i], low[current]);

                if (parent[current] == 0 && numberChild > 1)
                    cutVertex[current] = true;

                if (parent[current] != 0 && timeDiscover[current] <= low[i]) {
                    cutVertex[current] = true;
                }
            } else {
                low[current] = Math.min(low[current], timeDiscover[i]);
            }
        }
    }

    private static void DFS_normal(int current, ArrayList<Integer>[] graph, long[] number) {
        visited[current] = true;

        for (int v : graph[current]) {

            if (visited[v] == false) {
                DFS_normal(v, graph, number);
            }
        }

        number[0]++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int numberVertex, numberEdge;
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        numberVertex = Integer.parseInt(stringTokenizer.nextToken());
        numberEdge = Integer.parseInt(stringTokenizer.nextToken());

        graph = new ArrayList[numberVertex + 1];
        for (int i = 1; i <= numberVertex; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= numberEdge; i++) {
            int u, v;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            u = Integer.parseInt(stringTokenizer.nextToken());
            v = Integer.parseInt(stringTokenizer.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }

        timeDiscover = new int[numberVertex + 1];
        low = new int[numberVertex + 1];
        visited = new boolean[numberVertex + 1];
        parent = new int[numberVertex + 1];
        cutVertex = new boolean[numberVertex + 1];

        for (int i = 1; i <= numberVertex; i++) {
            if (visited[i] == false)
                DFS(i);
        }

        int index = -1;
        long decont = Long.MIN_VALUE;
        for (int i = 1; i <= numberVertex; i++) {

            if (cutVertex[i] == true) {
                for (int j = 0; j <= numberVertex; j++) {
                    visited[j] = false;
                }

                visited[i] = true;
                long rez = 0;
                ArrayList<Long> arrayList = new ArrayList<>();

                for (int k = 1; k <= numberVertex; k++) {
                    long[] number = new long[1];
                    if (visited[k] == false)
                        DFS_normal(k, graph, number);

                    if (number[0] != 0)
                        arrayList.add(number[0]);
                }

                for (int a = 0; a < arrayList.size() - 1; a++) {
                    for (int j = a + 1; j < arrayList.size(); j++) {
                        rez += arrayList.get(a) * arrayList.get(j);
                    }
                }

                if (decont < rez) {
                    index = i;
                    decont = rez;
                }

            }

        }


        System.out.println(index);
        System.out.println(decont);
    }
}
