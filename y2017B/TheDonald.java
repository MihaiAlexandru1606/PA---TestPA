package testpa.y2017B;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v2-meeseeks/challenges/test-2-thedonald-usoara
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TheDonald {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int numberVertex, numberEdge;
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        numberVertex = Integer.parseInt(stringTokenizer.nextToken());
        numberEdge = Integer.parseInt(stringTokenizer.nextToken());

        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] graph = new ArrayList[numberVertex + 1];
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
        boolean[] visited= new boolean[numberVertex + 1];
        Queue<Integer> queue = new LinkedList<>();
        int ans = -1;
        for(int i = 1; i <= numberVertex; i++){
            if(visited[i] == false){
                //dfs(graph, i, visited);
                bfs(graph, i, visited, queue);
                ans++;
            }
        }

        bufferedWriter.write(ans + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void dfs(ArrayList<Integer>[] graph, int current, boolean[] visited){
        visited[current] = true;

        for(int i : graph[current]){
            if(visited[i] == false){
                dfs(graph, i, visited);
            }
        }
    }

    private static void bfs(ArrayList<Integer>[] graph, int current, boolean[] visited, Queue<Integer> queue){
        visited[current] = true;
        queue.add(current);

        while (!queue.isEmpty()){
            int c = queue.poll();

            for(int i : graph[c]){
                if(visited[i] == false){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
