package testpa.y2017A;
/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v1-plumbus/challenges/test-1-2-2-medii/problem
 */
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Labirint2017 {
    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());
        char[][] map = new char[N][M];
        int[][] dist = new int[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().trim().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'G') {
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    dist[i][j] = 0;
                }
            }
        }

        int[] dx = {1, 0, -1, 0}; /** pentru deplasarea pe Ox*/
        int[] dy = {0, 1, 0, -1}; /** pentru deplasarea pe Oy*/


        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int new_x = x + dx[i];
                int new_y = y + dy[i];

                if (new_x == -1) {
                    new_x = N - 1;
                }
                if (new_x == N) {
                    new_x = 0;
                }

                if (new_y == -1) {
                    new_y = M - 1;
                }
                if (new_y == M) {
                    new_y = 0;
                }

                if (visited[new_x][new_y] == false) {
                    if (map[new_x][new_x] == '.') {
                        dist[new_x][new_y] = dist[x][y] + 1;
                        visited[new_x][new_y] = true;
                        queue.add(new Point(new_x, new_y));
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bufferedWriter.write(dist[i][j] + " ");
            }
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
