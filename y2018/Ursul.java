package testpa.y2018;

/**
 * Problma de la PA
 * https://www.hackerrank.com/contests/test-practic-pa-2018-v1-deadbeef/challenges/test-1-2-1-ursul-medie/problem
 */

import graph.MinHeap;

import java.io.*;
import java.util.StringTokenizer;


public class Ursul {
    private static final int NUMBER_COL = 2;
    private static final int INF = 1 << 27;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int[][] grid = new int[NUMBER_COL][n];
        int numberBlock = 0;

        for (int i = 0; i < NUMBER_COL; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());

            for (int j = 0; j < n; j++) {
                grid[i][j] = Integer.parseInt(stringTokenizer.nextToken());
                if (grid[i][j] == -1) numberBlock++;
            }
        }

        bufferedWriter.write(findShortestPath(grid, 0, 0, 1, n - 1, numberBlock) + "\n");

        bufferedReader.close();
        bufferedWriter.close();

    }

    private static int findShortestPath(int[][] grid, int Sx, int Sy, int Fx, int Fy, int numberBlock) {
        int[][] dist = new int[grid.length][grid[0].length];
        MinHeap heap = new MinHeap(grid.length * grid[0].length + 1);
        heap.resize(numberBlock);
        int row = grid[0].length;
        int N = 2;
        int M = row;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == -1)
                    continue;

                if (i == Sx && j == Sy) {
                    dist[i][j] = grid[i][j];
                } else {
                    dist[i][j] = INF;
                }

                heap.add(i * row + j + 1, dist[i][j]);
            }
        }

        int[] dx = {1, 0, -1, 0}; /** pentru deplasarea pe Ox*/
        int[] dy = {0, 1, 0, -1}; /** pentru deplasarea pe Oy*/

        while (!heap.isEmpty()) {
            int node = heap.getMinNode() - 1;
            int x = node / row;
            int y = node % row;


            for (int i = 0; i < 4; i++) {
                int new_x = x + dx[i];
                int new_y = y + dy[i];

                /** daca este o pozitie valida */
                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {

                    if (heap.contains(new_x * row + new_y + 1)) {
                        /** se aplica relaxrea */
                        if (dist[new_x][new_y] > dist[x][y] + grid[new_x][new_y]) {
                            dist[new_x][new_y] = dist[x][y] + grid[new_x][new_y];
                            /**actualiazam noua distanta*/
                            heap.add(new_x * row + new_y + 1, dist[new_x][new_y]);
                        }

                    }
                }
            }
        }

        return dist[Fx][Fy];
    }

}