package testpa.y2016_1B;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-2-goku/challenges/paralelipipede
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Paralelipipede {
    private static class Parallelepiped {
        private int x;
        private int y;
        private int z;

        public Parallelepiped(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static Parallelepiped[] parallelepipeds;
    private static int[] dp;
    private static int[] parent;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int tip = Integer.parseInt(bufferedReader.readLine().trim());
        N = Integer.parseInt(bufferedReader.readLine().trim());
        parallelepipeds = new Parallelepiped[N];

        for(int i = 0; i < N; i++){
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            int x, y, z;
            x = Integer.parseInt(stringTokenizer.nextToken());
            y = Integer.parseInt(stringTokenizer.nextToken());
            z = Integer.parseInt(stringTokenizer.nextToken());
            parallelepipeds[i] = new Parallelepiped(x, y, z);
        }

        dp = new int[N];
        parent = new int[N];
        Arrays.fill(parent, -1);
        Arrays.fill(dp , 1);

        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        int[] index = new int[1];

        computeDynamic(max, index);
        bufferedWriter.write(max[0] + "\n");

        if(tip == 1){
            ArrayList<Integer> path = createPath(index[0]);
            for(int i : path){
                bufferedWriter.write(i + " ");
            }

            bufferedWriter.write("\n");
        }

        bufferedReader.close();
        bufferedWriter.close();
    }

    private static boolean check(Parallelepiped paralelipipede1, Parallelepiped paralelipipede2) {
        if (paralelipipede1.x <= paralelipipede2.x &&
                paralelipipede1.y <= paralelipipede2.y &&
                paralelipipede1.z >= paralelipipede2.z) {
            return true;
        }

        return false;
    }

    private static void computeDynamic(int[] max, int[] index){

        for(int i = 1; i < N; i++){
            for(int j = 0; j < i; j++){
                if(check(parallelepipeds[j], parallelepipeds[i]) && dp[j] + 1 > dp[i]){
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }

            if(dp[i] > max[0]){
                max[0] = dp[i];
                index[0] = i;
            }
        }
    }

    private static ArrayList<Integer> createPath(int index){
        ArrayList<Integer> path = new ArrayList<>();
        int v = parent[index];
        path.add(index);

        while (v != -1){
            path.add(0, v);
            v = parent[v];
        }

        return path;
     }
}
