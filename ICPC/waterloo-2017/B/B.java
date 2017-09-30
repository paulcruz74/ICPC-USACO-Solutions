import java.io.*;
import java.util.*;

public class B {
  public static int a, b;
  public static int[] combos;
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    combos = new int[9];
    a = in.nextInt();
    b = in.nextInt();
    int[] rank = new int[5];
    int[] suit = new int[5];
    Arrays.fill(rank, -1);
    Arrays.fill(suit, -1);
    rank[0] = in.nextInt();
    suit[0] = in.nextInt();
    rank[1] = in.nextInt();
    suit[1] = in.nextInt();
    Set<Integer> used = new HashSet<>();
    used.add(hash(rank[0], suit[0]));
    used.add(hash(rank[1], suit[1]));
    tryPerm(rank, suit, 2, used);
    String ans = "";
    for (int i = 8; i >= 0; i--) {
      ans += combos[i]/6 + " ";
    }
    System.out.println(ans.trim());
  }
  public static void tryPerm(int[] rank, int[] suit, int next, Set<Integer> used) {
    if (next == 5) {
      if (straight(rank) && flush(suit)) {
        combos[8]++;
      }
      else if (four(rank)) {
        combos[7]++;
      }
      else if(fullHouse(rank)) {
        combos[6]++;
        return;
      }
      else if(flush(suit)) {
        combos[5]++;
      }
      else if(straight(rank)) {
        combos[4]++;
      }
      else if (three(rank)) {
        combos[3]++;
      }
      else if (twoPair(rank)) {
        combos[2]++;
      }
      else if (pair(rank)) {
        combos[1]++;
      }
      else {
        combos[0]++;
      }
      return;
    }
    else {
      for (int i = 0; i < a; i++) {
        for (int j = 0; j < b; j++) {
          if (!used.contains(hash(i, j))) {
            used.add(hash(i, j));
            rank[next] = i;
            suit[next] = j;
            tryPerm(rank, suit, next+1, used);
            used.remove(hash(i, j));
          }
        }
      }
    }
  }
  public static int hash(int rank, int suit) {
    return rank * b + suit;
  }
  public static boolean straight(int[] rank) {
    HashSet<Integer> uRank = new HashSet<>();
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int i : rank) {
      uRank.add(i);
      max = Math.max(max, i);
      min = Math.min(min, i);
    }
    boolean ret = true;
    if (max - min != 4) {
      return false;
    }
    for (int i = min; i <= max; i++) {
      if (!uRank.contains(i)) {
        return false;
      }
    }
    return true;
  }
  public static boolean flush(int[] suit) {
    HashSet<Integer> uSuit = new HashSet<>();
    for (int i : suit) {
      uSuit.add(i);
    }
    return uSuit.size() == 1;
  }
  public static boolean four(int[] rank) {
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int i : rank) {
      counts.put(i, counts.getOrDefault(i, 0) + 1);
    }
    for (int i : counts.keySet()) {
      if (counts.get(i) == 4) {
        return true;
      }
    }
    return false;
  }
  public static boolean fullHouse(int[] rank) {
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int i : rank) {
      counts.put(i, counts.getOrDefault(i, 0) + 1);
    }
    for (int i : counts.keySet()) {
      if (counts.get(i) != 3 && counts.get(i) != 2) {
        return false;
      }
    }
    return true;
  }
  public static boolean three(int[] rank) {
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int i : rank) {
      counts.put(i, counts.getOrDefault(i, 0) + 1);
    }
    for (int i : counts.keySet()) {
      if (counts.get(i) == 3) {
        return true;
      }
    }
    return false;
  }

  public static boolean twoPair(int[] rank) {
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int i : rank) {
      counts.put(i, counts.getOrDefault(i, 0) + 1);
    }
    int num = 0;
    for (int i : counts.keySet()) {
      if (counts.get(i) == 2) 
        num++;
    }
    return num == 2;
  }
  public static boolean pair(int[] rank) {
    HashMap<Integer, Integer> counts = new HashMap<>();
    for (int i : rank) {
      counts.put(i, counts.getOrDefault(i, 0) + 1);
    }
    for (int i : counts.keySet()) {
      if (counts.get(i) == 2) {
        return true;
      }
    }
    return false;
  }
}

