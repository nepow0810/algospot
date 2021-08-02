package com.algospot.fanmeeting;

import java.util.*;

public class FanMeeting {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int cases = scanner.nextInt();
    scanner.nextLine();
    while (cases-- > 0) {
      String singer = scanner.nextLine();
      String fan = scanner.nextLine();

      String[] singerArray = singer.split("");
      String[] fanArray = fan.split("");

      int result;

      if (singerArray.length > fanArray.length) {
        result = 0;
      } else {
        result = getAllHugCount(singerArray, fanArray);
      }

      System.out.println(result);
    }
    scanner.close();
  }

//  // 무식하게 풀기
//  public static int getAllHugCount(String[] singerArray, String[] fanArray) {
//    int result = 0;
//
//    for (int i = 0; i < fanArray.length - singerArray.length + 1 ; ++i) {
//      boolean isAllHug = true;
//      for (int j = 0; j < singerArray.length; ++j) {
//        if ("M".equals(singerArray[j]) && "M".equals(fanArray[i + j])) {
//          isAllHug = false;
//          break;
//        }
//      }
//
//      if (isAllHug) {
//        ++result;
//      }
//    }
//
//    return result;
//  }
//
//  // 분할을 이용해 풀어보기
//  public static int getAllHugCount(String[] singerArray, String[] fanArray, int fanStartIndex, int fanEndIndex) {
//    if (singerArray.length > (fanEndIndex - fanStartIndex + 1)) {
//      return 0;
//    }
//
//    int fanMiddleIndex = (fanStartIndex + fanEndIndex) / 2;
//
//    // 각자 배열일때 전체 포옹의 수 합하기
//    int result = getAllHugCount(singerArray, fanArray, fanStartIndex, fanMiddleIndex)
//        + getAllHugCount(singerArray, fanArray, fanMiddleIndex + 1, fanEndIndex);
//
//    // 양쪽 배열에 걸친 경우 합하기
//    int startIndex = fanMiddleIndex - singerArray.length + 2;
//
//    // 시작 인덱스가 -인경우 제외
//    while (startIndex < fanStartIndex) {
//      ++startIndex;
//    }
//
//    while (startIndex <= fanMiddleIndex && startIndex + singerArray.length - 1 <= fanEndIndex) {
//      boolean isAllHug = true;
//      for (int i = 0; i < singerArray.length; ++i) {
//        if ("M".equals(singerArray[i]) && "M".equals(fanArray[startIndex + i])) {
//          isAllHug = false;
//          break;
//        }
//      }
//
//      if (isAllHug) {
//        ++result;
//      }
//
//      ++startIndex;
//    }
//
//    return result;
//  }

  // 답보고 푼 코드
  // 알고스팟에선 시간초과로 나온다...
  public static int getAllHugCount(String[] singerArray, String[] fanArray) {
    ArrayList<Integer> singerIntList = new ArrayList<>();
    ArrayList<Integer> fanIntList = new ArrayList<>();

    for (int i = 0; i < singerArray.length; ++i) {
      if ("M".equals(singerArray[i])) {
        singerIntList.add(1);
      } else {
        singerIntList.add(0);
      }
    }

    for (int i = 0; i < fanArray.length; ++i) {
      fanIntList.add(0);
    }

    for (int i = 0; i < fanArray.length; ++i) {
      if ("M".equals(fanArray[i])) {
        fanIntList.set(fanArray.length - i - 1, 1);
      } else {
        fanIntList.set(fanArray.length - i - 1, 0);
      }
    }

    ArrayList<Integer> multiplyResult = karatsuba(singerIntList, fanIntList);

    int allHugCount = 0;
    for (int i = singerArray.length - 1; i < fanArray.length; ++i) {
      if (multiplyResult.get(i) == 0) {
        ++allHugCount;
      }
    }

    return allHugCount;
  }

  public static ArrayList<Integer> karatsuba(ArrayList<Integer> a, ArrayList<Integer> b) {
    int aSize = a.size();
    int bSize = b.size();

    // a가 b보다 짧을 경우 둘을 바꾼다.
    if (aSize < bSize) {
      return karatsuba(b, a);
    }

    // a, b가 빈 경우
    if (aSize == 0 || bSize == 0) {
      return new ArrayList<>();
    }

    // a가 비교적 짧은 경우 그냥 곱셈으로
    if (aSize <= 50) {
      return multiply(a,b);
    }

    int half = aSize / 2;

    ArrayList<Integer> a0 = new ArrayList<>(a.subList(0, half));
    ArrayList<Integer> a1 = new ArrayList<>(a.subList(half, a.size()));
    ArrayList<Integer> b0 = new ArrayList<>(b.subList(0, Math.min(b.size(), half)));
    ArrayList<Integer> b1 = new ArrayList<>(b.subList(Math.min(b.size(), half), b.size()));

    // z2 = a1 * b1
    ArrayList<Integer> z2 = karatsuba(a1, b1);
    // z0 = a0 * b0
    ArrayList<Integer> z0 = karatsuba(a0, b0);

    // a0 = a0 + a1, b0 = b0 + b1
    a0 = addTo(a0, a1, 0);
    b0 = addTo(b0, b1, 0);

    // z1 = (a0 * b0) - z0 -z2
    ArrayList<Integer> z1 = karatsuba(a0, b0);
    z1 = subFrom(z1, z0);
    z1 = subFrom(z1, z2);

    // result = z0 + z1 * 10^half + z2 * 10^(half * 2)
    ArrayList<Integer> result = new ArrayList<>();
    result = addTo(result, z0, 0);
    result = addTo(result, z1, half);
    result = addTo(result, z2, half + half);
    return result;
  }

  public static ArrayList<Integer> normalize(ArrayList<Integer> a) {
    a.add(0);

    for (int i = 0; i < a.size() - 1; ++i) {
      if (a.get(i) < 0) {
        int borrow = (Math.abs(a.get(i)) + 9) / 10;
        a.set(i + 1, a.get(i + 1) - borrow);
        a.set(i, a.get(i) + borrow * 10);
      } else {
        a.set(i + 1, a.get(i + 1) + a.get(i) / 10);
        a.set(i, a.get(i) % 10);
      }
    }

    if (a.get(a.size() - 1) == 0) {
      a.remove(a.size() - 1);
    }

    return a;
  }

  public static ArrayList<Integer> multiply(ArrayList<Integer> a, ArrayList<Integer> b) {
    ArrayList<Integer> c = new ArrayList<>();
    c = ensureSize(c, a.size() + b.size() + 1);

    for (int i = 0; i < a.size(); ++i) {
      for (int j = 0; j < b.size(); ++j) {
        c.set(i + j, c.get(i + j) + a.get(i) * b.get(j));
      }
    }
    c = normalize(c);
    return c;
  }

  public static ArrayList<Integer> addTo(ArrayList<Integer> a, ArrayList<Integer> b, int k) {
    a = ensureSize(a, Math.max(a.size(), b.size()+ k));
    for (int i = 0; i < b.size(); ++i) {
      a.set(k + i, a.get(k + i) + b.get(i));
    }
    a = normalize(a);
    return a;
  }

  public static ArrayList<Integer> subFrom(ArrayList<Integer> a, ArrayList<Integer> b) {
    a = ensureSize(a, Math.max(a.size(), b.size()+ 1));
    for (int i = 0; i < b.size(); ++i) {
      a.set(i, a.get(i) - b.get(i));
    }
    a = normalize(a);
    return a;
  }

  public static ArrayList<Integer> ensureSize(ArrayList<Integer> list, int size) {
    list.ensureCapacity(size);
    while (list.size() < size) {
      list.add(0);
    }
    return list;
  }
}
