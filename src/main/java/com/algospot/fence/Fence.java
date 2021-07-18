package com.algospot.fence;

import java.util.*;

public class Fence {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      int fenceCount = scanner.nextInt();
      int[] fenceArray = new int[fenceCount];
      for (int i = 0; i < fenceArray.length; ++i) {
        fenceArray[i] = scanner.nextInt();
      }

      int result = getMaxArea(fenceArray, 0, fenceCount - 1);

      System.out.println(result);
    }
    scanner.close();
  }

//  // 무식하게 풀기
//  public static int getMaxArea(int[] fenceArray) {
//    int result = 0;
//    for (int i = 0; i < fenceArray.length; ++i) {
//      int minHeight = fenceArray[i];
//      for (int j = i; j < fenceArray.length; ++j) {
//        minHeight = Math.min(minHeight, fenceArray[j]);
//        result = Math.max(result, minHeight * (j - i + 1));
//      }
//    }
//    return result;
//  }

  // 받은 배열을 반으로 나눠 분할하여 푼다.
  // 제일 큰 넓이가 양쪽 부분에 겹친 경우
  // 경계의 두 판자를 시작으로 가로로 한칸씩 넓혀가며 최대 넓이를 구한다.
  public static int getMaxArea(int[] fenceArray, int startIndex, int endIndex) {
    if (startIndex == endIndex) {
      return fenceArray[startIndex];
    }

    int middleIndex = (startIndex + endIndex) / 2;

    // 왼쪽 배열, 오른쪽 배열 중 넓은 것으로 선택
    int result = Math.max(getMaxArea(fenceArray, startIndex, middleIndex)
        , getMaxArea(fenceArray, middleIndex + 1, endIndex));

    // 양쪽에 걸친 사각형 중 가장 넓은 사각형 찾기
    int lowerIndex = middleIndex;
    int higherIndex = middleIndex + 1;
    int height = Math.min(fenceArray[lowerIndex], fenceArray[higherIndex]);

    result = Math.max(result, height * 2);

    while (startIndex < lowerIndex || higherIndex < endIndex) {
      if (higherIndex < endIndex && (lowerIndex == startIndex || fenceArray[lowerIndex - 1] < fenceArray[higherIndex + 1])) {
        ++higherIndex;
        height = Math.min(height, fenceArray[higherIndex]);
      } else {
        --lowerIndex;
        height = Math.min(height, fenceArray[lowerIndex]);
      }
      result = Math.max(result, height * (higherIndex - lowerIndex + 1));
    }

    return result;
  }
}
