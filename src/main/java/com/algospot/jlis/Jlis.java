package com.algospot.jlis;

import java.util.*;

public class Jlis {
  static int n, m;
  static List<Integer> A;
  static List<Integer> B;
  static int[][] cache;
  static final long NEGINF = Long.MIN_VALUE;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      n = scanner.nextInt();
      m = scanner.nextInt();
      A = new ArrayList<>();
      B = new ArrayList<>();

      for (int i = 0; i < n; ++i) {
        A.add(scanner.nextInt());
      }
      for (int i = 0; i < m; ++i) {
        B.add(scanner.nextInt());
      }

      initCache();
      int result = jlis(-1, -1) - 2;

      System.out.println(result);
    }
    scanner.close();
  }

  public static void initCache() {
    cache = new int[101][101];
    for (int i = 0; i < 101; ++i) {
      for (int j = 0; j < 101; ++j) {
        cache[i][j] = -1;
      }
    }
  }

  public static int jlis(int indexA, int indexB) {
    if (cache[indexA + 1][indexB + 1] != -1) {
      return cache[indexA + 1][indexB + 1];
    }

    cache[indexA + 1][indexB + 1] = 2;
    long a = (indexA == -1 ? NEGINF : A.get(indexA));
    long b = (indexB == -1 ? NEGINF : B.get(indexB));
    long maxElement = Math.max(a, b);

    for (int nextA = indexA + 1; nextA < n; ++nextA) {
      if (maxElement < A.get(nextA)) {
        cache[indexA + 1][indexB + 1] = Math.max(cache[indexA + 1][indexB + 1], jlis(nextA, indexB) + 1);
      }
    }
    for (int nextB = indexB + 1; nextB < m; ++nextB) {
      if (maxElement < B.get(nextB)) {
        cache[indexA + 1][indexB + 1] = Math.max(cache[indexA + 1][indexB + 1], jlis(indexA, nextB) + 1);
      }
    }

    return cache[indexA + 1][indexB + 1];
  }
}
