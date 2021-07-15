package com.algospot.quadtree;

import java.util.*;

public class QuadTree {
  private static int index;

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    scanner.nextLine();
    while (cases-- > 0) {
      String quadTreeString = scanner.nextLine();
      String[] quadTree = quadTreeString.split("");
      index = -1;

      String result = getReverseTree(quadTree);

      System.out.println(result);
    }
    scanner.close();
  }

  public static String getReverseTree(String[] quadTree) {
    ++index;
    if ("b".equals(quadTree[index]) || "w".equals(quadTree[index])) {
      return quadTree[index];
    }

    String upperLeft = getReverseTree(quadTree);
    String upperRight = getReverseTree(quadTree);
    String lowerLeft = getReverseTree(quadTree);
    String lowerRight = getReverseTree(quadTree);
    return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
  }
}
