package testpa.y2016_2B;

/**
 * https://www.hackerrank.com/contests/test-practic-pa-2-malfurion/challenges/labirint-usoara
 */

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Labirint {

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
        Point startPoint = null;
        ArrayList<Point> portals = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            map[i] = bufferedReader.readLine().trim().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    startPoint = new Point(i, j);
                }

                if (map[i][j] == 'P') {
                    portals.add(new Point(i, j));
                }
            }
        }

        int[] dx = {1, 0, -1, 0}; /** pentru deplasarea pe Ox*/
        int[] dy = {0, 1, 0, -1}; /** pentru deplasarea pe Oy*/

        boolean findPath = false;
        queue.add(startPoint);
        BFS:
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int new_x = x + dx[i];
                int new_y = y + dy[i];

                /** daca este o pozitie valida */
                if (new_x >= 0 && new_x < N && new_y >= 0 && new_y < M) {
                    if(visited[new_x][new_y] == false){
                        if(map[new_x][new_y] == 'P'){
                            for(Point point : portals){
                                visited[point.x][point.y] = true;
                                dist[point.x][point.y] = dist[x][y] + 1;
                                queue.add(point);
                            }
                        }

                        if(map[new_x][new_y] == 'D'){
                            dist[new_x][new_y] = dist[x][y] + 1;
                            bufferedWriter.write(dist[new_x][new_y] + "\n");
                            findPath = true;
                            break BFS;
                        }

                        if(map[new_x][new_y] == 'L'){
                            dist[new_x][new_y] = dist[x][y] + 1;
                            visited[new_x][new_y] = true;
                            queue.add(new Point(new_x, new_y));
                        }
                    }
                }
            }
        }

        if(findPath == false){
            bufferedWriter.write("-1");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}