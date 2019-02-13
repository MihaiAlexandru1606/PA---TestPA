package testpa.y2016_2A;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2-illidan/challenges/unique-edge-problema-usoara
 */

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UniqueEdge {

    private static class Disjcnt {
        /**
         * Clasa interana pentru retinerea muchilor
         * dest -> nodul destinatie
         * remove -> daca a fost eliminata de algorimul lui tarjan pentru aflarea puntilor
         * twin -> "geamana" sa, pentru (1, 2) , fiind un graf neorientat, avem muchie de la 1 la 2
         * si muchie de la 2 la 1, geamana lui 1 -> 2 este 2 -> 1 si invers
         */
        private static class Edge {
            private int dest;
            private boolean remove;
            private Edge twin;

            public Edge(int dest) {
                this.dest = dest;
                this.remove = false;
                this.twin = null;
            }

            private int getDest() {
                return dest;
            }

            private boolean isRemove() {
                return remove;
            }

            private void setRemove(boolean remove) {
                this.remove = remove;
            }

            private Edge getTwin() {
                return twin;
            }

            private void setTwin(Edge twin) {
                this.twin = twin;
            }
        }

        /**
         * folosit pentru algoritmul lui Tarjan descri in lab-08
         */
        private static int[] timeDiscover;
        private static int[] low;
        private static boolean[] visited;
        private static int[] parent;
        private static int time = 0;

        /**
         * dfs utilizat in algoritmul lui tarjan
         *
         * @param current nodul curent
         * @param graph   graful
         */
        private static void dfs_util_tarjan(int current, ArrayList<Edge>[] graph) {
            timeDiscover[current] = time;
            low[current] = time;
            time++;
            visited[current] = true;

            for (Edge neighbour : graph[current]) {
                int i = neighbour.getDest();

                if (visited[i] == false) {
                    parent[i] = current;
                    dfs_util_tarjan(i, graph);

                    low[current] = Math.min(low[i], low[current]);

                    if (timeDiscover[current] < low[i]) {
                        /**se gasescte muchie ca fiind o punte si se macheza ca si cum nu ar mai
                         * exisata
                         */
                        neighbour.setRemove(true);
                        neighbour.getTwin().setRemove(true);
                    }

                } else if (i != parent[current]) {
                    low[current] = Math.min(low[current], timeDiscover[i]);
                }
            }
        }

        private static void tarjan(int numberVertex, ArrayList<Edge>[] graph) {
            for (int i = 1; i <= numberVertex; i++) {
                if (visited[i] == false)
                    dfs_util_tarjan(i, graph);
            }

        }

        /**
         * dfs care numara cate noduri sunt conectate cu current, se folosesc decat muchile care nu
         * au fost marcate ca fiind eliminate de tarjan, care nu sunt punti, mai multe explicate in
         * readme
         *
         * @param current nodul curent pentru dfs
         * @param graph   garul
         */
        private static void dfs(int current, ArrayList<Edge>[] graph) {
            visited[current] = true;

            for (Edge neighbour : graph[current]) {
                int v = neighbour.getDest();
                if (visited[v] == false && neighbour.isRemove() == false) {
                    dfs(v, graph);
                }
            }
        }

        public static boolean isUnique(BufferedReader bufferedReader) throws IOException {
            int numberVertex, numberEdge;
            StringTokenizer stringTokenizer1 = new StringTokenizer(bufferedReader.readLine());
            numberVertex = Integer.parseInt(stringTokenizer1.nextToken());
            numberEdge = Integer.parseInt(stringTokenizer1.nextToken());

            @SuppressWarnings("unchecked")
            ArrayList<Edge>[] graph = new ArrayList[numberVertex + 1];
            for (int i = 1; i <= numberVertex; i++) {
                graph[i] = new ArrayList<>();
            }
            /** citirea garfului */
            for (int i = 1; i <= numberEdge; i++) {
                int u, v;
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
                u = Integer.parseInt(stringTokenizer.nextToken());
                v = Integer.parseInt(stringTokenizer.nextToken());
                Edge e = new Edge(v);
                Edge twinE = new Edge(u);
                e.setTwin(twinE);
                twinE.setTwin(e);

                graph[u].add(e);
                graph[v].add(twinE);
            }

            visited = new boolean[numberVertex + 1];
            parent = new int[numberVertex + 1];
            low = new int[numberVertex + 1];
            timeDiscover = new int[numberVertex + 1];
            tarjan(numberVertex, graph); /** eliminarea puntilor folosind tarjan */

            for (int i = 0; i <= numberVertex; i++) {
                visited[i] = false;
            }

            int u, v;
            u = Integer.parseInt(bufferedReader.readLine().trim());
            v = Integer.parseInt(bufferedReader.readLine().trim());
            dfs(u, graph);

            return !visited[v];
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int numberFiles = Integer.parseInt(bufferedReader.readLine().trim());

        for (int i = 0; i < numberFiles; i++) {
            if (Disjcnt.isUnique(bufferedReader)){
                bufferedWriter.write("1" + "\n");
            }else{
                bufferedWriter.write("0" + "\n");
            }
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
