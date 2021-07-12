package com.algospot.clocksync;

import java.util.*;

public class ClockSync {
  // 최초로 푼 답
//  // 시계에 연결된 버튼 리스트
//  private static final int[][] BUTTON_LIST = {
//      {0, 1, 2},
//      {3, 7, 9, 11},
//      {4, 10, 14, 15},
//      {0, 4, 5, 6, 7},
//      {6, 7, 8, 10, 12},
//      {0, 2, 14, 15},
//      {3, 14, 15},
//      {4, 5, 7, 14, 15},
//      {1, 2, 3, 4, 5},
//      {3, 4, 5, 9, 13}
//  };
//
//  // 버튼에 연결된 시계 리스트
//  private static final int[][] CLOCK_LIST = {
//      {0, 3, 5},
//      {0, 8},
//      {0, 5, 8},
//      {1, 6, 8, 9},
//      {2, 3, 7, 8, 9},
//      {3, 7, 8, 9},
//      {3, 4},
//      {1, 3, 4, 7},
//      {4},
//      {1, 9},
//      {2, 4},
//      {1},
//      {4},
//      {9},
//      {2, 5, 6, 7},
//      {2, 5, 6, 7}
//  };
//
//  // 연결된 버튼이 적은 순서의 시계 리스트
//  private static final int[] LESS_EFFECTED_CLOCK_LIST = {11, 8, 12, 13, 1, 9, 10, 6, 0, 2, 3, 5, 7, 14, 15, 4};
//
//  private static final int[] BUTTON_CLICK_COUNT_LIST = new int[16];
//
//  public static void main(String[] args) {
//    Scanner scanner = new Scanner(System.in);
//    int cases = scanner.nextInt();
//    while (cases-- > 0) {
//      int[] clockStatusList = new int[16];
//
//      for (int i = 0; i < 16; ++i) {
//        clockStatusList[i] = scanner.nextInt();
//      }
//
//      int result = getMinButtonCount(0, clockStatusList);
//
//      System.out.println(result);
//    }
//
//    scanner.close();
//  }
//
//  public static int getMinButtonCount(int clickCount, int[] clockStatusList) {
//
//    if (isAll12(clockStatusList)) {
//      return clickCount;
//    }
//
//    if (isMoreThan3Click()) {
//      return 987654321;
//    }
//
//    // 시계 중 12시가 아닌 시계 인덱스
//    int firstIndex = getFirstIndexToModify(clockStatusList);
//    // 큰 수
//    int result = 987654321;
//
//    for (int buttonIndex : CLOCK_LIST[firstIndex]) {
//      clickButton(clockStatusList, buttonIndex);
//      int temp = getMinButtonCount(++clickCount, clockStatusList);
//      result = Math.min(result, temp);
//      --clickCount;
//      clickBackButton(clockStatusList, buttonIndex);
//    }
//
//    if (result == 987654321) {
//      result = -1;
//    }
//
//    return result;
//  }
//
//  public static boolean isAll12(int[] clockStatusList) {
//    boolean result = true;
//    for (int clockStatus : clockStatusList) {
//      if (clockStatus != 12) {
//        result = false;
//        break;
//      }
//    }
//    return result;
//  }
//
//  public static boolean isMoreThan3Click() {
//    for (int clickCount : BUTTON_CLICK_COUNT_LIST) {
//      if (clickCount > 3) {
//        return true;
//      }
//    }
//    return false;
//  }
//
//  public static int getFirstIndexToModify(int[] clockStatusList) {
//    int firstIndex = -1;
//    for (int clockIndex : LESS_EFFECTED_CLOCK_LIST) {
//      if (clockStatusList[clockIndex] != 12) {
//        firstIndex = clockIndex;
//        break;
//      }
//    }
//    return firstIndex;
//  }
//
//  public static void clickButton(int[] clockStatusList, int buttonIndex) {
//    BUTTON_CLICK_COUNT_LIST[buttonIndex] += 1;
//    for (int index : BUTTON_LIST[buttonIndex]) {
//      runClock(clockStatusList, index);
//    }
//  }
//
//  public static void clickBackButton(int[] clockStatusList, int buttonIndex) {
//    BUTTON_CLICK_COUNT_LIST[buttonIndex] -= 1;
//    for (int index : BUTTON_LIST[buttonIndex]) {
//      backClock(clockStatusList, index);
//    }
//  }
//
//  public static void runClock(int[] clockStatusList, int index) {
//    clockStatusList[index] += 3;
//    if (clockStatusList[index] == 15) {
//      clockStatusList[index] = 3;
//    }
//  }
//
//  public static void backClock(int[] clockStatusList, int index) {
//    clockStatusList[index] -= 3;
//    if (clockStatusList[index] == 0) {
//      clockStatusList[index] = 12;
//    }
//  }

  // 답을 보고 정리한 답
  // 시계 버튼 연결 배열을 하나로 하여 표현이 가능하나 (boolean[10][16]) 보기 힘드므로 문제에서 제시하는대로 수정
  // 4번째 클릭까지 확인했는데 0~3번만 체크해도 됨
  // 4번 클릭시 처음 상태로 되돌아오기 때문에 백을 할필요가 없음
  // 각각 0~3번씩 클릭하여 12시가 되는지 확인하고 그중 최소 클릭을 답으로 반환
  // (for문과 재귀를 통해 전체 탐색)
  private static final int[][] BUTTON_LIST = {
      {0, 1, 2},
      {3, 7, 9, 11},
      {4, 10, 14, 15},
      {0, 4, 5, 6, 7},
      {6, 7, 8, 10, 12},
      {0, 2, 14, 15},
      {3, 14, 15},
      {4, 5, 7, 14, 15},
      {1, 2, 3, 4, 5},
      {3, 4, 5, 9, 13}
  };

  private static final int BIG_INT = 987654321;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      int[] clockStatusList = new int[16];

      for (int i = 0; i < 16; ++i) {
        clockStatusList[i] = scanner.nextInt();
      }

      int result = getMinButtonCount(0, clockStatusList);

      if (result >= BIG_INT) {
        result = -1;
      }

      System.out.println(result);
    }

    scanner.close();
  }

  public static int getMinButtonCount(int switchIndex, int[] clockStatusList) {
    if (switchIndex == 10) {
      return isAll12(clockStatusList) ? 0 : BIG_INT;
    }

    int result = BIG_INT;
    for (int i = 0; i < 4; ++i) {
      result = Math.min(result, i + getMinButtonCount(switchIndex + 1, clockStatusList));
      clickButton(clockStatusList, switchIndex);
    }
    return result;
  }

  public static boolean isAll12(int[] clockStatusList) {
    boolean result = true;
    for (int clockStatus : clockStatusList) {
      if (clockStatus != 12) {
        result = false;
        break;
      }
    }
    return result;
  }

  public static void clickButton(int[] clockStatusList, int buttonIndex) {
    for (int clockIndex: BUTTON_LIST[buttonIndex]) {
      clockStatusList[clockIndex] += 3;
      if (clockStatusList[clockIndex] == 15) {
        clockStatusList[clockIndex] = 3;
      }
    }
  }
}
