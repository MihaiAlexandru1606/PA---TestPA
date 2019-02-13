package testpa.y2017B;
/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v2-meeseeks/challenges/test-2-arborele-filogenetic-medie
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ArboreleFilogenetic {

    private static class Node {
        private int node;
        private int parrent;
        private ArrayList<Node> children;

        public Node(int _node) {
            this.node = _node;
            this.parrent = -1;
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine().trim());
        Node[] nodes = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }


        for (int i = 1; i <= N; i++) {
            int p = Integer.parseInt(bufferedReader.readLine().trim());
            if (p == -1) {
                continue;
            }
            nodes[i].parrent = p;
            nodes[p].children.add(nodes[i]);
        }

        int rez = Integer.MIN_VALUE;
        Queue<Node> queue = new LinkedList<>();
        int[] dist = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            if(nodes[i].parrent == -1){
                rez = Math.max(rez, bfs(nodes[i], queue, dist));
            }
        }

        bufferedWriter.write(rez + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static int bfs(Node node, Queue<Node> queue, int[] dist) {
        int r = 1;
        queue.add(node);
        dist[node.node] = 1;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Node node1 : current.children) {
                dist[node1.node] =  dist[current.node] + 1;
                r = Math.max(r, dist[node1.node]);
                queue.add(node1);
            }
        }

        return r;
    }
}
