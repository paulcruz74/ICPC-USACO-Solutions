import java.io.*;
import java.util.*;

public class C {
  public static HashMap<Integer, Integer> rcounts;
  public static HashMap<Integer, Integer> gcounts;
  public static HashMap<Integer, Integer> bcounts;
  public static TreeSet<Integer> rAngles;
  public static TreeSet<Integer> gAngles;
  public static TreeSet<Integer> bAngles;
  static class Card implements Comparable<Card>{  
    int r, g, b, id;
    public Card(int red, int blue, int green, int i) {
      this.r = red;
      this.b = blue;
      this.g = green;
      this.id = i;
    }
    public boolean equals(Object o) {
      if (o instanceof Card) {
        Card c = (Card)o;
        return (this.r == c.r) && (this.g == c.g) && (this.b == c.b) && (this.id == c.id);
      }
      else {
        return false;
      }
    }
    public int compareTo(Card c) {
      int a = calculateUniquness(this);
      int b = calculateUniquness(c);
      return a-b;

    }
    private int calculateUniquness(Card c) {
      int a = 0;
      // red uniqueness
      if (rcounts.get(c.r) > 1) {
        a += 0;
      }
      else {
        int l, h;
        if (rAngles.lower(c.r) == null) {
          l = c.r - rAngles.lower(360) + 360;
        }
        else {
          l = c.r - rAngles.lower(c.r);
        }
        if (rAngles.higher(c.r) == null) {
          h = rAngles.higher(0) + 360 - c.r;
        }
        else {
          h = rAngles.higher(c.r) - c.r;
        }
        a += l + h;
      }
      // blue uniqueness
      if (bcounts.get(c.b) > 1) {
        a += 0;
      }
      else {
        int l, h;
        if (bAngles.lower(c.b) == null) {
          l = c.b - bAngles.lower(360) + 360;
        }
        else {
          l = c.b - bAngles.lower(c.b);
        }
        if (bAngles.higher(c.b) == null) {
          h = bAngles.higher(0) + 360 - c.b;
        }
        else {
          h = bAngles.higher(c.b) - c.b;
        }
        a += l + h;
      }
      // green uniqueness
      if (gcounts.get(c.g) > 1) {
        a += 0;
      }
      else {
        int l, h;
        if (gAngles.lower(c.g) == null) {
          l = c.g - gAngles.lower(360) + 360;
        }
        else {
          l = c.g - gAngles.lower(c.g);
        }
        if (gAngles.higher(c.g) == null) {
          h = gAngles.higher(0) + 360 - c.g;
        }
        else {
          h = gAngles.higher(c.g) - c.g;
        }
        a += l + h;
      }
      return a; 
    }
  }
  public static void incr(HashMap<Integer, Integer> map, int val) {
    if (map.containsKey(val)) {
      map.put(val, map.get(val) + 1); 
    }
    else {
      map.put(val, 1);
    }
  }
  public static void main(String [] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    TreeSet<Card> sorted = new TreeSet<>();
    for (int i = 0; i < n; i++) {
      sorted.add(new Card(in.nextInt(),in.nextInt(),in.nextInt(),in.nextInt()));
    }
  }
}