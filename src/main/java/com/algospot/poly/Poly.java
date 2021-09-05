package com.algospot.poly;

import java.util.*;

public class Poly {
  static int MOD = 10000000;
  static int CACHE_SIZE = 101;
  static int[][] cache = new int[CACHE_SIZE][CACHE_SIZE];
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    initCache();
    while (cases-- > 0) {
      int n = scanner.nextInt();

      int result = 0;
      for (int i = 1; i <= n; ++i) {
        result += getPoly(n, i);
        result %= MOD;
      }

      System.out.println(result);
    }
    scanner.close();
  }

  public static void initCache() {
    for (int i = 0; i < CACHE_SIZE; ++i) {
      for (int j =0; j < CACHE_SIZE; ++j) {
        cache[i][j] = -1;
      }
    }
  }

  public static int getPoly(int n, int first) {
    if (n == first) {
      return 1;
    }

    if (cache[n][first] != -1) {
      return cache[n][first];
    }

    cache[n][first] = 0;
    for (int second = 1; second <= n-first; ++second) {
      int add = second + first - 1;
      add *= getPoly(n-first, second);
      add %= MOD;
      cache[n][first] += add;
      cache[n][first] %= MOD;
    }

    return cache[n][first];
  }
}
