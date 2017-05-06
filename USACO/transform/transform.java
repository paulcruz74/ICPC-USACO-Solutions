/*
ID: paulcru1
LANG: JAVA
TASK: transform
*/
import java.io.*;
import java.util.*;

class transform {
  public static void main (String [] args) throws IOException {
    BufferedReader f = new BufferedReader(new FileReader("transform.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
    int n = Integer.parseInt(f.readLine());
    char[][] board1 = readBoard(n, f);
    char[][] board2 = readBoard(n, f);
    if(equal(board2, rotate1(board1, n)))
      out.println("1");
    else if(equal(board2, rotate2(board1,n)))
      out.println("2");
    else if(equal(board2, rotate3(board1,n)))
      out.println("3");
    else if(equal(board2, reflect(board1,n)))
      out.println("4");
    else if(equal(board2, rotate1(reflect(board1,n),n)) || equal(board2, rotate2(reflect(board1,n),n)) || equal(board2, rotate3(reflect(board1,n),n)))
      out.println("5");
    else if(equal(board2,board1))
      out.println("6");
    else
      out.println("7");

    out.close();
    System.exit(0);
  }
  public static boolean equal(char[][] b1, char[][] b2){
    for(int i = 0;i < b1.length;i++){
      for(int j = 0;j < b1.length;j++){
        if(b1[i][j] != b2[i][j])
          return false;
      }
    }
    return true;
  }
  public static char[][] reflect(char[][] board, int n){
    char[][] ret = new char[n][n];
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        ret[i][n-1-j] = board[i][j];
      }
    }
    return ret;
  }
  public static char[][] rotate3(char[][] board, int n){
    char[][] ret = new char[n][n];
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        ret[n-1-j][i] = board[i][j];
      }
    }
    return ret;
  }
  public static char[][] rotate2(char[][] board, int n){
    char[][] ret = new char[n][n];
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        ret[n-1-i][n-1-j] = board[i][j];
      }
    }
    return ret;
  }
  public static char[][] rotate1(char[][] board, int n){
    char[][] ret = new char[n][n];
    for(int i = 0;i < n;i++){
      for(int j = 0;j < n;j++){
        ret[j][n-1-i] = board[i][j];
      }
    }
    return ret;
  }
  public static char[][] readBoard(int n, BufferedReader f) throws IOException{
    char [][] board = new char[n][n];
    for(int i = 0;i < n;i++){
      String line = f.readLine();
      for(int j = 0;j < n;j++){
        board[i][j] = line.charAt(j);
      }
    }
    return board;
  }
}