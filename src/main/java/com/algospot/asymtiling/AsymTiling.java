package com.algospot.asymtiling;

import java.util.*;

public class AsymTiling {
  static int MOD = 1000000007;
  static int CACHE_SIZE = 101;
  static int[] cache = new int[CACHE_SIZE];

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    initCache();

    while (cases-- > 0) {
      int n = scanner.nextInt();

      int result = getSymCount(n);

      System.out.println(result);
    }
    scanner.close();
  }

  public static void initCache() {
    for (int i = 0; i < CACHE_SIZE; ++i) {
      cache[i] = -1;
    }
  }

  public static int getTotalCount(int n) {
    if (n <= 1) {
      return 1;
    }

    if (cache[n] != -1) {
      return cache[n];
    }

    cache[n] = (getTotalCount(n - 2) + getTotalCount(n - 1)) % MOD;
    return cache[n];
  }

  public static int getSymCount(int n) {
    if (n % 2 == 1) {
      return (getTotalCount(n) - getTotalCount(n/2) + MOD) % MOD;
    }
    int result = getTotalCount(n);
    result = (result - getTotalCount(n/2) + MOD) % MOD;
    result = (result - getTotalCount(n/2 - 1) + MOD) % MOD;
    return result;
  }
}
