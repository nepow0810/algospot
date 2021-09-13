package com.algospot.numb3rs;

import java.util.*;

public class Numb3rs {

  static int n, d, p, q;
  static double[][] cache;
  static int[][] connected;
  static int[] deg;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      n = scanner.nextInt();
      d = scanner.nextInt();
      p = scanner.nextInt();
      connected = new int[n][n];
      deg = new int[n];

      for (int i = 0; i < n; ++i) {
        int sum = 0;
        for (int j = 0; j < n; ++j) {
          connected[i][j] = scanner.nextInt();
          if (connected[i][j] > 0) {
            ++sum;
          }
        }
        deg[i] = sum;
      }

      int t = scanner.nextInt();
      for (int i = 0; i < t; ++i) {
        q = scanner.nextInt();
        initCache();
        System.out.printf("%1.8f ", search2(p, 0));
      }
      System.out.println();
    }
    scanner.close();
  }

  public static void initCache() {
    cache = new double[51][101];
    for (int i = 0; i < 51; ++i) {
      for (int j = 0; j < 101; ++j) {
        cache[i][j] = -1;
      }
    }
  }

  public static double search2(int here, int days) {
    if (days == d) {
      return here == q ? 1.0 : 0.0;
    }

    if (cache[here][days] > -0.5) {
      return cache[here][days];
    }

    cache[here][days] = 0.0;
    for (int there = 0; there < n; ++there) {
      if (connected[here][there] == 1) {
        cache[here][days] += search2(there, days+1) / deg[here];
      }
    }
    return cache[here][days];
  }

  // 처음 푼 방법
//  static int n, d, p;
//  static int[][] A;
//  static double[][] probability;
//  static int[] loadCount;
//
//  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    int cases = scanner.nextInt();
//    while (cases-- > 0) {
//      n = scanner.nextInt();
//      d = scanner.nextInt();
//      p = scanner.nextInt();
//      A = new int[n][n];
//      loadCount = new int[n];
//
//      for (int i = 0; i < n; ++i) {
//        int sum = 0;
//        for (int j = 0; j < n; ++j) {
//          A[i][j] = scanner.nextInt();
//          if (A[i][j] > 0) {
//            ++sum;
//          }
//        }
//        loadCount[i] = sum;
//      }
//
//      probability = new double[d+1][n];
//      calculateProbabilities();
//
//      int t = scanner.nextInt();
//      int[] q = new int[t];
//
//      for (int i = 0; i < t; ++i) {
//        q[i] = scanner.nextInt();
//      }
//
//      for (int i = 0; i < t; ++i) {
//        System.out.printf("%1.8f ", probability[d][q[i]]);
//      }
//      System.out.println();
//    }
//    scanner.close();
//  }
//
//  public static void calculateProbabilities() {
//    int currentTown = p;
//    probability[0][currentTown] = 1;
//    for (int i = 1; i <= d; ++i) {
//      for (int j = 0; j < n; ++j) {
//        if (probability[i - 1][j] > 0) {
//          for (int k = 0; k < n; ++k) {
//            if (A[j][k] > 0) {
//              probability[i][k] += (double) probability[i - 1][j] / loadCount[j];
//            }
//          }
//        }
//      }
//    }
//  }
}
