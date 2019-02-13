package testpa.y2016_1A;

/**
 * https://www.hackerrank.com/contests/pa2016-test-1-varianta-1-sasuke/challenges/acs-impact-problema-usoara
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ACSImpact {

    private static class Probe implements Comparable<Probe> {
        private int minPoints;
        private int finalPoints;

        public Probe(int minPoints, int finalPoints) {
            this.minPoints = minPoints;
            this.finalPoints = finalPoints;
        }

        @Override
        public int compareTo(Probe o) {
            if (this.minPoints == o.minPoints) {
                return this.finalPoints - o.finalPoints;
            } else
                return this.minPoints - o.minPoints;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        int Pstart = Integer.parseInt(stringTokenizer.nextToken());
        int Pend = Integer.parseInt(stringTokenizer.nextToken());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        ArrayList<Probe> probes1 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine().trim());
            Probe probe = new Probe(Integer.parseInt(stringTokenizer.nextToken()),
                    Integer.parseInt(stringTokenizer.nextToken()));
            if (probe.finalPoints > Pstart && probe.finalPoints > probe.minPoints) {
                probes1.add(probe);
            }
        }

        Probe[] probes = new Probe[probes1.size()];
        for (int i = 0; i < probes1.size(); i++) {
            probes[i] = probes1.get(i);
        }

        Arrays.sort(probes);
        int count = 0;

        int max = -1;
        N = probes.length;
        for (int i = 0; i < probes.length; i++) {
            if (Pstart >= Pend) {
                break;
            }

            if (i != (N - 1) && probes[i + 1].minPoints > Pstart) {
                max = Math.max(max, probes[i].finalPoints);
                Pstart = max;
                count++;
                max = -1;
            } else {
                max = Math.max(max, probes[i].finalPoints);
            }

            if (i == N - 1) {
                Pstart = probes[i].finalPoints;
                count++;
            }
        }

        bufferedWriter.write(count + "\n");
        bufferedReader.close();
        bufferedWriter.close();
    }

}
