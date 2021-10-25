package com.algospot.lis;

import java.util.*;

public class Lis {
  static int n;
  static List<Integer> a;
  static int[] cache;
  static int[] choices;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      n = scanner.nextInt();
      a = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        a.add(scanner.nextInt());
      }

      initCache();
      int result = lis4(-1) - 1;
      List<Integer> seq = new ArrayList<>();
      reconstruct(-1, seq);
      System.out.println(result);
    }
    scanner.close();
  }

  public static void initCache() {
    cache = new int[101];
    choices = new int[101];
    for (int i = 0; i < 101; ++i) {
      cache[i] = -1;
      choices[i] = -1;
    }
  }

//  public static int lis(List<Integer> a) {
//    if (a.isEmpty()) {
//      return 0;
//    }
//    int result = 0;
//    for (int i = 0; i < a.size(); ++i) {
//      List<Integer> b = new ArrayList<>();
//      for (int j = i + 1; j < a.size(); ++j) {
//        if (a.get(i) < a.get(j)) {
//          b.add(a.get(j));
//        }
//      }
//      result = Math.max(result, 1 + lis(b));
//    }
//    return result;
//  }

//  public static int lis2(int start) {
//    if (cache[start] != -1) {
//      return cache[start];
//    }
//    // 항상 a[start]는 있기 때문에 길이 최하 1
//    cache[start] = 1;
//    for (int next = start + 1; next < n; ++next) {
//      if (a.get(start) < a.get(next)) {
//        cache[start] = Math.max(cache[start], lis2(next) + 1);
//      }
//    }
//    return cache[start];
//  }

//  public static int lis3(int start) {
//    if (cache[start + 1] != -1) {
//      return cache[start + 1];
//    }
//    // 항상 a[start]는 있기 때문에 길이 최하 1
//    cache[start + 1] = 1;
//    for (int next = start + 1; next < n; ++next) {
//      if (start == -1 || a.get(start) < a.get(next)) {
//        cache[start + 1] = Math.max(cache[start + 1], lis3(next) + 1);
//      }
//    }
//    return cache[start + 1];
//  }

  public static int lis4(int start) {
    if (cache[start + 1] != -1) {
      return cache[start + 1];
    }
    // 항상 a[start]는 있기 때문에 길이 최하 1
    cache[start + 1] = 1;
    int bestNext = -1;
    for (int next = start + 1; next < n; ++next) {
      if (start == -1 || a.get(start) < a.get(next)) {
        int cand = lis4(next) + 1;
        if (cand > cache[start + 1]) {
          cache[start + 1] = cand;
          bestNext = next;
        }
      }
    }
    choices[start + 1] = bestNext;
    return cache[start + 1];
  }

  public static void reconstruct(int start, List<Integer> seq) {
    if (start != -1) {
      seq.add(a.get(start));
    }
    int next = choices[start + 1];
    if (next != -1) {
      reconstruct(next, seq);
    }
  }
}
