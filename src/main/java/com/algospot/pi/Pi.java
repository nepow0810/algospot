package com.algospot.pi;

import java.util.*;

public class Pi {
  static String[] numberArray;
  static int[] cache = new int[10002];

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    scanner.nextLine();
    while (cases-- > 0) {
      numberArray = scanner.nextLine().trim().split("");
      initCache();

      int result = getDifficultySum(0);

      System.out.println(result);
    }
    scanner.close();
  }

  public static void initCache() {
    for (int i = 0; i < cache.length; ++i) {
      cache[i] = -1;
    }
  }

  public static int getDifficultySum(int startIndex) {

    if (startIndex == numberArray.length) {
      return 0;
    }

    if (cache[startIndex] != -1) {
      return cache[startIndex];
    }

    cache[startIndex] = 987654321;

    for (int i = 3; i <= 5; ++i) {
      if (startIndex + i <= numberArray.length) {
        cache[startIndex] = Math.min(cache[startIndex], getDifficultySum(startIndex + i) + classify(startIndex, startIndex + i));
      }
    }

    return cache[startIndex];
  }

  public static int classify(int startIndex, int endIndex) {
    if (isAllSameNumber(startIndex, endIndex)) {
      return 1;
    } else if (isIncreasingOrDecreasingByOne(startIndex, endIndex)) {
      return 2;
    } else if (isAlternatingNumber(startIndex, endIndex)) {
      return 4;
    } else if (isArithmeticalProgression(startIndex, endIndex)) {
      return 5;
    } else {
      return 10;
    }
  }

  public static boolean isAllSameNumber(int startIndex, int endIndex) {
    boolean result = true;
    String firstNumber = numberArray[startIndex];

    for (int i = startIndex; i < endIndex; ++i) {
      if (!firstNumber.equals(numberArray[i])) {
        result = false;
        break;
      }
    }
    return result;
  }

  public static boolean isIncreasingOrDecreasingByOne(int startIndex, int endIndex) {
    boolean result = true;
    int firstNumber = Integer.parseInt(numberArray[startIndex]);

    // 1씩 증가
    for (int i = 0; i < endIndex - startIndex; ++i) {
      if (firstNumber + i != Integer.parseInt(numberArray[startIndex + i])) {
        result = false;
        break;
      }
    }

    if (result) {
      return result;
    }

    result = true;

    // 1씩 감소
    for (int i = 0; i < endIndex - startIndex; ++i) {
      if (firstNumber - i != Integer.parseInt(numberArray[startIndex + i])) {
        result = false;
        break;
      }
    }

    return result;
  }

  public static boolean isAlternatingNumber(int startIndex, int endIndex) {
    boolean result = true;
    int judge = startIndex % 2;
    int firstNumber = Integer.parseInt(numberArray[startIndex]);
    int secondNumber = Integer.parseInt(numberArray[startIndex + 1]);

    for (int i = startIndex + 2; i < endIndex; ++i) {
      if (i % 2 == judge) {
        if (firstNumber != Integer.parseInt(numberArray[i])) {
          result = false;
          break;
        }
      } else {
        if (secondNumber != Integer.parseInt(numberArray[i])) {
          result = false;
          break;
        }
      }
    }

    return result;
  }

  public static boolean isArithmeticalProgression(int startIndex, int endIndex) {
    boolean result = true;
    int firstNumber = Integer.parseInt(numberArray[startIndex]);
    int secondNumber = Integer.parseInt(numberArray[startIndex + 1]);
    int minus = firstNumber - secondNumber;

    for (int i = startIndex + 2; i < endIndex; ++i) {
      if (minus != Integer.parseInt(numberArray[i - 1]) - Integer.parseInt(numberArray[i])) {
        result = false;
        break;
      }
    }

    return result;
  }
}


//public class Main {
//  static String[] numberArray;
//  static int[][] cache;
//
//  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    int cases = scanner.nextInt();
//    scanner.nextLine();
//    while (cases-- > 0) {
//      numberArray = scanner.nextLine().trim().split("");
//      initCache(numberArray.length);
//
//      int result = Math.min(getDifficultySum(0, 3),
//          Math.min(getDifficultySum(0, 4), getDifficultySum(0, 5)));
//
//      System.out.println(result);
//    }
//    scanner.close();
//  }
//
//  public static void initCache(int length) {
//    cache = new int[length + 1][length + 1];
//    for (int i = 0; i < length + 1; ++i) {
//      for (int j = 0; j < length + 1; ++j) {
//        cache[i][j] = Integer.MAX_VALUE;
//      }
//    }
//  }
//
//  public static int getDifficultySum(int startIndex, int endIndex) {
//
//    if (endIndex > numberArray.length || startIndex > numberArray.length) {
//      return Integer.MAX_VALUE;
//    }
//
//    if (cache[startIndex][endIndex] != Integer.MAX_VALUE) {
//      return cache[startIndex][endIndex];
//    }
//
//    int result = 0;
//
//    if (endIndex != numberArray.length) {
//      result  = Math.min(getDifficultySum(endIndex, endIndex + 3),
//          Math.min(getDifficultySum(endIndex, endIndex + 4),
//              getDifficultySum(endIndex, endIndex + 5)));
//    }
//
//    if (result == Integer.MAX_VALUE) {
//      return Integer.MAX_VALUE;
//    }
//
//    if (isAllSameNumber(startIndex, endIndex)) {
//      result += 1;
//    } else if (isIncreasingOrDecreasingByOne(startIndex, endIndex)) {
//      result += 2;
//    } else if (isAlternatingNumber(startIndex, endIndex)) {
//      result += 4;
//    } else if (isArithmeticalProgression(startIndex, endIndex)) {
//      result += 5;
//    } else {
//      result += 10;
//    }
//
//    cache[startIndex][endIndex] = result;
//    return result;
//  }
//
//  public static boolean isAllSameNumber(int startIndex, int endIndex) {
//    boolean result = true;
//    String firstNumber = numberArray[startIndex];
//
//    for (int i = startIndex; i < endIndex; ++i) {
//      if (!firstNumber.equals(numberArray[i])) {
//        result = false;
//        break;
//      }
//    }
//    return result;
//  }
//
//  public static boolean isIncreasingOrDecreasingByOne(int startIndex, int endIndex) {
//    boolean result = true;
//    int firstNumber = Integer.parseInt(numberArray[startIndex]);
//
//    // 1씩 증가
//    for (int i = 0; i < endIndex - startIndex; ++i) {
//      if (firstNumber + i != Integer.parseInt(numberArray[startIndex + i])) {
//        result = false;
//        break;
//      }
//    }
//
//    if (result) {
//      return result;
//    }
//
//    result = true;
//
//    // 1씩 감소
//    for (int i = 0; i < endIndex - startIndex; ++i) {
//      if (firstNumber - i != Integer.parseInt(numberArray[startIndex + i])) {
//        result = false;
//        break;
//      }
//    }
//
//    return result;
//  }
//
//  public static boolean isAlternatingNumber(int startIndex, int endIndex) {
//    boolean result = true;
//    int judge = startIndex % 2;
//    int firstNumber = Integer.parseInt(numberArray[startIndex]);
//    int secondNumber = Integer.parseInt(numberArray[startIndex + 1]);
//
//    for (int i = startIndex + 2; i < endIndex; ++i) {
//      if (i % 2 == judge) {
//        if (firstNumber != Integer.parseInt(numberArray[i])) {
//          result = false;
//          break;
//        }
//      } else {
//        if (secondNumber != Integer.parseInt(numberArray[i])) {
//          result = false;
//          break;
//        }
//      }
//    }
//
//    return result;
//  }
//
//  public static boolean isArithmeticalProgression(int startIndex, int endIndex) {
//    boolean result = true;
//    int firstNumber = Integer.parseInt(numberArray[startIndex]);
//    int secondNumber = Integer.parseInt(numberArray[startIndex + 1]);
//    int minus = firstNumber - secondNumber;
//
//    for (int i = startIndex + 2; i < endIndex; ++i) {
//      if (minus != Integer.parseInt(numberArray[i - 1]) - Integer.parseInt(numberArray[i])) {
//        result = false;
//        break;
//      }
//    }
//
//    return result;
//  }
//}
