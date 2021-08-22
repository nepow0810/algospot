package com.algospot.wildcard;

import java.util.*;

public class WildCard {
  static int[][] cache = new int[101][101];
  static String[] patternArray;
  static String fileName;
  static String[] fileNameArray;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    scanner.nextLine();
    while (cases-- > 0) {
      patternArray = scanner.nextLine().split("");
      int fileNameNumber = scanner.nextInt();
      scanner.nextLine();

      List<String> resultList = new ArrayList<>();

      for (int i = 0; i < fileNameNumber; ++i) {
        initCache();
        fileName = scanner.nextLine();
        fileNameArray = fileName.split("");

        if (isMatchPattern(0, 0)) {
          resultList.add(fileName);
        }
      }

      Collections.sort(resultList);

      for (String result : resultList) {
        System.out.println(result);
      }
    }
    scanner.close();
  }

  public static void initCache() {
    for (int i = 0; i < 101; ++i) {
      for (int j = 0; j < 101; ++j) {
        cache[i][j] = -1;
      }
    }
  }

  public static boolean isMatchPattern(int patternIndex, int fileNameIndex) {
    if (cache[patternIndex][fileNameIndex] != -1) {
      return cache[patternIndex][fileNameIndex] == 1;
    }

//    while (patternIndex < patternArray.length && fileNameIndex < fileNameArray.length
//        && ("?".equals(patternArray[patternIndex]) || patternArray[patternIndex].equals(fileNameArray[fileNameIndex]))) {
//      ++patternIndex;
//      ++fileNameIndex;
//    }

    if (patternIndex < patternArray.length && fileNameIndex < fileNameArray.length
        && ("?".equals(patternArray[patternIndex]) || patternArray[patternIndex].equals(fileNameArray[fileNameIndex]))) {
      cache[patternIndex][fileNameIndex] = isMatchPattern(patternIndex + 1, fileNameIndex + 1) ? 1 :0;
      return cache[patternIndex][fileNameIndex] == 1;
    }

    if (patternIndex == patternArray.length) {
      cache[patternIndex][fileNameIndex] = fileNameIndex == fileNameArray.length ? 1: 0;
      return cache[patternIndex][fileNameIndex] == 1;
    }

//    if ("*".equals(patternArray[patternIndex])) {
//      for (int skip = 0; skip + fileNameIndex <= fileNameArray.length; ++skip) {
//        if (isMatchPattern(patternIndex + 1, fileNameIndex + skip)) {
//          cache[patternIndex][fileNameIndex] = 1;
//          return cache[patternIndex][fileNameIndex] == 1;
//        }
//      }
//    }

    if ("*".equals(patternArray[patternIndex])) {
      if (isMatchPattern(patternIndex + 1, fileNameIndex)
          || (fileNameIndex < fileNameArray.length && isMatchPattern(patternIndex, fileNameIndex + 1))) {
        cache[patternIndex][fileNameIndex] = 1;
        return cache[patternIndex][fileNameIndex] == 1;
      }
    }

    cache[patternIndex][fileNameIndex] = 0;
    return cache[patternIndex][fileNameIndex] == 1;
  }
}


