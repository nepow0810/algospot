package com.algospot.boardcover;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while (cases-- > 0) {
      int height = scanner.nextInt();
      int width = scanner.nextInt();
      boolean[][] board = new boolean[20][20]; // true가 채울 수 있는
      int blankCount = 0; // 빈칸 수

      // 위의 높이 너비에서 입력된 엔터 제거
      scanner.nextLine();
      for (int i = 0; i < height; ++i) {
        String[] stringArray = scanner.nextLine().split("");

        for (int j = 0; j < width; ++j) {
          if (".".equals(stringArray[j])) {
            board[i][j] = true;
            ++blankCount;
          }
        }
      }

      int result = 0;

      if (blankCount % 3 == 0) {
        result = getBoardCoverCount(height, width, board);
      }

      System.out.println(result);
    }
  }

  // 최초로 푼 답
//  public static int getBoardCoverCount(int height, int width,boolean[][] board) {
//    int firstBlankHeightIndex = -1;
//    int firstBlankWidthIndex = -1;
//
//    boolean isBreak = false;
//    for (int h = 0; h < height; ++h) {
//      for (int w = 0; w < width; ++w) {
//        if (board[h][w]) {
//          firstBlankHeightIndex = h;
//          firstBlankWidthIndex = w;
//          isBreak = true;
//          break;
//        }
//      }
//      if (isBreak) {
//        break;
//      }
//    }
//
//    // 전체가 채워졌다면 횟수 1 추가
//    if (firstBlankHeightIndex == -1) {
//      return 1;
//    }
//
//    int result = 0;
//
//    // 첫 빈칸을 가지고 삼각형 채우기
//
//    // 오른쪽 가능, 오른쪽 위 가능
//    if (firstBlankWidthIndex < width - 1 && firstBlankHeightIndex > 0) {
//      if (board[firstBlankHeightIndex][firstBlankWidthIndex + 1] && board[firstBlankHeightIndex - 1][firstBlankWidthIndex + 1]) {
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex][firstBlankWidthIndex + 1] = false;
//        board[firstBlankHeightIndex - 1][firstBlankWidthIndex + 1] = false;
//        result += getBoardCoverCount(height, width, board);
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex][firstBlankWidthIndex + 1] = true;
//        board[firstBlankHeightIndex - 1][firstBlankWidthIndex + 1] = true;
//      }
//    }
//
//    // 오른쪽 가능, 오른쪽 아래 가능
//    if (firstBlankWidthIndex < width - 1 && firstBlankHeightIndex < height - 1) {
//      if (board[firstBlankHeightIndex][firstBlankWidthIndex + 1] && board[firstBlankHeightIndex + 1][firstBlankWidthIndex + 1]) {
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex][firstBlankWidthIndex + 1] = false;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex + 1] = false;
//        result += getBoardCoverCount(height, width, board);
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex][firstBlankWidthIndex + 1] = true;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex + 1] = true;
//      }
//    }
//
//    // 아래 가능, 아래 왼쪽 가능
//    if (firstBlankHeightIndex < height - 1 && firstBlankWidthIndex > 0) {
//      if (board[firstBlankHeightIndex + 1][firstBlankWidthIndex] && board[firstBlankHeightIndex + 1][firstBlankWidthIndex - 1]) {
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex - 1] = false;
//        result += getBoardCoverCount(height, width, board);
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex - 1] = true;
//      }
//    }
//
//    // 아래 가능, 아래 오른쪽 가능
//    if (firstBlankWidthIndex < width - 1 && firstBlankHeightIndex < height - 1) {
//      if (board[firstBlankHeightIndex + 1][firstBlankWidthIndex] && board[firstBlankHeightIndex + 1][firstBlankWidthIndex + 1]) {
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex + 1] = false;
//        result += getBoardCoverCount(height, width, board);
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex + 1] = true;
//      }
//    }
//
//    // 오른쪽 가능, 아래 가능
//    if (firstBlankWidthIndex < width - 1 && firstBlankHeightIndex < height - 1) {
//      if (board[firstBlankHeightIndex][firstBlankWidthIndex + 1] && board[firstBlankHeightIndex + 1][firstBlankWidthIndex]) {
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex] = false;
//        board[firstBlankHeightIndex][firstBlankWidthIndex + 1] = false;
//        result += getBoardCoverCount(height, width, board);
//        board[firstBlankHeightIndex][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex + 1][firstBlankWidthIndex] = true;
//        board[firstBlankHeightIndex][firstBlankWidthIndex + 1] = true;
//      }
//    }
//
//    return result;
//  }

  // 답을 보고 정리한 답
  // isBreak 제거
  // 오른쪽 가능, 오른쪽 위 가능 제거
  // COVER_TYPE 추가 및 board 세팅하는 함수를 따로 빼기
  public static int[][][] COVER_TYPE = {
      {{0, 0}, {0, 1}, {1, 1}},
      {{0, 0}, {1, 0}, {1, -1}},
      {{0, 0}, {1, 0}, {1, 1}},
      {{0, 0}, {0, 1}, {1, 0}}
  };

  public static int getBoardCoverCount(int height, int width, boolean[][] board) {
    int firstBlankHeightIndex = -1;
    int firstBlankWidthIndex = -1;

    for (int h = 0; h < height; ++h) {
      for (int w = 0; w < width; ++w) {
        if (board[h][w]) {
          firstBlankHeightIndex = h;
          firstBlankWidthIndex = w;
          break;
        }
      }
      if (firstBlankHeightIndex != -1) {
        break;
      }
    }

    // 전체가 채워졌다면 횟수 1 추가
    if (firstBlankHeightIndex == -1) {
      return 1;
    }

    int result = 0;

    for (int type = 0; type < 4; ++type) {
      // 블록을 채운다.
      if (setBoard(board, firstBlankHeightIndex, firstBlankWidthIndex, height, width, type, false)) {
        result += getBoardCoverCount(height, width, board);
        // 블록을 지운다.
        setBoard(board, firstBlankHeightIndex, firstBlankWidthIndex, height, width, type, true);
      }
    }

    return result;
  }

  public static boolean setBoard(boolean[][] board, int heightIndex, int widthIndex, int height, int width, int type , boolean settingValue) {
    boolean settingResult = true;

    // 채우거나 빼도 되는지 확인
    for (int i = 0; i < 3; ++i) {
      int newHeightIndex = heightIndex + COVER_TYPE[type][i][0];
      int newWidthIndex = widthIndex + COVER_TYPE[type][i][1];

      if (newHeightIndex < 0 || newHeightIndex >= height || newWidthIndex < 0 || newWidthIndex >= width
          || !board[newHeightIndex][newWidthIndex] && !settingValue) {
        settingResult = false;
        break;
      }
    }

    // 확인이 되었다면 값 변경
    if (settingResult) {
      for (int i = 0; i < 3; ++i) {
        int newHeightIndex = heightIndex + COVER_TYPE[type][i][0];
        int newWidthIndex = widthIndex + COVER_TYPE[type][i][1];
        board[newHeightIndex][newWidthIndex] = settingValue;
      }
    }

    return settingResult;
  }
}
