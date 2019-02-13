package testpa.y2017A;
/**
 * https://www.hackerrank.com/contests/test-practic-pa-2017-v1-plumbus/challenges/test-1-secvente
 */

import java.util.Scanner;

public class Secvente {
    public static int modPower(int base, int exponent, int mod) {
        if (exponent == 0)
            return 1;

        if (exponent == 1)
            return base;

        if (exponent % 2 == 0) {
            double rezult = modPower(base, exponent / 2, mod);
            rezult = rezult * rezult;
            rezult = rezult % mod;
            return (int) rezult;
        } else {
            double rezult = modPower(base, exponent / 2, mod);
            rezult = rezult * rezult;
            rezult = rezult % mod;
            rezult = rezult * base;
            rezult = rezult % mod;
            return (int) rezult;
        }

    }

    public static int fastPower(int base, int exponent, int mod) {
        return modPower(base % mod, exponent, mod);
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int MOD = 666013;
        long power = fastPower(M - 1, N - 1, MOD); /** pentru (M - 1) ^ (N - 1) */
        long a; /** din formula modulo */
        int b;

        if (N % 2 == 1) {
            a = power + ( (M - 1) % MOD );
            b = M;
        } else {
            a = (power - ( (M - 1) % MOD ) + MOD ) % MOD;
            b = M;
        }

        /** a/b % MOD = ( a % MOD ) * ( b ^ (MOD - 2) % MOD) % MOD */
        long rez;

        rez = ((a % MOD) * (fastPower(b, MOD - 2, MOD) % MOD )) % MOD;
        System.out.println(rez);

    }
}
