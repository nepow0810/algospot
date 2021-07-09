package com.algospot.picnic;

import java.util.Scanner;

public class Picnic {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    while(cases-- > 0) {
      int studentNumber = scanner.nextInt();
      int friendPairNumber = scanner.nextInt();
      boolean[][] friendPair = new boolean[10][10];

      for (int i = 0; i < friendPairNumber; ++i) {
        int firstStudent = scanner.nextInt();
        int secondStudent = scanner.nextInt();

        friendPair[firstStudent][secondStudent] = true;
        friendPair[secondStudent][firstStudent] = true;
      }

      int result = getPossibleFriendPairCount(studentNumber, friendPair, new boolean[10]);

      System.out.println(result);
    }
  }

  public static int getPossibleFriendPairCount(int studentNumber, boolean[][] friendPair, boolean[] friendPairStatusList) {
    int firstFreeFriend = -1;

    for (int i = 0; i < studentNumber; ++i) {
      if (!friendPairStatusList[i]) {
        firstFreeFriend = i;
        break;
      }
    }

    if (firstFreeFriend == -1) {
      return 1;
    }

    int result = 0;

    for (int pairWith = firstFreeFriend + 1; pairWith < studentNumber; ++pairWith) {
      if (!friendPairStatusList[pairWith] && friendPair[firstFreeFriend][pairWith]) {
        friendPairStatusList[firstFreeFriend] = true;
        friendPairStatusList[pairWith] = true;
        result += getPossibleFriendPairCount(studentNumber, friendPair, friendPairStatusList);
        friendPairStatusList[firstFreeFriend] = false;
        friendPairStatusList[pairWith] = false;
      }
    }

    return result;
  }
}
