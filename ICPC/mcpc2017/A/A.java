import java.io.*;
import java.util.*;
import java.awt.Point;

public class A {
  public static void main(String[] args) {
    char[][] board = new char[5][5];
    int numKnights = 0;
    List<Point> locations = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    for (int i = 0; i < 5; i++) {
      String line = in.nextLine();
      for (int j = 0; j < 5; j++) {
        board[i][j] = line.charAt(j);
        if (board[i][j] == 'k') {
          numKnights++;
          locations.add(new Point(i, j));
        }
      }
    }
    if (numKnights != 9) {
      System.out.println("invalid");
    }
    else {
      boolean works = true;
      int[] dr = {2, 1, -1, -2, -2, -1, 1, 2};
      int[] dc = {1, 2, 2, 1, -1, -2, -2, -1};
      for (int i = 0; i < locations.size(); i++) {
        for (int j = i+1; j < locations.size(); j++) {
          if (i == j) continue;
          for (int k = 0; k < dr.length; k++) {
            Point p1 = locations.get(i);
            Point p2 = locations.get(j);
            if (p1.getX()+dr[k]==p2.getX() && p1.getY()+dc[k]==p2.getY()) {
              works = false;
            }
          }
        }
      }
      if (works) {
        System.out.println("valid");
      }
      else {
        System.out.println("invalid");
      }
    }
  }
}