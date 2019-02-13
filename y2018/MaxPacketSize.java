package testpa.y2018;

/**
 * Problema PA
 * https://www.hackerrank.com/contests/test-practic-pa-2018-v1-deadbeef/challenges/test-1-2-2-max-packet-size-medie
 */

import java.io.*;
import java.util.*;

public class MaxPacketSize {

    private static class Edge{
        private int nodeDest;
        private int cost;

        public Edge(int nodeDest, int cost) {
            this.nodeDest = nodeDest;
            this.cost = cost;
        }
    }

    private static class Retea{
        private int start;
        private int end;
        private long max_packet_size;

        public Retea(int start, int end, long max_packet_size) {
            this.start = start;
            this.end = end;
            this.max_packet_size = max_packet_size;
        }
    }

    private static boolean[] visted;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int numberVertex = Integer.parseInt(stringTokenizer.nextToken());
        int numberEdges = Integer.parseInt(stringTokenizer.nextToken());
        ArrayList<Retea> reteas = new ArrayList<>();
        @SuppressWarnings("unchecked")
        ArrayList<Edge>[] graph = new ArrayList[numberVertex + 1];
        for(int i = 1; i <= numberVertex; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i < numberEdges; i++){
            int u, v, cost;
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());

            u = Integer.parseInt(stringTokenizer.nextToken());
            v = Integer.parseInt(stringTokenizer.nextToken());
            cost = Integer.parseInt(stringTokenizer.nextToken());
            graph[u].add(new Edge(v, cost));
        }

        visted = new boolean[numberVertex + 1];
        ArrayList<Integer> topSort = topSort(graph);

        Arrays.fill(visted, false);
        for(int i : topSort){
            if(visted[i] == false){
                Stack<Integer> stack = new Stack<>();
                long[] maxPackedSize = new long[1];
                maxPackedSize[0] = Long.MAX_VALUE;

                findMaxPackedSize(i, graph, stack, maxPackedSize);
                int end = stack.pop();
                if(end == i)
                    maxPackedSize[0] = 0;

                reteas.add(new Retea(i, end, maxPackedSize[0]));

            }
        }

        reteas.sort(new Comparator<Retea>() {
            @Override
            public int compare(Retea o1, Retea o2) {
                return o1.start - o2.start;
            }
        });

        bufferedWriter.write(reteas.size() + "\n");
        for(Retea retea : reteas){
            bufferedWriter.write(retea.start + " " + retea.end + " " + retea.max_packet_size + "\n");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void dfs(int current, ArrayList<Edge>[] graph, Stack<Integer> stack){
        visted[current] = true;

        for(Edge edge : graph[current]){
            int v = edge.nodeDest;

            if(visted[v] == false){
                dfs(v, graph, stack);
            }
        }

        stack.push(current);
    }

    private static ArrayList<Integer> topSort(ArrayList<Edge>[] graph){
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> topsort = new ArrayList<>();

        for(int i = 1; i < graph.length; i++ ){
            if(visted[i] == false){
                dfs(i, graph, stack);
            }
        }

        while (!stack.isEmpty()){
            topsort.add(stack.pop());
        }

        return topsort;
    }

    private static void findMaxPackedSize(int current, ArrayList<Edge>[] graph, Stack<Integer> stack,
                                          long[] maxPackedSize){

        visted[current] = true;
        stack.push(current);

        for(Edge edge : graph[current]){
            int v = edge.nodeDest;
            int cost = edge.cost;

            if(visted[v] == false){
                maxPackedSize[0] = Math.min(maxPackedSize[0], cost);

                findMaxPackedSize(v, graph, stack, maxPackedSize);
            }
        }

    }
}
