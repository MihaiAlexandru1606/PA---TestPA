package testpa.y2018;

/**
 * Problema PA
 * https://www.hackerrank.com/contests/test-practic-pa-2018-v1-deadbeef/challenges/test-1-3-1-plimbare-grea/problem
 */

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Plimbare {

    private static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int N, M, D;

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        D = Integer.parseInt(stringTokenizer.nextToken());
        long[][] grid = new long[N][M];

        int Gx, Gy, Ix, Iy;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        Gx = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        Gy = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        Ix = Integer.parseInt(stringTokenizer.nextToken()) - 1;
        Iy = Integer.parseInt(stringTokenizer.nextToken()) - 1;

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Long.parseLong(stringTokenizer.nextToken());
            }
        }

        long win = Long.MIN_VALUE;
        long[] maxWinGigel = bfs(grid, Gx, Gy, D);
        long[] maxWinIon = bfs(grid, Ix, Iy, D);

        for(int i = 1;i <= D; i++){
            maxWinGigel[i] = Math.max(maxWinGigel[i], maxWinGigel[i - 1]);
            maxWinIon[i] = Math.max(maxWinIon[i], maxWinIon[i - 1]);
        }

        for (int i = 0; i <= D; i++) {
            int j = D - i;
            win = Math.max(win, maxWinGigel[i] + maxWinIon[j]);

        }

        bufferedWriter.write(win + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static long[] bfs(long[][] grid, int Sx, int Sy, int D) {
        long[] maxWin = new long[D + 1];
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        int[][] dist = new int[grid.length][grid[0].length];
        boolean[][] visted = new boolean[grid.length][grid[0].length];
        dist[Sx][Sy] = 0;
        int N = grid.length;
        int M = grid[0].length;

        queue.add(new Pair<Integer, Integer>(Sx, Sy));
        Arrays.fill(maxWin, Long.MIN_VALUE);

        int[] dx = {1, 0, -1, 0}; /** pentru deplasarea pe Ox*/
        int[] dy = {0, 1, 0, -1}; /** pentru deplasarea pe Oy*/

        BFS:
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> current = queue.poll();
            int x = current.key;
            int y = current.value;
            maxWin[dist[x][y]] = Math.max(maxWin[dist[x][y]], grid[x][y]);

            for (int i = 0; i < 4; i++) {
                int new_x = x + dx[i];
                int new_y = y + dy[i];

                /** daca este o pozitie valida */
                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {

                    if (visted[new_x][new_y] == false) {
                        dist[new_x][new_y] = dist[x][y] + 1;
                        visted[new_x][new_y] = true;
                        if (dist[new_x][new_y] > D)
                            break BFS;
                        queue.add(new Pair<Integer, Integer>(new_x, new_y));
                    }
                }
            }
        }
        return maxWin;
    }
}