package com.algospot.quantization;

import java.util.*;

public class Quantization {
  static int n;
  static int[] A;
  static int[] pSum;
  static int[] pSqSum;
  static int[][] cache;
  static final int INF = 987654321;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      n = scanner.nextInt();
      int s = scanner.nextInt();
      A = new int[n];
      pSum = new int[n];
      pSqSum = new int[n];

      for (int i = 0; i < A.length; ++i) {
        A[i] = scanner.nextInt();
      }

      initCache();

      // A 정렬 및 부분합 계산
      Arrays.sort(A);
      pSum[0] = A[0];
      pSqSum[0] = A[0] * A[0];

      for (int i = 1; i < n; ++i) {
        pSum[i] = pSum[i - 1] + A[i];
        pSqSum[i] = pSqSum[i - 1] + (A[i] * A[i]);
      }

      int result = quantize(0, s);

      System.out.println(result);
    }
    scanner.close();
  }

  public static void initCache() {
    cache = new int[101][11];
    for (int i = 0; i < 101; ++i) {
      for (int j = 0; j < 11; ++j) {
        cache[i][j] = -1;
      }
    }
  }

  // A[lower] ~ A[higher] 구간을 하나의 숫자로 표현할 때 최소 오차 합을 계산
  public static int minError(int lower, int higher) {
    int sum = pSum[higher] - (lower == 0 ? 0 : pSum[lower - 1]);
    int sqSum = pSqSum[higher] - (lower == 0 ? 0 : pSqSum[lower - 1]);
    int m = (int) (0.5 + (double)sum / (higher - lower + 1));
    return sqSum - 2 * m * sum + m * m * (higher - lower + 1);
  }

  public static int quantize(int from, int parts) {
    // 모두 양자화 했을때
    if (from == n) {
      return 0;
    }
    // 숫자는 남았는데 더 묶을 숫자가 없을때 큰값 반환
    if (parts == 0) {
      return INF;
    }

    if (cache[from][parts] != -1) {
      return cache[from][parts];
    }

    cache[from][parts] = INF;

    for (int partSize = 1; from + partSize <= n; ++partSize) {
      cache[from][parts] = Math.min(cache[from][parts],
          minError(from, from + partSize - 1) + quantize(from + partSize, parts - 1));
    }
    return cache[from][parts];
  }
}
